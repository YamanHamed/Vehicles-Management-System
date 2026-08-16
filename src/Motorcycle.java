public class Motorcycle extends Vehicle {
    private int engineCapacity;
    private boolean hasSidecar;

    public Motorcycle() {

        super("motorcycle");
        this.engineCapacity = 0;
        this.hasSidecar = false;
    }

    public Motorcycle(String plateNumber1, String brand1, int model1, double dailyRate1, int engineCapacity1,
            boolean hasSidecar1) {

        super(plateNumber1, brand1, model1, dailyRate1, "motorcycle");
        this.engineCapacity = engineCapacity1;
        this.hasSidecar = hasSidecar1;
    }

    public void setEngineCapacity(int engineCapacity1) {
        this.engineCapacity = engineCapacity1;
    }

    public int getEngineCapacity() {
        return this.engineCapacity;
    }

    public void setHasSidecar(boolean hasSidecar1) {
        this.hasSidecar = hasSidecar1;
    }

    public boolean getHasSidecar() {
        return this.hasSidecar;
    }

    public double calculateBaseCost(long rentalDays) {
        double initialCost = rentalDays * getDailyRate();
        double finalCost = initialCost * 1.02;

        if (hasSidecar)
            return finalCost;
        else
            return initialCost;
    }

    @Override
    public void printInfo() {
        System.out.println("Vehicle Type : Motorcycle.");
        super.printInfo();
        System.out.println("Engine Capacity is : " + engineCapacity + " cc");
        System.out.println("Has Sidecar : " + hasSidecar);

    }
}
