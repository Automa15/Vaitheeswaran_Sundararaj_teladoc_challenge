package teladoc_challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
	
	
	@DataProvider(name="RegisterationDataProvider")
	public Iterator<String> getDatafromExcel()
	{
		ArrayList<String> dataListexcel = Utilities.getRegisterData();
		System.out.println(dataListexcel);
		return dataListexcel.iterator();
	}
	
	
	
	@Test(dataProvider = "RegisterationDataProvider")
		public void addNewUser(String data) throws IOException {
		
		System.out.println(data);
		String testurl = prop.getProperty("url");
		System.out.println(testurl);
		driver.get(testurl);
		driver.manage().window().maximize();
		SoftAssert a =new SoftAssert();
		
		HashMap<String,String> hm = new HashMap<String,String>();
		String[] inputdata = data.split(";");
		hm.put("fm", inputdata[0]);
		hm.put("ln", inputdata[1]);
		hm.put("uname", inputdata[2]);
		hm.put("pw", inputdata[3]);
		hm.put("customer", inputdata[4]);
		hm.put("role", inputdata[5]);
		hm.put("em", inputdata[6]);
		hm.put("ph", inputdata[6]);
		
		
		LandingPage lpage = new LandingPage(driver);
		
		lpage.getbtnAdd().click();
		
		a.assertTrue(lpage.getReqFMElemIsVisible());
		a.assertTrue(lpage.getReqRoleElemIsVisible());
		
		lpage.gettxtFirstName().sendKeys(hm.get("fm"));
		lpage.gettxtLastName().sendKeys(hm.get("ln"));
		lpage.gettxtUserName().sendKeys(hm.get("uname"));
		lpage.gettxtPassword().sendKeys(hm.get("pw"));
		lpage.radioBtnSelect(hm.get("customer")).click();
		lpage.getdrpDwnSelect(hm.get("role"));
		lpage.gettxtEmail().sendKeys(hm.get("em"));
		lpage.gettxtMobilephone().sendKeys(hm.get("ph"));
		
		a.assertFalse(lpage.getReqFMElemIsVisible());
		a.assertFalse(lpage.getReqRoleElemIsVisible());
		
		a.assertAll();
		
		lpage.getbtnSave().click();
		log.info("Record saved successfully");
		
		//check the table is updated with the new user details
		String	uNamerFound = lpage.getSpecificRow(hm.get("uname"));
		Assert.assertEquals(hm.get("uname"), uNamerFound);
		
		log.info("Add User Test completed");
		
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
