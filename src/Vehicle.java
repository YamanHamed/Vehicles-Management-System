public abstract class Vehicle {
    private String plateNumber;
    private String brand;
    private int model;
    private double dailyRate;
    private boolean available;
    private int rentCount = 0;
    private String vehicleType;

    public Vehicle(String vehicleType) {
        this.plateNumber = "unknown";
        this.brand = "unknown";
        this.model = 0;
        this.dailyRate = 0;
        this.available = true;
        this.vehicleType = vehicleType;
    }

    public Vehicle(String plateNumber1, String brand1, int model1, double dailyRate1, String vehicleType1) {
        this.plateNumber = plateNumber1;
        this.brand = brand1;
        this.model = model1;
        this.dailyRate = dailyRate1;
        this.available = true;
        this.vehicleType = vehicleType1;

    }

    public void setPlateNumber(String plateNumber1) {
        this.plateNumber = plateNumber1;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setBrand(String brand1) {
        this.brand = brand1;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setModel(int model1) {
        this.model = model1;
    }

    public int getModel() {
        return this.model;
    }

    public void setDailyRate(double dailyRate1) {
        this.dailyRate = dailyRate1;
    }

    public double getDailyRate() {
        return this.dailyRate;
    }

    public void setAvailable(boolean available1) {
        this.available = available1;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public int getRentCount() {
        return this.rentCount;
    }

    public int incrementRentCount() {
        int i = ++rentCount;
        return i;
    }

    public abstract double calculateBaseCost(long rentalDays);

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void printInfo() {
        System.out.println("Plate Number is : " + plateNumber);
        System.out.println("Brand : " + brand);
        System.out.println("Model : " + model);
        System.out.println("Daily Rate : " + dailyRate + "$");
        System.out.println("Available : " + available);
        System.out.println("Rent count : " + rentCount);
    }
}
