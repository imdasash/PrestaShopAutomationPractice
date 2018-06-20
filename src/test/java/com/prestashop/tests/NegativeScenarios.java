package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeScenarios {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("http://automationpractice.com");

		driver.findElement(By.className("login")).click();
	}

	@Test
	public void wrongCredentials() {

		// insert email
		driver.findElement(By.id("email")).sendKeys("asdasd@gmail.com");
		// insert password
		driver.findElement(By.id("passwd")).sendKeys("2132142dfa");
		// click Submit
		driver.findElement(By.id("SubmitLogin")).click();
		// Verify "Authentication failed".
		String mytext1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();

		Assert.assertTrue(mytext1.contains("Authentication failed."));

	}

	@Test
	public void invalidEmail() {

		driver.findElement(By.id("email")).sendKeys("asdasdsds");

		driver.findElement(By.id("passwd")).sendKeys("2132142dfa");

		driver.findElement(By.id("SubmitLogin")).click();
		String mytext1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		Assert.assertTrue(mytext1.contains("Invalid email address."));

	}

	@Test
	public void blankEmail() {

		driver.findElement(By.id("email")).sendKeys("");

		driver.findElement(By.id("passwd")).sendKeys("2132142dfa");

		driver.findElement(By.id("SubmitLogin")).click();
		
		String mytext1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		
		Assert.assertTrue(mytext1.contains("An email address required."));
		

	}
	@Test
	public void blankPassword() {

		driver.findElement(By.id("email")).sendKeys("dfjdjsf@gmail.com");

		driver.findElement(By.id("passwd")).sendKeys("");

		driver.findElement(By.id("SubmitLogin")).click();
		
		String mytext1 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		
		Assert.assertTrue(mytext1.contains("Password is required."));
		

	}
	
	
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	

}
