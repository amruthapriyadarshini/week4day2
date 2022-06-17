package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ToyWithTables {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html ");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		//Getting the row count
		List<WebElement> lstFindRows = driver.findElements(By.xpath("//section[@class='innerblock']//table//tr"));
		System.out.println("Row count " + lstFindRows.size());
		
		//Getting the column count
		List<WebElement> lstFindCols = driver.
				findElements(By.xpath("//section[@class='innerblock']//table//tr/th"));
		System.out.println("Columns size " + lstFindCols.size());
		//Getting the progress value of 'Learn to interact with Elements'
		for(int i=2;i <= lstFindRows.size();i++) {
			WebElement findElement = driver.findElement(By.xpath("//section[@class='innerblock']//table//tr["+i+"]/td[1]"));
			System.out.println(findElement.getText());
			if(findElement.getText().equals("Learn to interact with Elements")) {
				WebElement findElement1 = driver.findElement(By.xpath("//section[@class='innerblock']//table//tr["+i+"]/td[2]"));
				System.out.println("Progress " + findElement1.getText());
			}
		}
		
		//getting the vital task of least completed progress.
		Set wbSet = new HashSet<WebElement>();
		List lst = new ArrayList();
		for(int i=2;i<=lstFindRows.size();i++) {
			WebElement wbVitalTask = driver.findElement(By.xpath("//section[@class='innerblock']//table//tr["+i+"]/td[2]"));
			System.out.println("Progress here " + wbVitalTask.getText());
			String str = wbVitalTask.getText().replace("%", "");
			System.out.println("str" + str);
			lst.add(Integer.parseInt(str));
		}
		
		//Integer.parseInt(null)
		Integer min = Collections.min(lst);
		
		List<WebElement> rows = driver.findElements(By.tagName("tr"));
			
			if(min.intValue() == 20) {
				WebElement chkBox = rows.get(5).findElement(By.tagName("input"));
				chkBox.click();
			}
		
		//driver.close();
	
	}

}
