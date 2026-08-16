public class RentalContract {
    private int contractId;
    private Vehicle vehicle;
    private Client client;
    private Invoice invoice;

    private Date startDate;
    private int rentalDays;
    private Date actualReturnDate;
    private int delayDays;

    private boolean active;

    public RentalContract(int contractId, Vehicle vehicle, Client client, Date startDate,
            int rentalDays) {
        this.contractId = contractId;
        this.vehicle = vehicle;
        this.client = client;
        this.startDate = startDate;
        this.rentalDays = rentalDays;
        this.active = true;
        this.actualReturnDate = null;
        this.invoice = null;
        this.delayDays = 0;
    }

    public boolean isActive() {
        return active;
    }

    public void endContract(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
        this.active = false;
        this.invoice = new Invoice(this, actualReturnDate);
        Date expectedReturn = startDate.addDays(rentalDays);
        if (actualReturnDate.isAfter(expectedReturn)) {
            this.delayDays = expectedReturn.calcDaysBetween(actualReturnDate);
        } else {
            this.delayDays = 0;
        }
    }

    // Getters
    public int getContractId() {
        return contractId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Invoice getInvoice() {

        return this.invoice;
    }

    public Client getClient() {
        return client;
    }

    public Date getStartDate() {

        return startDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Date getActualReturnDate() {

        return actualReturnDate;
    }

    public int getDelayDays() {
        return delayDays;
    }

    public double getBaseCost() {
        return vehicle.calculateBaseCost(rentalDays);
    }

    // Print
    public void printContract() {
        System.out.println("Contract ID: " + contractId);
        System.out.println("Vehicle plate number: " + vehicle.getPlateNumber());
        System.out.println("Client: " + client.clientname);
        System.out.println("Start Date: ");
        startDate.printDate();
        System.out.println("Rental Days: " + rentalDays);
        if (actualReturnDate != null) {
            System.out.println("Actual Return Date: ");
            actualReturnDate.printDate();
            System.out.println("Delay Days: " + delayDays);
        }

        System.out.println("Base Cost: " + getBaseCost());
        System.out.println("Active: " + active);

    }
}