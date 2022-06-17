package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Drag {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/drag.html");
		driver.manage().window().maximize();
		WebElement dragElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		Actions builder = new Actions(driver);
		int x = dragElement.getLocation().x;
		int y = dragElement.getLocation().y;
		builder.dragAndDropBy(dragElement, 77, 77).build().perform();
	
	}

}
