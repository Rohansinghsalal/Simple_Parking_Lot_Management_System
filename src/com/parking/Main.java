package com.parking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nFloors = 4;
        int nSlotsPerFloor = 6;
        ParkingLot parkingLot = new ParkingLot("PR1234", nFloors, nSlotsPerFloor);

        while (true) {
            System.out.println("\n----- Parking Lot System -----");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Unpark Vehicle");
            System.out.println("3. Show Available Slots");
            System.out.println("4. Show Occupied Slots");
            System.out.println("5. Show Price for Vehicle Type");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1: // Park Vehicle
                        String type, regNo, color, name;
                        while (true) {
                            System.out.print("Enter vehicle type (car/bike/truck): ");
                            type = scanner.nextLine();
                            if (!type.equals("car") && !type.equals("bike") && !type.equals("truck")) {
                                System.out.println("Invalid vehicle type. Please try again.");
                                continue;
                            }

                            System.out.print("Enter vehicle registration number: ");
                            regNo = scanner.nextLine();

                            System.out.print("Enter vehicle color: ");
                            color = scanner.nextLine();

                            System.out.print("Enter vehicle name: ");
                            name = scanner.nextLine();

                            System.out.printf("You entered:\nType: %s\nRegistration: %s\nColor: %s\nName: %s\n", type, regNo, color, name);
                            
                            // Handle confirmation input with validation
                            String confirmation;
                            while (true) {
                                System.out.print("Is this information correct? (yes/no): ");
                                confirmation = scanner.nextLine().toLowerCase();
                                if (confirmation.equals("yes")) {
                                    break; // Exit the loop, proceed to park the vehicle
                                } else if (confirmation.equals("no")) {
                                    System.out.println("Let's try again to enter vehicle details.");
                                    break; // Exit the loop to re-enter details
                                } else {
                                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                                }
                            }

                            // Check if the user wants to re-enter details or park the vehicle
                            if (confirmation.equals("yes")) {
                                String ticket = parkingLot.parkVehicle(type, regNo, color, name);
                                if (ticket != null) {
                                    System.out.printf("Your vehicle has been parked. Ticket ID: %s\n", ticket);
                                    System.out.print("How long do you want to park? (hour/day/month): ");
                                    String duration = scanner.nextLine().toLowerCase();
                                    double price = 0.0;

                                    switch (duration) {
                                        case "hour":
                                            price = parkingLot.getHourlyPrice(type);
                                            break;
                                        case "day":
                                            price = parkingLot.getDailyPrice(type);
                                            break;
                                        case "month":
                                            price = parkingLot.getMonthlyPrice(type);
                                            break;
                                        default:
                                            System.out.println("Invalid duration. Please try again.");
                                            continue;
                                    }

                                    System.out.printf("You have parked your %s for %.2f.\n", type, price);
                                } else {
                                    System.out.println("Unable to park the vehicle due to unavailability of slots.");
                                }
                                break; // Break the inner loop
                            }
                        }
                        break;

                    case 2: // Unpark Vehicle
                        System.out.print("Enter ticket ID to unpark: ");
                        String ticketId = scanner.nextLine();
                        parkingLot.unPark(ticketId);
                        break;

                    case 3: // Show Available Slots
                        System.out.print("Enter vehicle type (car/bike/truck): ");
                        String availableType = scanner.nextLine();
                        parkingLot.displayOpenSlots(availableType);
                        break;

                    case 4: // Show Occupied Slots
                        System.out.print("Enter vehicle type (car/bike/truck): ");
                        String occupiedType = scanner.nextLine();
                        parkingLot.displayOccupiedSlots(occupiedType);
                        break;

                    case 5: // Show Price for Vehicle Type
                        System.out.print("Enter vehicle type (car/bike/truck): ");
                        String priceType = scanner.nextLine();
                        System.out.printf("Hourly: %.2f, Daily: %.2f, Monthly: %.2f\n",
                                parkingLot.getHourlyPrice(priceType),
                                parkingLot.getDailyPrice(priceType),
                                parkingLot.getMonthlyPrice(priceType));
                        break;

                    case 6: // Exit
                        System.out.println("Exiting the system.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
