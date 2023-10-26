package CarParkingSystem;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    static ParkingLot parkingLot = new ParkingLot();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n-----------------------------------------");
            System.out.println("\t\tCar Parking System");
            System.out.println("-----------------------------------------");
            System.out.println("[1] Park My Car");
            System.out.println("[2] Retrieve My Car");
            System.out.println("[3] Parking Lot Status");
            System.out.println("[4] Exit");
            System.out.print(">> ");

            try {
                String choice = input.nextLine();
                switch (Integer.parseInt(choice)){
                    case 1 -> parkMyCar();
                    case 2 -> retrieveMyCar();
                    case 3 -> parkingStatus();
                    case 4 -> {
                        System.out.println("Thank you for using this program...");
                        exit = true;
                    }
                    default -> System.out.println("Please choose from the choices...");
                }
            } catch (Exception e){
                System.out.println("Invalid Input...");
            }
        }
    }

    public static void parkMyCar(){
        boolean back = false;

        while(!back) {
            String ownerName, plateNumber, brand, model, slot;
            int column;
            char row;

            try {
                System.out.println("\n-----------------------------------------");
                System.out.println("\t\tPark My Car");
                System.out.println("-----------------------------------------");
                System.out.print("Enter Owner Name: ");
                ownerName = input.nextLine();
                System.out.print("Enter Plate Number: ");
                plateNumber = input.nextLine().toUpperCase();
                System.out.print("Enter Car Brand: ");
                brand = input.nextLine();
                System.out.print("Enter Car Model: ");
                model = input.nextLine();
                System.out.print("Enter Parking Row: ");
                row = input.nextLine().toUpperCase().charAt(0);
                System.out.print("Enter Parking Column: ");
                column = Integer.parseInt(input.nextLine());

                slot = row + String.valueOf(column);

                Car car = new Car(ownerName, plateNumber, brand, model, slot);
                parkingLot.park(car, row, column);

            } catch (Exception e){
                System.out.println("Invalid Input...");
            }

            while (true) {
                System.out.println("\n[1] Try Again");
                System.out.println("[2] Back");
                System.out.print(">> ");

                try {
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 1) {
                        break;
                    } else if (choice == 2) {
                        back = true;
                        break;
                    } else {
                        System.out.println("Please choose from the choices...");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input...");
                }
            }
        }
    }

    public static void retrieveMyCar(){
        boolean back = false;
        while (!back) {
            try {
                System.out.println("-----------------------------------------");
                System.out.println("\t\tRetrieve My Car");
                System.out.println("-----------------------------------------");
                System.out.print("Enter Plate Number: ");
                String plateNumber = input.nextLine().toUpperCase();

                Car car = parkingLot.findCar(plateNumber);


                if (car != null) {
                    float balance = car.balance();

                    car.details();

                    System.out.println("-----------------------------------------");
                    System.out.printf("Balance          : %.2f\n", balance);
                    System.out.print("Enter Cash       : ");
                    float cash = Float.parseFloat(input.nextLine());

                    if (cash < balance) {
                        System.out.println("You don't have enough cash...");
                    } else {
                        System.out.printf("Change           : %.2f\n",(cash - balance));
                        System.out.println("-----------------------------------------");

                        parkingLot.removeCar(car);
                        System.out.println("Car successfully retrieve " + car.getParkingSlot() + " slot is now available...");
                    }
                } else {
                    System.out.println("\nCar can't find in parking lot...");
                }

            } catch (Exception e){
                System.out.println("Invalid Input...");
            }

            while (true) {
                System.out.println("\n[1] Try Again");
                System.out.println("[2] Back");
                System.out.print(">> ");

                try {
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 1) {
                        break;
                    } else if (choice == 2) {
                        back = true;
                        break;
                    } else {
                        System.out.println("Please choose from the choices...");
                    }
                }catch (Exception e) {
                    System.out.println("Invalid Input...");
                }
            }
        }
    }

    public static void parkingStatus(){
        boolean back = false;
        while (!back) {
            System.out.println("\n-----------------------------------------");
            System.out.println("\t\tParking Lot Status");
            System.out.println("-----------------------------------------");
            parkingLot.parkingLotStatus();

            while(true) {
                System.out.println("\n[1] Back");
                System.out.print(">> ");
                try {
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 1) {
                        back = true;
                        break;

                    } else {
                        System.out.println("Please choose from the choices...");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Input...");
                }
            }
        }

    }
}
