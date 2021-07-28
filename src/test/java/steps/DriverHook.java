package steps;

import driver.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class DriverHook {
    @Before
    public void setUp() {
        DriverSingleton.getDriver();
    }

    @After
    public void tearDown(){
        DriverSingleton.closeDriver();
    }
}