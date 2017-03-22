package ru.stqa.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LiteCartTools {

    static public void login(WebDriver driver, String login, String pass) {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("login")).click();
    }

}