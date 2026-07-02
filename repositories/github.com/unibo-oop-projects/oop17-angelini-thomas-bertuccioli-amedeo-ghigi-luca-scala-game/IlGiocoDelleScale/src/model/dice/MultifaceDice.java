package model.dice;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class MultifaceDice implements Dice{

	private Optional<Integer> number;
	private final int face;
	private final Random randomNumber = new Random();
	private final static int MAXFACE=20;
	private final static int MINFACE=4;
	private static final Supplier<RuntimeException> minError = () -> new IllegalStateException("Too low numbers");
	private static final Supplier<RuntimeException> maxError = () -> new IllegalStateException("Too much numbers");
	
	public MultifaceDice(int numberOfFace) {
		
		this.face=numberOfFace;
		if (this.face<MINFACE){
			throw minError.get();
		}else if (this.face>MAXFACE){
			throw maxError.get();
		}
		this.number=Optional.empty();
	}
	
	
	@Override
	public int roll() {
		
		this.setNumber(this.randomNumber.nextInt(this.face) + 1);
		return this.getNumber();
	}
	
	@Override
	public void setNumber(int number) {
	
		this.number=Optional.of(number);
	}

	@Override
	public int getNumber() {
		
		return this.number.get();
	}
	
	@Override
	public int viewNum() {
		
		return this.getNumber();
	}

}

