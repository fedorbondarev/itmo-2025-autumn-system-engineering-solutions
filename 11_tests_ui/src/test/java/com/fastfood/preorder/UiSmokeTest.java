package com.fastfood.preorder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

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
  void staffPageLoads() {
    driver.get(baseUrl + "/staff");
    String header = driver.findElement(By.tagName("h1")).getText();
    assertTrue(header.toLowerCase().contains("панель сотрудника"));
  }
}
