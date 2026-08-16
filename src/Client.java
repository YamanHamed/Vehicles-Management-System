import java.util.ArrayList;

public abstract class Client {
    protected int clientld;
    protected String clientname;
    protected String address;
    protected String phone;
    protected String clientType;
    protected double discountRate;
    protected ArrayList<RentalContract> contracts;

    public Client(String clientType) {
        clientld = 0;
        clientname = "No Name";
        address = "Any Address";
        phone = "Enter Number";
        discountRate = 0.0;
        this.clientType = clientType;
    }

    public Client(int clientld, String clientname, String address, String phone, String clientType,
            double discountRate) {
        this.clientld = clientld;
        this.clientname = clientname;
        this.address = address;
        this.phone = phone;
        this.clientType = clientType;
        this.discountRate = discountRate;
        this.contracts = new ArrayList<>();
    }

    public void addContract(RentalContract contract) {
        contracts.add(contract);
    }

    public ArrayList<RentalContract> getContracts() {
        return contracts;
    }

    // getters and setters
    public int getClientld() {
        return clientld;
    }

    public void setClientld(int clientld) {
        this.clientld = clientld;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientType() {
        return clientType;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void printclient() {
        System.out.println("Client Id: " + clientld);
        System.out.println("Client name:" + clientname);
        System.out.println("Address: " + address);
        System.out.println("phone: " + phone);
        System.out.println("Client Type: " + clientType);
        System.out.println("Discount Rate:" + discountRate);
    }

}
