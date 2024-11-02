package com.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private String parkingLotId;
    private List<List<Slot>> slots;
    private Map<String, Double> hourlyPrices;
    private Map<String, Double> dailyPrices;
    private Map<String, Double> monthlyPrices;

    public ParkingLot(String parkingLotId, int nFloors, int nSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        slots = new ArrayList<>();
        hourlyPrices = new HashMap<>();
        dailyPrices = new HashMap<>();
        monthlyPrices = new HashMap<>();

        // Set prices for each vehicle type
        hourlyPrices.put("car", 10.0);
        hourlyPrices.put("bike", 5.0);
        hourlyPrices.put("truck", 15.0);
        
        dailyPrices.put("car", 100.0);
        dailyPrices.put("bike", 50.0);
        dailyPrices.put("truck", 150.0);
        
        monthlyPrices.put("car", 1000.0);
        monthlyPrices.put("bike", 500.0);
        monthlyPrices.put("truck", 1500.0);

        // Initialize slots
        for (int i = 0; i < nFloors; i++) {
            List<Slot> floorSlots = new ArrayList<>();
            floorSlots.add(new Slot("truck"));
            floorSlots.add(new Slot("bike"));
            floorSlots.add(new Slot("bike"));
            for (int j = 3; j < nSlotsPerFloor; j++) {
                floorSlots.add(new Slot("car"));
            }
            slots.add(floorSlots);
        }
    }

    public String parkVehicle(String type, String regNo, String color, String name) {
        Vehicle vehicle = new Vehicle(type, regNo, color, name);
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.isAvailable() && slot.getType().equals(type)) {
                    slot.setVehicle(vehicle);
                    String ticketId = generateTicketId(i + 1, j + 1);
                    slot.setTicketId(ticketId);
                    System.out.printf("Vehicle Name: %s, Floor: %d, Slot: %d\n", name, i + 1, j + 1);
                    return ticketId;
                }
            }
        }
        System.out.println("NO slot available for the given type");
        return null;
    }

    public void unPark(String ticketId) {
        String[] extract = ticketId.split("_");
        int floorIndex = Integer.parseInt(extract[1]) - 1;
        int slotIndex = Integer.parseInt(extract[2]) - 1;

        if (floorIndex < 0 || floorIndex >= slots.size() || slotIndex < 0 || slotIndex >= slots.get(floorIndex).size()) {
            System.out.println("Invalid ticket ID.");
            return;
        }

        Slot slot = slots.get(floorIndex).get(slotIndex);
        if (slot.getVehicle() != null) {
            System.out.println("Unparked vehicle: " + slot.getVehicle().getName());
            slot.setVehicle(null);
            slot.setTicketId(null);
        } else {
            System.out.println("Slot is already empty.");
        }
    }

    public double getHourlyPrice(String type) {
        return hourlyPrices.getOrDefault(type, 0.0);
    }

    public double getDailyPrice(String type) {
        return dailyPrices.getOrDefault(type, 0.0);
    }

    public double getMonthlyPrice(String type) {
        return monthlyPrices.getOrDefault(type, 0.0);
    }

    private String generateTicketId(int floor, int slotNumber) {
        return parkingLotId + "_" + floor + "_" + slotNumber;
    }

    public void displayOpenSlots(String type) {
        System.out.println("Available slots for " + type + ":");
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.isAvailable() && slot.getType().equals(type)) {
                    System.out.println("Floor " + (i + 1) + ", Slot " + (j + 1));
                }
            }
        }
    }

    public void displayOccupiedSlots(String type) {
        System.out.println("Occupied slots for " + type + ":");
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (!slot.isAvailable() && slot.getType().equals(type)) {
                    System.out.println("Floor " + (i + 1) + ", Slot " + (j + 1));
                }
            }
        }
    }
}
