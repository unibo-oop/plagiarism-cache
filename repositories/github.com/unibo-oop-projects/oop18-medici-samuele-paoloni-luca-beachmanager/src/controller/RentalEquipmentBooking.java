package controller;

import model.Equipment;

public interface RentalEquipmentBooking {
		
	RentalEquipmentBooking removeEquipment(Equipment equipment);
	
	RentalEquipmentBooking addEquipment(Equipment equipment);

	double calculateCost();
		
}
