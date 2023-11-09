package Ultis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseStaticDriver {
    public static WebDriver driver;

    public static WebDriverWait wait;

    @BeforeClass
    public void BaslangicIslemleri() {
        System.out.println("Driver start ....");
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        //çalıştır kısmında çıkan ilk 3 satırı siliyor


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);



        driver.manage().window().maximize(); //maximize ediyor büyük ekranda açıyor pencere boyutunda değil
        driver.manage().deleteAllCookies(); //bilgisayarındaki cookileri (çerezleri) siliyor

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));


        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); //sadece ana sayfa yüklenirken gecikme yapıyor ki hata olmasın
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // bütün webElemenler yüklenirken gecikme yapıyor ki hata olmasın

        driver.get("http://demowebshop.tricentis.com/");
        LoginTest();

    }

    void LoginTest() {

        WebElement login =driver.findElement(By.cssSelector("a[class='ico-login']"));
        login.click();

        WebElement Email=driver.findElement(By.id("Email"));
        Email.sendKeys("asdasdasdqweqwe123123@gmail.com");

        WebElement Password=driver.findElement(By.id("Password"));
        Password.sendKeys("asdqwe123");

        WebElement Login=driver.findElement(By.cssSelector("input[class='button-1 login-button']"));
        Login.click();
    }

    @AfterClass
    public void BitisIslemleri() {
        System.out.println("Driver stop ....");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }

    public static void Bekle(int saniye) {

        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
