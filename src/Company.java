public class Company extends Client {
    private String commercialregister;

    public Company() {
        super("company");
        commercialregister = "UnKnown";

    }

    public Company(int clientld, String clientname, String address, String phone, String commercialregister,
            double discountRate) {

        super(clientld, clientname, address, phone, "company", discountRate);
        this.commercialregister = commercialregister;
    }

    public String getCommercialregister() {
        return commercialregister;
    }

    public void setCommercialregister(String commercialregister) {
        this.commercialregister = commercialregister;
    }

    @Override
    public void printclient() {

        super.printclient();
        System.out.println("Commercial register:" + commercialregister);
    }
}