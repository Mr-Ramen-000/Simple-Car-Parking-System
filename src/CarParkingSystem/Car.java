package CarParkingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Car {
    private String ownerName;
    private String plateNumber;
    private String brand;
    private String model;
    private String[] dateTimeIn;
    private int[] timeIn = new int[3];
    private int[] dateIn = new int[3];
    private String parkingSlot;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");


    public Car(String ownerName, String plateNumber, String brand, String model, String parkingSlot) {
        this.ownerName = ownerName;
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.model = model;
        this.parkingSlot = parkingSlot;
        this.dateTimeIn = formatter.format(LocalDateTime.now()).split(" ");

        for (int i = 0; i < 3; i++) {
            dateIn[i] = Integer.parseInt(dateTimeIn[0].split("-")[i]);
            timeIn[i] = Integer.parseInt(dateTimeIn[1].split(":")[i]);
        }

    }

    public String getParkingSlot() {
        return parkingSlot;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void details(){
        System.out.println("\nOwner Name       : " + ownerName);
        System.out.println("Car Plate Number : " + plateNumber);
        System.out.println("Car Brand        : " + brand);
        System.out.println("Car Model        : " + model);
        System.out.println("Occupied Slot    : " + parkingSlot);
        System.out.println("Date             : " + dateTimeIn[0]);
        if (timeIn[0] == 0) {
            System.out.println("Time             : " + (timeIn[0] + 12) + ":" + timeIn[1]+ ":" + timeIn[2] + " Midnight");
        } else if (timeIn[0] == 12){
            System.out.println("Time             : " + (timeIn[0]) + ":" + timeIn[1]+ ":" + timeIn[2] + " Noon" );
        } else if (timeIn[0] > 12){
            System.out.println("Time             : " + (timeIn[0] - 12) + ":" + timeIn[1]+ ":" + timeIn[2] + " PM");
        } else {
            System.out.println("Time             : " + dateTimeIn[1] + " AM");
        }
    }

    public float balance(){
        int perHour = 20;
        float balance = perHour;

        int[] dateDuration = getDateDuration();
        int[] timeDuration = getTimeDuration();

        if (timeDuration[0] >= 1) {
            float dayInHour = dateDuration[1] * 24;
            float monthInHour = (dateDuration[0] * 30) * 24;
            float yearInHour = (dateDuration[2] * 365) * 24;

            float minuteInHour = timeDuration[1] / 60f;
            float secondsInHour = timeDuration[2] / 3600f;

            float totalHour = dayInHour + monthInHour + yearInHour + minuteInHour + secondsInHour + timeDuration[0];
            balance += (totalHour - 1) * perHour;
        }
        return balance;
    }

    public int[] getDateDuration(){
        String[] currentDateTime = formatter.format(LocalDateTime.now()).split(" ");

        int[] duration = new int[3];
        int[] currDate = new int[3];

        for (int i = 0; i < 3; i++) {
            currDate[i] = Integer.parseInt(currentDateTime[0].split("-")[i]);
        }

        if (currDate[1] < this.dateIn[1]){
            currDate[0] -= 1;
            currDate[1] += 30;
        }

        if (currDate[0] < this.dateIn[0]){
            currDate[2] -= 1;
            currDate[0] += 12;
        }

        duration[0] = currDate[0] - this.dateIn[0];
        duration[1] = currDate[1] - this.dateIn[1];
        duration[2] = currDate[2] - this.dateIn[2];

        return duration;
    }

    public int[] getTimeDuration(){
        String[] currentDateTime = formatter.format(LocalDateTime.now()).split(" ");

        int[] duration = new int[3];

        int[] currTime = new int[3];

        for (int i = 0; i < 3; i++) {
            currTime[i] = Integer.parseInt(currentDateTime[1].split(":")[i]);
        }

        if (currTime[2] < this.timeIn[2]){
            currTime[1] -= 1;
            currTime[2] += 60;
        }

        if (currTime[1] < this.timeIn[1]){
            currTime[0] -= 1;
            currTime[1] += 60;
        }

        if (currTime[0] < this.timeIn[0]){
            currTime[0] += 24;
        }

        duration[0] = currTime[0] - this.timeIn[0];
        duration[1] = currTime[1] - this.timeIn[1];
        duration[2] = currTime[2] - this.timeIn[2];

        return duration;
    }
}
