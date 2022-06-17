package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizeable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable");
		driver.manage().window().maximize();
		Actions builder = new Actions(driver);
		WebElement frameElement = driver.findElement(By.xpath("//div[@class='demo-list']/following-sibling::iframe"));
		driver.switchTo().frame(frameElement);
		WebElement wbElement = driver.findElement(By.id("resizable"));
		int x = wbElement.getLocation().getX();
		int y = wbElement.getLocation().getY();
		//builder.clickAndHold(wbElement).moveByOffset(50, 35).perform();
		builder.clickAndHold(wbElement).moveByOffset(100, 50).release(wbElement).build().perform();
		//driver.close();
		System.out.println("ENd-->");

	}

}
