
public class Truck extends Vehicle {
    private double cargoCapacity;
    private boolean refrigerated;

    public Truck() {
        super("truck");
        this.cargoCapacity = 0.0;
        this.refrigerated = false;
    }

    public Truck(String plateNumber1, String brand1, int model1, double dailyRate1, double cargoCapacity1,
            boolean refrigerated1) {
        super(plateNumber1, brand1, model1, dailyRate1, "truck");
        this.cargoCapacity = cargoCapacity1;
        this.refrigerated = refrigerated1;
    }

    public void setCargoCapacity(double cargoCapacity1) {
        this.cargoCapacity = cargoCapacity1;
    }

    public double getCargoCapacity() {
        return this.cargoCapacity;
    }

    public void setRefrigerated(boolean refrigeraated1) {
        this.refrigerated = refrigeraated1;
    }

    public boolean getRefrigerated() {
        return this.refrigerated;
    }

    public double calculateBaseCost(long rentalDays) {
        double initialCost = rentalDays * getDailyRate();
        double finalCost = initialCost * 1.05;

        if (refrigerated)
            return finalCost;
        else
            return initialCost;
    }

    @Override
    public void printInfo() {
        System.out.println("Vehicle Type : Truck.");
        super.printInfo();
        System.out.println("Cargo Capacity is : " + cargoCapacity + " Ton");
        System.out.println("Has Refrigerated : " + refrigerated);

    }
}