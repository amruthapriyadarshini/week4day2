package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Sortable {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/sortable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[1]"));
		WebElement item5 = driver.findElement(By.xpath("//ul[@id='sortable']/li[5]"));
		Actions builder = new Actions(driver);
		int x = item5.getLocation().x;
		int y = item5.getLocation().y;
		//builder.dragAndDropBy(item1, x, y).perform();
		//builder.clickAndHold(item1).clickAndHold(item5).perform();
		
		 builder.clickAndHold(item1); builder.moveToElement(item5);
		  builder.release(item5); builder.build().perform();
		 
	}

}
