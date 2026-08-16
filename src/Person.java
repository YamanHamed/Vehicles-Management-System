public class Person extends Client {
    private String drivingliceensenumber;
    private Date brithdate;

    public Person() {
        super("person");
        drivingliceensenumber = "UnKnown";
        brithdate = new Date();

    }

    public Person(int clientld, String clientname, String address, String phone, String drivingliceensenumber,
            Date brithdate) {

        super(clientld, clientname, address, phone, "person", 0.0);
        this.drivingliceensenumber = drivingliceensenumber;
        this.brithdate = brithdate;
    }

    public String getDrivingliceensenumber() {
        return drivingliceensenumber;
    }

    public void setDrivingliceensenumber(String drivingliceensenumber) {
        this.drivingliceensenumber = drivingliceensenumber;
    }

    public Date getBrithdate() {
        return this.brithdate;
    }

    public void setBrithdate(Date brithdate) {
        this.brithdate = brithdate;
    }

    @Override
    public void printclient() {
        super.printclient();
        System.out.println("Driving liceense number: " + drivingliceensenumber);
        System.out.print("Brith date: ");
        this.brithdate.printDate();

    }

}