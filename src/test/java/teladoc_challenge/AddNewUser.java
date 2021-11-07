package teladoc_challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

public class AddNewUser extends DriverSetup{
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(DriverSetup.class.getName());
	
	@BeforeTest
	public void initilizeDriver() throws IOException {
		driver = getWebDriver();
		log.info("Driver is initilized");
		
		
	}
	
	@Test(dataProvider = "getData")
	public void addNewUser(String fm, String ln, String uname, String pw, String customer, String role, String em, String ph) throws IOException {
	
		String testurl = prop.getProperty("url");
		System.out.println(testurl);
		driver.get(testurl);
		driver.manage().window().maximize();
		
		SoftAssert a =new SoftAssert();
		
		LandingPage lpage = new LandingPage(driver);
		
		lpage.getbtnAdd().click();
		
		a.assertTrue(lpage.getReqFMElemIsVisible());
		a.assertTrue(lpage.getReqRoleElemIsVisible());
		
		lpage.gettxtFirstName().sendKeys(fm);
		lpage.gettxtLastName().sendKeys(ln);
		lpage.gettxtUserName().sendKeys(uname);
		lpage.gettxtPassword().sendKeys(pw);
		lpage.radioBtnSelect(customer).click();
		lpage.getdrpDwnSelect(role);
		lpage.gettxtEmail().sendKeys(em);
		lpage.gettxtMobilephone().sendKeys(ph);
		
		a.assertFalse(lpage.getReqFMElemIsVisible());
		a.assertFalse(lpage.getReqRoleElemIsVisible());
		
		a.assertAll();
		
		lpage.getbtnSave().click();
		log.info("Record saved successfully");
		
		
		//check the table is updated with the new user details
		String	uNamerFound = lpage.getSpecificRow(uname);
		
		log.info("UserName "+uNamerFound+ " found under the Table and user Successfully added");
		
		LandingPageDeleteRecord lPageDeleteRecord = new LandingPageDeleteRecord(driver);
		
		//lpage.getTable();
		
		lPageDeleteRecord.getSpecificRow(uname).click();
		lPageDeleteRecord.getbtnDeleteConfirm().click();
		
		String	uNamerNotFound = lpage.getSpecificRow(uname);
		a.assertEquals(uNamerNotFound, "");
		
		if(uNamerNotFound == "") {
		log.info("UserName "+uNamerNotFound+ " Not found under the Table and user Successfully added");
		}
		
		log.info("Add User Test completed");
		
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
	}

	@DataProvider
	public Object[][] getData() {
				
		Object[][] dataObjects = new Object[1][8];
		
		dataObjects[0][0] = "TestFM";
		dataObjects[0][1] = "NameLast";
		dataObjects[0][2] = "testUserv";
		dataObjects[0][3] = "Password@1";
		dataObjects[0][4] = "15";
		dataObjects[0][5] = "2";
		dataObjects[0][6] = "12test11212@gmail.com";
		dataObjects[0][7] = "3214567890";
		

		return dataObjects;
		
	}
}
