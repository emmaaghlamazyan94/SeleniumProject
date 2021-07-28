package service;

import model.Calculator;

public class CalculatorCreation {
    public static final String OS_OPTION = "osOption";
    public static final String MACHINE_CLASS_OPTION = "machineClassOption";
    public static final String MACHINE_TYPE_OPTION = "machineTypeOption";
    public static final String SEARCH_TEXT = "searchText";

    public static Calculator calculatorData() {
        return new Calculator(TestDataReader.getTestData(OS_OPTION), TestDataReader.getTestData(MACHINE_CLASS_OPTION),
                TestDataReader.getTestData(MACHINE_TYPE_OPTION), TestDataReader.getTestData(SEARCH_TEXT));
    }
}