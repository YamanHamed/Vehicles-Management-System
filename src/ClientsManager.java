import java.util.ArrayList;

public class ClientsManager {

    private ArrayList<Client> clients;

    public ClientsManager() {
        clients = new ArrayList<>();
    }

    // إضافة عميل
    public boolean addClient(Client client) {
        if (searchClient(client.getClientld()) != null) {
            return false;
        }
        clients.add(client);
        return true;
    }

    // البحث عن عميل
    public Client searchClient(int clientld) {
        for (Client client : clients) {
            if (client.getClientld() == clientld) {
                return client;
            }
        }
        return null;
    }

    // تعديل عميل
    public boolean updateClient(int clientld, String newname, String newaddress, String newphone) {

        Client client = searchClient(clientld);
        if (client != null) {
            client.setClientname(newname);
            client.setAddress(newaddress);
            client.setPhone(newphone);
            return true;
        }
        return false;
    }

    // عرض جميع العملاء
    public void printallclient() {
        if (clients.isEmpty()) {
            System.out.println("No clients in the system.");
            return;
        }
        for (Client client : clients) {
            client.printclient();
            System.out.println("==");
        }
    }

    // vip عرض العملاء ال
    public void printVIPClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients in the system.");
            return;
        }

        boolean found = false;

        for (Client client : clients) {
            int finishedContracts = 0;
            for (RentalContract contract : client.getContracts()) {
                if (!contract.isActive()) {
                    finishedContracts++;
                }
            }
            if (finishedContracts >= 5) {
                client.printclient();
                System.out.println("Finished contracts: " + finishedContracts);
                System.out.println("==");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No VIP clients found.");
        }
    }

}