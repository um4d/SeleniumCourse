package ru.stqa.lesson3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StickerVerify {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
    }

    @Test
    public void testGO() {

        driver.navigate().to("http://localhost/litecart/en/");
        WebElement main = driver.findElement(By.cssSelector("div.middle > div.content"));

        List products = main.findElements(By.cssSelector("a.link"));
        WebElement tmp;
        for (int i = 0; i < products.size(); i++) {
            tmp = (WebElement)products.get(i);
            try {
                assert (tmp.findElements(By.cssSelector("div[class^=sticker]")).size() == 2);
            } catch(NoSuchElementException e) {
                System.out.println("Product #" + (i + 1)  + " have no sticker");
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
