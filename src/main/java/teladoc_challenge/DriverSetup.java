package teladoc_challenge;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverSetup {
	
	private static WebDriver driver;
	public static Properties prop;
	
	//public static Properties prop;
	@SuppressWarnings("deprecation")
	public static WebDriver getWebDriver() throws IOException{
		
	//Property file path	
			prop = new Properties();
			
			 FileInputStream fis = new FileInputStream("C:\\Users\\vaith\\eclipse-workspace\\Vaitheeswaran_Sundararaj_teladoc_challenge\\src\\main\\java\\teladoc_challenge\\data.properties");
			 prop.load(fis);
			 String browserName=prop.getProperty("browser");
			 System.out.println(browserName);
		
		//select browser name based on the value from properties file 
		switch(browserName) {
		
		case "chrome":
			
			System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "C:\\chromeDriver\\geckodriver.exe");
			 driver = new FirefoxDriver();
			 break;
		default:
			System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			 break;
		}
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		 
		return driver;
		
	}
	
	
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;


	}

	

}
