package teladoc_challenge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static ArrayList<String>getRegisterData()
	{
		System.out.print(System.getProperty("user.dir"));
	File file =    new File(System.getProperty("user.dir") + "\\TestData\\RegisterTestData.xlsx");
	FileInputStream inputStream;
	ArrayList<String> registerData = new ArrayList<String>();
	try {
		inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
		XSSFSheet sheet=wb.getSheet("RegisterData");
		
		for (Row myrow : sheet) {
			if(myrow.getRowNum()==1) continue;
			String temp = "";
		    for (Cell mycell : myrow) {
		    	
		    	if(mycell.getCellType()==CellType.STRING) {
		    		temp = temp+ mycell.getStringCellValue() +";";
		    	}
		    	else {
		    		NumberToTextConverter.toText(mycell.getNumericCellValue());
		    		
		    		temp = temp+ NumberToTextConverter.toText(mycell.getNumericCellValue()) +";";
		    	}
		    	
//		    	try {
//		    	temp = temp+ mycell.getStringCellValue() +";";
//		    	}
//		    	catch (IllegalStateException e)
//		    	{
//		    		temp = temp+ mycell.getNumericCellValue() +";";
//		    	}
		    	
		    }
		    //double numberIndex = myrow.getCell(8).getNumericCellValue()+1;
	    	//myrow.getCell(8).setCellValue(numberIndex);
		    registerData.add(cleanseData(temp));
		    System.out.println(temp);
		}
		FileOutputStream outputStream = new FileOutputStream("TestData\\RegisterTestData.xlsx");
        wb.write(outputStream);
        wb.close();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	
	return registerData;
	}
	
	public static String cleanseData(String s)
	{
		
		
		
		return (s == null || s.length() == 0)
			      ? null 
			    	      : (s.substring(0, s.length() - 1));
		
	}
	
	
}
