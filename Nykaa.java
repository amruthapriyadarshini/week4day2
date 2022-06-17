package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement wbMouseHover = driver.findElement(By.xpath("//div[@id='headerMenu']/div/ul[2]//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(wbMouseHover).perform();
		
		//Mouse Hovering on Brands and typing Loreal Paris and pressing Enter
		WebElement search = driver.findElement(By.xpath("//div[@class='brandSearchMain']/input[@id='brandSearchBox']"));
		search.sendKeys("L'Oreal Paris");
		search.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		WebElement wbResult = driver.findElement(By.xpath("//div[@id='list_L']/following-sibling::div/a"));
		
		//Getting the title of the web element
		System.out.println("text name " + wbResult.getText());
		Thread.sleep(1000);
		if(wbResult.getText().equals("L'Oreal Paris")) {
			System.out.println("the match is correct");
		}
		//driver.findElement(By.xpath("//div[@id='list_L']/following-sibling::div/a[text()=\"L'Oreal Paris\"]")).click();
		Thread.sleep(1000);
		//Clicking Loreal Paris with  mouse
		WebElement findElement = driver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]"));
		builder.click(findElement).perform();
		//
		Thread.sleep(1000);
		//Clicking on SortBy,Customer top Rated
		driver.findElement(By.xpath("//span[@class='sort-name']/parent::button")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		
		//Clicking on Color Protection
		WebElement colorProtect = driver.
				findElement(By.xpath("//span[text()='Color Protection']/ancestor::label[@class='control control-checkbox']"));
		colorProtect.click();
		
		//Check whether filter is applied with Shampoo
		WebElement filterShampoo = driver.findElement(By.xpath("//span[text()='Shampoo']"));
		if(filterShampoo.getText().equals("Shampoo")){
			System.out.println("Filter is applied for Shampoo");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWinHand = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lstWinHand.get(1));
		
		Thread.sleep(1000);
		//Selecting the option as 175ml.
		WebElement sizeElt = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select selObj = new Select(sizeElt);
		selObj.selectByValue("0");
		
		//MRP of the shampoo
		WebElement mrp = driver.findElement(By.xpath("//span[@class='css-1jczs19']"));
		System.out.println("MRP "+ mrp.getText());
		
		//Clicking on ADD to Bag
		driver.findElement(By.xpath("//span[text()='Add to Bag']/parent::button")).click();
		
		//Clicking on Shopping bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		
		Thread.sleep(1000);
		WebElement frameElt = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(frameElt);
		//Printing the grand total
		WebElement grandTotal = driver.findElement(By.xpath("//div[@class='sticky-bottom proceed-cart-btn']//div/div[@class='value']"));
		String strGrTotal = grandTotal.getText();
		System.out.println("Grand Total "+ strGrTotal);
		
		//Clicking on Proceed Button
		driver.findElement(By.xpath("//button[@class='btn full fill no-radius proceed ']")).click();
		
		//Clicking on Continue as Guest
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		
		//Checking if the grans total is same as in step 14
		Thread.sleep(1000);
		WebElement finalGranTot = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"));
		System.out.println("Final Grand total "+ finalGranTot.getText());
		Thread.sleep(2000);
		if ((finalGranTot.getText()).equals(strGrTotal)) {
			System.out.println("The value is unchanged");
		}
		
		//Closing all the windows
		driver.close();
		driver.quit();
		System.out.println("Here---");
	}

}
