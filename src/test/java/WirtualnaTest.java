import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class WirtualnaTest {
    private WebDriver webDriver;
    private String login;
    private String passwd;
    private String url;

    @Before
    public void setUp(){
        url="https://profil.wp.pl/login.html?zaloguj=poczta";
        login="tomek";
        passwd="Qwerty";

    }

    @Test
    public void is_Login_Fail(){
        System.setProperty("webdriver.gecko.driver","C:\\geckodriver\\geckodriver.exe");
        DesiredCapabilities desiredCapabilities=DesiredCapabilities.firefox();
        if (webDriver==null){
            webDriver=new FirefoxDriver(desiredCapabilities);
            webDriver.get(url);
            webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            //webDriver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[3]/button[2]")).click();
            WebElement loginField=webDriver.findElement(By.name("login_username"));
            loginField.clear();
            loginField.sendKeys(login);
            WebElement passwordField=webDriver.findElement(By.id("password"));
            passwordField.clear();
            passwordField.sendKeys(passwd);
            webDriver.findElement(By.id("btnSubmit")).click();
            Assert.assertTrue(webDriver.getPageSource().contains("Niestety podany login lub has"));
        }
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

}
