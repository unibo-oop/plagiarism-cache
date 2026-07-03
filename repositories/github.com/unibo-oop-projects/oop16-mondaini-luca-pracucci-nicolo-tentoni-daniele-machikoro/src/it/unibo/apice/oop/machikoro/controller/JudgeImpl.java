package it.unibo.apice.oop.machikoro.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import it.unibo.apice.oop.machikoro.model.AimCard;
import it.unibo.apice.oop.machikoro.model.AlreadyBoughtCardException;
import it.unibo.apice.oop.machikoro.model.BoardCard;
import it.unibo.apice.oop.machikoro.model.Card;
import it.unibo.apice.oop.machikoro.model.CardType;
import it.unibo.apice.oop.machikoro.model.Effect;
import it.unibo.apice.oop.machikoro.model.EffectColor;
import it.unibo.apice.oop.machikoro.model.EffectColorNotMatch;
import it.unibo.apice.oop.machikoro.model.EffectImpl;
import it.unibo.apice.oop.machikoro.model.IAImpl;
import it.unibo.apice.oop.machikoro.model.NotEnoughMoneyException;
import it.unibo.apice.oop.machikoro.model.Player;
import it.unibo.apice.oop.machikoro.model.PlayerImpl;

/**
 * Classe che istanzia un giudice di gioco per Machi-Koro.
 */
public class JudgeImpl implements Judge {
    private int turn = 1;
    private int nDice = 1;
    private int dice1;
    private int dice2;
    private int skin;
    private static final int ID_EFFECT_COLOR = 5;
    private static final int ID_REVENUE = 6;
    private static final int ID_CARD_TYPE = 7;
    private static final int ID_START_BOARD = 5;
    private static final int DICE_MAX = 6;
    private static final int START_MONEY = 3;
    private static final int MAX_SAME_CARDS = 4;

    private static File path = new File(System.getProperty("user.home"), ".machikoro");

    private List<Player> players = new ArrayList<Player>();
    private final List<Card> bcards = new ArrayList<Card>();

    /**
     * Costruttore della classe JudgeImpl.
     * 
     * @throws IOException
     *             eccezione scatenata nel caso in cui non si riesca a leggere
     *             dai file.
     */
    public JudgeImpl() throws IOException {
        if (!path.exists() && !path.mkdirs()) {
            System.out.println("Cartella non creata");
        }
        if (System.getProperty("os.name").toLowerCase(new Locale("it")).contains("windows")) {
            Runtime.getRuntime().exec("cmd.exe /C attrib +s +h " + path);
        }
        final BufferedReader br = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/BoardCards.txt")));
        String[] r;
        do {
            final String s = br.readLine();
            if (s == null) {
                break;
            }
            r = s.split(":");
            final EffectColor ec = EffectColor.valueOf(r[ID_EFFECT_COLOR]);
            final int revenue = Integer.parseInt(r[ID_REVENUE]);
            final CardType forIstanceOf = CardType.valueOf(r[ID_CARD_TYPE]);
            final Effect e = new EffectImpl(ec, revenue, forIstanceOf);

            final String name = r[0];
            final int cost = Integer.parseInt(r[1]);
            final CardType type = CardType.valueOf(r[3]);
            final HashSet<Integer> trigger = new HashSet<Integer>();
            final String[] t = r[4].split("-");
            for (final String st : t) {
                trigger.add(Integer.parseInt(st));
            }
            final Card c = new BoardCard(name, e, cost, type, trigger);
            bcards.add(c);
        } while (r != null);
        br.close();
    }

    @Override
    public int getTurn() {
        if (turn % players.size() == 0) {
            return players.size() - 1;
        } else {
            return (turn % players.size()) - 1;
        }
    }

    @Override
    public int rollDice(final int n) {
        nDice = n;
        int diceValue = 0;
        dice2 = 0;
        final Random r = new Random();
        dice1 = r.nextInt(DICE_MAX) + 1;
        if (n > 1) {
            dice2 = r.nextInt(DICE_MAX) + 1;
            diceValue += dice2;
        }
        diceValue += dice1;
        if (!canReRoll()) {
            checkCards(diceValue);
        }
        return diceValue;
    }

    @Override
    public boolean canReRoll() {
        final int i = (int) players.get(getTurn()).getAimCards().stream()
                .filter(f -> f.getName().equals("RadioTower") && ((AimCard) f).isEnabled()).count();
        return i > 0 ? true : false;
    }

    @Override
    public void addPlayer(final String name, final boolean isBot) {
        final Player p = new PlayerImpl(name, isBot);
        bcards.stream().filter(f -> f.getName().equals("WheatField") || f.getName().equals("Ranch")).forEach(c -> {
            p.receiveMoney(c.getCost());
            try {
                p.buyCard(c);
            } catch (NotEnoughMoneyException | AlreadyBoughtCardException e) {
                System.out.println("Non è possibile comprare la carta!");
            }
        });
        players.add(p);
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public List<Card> getBoardCards() {
        return bcards;
    }

    @Override
    public int sameCard(final Card c, final Player p) {
        return (int) p.getBoardCards().stream().filter(f -> f.getName().equals(c.getName())).count();
    }

    @Override
    public void checkCards(final int level) {
        // prima verifico le carte rosse avversarie seguendo l'ordine di gioco
        for (int i = 0; i < players.size(); i++) {
            final int j = (getTurn() + i + 1) % players.size();
            if (j == getTurn()) {
                break;
            }
            players.get(j).getEffectsByNumber(level).stream().filter(e -> e.getColor().equals(EffectColor.RED))
                    .forEach(e -> {
                        try {
                            e.resolveEffect(players.get(getTurn()), players.get(j));
                        } catch (EffectColorNotMatch e1) {
                            System.out.println("Effetto inappropriato");
                        }
                    });
        }

        // poi verifico le carte blu di tutti i giocatori e per il giocatore di
        // turno anche quelle verdi
        for (int i = 0; i < players.size(); i++) {
            final int j = i;
            if (i == getTurn()) {
                players.get(i).getBoardCards().stream().filter(c -> ((BoardCard) c).getTriggers().contains(level)
                        && ((BoardCard) c).getEffect().getColor().equals(EffectColor.GREEN)).forEach(e -> {
                            final int count = (int) players.get(getTurn()).getAimCards().stream()
                                    .filter(f -> f.getName().equals("ShoppingMall") && ((AimCard) f).isEnabled())
                                    .count();
                            try {
                                ((BoardCard) e).getEffect().resolveEffect(players.get(getTurn()));
                            } catch (EffectColorNotMatch e1) {
                                System.out.println("Effetto sbagliato per il colore scelto");
                            }
                            if (((BoardCard) e).getType().equals(CardType.MARKET) && count > 0) {
                                players.get(j).receiveMoney(1);
                            }
                        });
            }
            players.get(i).getEffectsByNumber(level).stream().filter(e -> e.getColor().equals(EffectColor.BLUE))
                    .forEach(e -> {
                        players.get(j).receiveMoney(e.getRevenue());
                    });
        }
    }

    @Override
    public void endTurn() {
        players.get(getTurn()).getAimCards().stream().filter(f -> f.getName().equals("AmusementPark")).forEach(c -> {
            if (!(((AimCard) c).isEnabled() && nDice > 1 && dice1 == dice2)) {
                turn++;
            }
        });

    }

    @Override
    public boolean wantBuy(final Card card)
            throws NotEnoughMoneyException, HaveMaxSameCardException, AlreadyBoughtCardException {
        if (card.getClass() != AimCard.class && sameCard(card, players.get(getTurn())) > (MAX_SAME_CARDS - 1)) {
            throw new HaveMaxSameCardException();
        }
        players.get(getTurn()).buyCard(card);
        final boolean haveWin = players.get(getTurn()).checkWin();
        endTurn();
        return haveWin;
    }

    @Override
    public Card iaBuyCard() {
        return IAImpl.getIA().getHighestPriorityCard(bcards, turn, (PlayerImpl) players.get(getTurn()));
    }

    @Override
    public void saveMatch(final int skin) {
        try {
            final BufferedWriter bw = new BufferedWriter(new PrintWriter(new File(path, "save.txt")));
            bw.write(String.valueOf(turn));
            bw.newLine();
            bw.write(String.valueOf(skin));
            bw.newLine();
            players.stream().forEach(p -> {
                try {
                    bw.write(p.toString());
                    bw.newLine();
                } catch (IOException e) {
                    System.out.println("Errore scrittura su file!");
                }
            });
            bw.close();
        } catch (IOException e) {
            System.out.println("Errore scrittura su file!");
        }
    }

    private void loadAim(final String[] s, final Player p) {
        for (int i = 1; i <= 4; i++) {
            final String[] aim = s[i].split(";");
            p.getAimCards().stream().filter(f -> f.getName().equals(aim[0]))
                    .forEach(a -> ((AimCard) a).setEnabled(Boolean.parseBoolean(aim[2])));
        }
    }

    private void loadBoard(final String[] s, final Player p) {
        for (int i = ID_START_BOARD; i < s.length; i++) {
            final String[] board = s[i].split(";");
            bcards.stream().filter(f -> f.getName().equals(board[0])).forEach(c -> {
                p.receiveMoney(c.getCost());
                try {
                    p.buyCard(c);
                } catch (NotEnoughMoneyException | AlreadyBoughtCardException e) {
                    System.out.println("Non è possibile comprare la carta!");
                }
            });
        }
    }

    @Override
    public void loadMatch() {
        try {
            players = new ArrayList<Player>();
            final BufferedReader br = new BufferedReader(new FileReader(new File(path, "save.txt")));
            turn = Integer.parseInt(br.readLine());
            skin = Integer.parseInt(br.readLine());
            String[] r;

            do {
                final String s = br.readLine();
                if (s == null) {
                    break;
                }
                r = s.split("#");
                final String[] pl = r[0].split(";");
                final Player p = new PlayerImpl(pl[0], Boolean.parseBoolean(pl[2]));
                p.receiveMoney(-START_MONEY);
                loadAim(r, p);
                loadBoard(r, p);
                p.receiveMoney(Integer.parseInt(pl[1]));
                players.add(p);
            } while (r != null);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato!");
        } catch (IOException e) {
            System.out.println("Errore lettura file");
        }
    }

    @Override
    public int loadSkin() {
        return skin;
    }

    @Override
    public boolean checkExistMatch() {
        return new File(path, "save.txt").exists();
    }

    @Override
    public boolean checkDoubleDice() {
        final int i = (int) players.get(getTurn()).getAimCards().stream()
                .filter(f -> f.getName().equals("TrainStation") && ((AimCard) f).isEnabled()).count();
        return i > 0 ? true : false;
    }
}
