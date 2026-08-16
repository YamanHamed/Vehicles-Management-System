
public class Car extends Vehicle {
    private String fuelType;
    private boolean hasAirConditioner;
    private int seatCount;

    public Car() {
        super("car");
        this.fuelType = "petrol";
        this.hasAirConditioner = false;
        this.seatCount = 5;
    }

    Car(String plateNumber1, String brand1, int model1, double dailyRate1, String fuelType1, boolean hasAirConditioner1,
            int seatCount1) {
        super(plateNumber1, brand1, model1, dailyRate1, "car");
        this.fuelType = fuelType1;
        this.hasAirConditioner = hasAirConditioner1;
        this.seatCount = seatCount1;
    }

    public void setFuelType(String fuelType1) {
        this.fuelType = fuelType1;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setHasAirConditioner(boolean hasAirConditioner1) {
        this.hasAirConditioner = hasAirConditioner1;
    }

    public boolean getHasAirConditioner() {
        return this.hasAirConditioner;
    }

    public void setSeatCount(int seatCount1) {
        this.seatCount = seatCount1;
    }

    public int getSeatCount() {
        return this.seatCount;
    }

    public double calculateBaseCost(long rentalDays) {
        double initialCost = rentalDays * getDailyRate();
        double finalCost = initialCost * 1.05;
        double finalCost1 = initialCost * 1.02;

        if (fuelType.equalsIgnoreCase("Electric"))
            return finalCost;
        else if (fuelType.equalsIgnoreCase("Gasoline"))
            return finalCost1;
        else
            return initialCost;
    }

    @Override
    public void printInfo() {
        System.out.println("Vehicle Type : Car.");
        super.printInfo();
        System.out.println("Fuel Type is : " + fuelType);
        System.out.println("Air Conditioner : " + hasAirConditioner);
        System.out.println("Seat Count : " + seatCount);

    }
}
