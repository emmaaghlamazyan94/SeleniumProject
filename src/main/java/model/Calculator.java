package model;

public class Calculator {
    private String OSoption;
    private String machineClassOptionText;
    private String machineTypeOptionText;
    private String searchText;

    public Calculator(String OSoption, String machineClassOptionText, String machineTypeOptionText,String searchText) {
        this.OSoption = OSoption;
        this.machineClassOptionText = machineClassOptionText;
        this.machineTypeOptionText = machineTypeOptionText;
        this.searchText = searchText;
    }

    public String getOSoption() {
        return OSoption;
    }

    public void setOSoption(String OSoption) {
        this.OSoption = OSoption;
    }

    public String getMachineClassOptionText() {
        return machineClassOptionText;
    }

    public void setMachineClassOptionText(String machineClassOptionText) {
        this.machineClassOptionText = machineClassOptionText;
    }

    public String getMachineTypeOptionText() {
        return machineTypeOptionText;
    }

    public void setMachineTypeOptionText(String machineTypeOptionText) {
        this.machineTypeOptionText = machineTypeOptionText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}