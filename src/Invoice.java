public class Invoice {
    private int contractId;
    private Client client;
    private Vehicle vehicle;

    private Date startDate;
    private int rentalDays;
    private Date expectedReturnDate;
    private Date actualReturnDate;

    Invoice(RentalContract rentalContract, Date actualReturnDate) {
        this.contractId = rentalContract.getContractId();
        this.client = rentalContract.getClient();
        this.vehicle = rentalContract.getVehicle();
        this.startDate = rentalContract.getStartDate();
        this.rentalDays = rentalContract.getRentalDays();
        this.expectedReturnDate = startDate.addDays(rentalDays);
        this.actualReturnDate = actualReturnDate;

    }

    // getters with calaculations
    public double getBaseCost() {
        return vehicle.calculateBaseCost(this.rentalDays);
    }

    public double getDiscounts() {
        return client.getDiscountRate() * this.getBaseCost();
    }

    public double getLateFees() {
        return this.getLateDays() * 0.10 * (this.getBaseCost() / rentalDays);
    }

    public double getTotalCost() {
        return getBaseCost() - getDiscounts() + getLateFees();
    }

    public int getLateDays() {
        if (actualReturnDate.isBefore(expectedReturnDate) ||
                actualReturnDate.isEqual(expectedReturnDate)) {
            return 0;
        } else {
            return expectedReturnDate.calcDaysBetween(actualReturnDate);
        }

    }

    // Getters
    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void printInvoice() {
        System.out.println(" ====== INVOICE ====== ");
        System.out.println("Contract ID         : " + contractId);
        System.out.println("Client              : " + client.getClientname());
        System.out.println("Client type        : " + client.getClientType());
        System.out.println("Vehicle            : " + vehicle.getModel() + " (" + vehicle.getPlateNumber() + ")");
        System.out.println("Vehicle type       : " + vehicle.getVehicleType());
        System.out.print("Start date         : ");
        startDate.printDate();
        System.out.print("Expected return date   : ");
        expectedReturnDate.printDate();
        System.out.print("Actual return date      : ");
        actualReturnDate.printDate();
        System.out.println("Rental days        : " + rentalDays);
        System.out.println("Vehicle daily price : " + vehicle.getDailyRate());
        System.out.println("Base cost ( without discounts or late fees ) : " + getBaseCost());
        System.out.println("Discounts         : " + getDiscounts());
        System.out.println("Late days     : " + getLateDays());
        System.out.println("Late fees (10% per day)      : " + getLateFees());
        System.out.println("TOTAL AMOUNT: " + getTotalCost());
        System.out.println("=====================");
    }
}