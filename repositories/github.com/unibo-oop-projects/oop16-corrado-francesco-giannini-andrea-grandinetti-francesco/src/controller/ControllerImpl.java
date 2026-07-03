package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.file.F1Files;
import controller.file.F1FilesImpl;
import controller.player.AI;
import controller.player.Player;
import controller.player.User;
import controller.session.QualifyingSession;
import controller.session.RaceSession;
import model.RaceDirection;
import model.RaceDirectionImpl;
import utility.Driver;
import utility.Position;
import utility.TyreType;
import view.View;
import view.game.InfoDriver;
import view.game.InfoDriverBuilder;

/**
 * Controller.
 */
public class ControllerImpl implements Controller {

    private static final String MODEL_ERROR = "The model hasn't been initialized yet!";
    private static final String TYRE_ERROR  = "The tyre used isn't allowed in this circuit!";

    private RaceDirection model;
    private final View view;
    private TyreController tyreControl;

    private List<Player> playerList;
    private boolean onlyRace;
    private int raceLaps;

    /**
     * Constructor.
     * @param view the view that the controller has to use
     */
    public ControllerImpl(final View view) {
        this.playerList = new ArrayList<>();
        this.view = view;
        this.startView();
    }

    @Override
    public void setOnlyRace() {
        this.onlyRace = true;
    }

    @Override
    public void addPlayer(final String name, final Driver driver) {
        // If someone wants to use a driver already chosen
        if (this.playerList.stream()
                           .map(x -> x.getDriver())
                           .anyMatch(y -> y.equals(driver))) {
            throw new IllegalArgumentException();
        }
        final Player newUser = new User(driver, name);
        this.playerList.add(newUser);
    }

    @Override
    public void setCircuit(final CircuitPlayable cir) {
        final F1Files creatorFile = new F1FilesImpl();
        try {
            this.model = new RaceDirectionImpl(creatorFile.getFileForModel(cir));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.view.setViewFile(creatorFile.getFileForView(cir));
        this.raceLaps = cir.getLaps();
        this.tyreControl = new TyreControllerImpl(cir);
    }

    @Override
    public void startWeekend() {
        if (this.model == null || this.tyreControl == null) {
            throw new IllegalStateException(MODEL_ERROR); 
        }
        //Adding to the player set all the drivers not chosen by the users
        this.initializeAIPlayers();
        this.checkUserPlayers();
        if (!this.onlyRace) {
            this.qualifying();
        }
        this.race();
    }

    @Override
    public int lapsCompleted() {
        if (this.model == null) {
           throw new IllegalStateException(MODEL_ERROR); 
        }
        int lap = this.raceLaps - this.model.getLapsRemainingByDriver(this.playerList.get(0).getDriver());
        lap = (lap < 0) ? 0 : lap;
        return lap;
    }

    @Override
    public void setPlayerInitialTyre(final Driver driver, final TyreType tyre) {
        if (this.model == null || this.tyreControl == null) {
            throw new IllegalStateException(MODEL_ERROR); 
        }
        if (this.tyreControl.isTheWrongTyre(tyre)) {
            throw new IllegalStateException(TYRE_ERROR);
        }
        model.setInitialTyre(driver, tyre);
        this.playerList.stream().filter(x -> x.getDriver() == driver)
                                .forEach(elem -> elem.addStint(tyre));
    }

    @Override
    public void restart() {
        this.playerList.clear();
        this.onlyRace = false;
        this.raceLaps = 0;
        this.startView();
    }

    @Override
    public InfoDriver getInfo(final Driver drv) {
        Player ply = null;
        for (final Player elem : this.playerList) {
            if (elem.getDriver() == drv) {
                ply = elem;
            }
        }
        final boolean user = ply instanceof User;
        return new InfoDriverBuilder()
               .namePlayer(user ? Optional.of(((User) ply).getName()) : Optional.empty())
               .driver(drv)
               .deg(this.model.getDeg(drv))
               .tyre(this.model.getTyreType(drv))
               .position(this.playerList.indexOf(ply) + 1)
               .shellPoints(this.model.getCarFrameByDriver(drv))
               .isUser(user)
               .build();
    }

    /**
     * Method to know who is playing.
     * @return a list with all the player
     */
    public List<Player> getPlayerList() {
        return Collections.unmodifiableList(this.playerList);
    }

    private void initializeAIPlayers() {
        final Set<Driver> presentDriver = this.playerList.stream()
                                                         .map(x -> x.getDriver())
                                                         .collect(Collectors.toSet());
        final Set<Driver> set = Stream.of(Driver.values()).collect(Collectors.toSet());
        set.forEach(x -> {
            if (!presentDriver.contains(x)) {
                final Player elem = new AI(x, this.tyreControl.getTyresPermitted());
                this.model.setInitialTyre(elem.getDriver(), ((AI) elem).getInitialTyre());
                this.playerList.add(elem);
            }
        });
    }

    private void checkUserPlayers() {
        this.playerList.stream()
                       .filter(x -> x instanceof User && x.getPlayerStints().isEmpty())
                       .forEach(elem -> {
                           final TyreType tyre = this.tyreControl.getTyresPermitted().get(0);
                           elem.addStint(tyre);
                           this.model.setInitialTyre(elem.getDriver(), tyre);
                       });
    }

    // Method for the qualifying session
    private void qualifying() {
        this.playerList = new QualifyingSession(this.model, this.view, this.playerList)
                              .startSession();
    }

    // Method for the race session
    private void race() {
        this.racePreparation();
        this.playerList = new RaceSession(this.model, this.view, this.playerList, this.tyreControl)
                              .startSession();
        final List<Driver> finalRank = this.finalControls(this.model.getPlacement());
        this.view.rankRace(finalRank, true);
    }

    private void racePreparation() {
        if (this.onlyRace) {
            Collections.shuffle(this.playerList); 
        }
        final List<Driver> tmp = this.playerList.stream().map(x -> x.getDriver()).collect(Collectors.toList());
        final Map<Driver, Position> map = this.model.setDriverForRace(tmp, this.raceLaps);
        map.keySet().forEach(x -> view.update(x, map.get(x), false));
        this.view.rankRace(tmp, false);
        this.view.startRace();
    }

    private List<Driver> finalControls(final List<Driver> rank) {
        final List<Player> disqPly = new ArrayList<>();
        final Map<Driver, Optional<String>> map = new HashMap<>();
        this.playerList.stream()
                       .filter(elem -> !elem.isRetired())
                       .filter(ply -> this.tyreControl.hasToBeDisqualified(ply.getPlayerStints()))
                       .forEach(x -> disqPly.add(x));
        disqPly.forEach(ply -> {
            rank.remove(ply.getDriver());
            rank.add(ply.getDriver());
            Optional<String> name = Optional.empty();
            name = (ply instanceof User) ? Optional.of(((User) ply).getName()) : name;
            map.put(ply.getDriver(), name);
        });
        if (!map.isEmpty()) {
            this.view.disqualify(map);
        }
        return rank;
    }

    private void startView() {
        this.view.setController(this);
        view.start();
    }

}