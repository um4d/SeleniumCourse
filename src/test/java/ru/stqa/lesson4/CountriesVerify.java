package ru.stqa.lesson4;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CountriesVerify {
    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testGo() {

        LiteCartTools.login(driver, "admin", "admin");
        driver.findElement(By.linkText("Countries")).click();

        ArrayList<WebElement> rows = (ArrayList<WebElement>)driver
                .findElements(By.cssSelector("#content tr.row"));
        ArrayList<String> countries = new ArrayList<>();

        for (WebElement elem : rows) {
            elem = elem.findElement(By.tagName("a"));
            countries.add(elem.getText());
        }

        ArrayList<String> countriesSorted = new ArrayList<>(countries);

        Collator collator = Collator.getInstance();
        String rules = ((RuleBasedCollator) Collator.getInstance()).getRules();


        try {
            collator = new RuleBasedCollator(rules.replaceAll("<'\u005f'", "<' '<'\u005f'"));
        } catch(ParseException e) {
            e.printStackTrace();
        }

        countriesSorted.sort(collator);
        assert (countries.equals(countriesSorted));

        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i) != countriesSorted.get(i)) {
                System.out.println(countries.get(i) + " === " + countriesSorted.get(i));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}