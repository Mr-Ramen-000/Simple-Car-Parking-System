package CarParkingSystem;


public class ParkingLot {
    int row = 2;
    int col = 6;
    Car[][] parkingSlot = new Car[row][col];

    public void park(Car carObj, char row, int column) {
        try {
            if (parkingSlot[row - 65][column - 1] == null) {
                Car car = findCar(carObj.getPlateNumber());
                if (car == null){
                    parkingSlot[row- 65][column - 1] = carObj;
                    System.out.println("Car park Successful...");
                } else {
                    System.out.println("Car is already exist it park at "+ car.getParkingSlot() + "...");
                }
            } else {
                System.out.println("Parking slot already occupied...");
            }
        } catch (Exception e){
            System.out.println("Parking slot not existing...");
        }
    }

    public void removeCar(Car carObj){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (parkingSlot[i][j] != null && parkingSlot[i][j].equals(carObj)){
                    parkingSlot[i][j] = null;
                }
            }
        }
    }

    public Car findCar(String plateNumber){
        for (Car[] cars: parkingSlot) {
            for (Car car: cars) {
                if (car != null && car.getPlateNumber().equals(plateNumber)){
                    return car;
                }
            }
        }
        return null;
    }


    public void parkingLotStatus(){
        int occupiedSlot = 0;
        int availableSlot = 0;

        System.out.print("   ");

        for (int i=0; i<col; i++){
            System.out.printf(" %d  ", i+1);
        }

        System.out.print("\n  -");
        for (int i = 0; i < col; i++) {
            System.out.print("----");
        }
        System.out.println();

        for (int i = 0; i < row; i++) {
            System.out.printf("%c |", (char)(65 + i));
            for (int j = 0; j < col; j++) {
                if (parkingSlot[i][j] != null) {
                    System.out.print(" P |");
                    occupiedSlot += 1;
                } else {
                    System.out.print("   |");
                    availableSlot += 1;
                }
            }
            System.out.print("\n  -");
            for (int j = 0; j < col; j++) {
                System.out.print("----");
            }
            System.out.println();
        }
        System.out.println("Total Parking Slot  : " + (row * col));
        System.out.println("Total Occupied Slot : " + occupiedSlot);
        System.out.println("Total Available Slot: " + availableSlot);

    }
}
