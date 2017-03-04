package ru.stqa.lesson3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class MenuClicker {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void TestGo() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        int menuElementsCount = driver.findElements
                (By.cssSelector("ul#box-apps-menu > li"))
                .size();

        for (int i = 1; i <= menuElementsCount; i++) {
            driver.findElement
                    (By.cssSelector("ul#box-apps-menu > li:nth-child(" + i +")"))
                    .click();

            int subMenuElementsCount = driver.findElements
                    (By.cssSelector("ul#box-apps-menu li:not(#app-)"))
                    .size();

            for (int j = 1; j <= subMenuElementsCount; j++) {
                driver.findElement
                        (By.cssSelector("ul#box-apps-menu li:not(#app-):nth-child(" + j +")"))
                        .click();
                try {
                    driver.findElement(By.tagName("h1"));
                } catch (NoSuchElementException e) {
                    System.out.println("Can't find header. Menu # " + i + "." + j);
                } finally {continue;}
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}