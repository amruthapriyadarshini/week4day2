package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new  ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver.get("http://www.snapdeal.com");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement menFash = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		Actions builder = new Actions(driver);
		
		
		builder.moveToElement(menFash).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		
		WebElement shoesCount = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		System.out.println("Shoes count "+shoesCount.getText());
		Thread.sleep(1000);
		//Clicking on Training shoes.
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[@data-index='1']")).click();
		
		Thread.sleep(1000);
		//Checking if they are sorted.
		List<WebElement> lstSorted = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		
		List lstNew = new ArrayList();
		for(WebElement element:lstSorted) {
			//System.out.println("Price is " +element.getText());
			String str = element.getText().substring(3).replace(',',' ').trim().replaceAll(" ","");
			lstNew.add(Integer.parseInt(str));
		}
		Collections.sort(lstNew);
		for(int i=0;i<lstNew.size();i++) {
			//System.out.println("Price is " +element.getText());
			System.out.println("Price is "+ lstNew.get(i));
			//lstNew.add(element.getText());
		}
		//select the price range 900-1200
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='filter-type-name lfloat']")).click();
		driver.findElement(By.xpath("//div[@class='filter-accordian']/i")).click();
		WebElement fromValue = driver.findElement(By.xpath("//div[@class='price-text-box']/input[@name='fromVal']"));
		Thread.sleep(1000);
		fromValue.clear();
		fromValue.sendKeys("900");
		WebElement toValue = driver.findElement(By.xpath("//input[@name='toVal']"));
		toValue.clear();
		toValue.sendKeys("1200");
	
		//Displaying the items in the range by clicking on GO
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
	
		Thread.sleep(1000);
		//Filter the colour with Yellow
		driver.findElement(By.xpath("//button[text()='View More '][1]")).click();
		Thread.sleep(2000);
		WebElement color = driver.findElement(By.xpath("//label[@for='Color_s-Yellow']"));
		color.click();
		//Check if the filter is applied
		Thread.sleep(2000);
		
		//System.out.println("The color chosen is "+ color.isEnabled());
		Thread.sleep(1000);
		//if(color.isEnabled())
			System.out.println("Filter is enabled");
		
		WebElement filterElement = driver.findElement(By.xpath("(//a[@class='clear-filter-pill'])[1]"));
		System.out.println("Filter Range"+ filterElement.getText());
		WebElement firstItem = driver.findElement(By.xpath("//p[@class='product-title']"));
		Actions builder1 = new Actions(driver); 
		builder1.moveToElement(firstItem).perform();
		System.out.println("Description of first item " + firstItem.getText());
		//Quickview of the item
		WebElement quickView = driver.findElement(By.xpath("//div[@class='center quick-view-bar  btn btn-theme-secondary  ']"));
		quickView.click();
		
		//Printing the cost and percentage value.		
		WebElement cost = driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		System.out.println("Cost "+ cost.getText());
		WebElement percent = driver.findElement(By.xpath("//span[@class='percent-desc ']"));
		System.out.println("Percentage off "+percent.getText());
		Thread.sleep(1000);
		//Screen shot of the screen.
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snapdeal.png");
		FileUtils.copyFile(source, destination);
		
		//closing the current window.
		driver.close();
		
		//closing the main window.
		driver.quit();
	}

}
