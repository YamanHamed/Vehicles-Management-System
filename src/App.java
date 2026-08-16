import java.util.Locale;
import java.util.Scanner;

public class App {
    // javac *.java ; java App

    private static VehiclesManager vehiclesManager = new VehiclesManager();
    private static RentalManager rentalManager = new RentalManager();
    private static ClientsManager clientsManager = new ClientsManager();
    private static int nextClientId = 1;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in).useLocale(Locale.US);

        // Initial Vehicles
        Car car1 = new Car("ABC123", "Toyota", 2022, 50.0, "petrol", true, 5);
        vehiclesManager.addVehicle(car1);

        Motorcycle bike1 = new Motorcycle("MOTO1", "Honda", 2023, 35.0, 250, false);
        vehiclesManager.addVehicle(bike1);

        Truck truck1 = new Truck("TRK001", "Volvo", 2021, 100.0, 5.0, true);
        vehiclesManager.addVehicle(truck1);

        // Initial Clients
        Person person1 = new Person(nextClientId++, "Yaman ", "Damascus", "0999999999", "DL12345",
                new Date(1, 1, 2001));
        clientsManager.addClient(person1);

        Company company1 = new Company(nextClientId++, "Bee order", "Damascus", "0938383838", "CR789", 0.10);
        clientsManager.addClient(company1);

        while (true) {
            try {
                int choice;
                System.out.println("===== MANAGEMENT SYSTEM =====");
                System.out.println("1. Manage Vehicles");
                System.out.println("2. Manage Clients");
                System.out.println("3. Rent a Vehicle");
                System.out.println("4. Return a Vehicle");
                System.out.println("5. Show Contracts");
                System.out.println("6. Reports");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        vehicleMenu(input);
                        break;
                    case 2:
                        clientMenu(input);
                        break;
                    case 3:
                        rentVehicleMenu(input);
                        break;
                    case 4:
                        returnVehicleMenu(input);
                        break;
                    case 5:
                        contractMenu(input);
                        break;
                    case 6:
                        reportsMenu(input);
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
                input.nextLine();
                continue;
            }
        }
    }

    private static void vehicleMenu(Scanner input) {

        while (true) {
            try {
                System.out.println("=== VEHICLES MANAGEMENT ===");
                System.out.println("1. Add Vehicle");
                System.out.println("2. Delete Vehicle");
                System.out.println("3. Search Vehicle ");
                System.out.println("4. Show All Vehicles");
                System.out.println("5. Show Available Vehicles");
                System.out.println("6. Show Rented Vehicles");
                System.out.println("0. Back to Main Menu");
                System.out.print("Choice: ");
                int option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("== add vehicle ==");

                        addVehicle(input);
                        break;
                    case 2:
                        System.out.println("== delete vehicle ==");

                        System.out.print("Enter plate number to delete: ");
                        String plate = input.nextLine();
                        boolean done = vehiclesManager.deleteVehicle(plate);
                        if (done) {
                            System.out.println(" Vehicle deleted successfully ");
                        } else {
                            System.out.println(" Vehicle not found or not available ");
                        }

                        break;
                    case 3:
                        System.out.println("== search vehicle ==");

                        System.out.print("Enter plate number: ");
                        plate = input.nextLine();
                        Vehicle vehicle = vehiclesManager.searchVehicle(plate);
                        if (vehicle != null) {
                            vehicle.printInfo();
                        } else {
                            System.out.println("Vehicle not found.");
                        }
                        break;
                    case 4:
                        System.out.println("== all vehicles ==");

                        vehiclesManager.printAllVehicles();
                        break;
                    case 5:
                        System.out.println("== available vehicles ==");

                        vehiclesManager.printAvailableVehicles();
                        break;
                    case 6:
                        System.out.println("== rented vehicles ==");

                        vehiclesManager.printRentedVehicles();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
                input.nextLine();
                continue;
            }
        }
    }

    private static void addVehicle(Scanner input) {

        // get vehicle attributes
        System.out.print("Plate number: ");
        String plate = input.nextLine();
        if (vehiclesManager.searchVehicle(plate) != null) {
            System.out.println("Vehicle with plate number number : " + plate + " , already exists");
            return;
        }
        System.out.print("Brand: ");
        String brand = input.nextLine();
        System.out.print("Year Model , please enter only an integer number : ");
        int model = input.nextInt();
        System.out.print("Daily rate (daily price of the vehicle): ");
        double dailyRate = input.nextDouble();
        input.nextLine();
        System.out.print("Vehicle type (car/motorcycle/truck): ");
        String type = input.nextLine().toLowerCase();

        if (type.equals("car")) {
            System.out.print("Fuel type , please enter only (petrol , diesel or electric) : ");
            String fuel = input.nextLine();
            System.out.print("Has air conditioning , please enter only (true or false): ");
            boolean hasAC = input.nextBoolean();
            input.nextLine();
            System.out.print("Number of seats: ");
            int seats = input.nextInt();
            input.nextLine();

            Car car = new Car(plate, brand, model, dailyRate,
                    fuel, hasAC, seats);

            boolean done = vehiclesManager.addVehicle(car);
            if (!done) {
                System.out.println("Adding vehicle failed, try again. ");
                return;
            }

        } else if (type.equals("motorcycle")) {
            System.out.print("Engine capacity (cc) , please enter only an integer number: ");
            int engineCapacity = input.nextInt();
            input.nextLine();
            System.out.print("Has sidecar , please enter only (true or false): ");
            boolean hasSidecar = input.nextBoolean();
            input.nextLine();

            Motorcycle motorcycle = new Motorcycle(plate, brand, model, dailyRate,
                    engineCapacity, hasSidecar);

            boolean done = vehiclesManager.addVehicle(motorcycle);
            if (!done) {
                System.out.println("Adding vehicle failed, try again. ");
                return;
            }

        } else if (type.equals("truck")) {
            System.out.print("Cargo capacity (tons) , please enter only a number: ");
            double cargoCapacity = input.nextDouble();
            input.nextLine();
            System.out.print("Refrigerated , please enter only (true or false): ");
            boolean refrigerated = input.nextBoolean();
            input.nextLine();
            Truck truck = new Truck(plate, brand, model, dailyRate,
                    cargoCapacity, refrigerated);

            boolean done = vehiclesManager.addVehicle(truck);
            if (!done) {
                System.out.println("Adding vehicle failed, try again. ");
                return;
            }

        } else {
            System.out.println("Unknown vehicle type. please enter (car , motorcycle or truck ");
        }
    }

    private static void clientMenu(Scanner input) {

        while (true) {
            try {
                System.out.println("=== CLIENTS MANAGEMENT ===");
                System.out.println("1. Add Client");
                System.out.println("2. Search Client by ID");
                System.out.println("3. Update Client");
                System.out.println("4. Show All Clients");
                System.out.println("0. Back to Main Menu");
                System.out.print("Choice: ");
                int option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("== add client ==");

                        addClient(input);
                        break;
                    case 2:
                        System.out.println("== search client ==");

                        System.out.print("Enter client ID: ");
                        int id = input.nextInt();
                        input.nextLine();
                        Client client = clientsManager.searchClient(id);
                        if (client != null) {
                            client.printclient();
                        } else {
                            System.out.println("Client not found.");
                        }
                        break;
                    case 3:
                        System.out.println("== update client ==");

                        System.out.print("Enter client ID: ");
                        id = input.nextInt();
                        input.nextLine();
                        System.out.print("New name: ");
                        String newName = input.nextLine();
                        System.out.print("New address: ");
                        String newAddress = input.nextLine();
                        System.out.print("New phone: ");
                        String newPhone = input.nextLine();
                        boolean done = clientsManager.updateClient(id, newName, newAddress, newPhone);
                        if (done) {
                            System.out.println("Client updated successfully.");
                        } else {
                            System.out.println("Client not found. ( ID not found ) ");
                        }
                        break;
                    case 4:
                        System.out.println("== all clients ==");

                        clientsManager.printallclient();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
                input.nextLine();
                continue;
            }
        }
    }

    private static void addClient(Scanner input) {

        try {
            int clientId = nextClientId++;
            System.out.print("Enter client name: ");
            String name = input.nextLine();
            System.out.print("Enter client address: ");
            String address = input.nextLine();
            System.out.print("Enter client phone: ");
            String phone = input.nextLine();
            System.out.print("Enter client type (person / company): ");
            String type = input.nextLine().toLowerCase();

            if (type.equals("person")) {
                System.out.print("Enter driving license number: ");
                String license = input.nextLine();

                Date birthDate = inputDate(input, "Enter client Birth date :");

                Person person = new Person(clientId, name, address, phone, license, birthDate);
                boolean done = clientsManager.addClient(person);
                if (done) {
                    System.out.println("Person client added successfully. ID: " + clientId);
                } else {
                    System.out.println("Adding client failed, try again. ");
                    nextClientId--;
                }
            } else if (type.equals("company")) {
                System.out.print("Commercial register number: ");
                String commercialRegister = input.nextLine();
                double discountRate;
                while (true) {
                    System.out.print("Discount rate , please enter a fraction (like 0.01 for 1%): ");

                    discountRate = input.nextDouble();
                    if (discountRate < 1 && 0 <= discountRate) {
                        break;

                    } else {
                        continue;
                    }
                }

                input.nextLine();
                Company company = new Company(clientId, name, address, phone, commercialRegister, discountRate);
                boolean done = clientsManager.addClient(company);
                if (done) {
                    System.out.println("Company client added successfully. ID: " + clientId);
                } else {
                    System.out.println("Failed to add client (ID may already exist).");
                    nextClientId--;
                }
            } else {
                System.out.println("Unknown client type. Use 'person' or 'company'.");
                nextClientId--;
            }
        } catch (Exception e) {
            System.out.println(
                    "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
            nextClientId--;
            input.nextLine();
            return;
        }
    }

    private static void rentVehicleMenu(Scanner input) {
        try {
            System.out.println("==== RENT VEHICLE =====");

            // Get vehicle plate number
            System.out.print("Enter vehicle plate number: ");
            String plate = input.nextLine();
            Vehicle vehicle = vehiclesManager.searchVehicle(plate);
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }
            if (!vehicle.getAvailable()) {
                System.out.println("Vehicle is not available.");
                return;
            }

            // Get client ID
            System.out.print("Enter client ID: ");
            int clientId = input.nextInt();
            input.nextLine();
            Client client = clientsManager.searchClient(clientId);
            if (client == null) {
                System.out.println("Client not found.");
                return;
            }

            // Get start date
            Date startDate = inputDate(input, "Enter start date: ");

            // Get rental days
            System.out.print("Enter rental days: ");
            int rentalDays = input.nextInt();
            input.nextLine();

            RentalContract contract = rentalManager.rentVehicle(vehicle, client, startDate, rentalDays);
            if (contract == null) {
                System.out.println("vehicle renting failed, try again. ");
                return;
            }
            System.out.println("== the rent contract  ==");
            contract.printContract();

        } catch (Exception e) {
            System.out.println(
                    "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
            input.nextLine();
            return;
        }
    }

    private static void returnVehicleMenu(Scanner input) {

        try {
            System.out.println("=== RETURN VEHICLE ===");

            // Get vehicle plate number
            System.out.print("Enter vehicle plate number: ");
            String plateNumber = input.nextLine();
            Vehicle vehicle = vehiclesManager.searchVehicle(plateNumber);
            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            // Get contract
            RentalContract contract = rentalManager.getActiveContractByVehiclePlate(plateNumber);
            if (contract == null) {
                System.out.println("No rent contract found for vehicle with plate number : " + plateNumber);
                return;
            }

            // Get actual return date
            Date actualReturnDate = inputDate(input, "Actual return date (today's date): ");

            RentalContract newContract = rentalManager.returnVehicle(contract.getContractId(), actualReturnDate);
            if (newContract == null) {
                System.out.println("vehicle returing failed, try again. ");
                return;
            }
            System.out.println("== new contract and invoice ==");
            newContract.printContract();
            newContract.getInvoice().printInvoice();

        } catch (Exception e) {
            System.out.println(
                    "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
            input.nextLine();
            return;
        }
    }

    private static void contractMenu(Scanner input) {

        while (true) {
            try {
                System.out.println("==== CONTRACTS ====");
                System.out.println("1. Show Active Contracts");
                System.out.println("2. Show Finished Contracts");
                System.out.println("3. Show Contracts by Client Name");
                System.out.println("4. Show Contracts for company clients");
                System.out.println("0. Back to Main Menu");
                System.out.print("Choice: ");
                int option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("== active contracts ==");
                        rentalManager.showActiveContracts();
                        break;
                    case 2:
                        System.out.println("== finished contracts ==");
                        rentalManager.showFinishContracts();
                        break;
                    case 3:
                        System.out.print("Enter client name: ");
                        String name = input.nextLine();
                        System.out.println("== contracts for " + name + " ==");
                        rentalManager.showContractsByClientName(name);
                        break;
                    case 4:
                        System.out.println("== contracts by copmanies ==");
                        rentalManager.showContractsByClientType("company");
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
                input.nextLine();
                continue;
            }
        }
    }

    private static void reportsMenu(Scanner input) {
        while (true) {
            try {
                System.out.println("==== REPORTS ====");
                System.out.println("1. Total Revenue");
                System.out.println("2. Top 5 Most Rented Vehicles");
                System.out.println("3. VIP Clients (5+ finished contracts)");
                System.out.println("0. Back to Main Menu");
                System.out.print("Choice: ");
                int option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("== Total Revenue ==");
                        System.out.println("Total company revenue: " + rentalManager.getTotalRevenue());
                        break;
                    case 2:
                        System.out.println("== Most Rented Vehicles ==");
                        vehiclesManager.printTopRentedVehicles(5);
                        break;
                    case 3:
                        System.out.println("== VIP Clients ==");
                        clientsManager.printVIPClients();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid input. please Enter a suitable type  ( don't enter a string if asked for a number )");
                input.nextLine();
                continue;
            }
        }
    }

    private static Date inputDate(Scanner input, String output) {
        System.out.print(output);
        while (true) {
            System.out.println(" Please enter three integers: (day month year)");
            try {
                int day = input.nextInt();
                int month = input.nextInt();
                int year = input.nextInt();
                input.nextLine();
                if (day < 1 || day > 31 || month < 1 || month > 12 || year < 2000) {
                    System.out.println("Invalid date range. Use day(1-31) month(1-12) year(>=2000).");
                    continue;
                }
                return new Date(day, month, year);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter three integers separated by spaces.");
                input.nextLine();
            }
        }
    }

}