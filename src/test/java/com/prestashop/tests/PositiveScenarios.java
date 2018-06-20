package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveScenarios {
	
	Faker faker = new Faker();
	
	String firstName = faker.name().firstName();
	String lastName = faker.name().lastName();
	String streetAddress = faker.address().streetAddress();
	String email = faker.internet().emailAddress();
	String password = faker.internet().password();
	String city = faker.address().city();
	String state = faker.address().state();
	String zipCode = "08123";
	
	
	
	
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("http://automationpractice.com");

		driver.findElement(By.className("login")).click();
		driver.manage().window().fullscreen();
		
	}
	
	@Test
	public void loginTest() throws InterruptedException {
		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(password);
//		driver.findElement(By.id("firstname")).sendKeys(firstName);
//		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("address1")).sendKeys(streetAddress);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("id_state")).sendKeys(state);
		driver.findElement(By.id("postcode")).sendKeys(zipCode);
		driver.findElement(By.id("phone_mobile")).sendKeys("7032342311");
		driver.findElement(By.id("submitAccount")).click();
		//Sign Out
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("SubmitLogin")).click();
		
		String mytext1 = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText();
		
		Assert.assertTrue(mytext1.contains(firstName+ " "+ lastName));
		
		driver.close();
		
	}

}
