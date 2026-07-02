package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import enumeration.Characters;
import model.board.Coordinate;
import model.converter.Converter;
import model.converter.ConverterImpl;
import model.data.Data;
import model.data.DataImpl;
import model.dice.Dice;
import model.dice.ListDice;
import model.dice.ListDiceImpl;
import model.model.*;
import model.pawns.Pawns;

public class ControllerImpl implements Controller {

	private Map mapSpecial;
	private final Model game;
	private boolean control;	//dadi
	List<Dice> diceList;
	Map<Optional<Integer>, enumeration.Dice> DiceMap;
	private int numCell;
	List<Pawns> PawnsList;			//per ogni pedone occorre aggiungere un numero identificativo per gestire il turno
	List<Characters> CharacterList;
	int lastNumber;
	private Data data;
	private Optional<SettingImpl> setting;
	private Optional<Pawns> p;
	private Converter converse;
	private Coordinate Newcoordinate;


	public ControllerImpl() {
		this.game = new ModelImpl();
		this.p=Optional.empty();
		this.setting = Optional.empty();
		this.DiceMap = new HashMap<>();
		this.mapSpecial=new HashMap<>();
		this.mapSpecial.put(4, 3);
		this.mapSpecial.put(8, -5);
		this.mapSpecial.put(12, 7);
		this.mapSpecial.put(16, -2);
		this.mapSpecial.put(20, 10);
		//this.diceList.add(new ListDiceImpl().classicDice());
	}
	
	@Override
	public void Play() {
        
		if(control) {
	        this.p = Optional.of(this.PawnsList.get(this.setting.get().getTurn()));
	        int newPos = this.game.movePawn(this.p.get());			//prendo la pos finale
	        this.Newcoordinate = this.ConverteToCoordinate(newPos);				//mandare alla view le coordinate finali della pedina
	        
	        this.p.get().setState(false);
	        this.setting.get().moveTurn();
	        if(this.game.checkWin(this.p.get())) {
	        	try {
					this.FinishGame(this.setting.get().getTurn());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
        } else {
        	throw new IllegalStateException();
        }
    }
	
	
	@Override
	public void FinishGame(int turn) throws IOException{
		if(this.control) {				//finestra che permette di uscire o tornare al menu iniziale
			
			
			this.control = false;
		} else {
			throw new IllegalStateException();
		}
		
	}
	
	
	public void start(Map<Optional<Integer>, enumeration.Dice> DiceMap, int numCell, List<Characters> Character) {	
		
		this.CharacterList=Character;
		this.DiceMap = DiceMap;
		this.ConverteListDice(this.DiceMap);
		this.numCell=numCell;
		this.converse = new ConverterImpl((int)Math.sqrt(this.numCell));
		this.data= new DataImpl(this.diceList, this.numCell);
		this.setting = Optional.of(new SettingImpl(this.PawnsList.size(), this.data));
		this.game.startGame(this.data);
		
		this.CharacterList.forEach(e -> {
			System.out.println(e);
		});
		this.DiceMap.forEach((x, y) -> {
			System.out.println("Nella DiceMap i valori sono: " + x + "assegnati a " + y);
		});
	}
	

	public void startController() {
		this.control = true;
		//this.view.start();
	}

	@Override
	public int ConverteToInt(Coordinate coordinate) {
		return this.converse.toInt(coordinate);
	}

	@Override
	public Coordinate ConverteToCoordinate(int pos) {
		return this.converse.toCoordinate(pos);
	}
	
	public void ConverteListDice(Map<Optional<Integer>, enumeration.Dice> DiceMap) {
//		this.DiceMap.values().forEach(e -> {
//			this.diceList.addAll(this.DiceMap.values());
//		});
		
		ListDice diceBuilder = new ListDiceImpl();
		@SuppressWarnings("unchecked")
		List<Optional<Integer>> list = (List<Optional<Integer>>) this.mapSpecial.keySet().stream().collect(Collectors.toList());
		
		for (int i=0;i<this.DiceMap.size();i++) {
			if (this.DiceMap.get(i).equals("Multiface")) {
				this.diceList.add(diceBuilder.multiFaceDice(list.get(i).get()));
			}else if (this.DiceMap.get(i).equals("Total Personalized")) {
				this.diceList.add(diceBuilder.totalPersonalized(this.mapSpecial,list.get(i).get()));
			}else if (this.DiceMap.get(i).equals("Special Dice")) {
				this.diceList.add(diceBuilder.specialClassicDice(this.mapSpecial));
			}else if (this.DiceMap.get(i).equals("Special Twenty")) {
				this.diceList.add(diceBuilder.specialTwentyDice(this.mapSpecial));
			}else if(this.DiceMap.get(i).equals("Classic")) {
				this.diceList.add(diceBuilder.classicDice());
			}else {
				this.diceList.add(diceBuilder.twentyFaceDice());
			}
		}
	}

	
	
	
}
