package controller;

import java.util.Deque;
import java.util.LinkedList;

import model.Equipment;

public class Booking implements RentalEquipmentBooking {

	private Deque<Equipment> equipments;
	private double cost;
	
	public Booking() {
		this.equipments = new LinkedList<>();
		this.cost = 0;
	}

	public Deque<Equipment> getAllEquipments(){
		return this.equipments;
	}
	@Override
	public RentalEquipmentBooking addEquipment(Equipment equipment) {
		this.equipments.addLast(equipment);
		return this;
	}
	
	@Override
	public RentalEquipmentBooking removeEquipment(Equipment equipment) {
		if(this.equipments.contains(equipment)) {
			this.equipments.remove(equipment);
		}
		return null;
	}

	@Override
	public double calculateCost() {
		this.cost = 0;
		this.equipments.forEach(x->{
			this.cost += x.getCost();			
		});
		return this.cost;
	}

	
}
