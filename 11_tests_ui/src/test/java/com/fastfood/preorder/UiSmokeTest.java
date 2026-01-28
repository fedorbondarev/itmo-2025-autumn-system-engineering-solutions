package com.fastfood.preorder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiSmokeTest {
  private WebDriver driver;
  private String baseUrl;

  @BeforeEach
  void setUp() {
    String remoteUrl = System.getenv("REMOTE_URL");
    baseUrl = System.getenv("BASE_URL");
    if (baseUrl == null || baseUrl.isBlank()) {
      baseUrl = "http://localhost:8080";
    }
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new");
    if (remoteUrl != null && !remoteUrl.isBlank()) {
      try {
        driver = new RemoteWebDriver(new java.net.URL(remoteUrl), options);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      driver = new ChromeDriver(options);
    }
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  void clientPageLoads() {
    driver.get(baseUrl + "/");
    String header = driver.findElement(By.tagName("h1")).getText();
    String h = header.toLowerCase();
    assertTrue(h.contains("предварительный заказ") || h.contains("fastfood preorder"));
  }

  @Test
  void createOrderFlow() {
    driver.get(baseUrl + "/");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#menu .card")));
    driver.findElement(By.cssSelector("#menu button")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.id("submitOrder")));
    var submit = driver.findElement(By.id("submitOrder"));
    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderResult")));
    String text = driver.findElement(By.id("orderResult")).getText();
    assertTrue(text.contains("Заказ №") || text.contains("Заказ №".toLowerCase()));
  }

  @Test
  void itemAddedToCart() {
    driver.get(baseUrl + "/");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#menu .card")));
    driver.findElement(By.cssSelector("#menu button")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cart .border")));
    String cartText = driver.findElement(By.cssSelector("#cart .border")).getText();
    assertTrue(cartText.contains("шт."));
  }

  @Test
  void staffPageLoads() {
    driver.get(baseUrl + "/staff");
    String header = driver.findElement(By.tagName("h1")).getText();
    assertTrue(header.toLowerCase().contains("панель сотрудника"));
  }
}
