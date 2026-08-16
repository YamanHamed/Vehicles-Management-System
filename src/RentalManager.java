import java.util.ArrayList;

public class RentalManager {
    private ArrayList<RentalContract> contracts;
    private int nextContractId;

    public RentalManager() {
        contracts = new ArrayList<>();
        nextContractId = 1;
    }

    public RentalContract rentVehicle(Vehicle vehicle, Client client,
            Date startDate, int rentalDays) {

        if (!vehicle.getAvailable()) {
            return null;
        }
        vehicle.setAvailable(false);
        vehicle.incrementRentCount();

        int contractId = nextContractId++;
        RentalContract contract = new RentalContract(contractId, vehicle, client, startDate, rentalDays);
        contracts.add(contract);

        client.addContract(contract);

        return contract;
    }

    public RentalContract returnVehicle(int contractId, Date actualReturnDate) {
        for (RentalContract contract : contracts) {
            if (contract.getContractId() == contractId && contract.isActive()) {
                contract.endContract(actualReturnDate);
                contract.getVehicle().setAvailable(true);

                return contract;
            }
        }

        return null;

    }

    public RentalContract getActiveContractByVehiclePlate(String plateNumber) {
        for (RentalContract contract : contracts) {
            if (contract.isActive() && contract.getVehicle().getPlateNumber().equalsIgnoreCase(plateNumber)) {
                return contract;
            }
        }
        return null;
    }

    public double getTotalRevenue() {
        double total = 0;
        for (RentalContract contract : contracts) {
            if (!contract.isActive() && contract.getInvoice() != null) {
                total += contract.getInvoice().getTotalCost();
            }
        }
        return total;
    }

    // contracts prints
    public void showActiveContracts() {

        boolean found = false;
        for (RentalContract contract : contracts) {
            if (contract.isActive()) {
                contract.printContract();
                System.out.println("== ");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No active contracts available.");
        }
        System.out.println("===============");
    }

    public void showFinishContracts() {
        boolean found = false;
        for (RentalContract contract : contracts) {
            if (!contract.isActive()) {
                contract.printContract();
                System.out.println("== ");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No finished contracts available.");
        }
        System.out.println("===============");
    }

    public void showContractsByClientType(String clientType) {
        boolean found = false;
        for (RentalContract contract : contracts) {
            if (contract.getClient().getClientType().equalsIgnoreCase(clientType)) {
                contract.printContract();
                System.out.println("== ");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contracts found for client type: " + clientType);
        }
        System.out.println("===============");
    }

    public void showContractsByClientName(String name) {
        boolean found = false;
        for (RentalContract contract : contracts) {
            if (contract.getClient().getClientname().equalsIgnoreCase(name)) {
                contract.printContract();
                System.out.println("== ");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contracts found for this client.");
        }
        System.out.println("===============");
    }

    // vehicles prints
    public void showAllVehiclesRentedBetween(Date start, Date end) {
        boolean found = false;

        for (RentalContract contract : contracts) {
            Date contractStartDate = contract.getStartDate();
            if (!contractStartDate.isBefore(start) && !contractStartDate.isAfter(end)) {
                System.out.println(
                        contract.getVehicle().getVehicleType() + " with plate number :  "
                                + contract.getVehicle().getPlateNumber());
                System.out.println("== ");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles found for this period.");
        }
        System.out.println("===============");
    }

    public void showMotorcyclesRentedBetween(Date start, Date end) {
        boolean found = false;
        for (RentalContract contract : contracts) {
            Date contractStartDate = contract.getStartDate();
            if (!contractStartDate.isBefore(start) && !contractStartDate.isAfter(end)) {
                String vehicleType = contract.getVehicle().getVehicleType();
                // filtering motorcycels
                if (vehicleType.equalsIgnoreCase("motorcycle")) {
                    System.out.println("motorcycle with plate number : " + contract.getVehicle().getPlateNumber());
                    System.out.println("== ");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No motorcycles rented in this period.");
        }
        System.out.println("===============");

    }

    public void showDelayedVehicles(Date today) {
        boolean found = false;

        for (RentalContract contract : contracts) {

            if (contract.isActive()) {
                Date expectedReturn = contract.getStartDate().addDays(contract.getRentalDays());
                if (today.isAfter(expectedReturn)) {
                    System.out.println("Delayed with plate number :" + contract.getVehicle().getPlateNumber());
                    System.out.println("== ");
                    found = true;
                }
            }

        }
        if (!found) {
            System.out.println("No vehicles with late fees.");
        }
        System.out.println("===============");
    }

    // clients print
    public void showClientsByVehicle(String plate) {
        boolean found = false;
        for (RentalContract contract : contracts) {
            if (contract.getVehicle().getPlateNumber().equalsIgnoreCase(plate)) {
                System.out.println("Client : " + contract.getClient().getClientname());
                System.out.println("Client ID : " + contract.getClient().getClientld());
                System.out.println("== ");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No clients found for vehicle: " + plate);
        }
        System.out.println("===============");
    }

}
