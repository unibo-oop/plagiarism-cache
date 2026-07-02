package model.converter;

import model.board.Coordinate;

public class ConverterImpl implements Converter{

	private final int width;
	private final ConverterStrategy Even;
	private final ConverterStrategy Odd;
	
	public ConverterImpl(int width) {
		
		this.width=width;
		this.Even=new Even(this.width);
		this.Odd=new Odd(this.width);
	}
	
	@Override
	public int toInt(Coordinate coordinate) {
		
		if (this.checkIntEven(coordinate.getY())){
			return this.Even.getNumber(coordinate);
		}else{
			return this.Odd.getNumber(coordinate);
		}
	}
	
	@Override
	public Coordinate toCoordinate(int num) {
		
		if (this.checkCooEven(this.getHeight(num))){
			return this.Even.getCoordinate(num);
		}else{
			return this.Odd.getCoordinate(num);
		}
		
	}
	
	private boolean checkIntEven(int num){
		
		return (num%2)==0;
	}
	
	private int getHeight(int num){
		
		return (num/this.width);
	}
	
	private boolean checkCooEven(int num){
		
		return (num%2)==0;
	}
}
