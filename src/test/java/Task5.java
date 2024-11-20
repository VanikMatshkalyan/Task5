
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import Utility.ExcelUtility;

public class Task5 {

    private WebDriver driver = new ChromeDriver();
    private ExcelUtility excelUtility;

    @BeforeTest
    public void setUp() throws IOException {
        excelUtility = new ExcelUtility("C:\\i9-ATGAA.xlsx");
    }


    @Test
    public void Login() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://uatfile-online.taxservice.am/pages/loginPage.jsf");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement userName = driver.findElement(By.id("userName"));
        String searchName = "user_a";
        userName.sendKeys(searchName);

        WebElement userTin = driver.findElement(By.id("tin"));
        String searchTin = "00000019";
        userTin.sendKeys(searchTin);

        WebElement userPass = driver.findElement(By.id("password"));
        String searchPass = "a";
        userPass.sendKeys(searchPass);


        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("mainForm:loginBtn")));
        loginButton.click();

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='popupForm_notificationPanel']/input")));
        continueButton.click();
    }

    @Test

    public void TC4() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        Thread.sleep(4000);
        WebElement menuLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("menuLink_2")));
        menuLink.click();

        WebElement menuLink219 = driver.findElement(By.xpath("//*[@id=\"i9\"]/div[1]"));
        menuLink219.click();

        WebElement ENV_ONLY_FRONT = wait.until(ExpectedConditions.elementToBeClickable(By.name("ENV_ONLY_FRONT")));
        ENV_ONLY_FRONT.click();

        WebElement ENV_FRONT = driver.findElement(By.name("ENV_FRONT"));
        ENV_FRONT.click();

        WebElement ENV_DANG_3 = driver.findElement(By.name("ENV_DANG_3"));
        ENV_DANG_3.click();

        for (int i = 1; i <= 6; i++) {
            WebElement envElement = driver.findElement(By.name("ENV_3_1_DANG_ATGAA" + (i > 1 ? "___" + i : "")));
            envElement.click();

            String excelValue = excelUtility.getCellData(0, i, 0);
            System.out.println(excelValue);
            envElement.sendKeys(excelValue);

            WebElement select1 = driver.findElement(By.xpath("//*[@id=\"hatisForm\"]/div/div[15]/div/div[2]/div[4]/div/table[1]/tbody/tr[" + i + "]/td[1]/div/ul/li"));
            select1.click();

            WebElement select2 = driver.findElement(By.xpath("//*[@id=\"hatisForm\"]/div/div[15]/div/div[2]/div[4]/div/table[1]/tbody/tr[" + i + "]/td[2]/div[2]/ul/li"));
            select2.click();

            WebElement select3 = driver.findElement(By.xpath("//*[@id=\"hatisForm\"]/div/div[15]/div/div[2]/div[4]/div/table[1]/tbody/tr[" + i + "]/td[2]/div[2]/ul/ul/li"));
            select3.click();

            WebElement getValueElement = driver.findElement(By.xpath("//*[@id=\"hatisForm\"]/div/div[15]/div/div[2]/div[4]/div/table[1]/tbody/tr[" + i + "]/td[2]/div[2]/ul/li/span[1]"));
            String actualValue = getValueElement.getText();

            String expectedValue = excelUtility.getCellData(0, i, 1);
            System.out.println(expectedValue);

            Assert.assertEquals(actualValue, expectedValue);
            System.out.println("Assertion passed: " + expectedValue + " = " + actualValue);

            if (i < 7) {
                WebElement addButton = driver.findElement(By.name("ENV_ADDROW_T3_11"));
                addButton.click();
            }
        }


        WebElement ENV_3_1_DANG_ATGAA___7 = driver.findElement(By.name("ENV_3_1_DANG_ATGAA___7"));
        ENV_3_1_DANG_ATGAA___7.click();

        String first_6 = excelUtility.getCellData(0, 7, 0);
        System.out.println(first_6);

        ENV_3_1_DANG_ATGAA___7.sendKeys(first_6);

        WebElement getValue_7 = driver.findElement(By.xpath("//*[@id=\"hatisForm\"]/div/div[15]/div/div[2]/div[4]/div/table[1]/tbody/tr[7]/td[2]/div[2]/ul/li/span[1]"));
        String value_7 = getValue_7.getText();

        String eight = excelUtility.getCellData(0,7,1);
        System.out.println(eight);

        Assert.assertNotEquals(value_7, eight);
        System.out.println("Assertion passed:" + eight + " != " + value_7);


    }
}




