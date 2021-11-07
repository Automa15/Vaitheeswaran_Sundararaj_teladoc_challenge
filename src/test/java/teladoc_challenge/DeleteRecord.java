package teladoc_challenge;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteRecord extends DriverSetup{

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(DriverSetup.class.getName());
	
	@BeforeTest
	public void initilizeDriver() throws IOException {
		driver = getWebDriver();
		log.info("Driver is initilized");
		
	}
	
	@Test(dataProvider = "getDeleteData")
	public void DeleteUser(String userName) throws IOException {
		
			String testurl = prop.getProperty("url");
			System.out.println(testurl);
			driver.get(testurl);
			driver.manage().window().maximize();
			SoftAssert a =new SoftAssert();
			
			LandingPageDeleteRecord lPageDeleteRecord = new LandingPageDeleteRecord(driver);
			log.info("Test completed");
			//lpage.getTable();
			
			lPageDeleteRecord.getSpecificRow(userName).click();
			//lPageDeleteRecord.getbtnDeleteCancel().click();
			lPageDeleteRecord.getbtnDeleteConfirm().click();
			
			LandingPage landingPage = new LandingPage(driver);
			String	uNamerNotFound = landingPage.getSpecificRow(userName);
			a.assertEquals(uNamerNotFound, "");
			
			if(uNamerNotFound == "") {
			log.info("UserName "+uNamerNotFound+ " Not found under the Table and user Successfully added");
			}
			
			log.info("Test completed");
			
			
			
			
			
			a.assertAll();
		}
		
		@AfterTest
		public void teardown() {
			driver.close();
		}
		
		
	
		@DataProvider
		public Object[][] getDeleteData() {
			
			
			Object[][] dataObjects = new Object[1][1];
			
			dataObjects[0][0] = "novak";
			return dataObjects;
			
		}
	
}
