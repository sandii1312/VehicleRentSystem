public class Vehicle {
    private String brand;
    private String modelName;
    private String category;
    private String type;
    private String numberPlate;
    private int kmDriven;
    private int nextServices;
    private boolean availableStatus;

    private int vehicleDeposit;

    private int rentPerDay;

    public int getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(int rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public int getNextServices() {
        return nextServices;
    }

    public void setNextServices(int nextServices) {
        this.nextServices = nextServices;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }

    public boolean isAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(boolean availableStatus) {
        this.availableStatus = availableStatus;
    }

    public int getVehicleDeposit() {
        return vehicleDeposit;
    }

    public void setVehicleDeposit(int vehicleDeposit) {
        this.vehicleDeposit = vehicleDeposit;
    }
}
