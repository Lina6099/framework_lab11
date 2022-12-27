package test;

import driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonConditions {

    protected WebDriver driver;

    private final Logger logger = LogManager.getRootLogger();

    @BeforeMethod(alwaysRun = true)
    public void browserSetUp() {
        driver = DriverSingleton.getDriver();
        logger.info("Browser Start. Thread id is: " + Thread.currentThread().getId());
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        DriverSingleton.closeBrowser();
        logger.info("Browser close. Thread id is: " + Thread.currentThread().getId());
    }
}
