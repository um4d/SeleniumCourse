package ru.stqa.lesson2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get("http://www.stackoverflow.com");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
