package teladoc_challenge;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPageDeleteRecord {

	public WebDriver driver;
	By tblWebTable = By.xpath("//table/tbody");
	By tblFNameColum = By.xpath("//table/tbody/tr/td[3]");
	By btnDeleteConfirm = By.xpath("//button[@class='btn ng-scope ng-binding btn-primary']");
	By btnDeleteCancel = By.xpath("//button[@class='btn ng-scope ng-binding']");

	// this is to get the row dynamically based on the first name supplied from test
	String before_XPath = "//table/tbody/tr[";
	String after_XPath = "]/td[11]/button";

	public LandingPageDeleteRecord(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSpecificRow(String name) {

		WebElement table = driver.findElement(tblWebTable);
		List<WebElement> rowsList = table.findElements(tblFNameColum);
		WebElement deletElement = null;
		int count = 0;
		for (WebElement row : rowsList) {
			String firstNameClm = row.getText();
			++count;
			if (firstNameClm != null && firstNameClm.equals(name)) {
				System.out.println(count);
				String pathString = before_XPath + count + after_XPath;
				// System.out.println(table.findElement(By.xpath(pathString)).getText());
				deletElement = table.findElement(By.xpath(pathString));
				return deletElement;
			}
		}
		return deletElement;

	}

	public WebElement getbtnDeleteCancel() {
		WebElement deleteCancel = driver.findElement(btnDeleteCancel);
		return deleteCancel;

	}

	public WebElement getbtnDeleteConfirm() {
		WebElement deleteConfirm = driver.findElement(btnDeleteConfirm);
		return deleteConfirm;

	}

}
