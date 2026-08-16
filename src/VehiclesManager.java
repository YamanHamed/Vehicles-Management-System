import java.util.ArrayList;

public class VehiclesManager {

    private ArrayList<Vehicle> vehicles;

    public VehiclesManager() {
        vehicles = new ArrayList<>();
    }

    // إضافة مركبة
    public boolean addVehicle(Vehicle vehicle) {
        if (searchVehicle(vehicle.getPlateNumber()) != null) {
            return false;
        }
        vehicles.add(vehicle);
        return true;
    }

    // البحث عن مركبة
    public Vehicle searchVehicle(String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equalsIgnoreCase(plateNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    // حذف مركبة
    public boolean deleteVehicle(String plateNumber) {
        Vehicle vehicle = searchVehicle(plateNumber);
        if (vehicle != null && vehicle.getAvailable()) {
            vehicles.remove(vehicle);
            return true;
        }
        return false;
    }

    // عرض جميع المركبات
    public void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            vehicle.printInfo();
            System.out.println("==");
        }
        System.out.println("===========");
    }

    // عرض المركبات المتاحة فقط
    public void printAvailableVehicles() {
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getAvailable()) {
                vehicle.printInfo();
                System.out.println("===");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available vehicles.");
        }
        System.out.println("===============");
    }

    // عرض المركبات المؤجرة حاليا
    public void printRentedVehicles() {
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.getAvailable()) {
                vehicle.printInfo();
                System.out.println("===");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rented vehicles.");
        }
        System.out.println("===============");

    }

    // عرص المركبات الاكثر تاجيرا
    public void printTopRentedVehicles(int topN) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        if (topN <= 0) {
            return;
        }

        // ترتيب المركبات من الاعلى تاجيرا للاقل تاجيرا
        ArrayList<Vehicle> newSortedVehicles = new ArrayList<>(vehicles);
        // (Bubble Sort) خوارزمية ترتيب مشهورة اسمها ال
        for (int i = 0; i < newSortedVehicles.size() - 1; i++) {
            for (int j = 0; j < newSortedVehicles.size() - i - 1; j++) {
                if (newSortedVehicles.get(j).getRentCount() < newSortedVehicles.get(j + 1).getRentCount()) {
                    Vehicle vehicle = newSortedVehicles.get(j);
                    newSortedVehicles.set(j, newSortedVehicles.get(j + 1));
                    newSortedVehicles.set(j + 1, vehicle);
                }
            }
        }

        int count = 0;
        for (Vehicle vehicle : newSortedVehicles) {
            if (count >= topN)
                break;
            System.out.println((count + 1) + ") vehicle");
            vehicle.printInfo();
            System.out.println("==");

            count++;
        }
    }

}