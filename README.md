# Parking Lot Management System

## Overview
The Parking Lot Management System is a Java application that simulates the operations of a parking lot. This system allows users to park and unpark vehicles while keeping track of available and occupied slots. It handles different types of vehicles (cars, bikes, trucks) and provides pricing based on parking duration.

## Features
- **Park Vehicle**: Input vehicle details including type, registration number, color, and name to park the vehicle.
- **Unpark Vehicle**: Remove vehicles using a generated ticket ID.
- **Display Available Slots**: View available parking slots for a specific vehicle type.
- **Display Occupied Slots**: Check occupied slots along with vehicle details such as name and registration number.
- **Pricing Information**: Access hourly, daily, and monthly pricing for different vehicle types.

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- Collections Framework (ArrayLists, HashMaps)

## How to Run
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/parking-lot-management.git
2. **Navigate to the project directory**:
   ```bash
   cd parking-lot-management

3. **Compile the Java files**:
   ```bash
   javac src/com/parking/*.java
   
4. **Run the application**:
    ```bash
    java -cp src com.parking.Main

## Usage
Once the application starts, a menu will be displayed with options to choose from:

1 Park Vehicle
2 Unpark Vehicle
3 Show Available Slots
4 Show Occupied Slots
5 Show Price for Vehicle Type
6 Exit
Follow the on-screen prompts to interact with the system
