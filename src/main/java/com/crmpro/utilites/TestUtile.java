package com.crmpro.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.crmpro.baseClass.BasePage;


public class TestUtile extends BasePage {
	
	public TestUtile() {
		super();
	}

	public static int Page_Load_TimeOut = 30;
	public static int Implicitly_Wait = 20;
	
	//This method will take Screenshot
	public final void takeScreenshot(String fileName){
		//Take screenshot by using TakesScreenshot interface and store it into file.
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//copy that screenshot to a desire location by using copyFile() method of FileUtils class.
		try {
			FileUtils.copyFile(file, new File("C:\\Users\\Hossain Sakhaout\\Desktop\\MavenProject\\com.crmpro\\screenshot\\"+fileName +".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//This method will read data from a specific cell.
	@SuppressWarnings("resource")
	public final String readExcelFile(int rowNumber, int collumNumber) throws FileNotFoundException, IOException{
		String readData;
		String files = "TestData.xlsx\\";  // Excel file path.
		FileInputStream FileInput = new FileInputStream(files); //Create object of FileInputStream class, and set file path in parameter.
		//Create object of Workbook of excel file.
		XSSFWorkbook workbook = new XSSFWorkbook(FileInput);
		//get sheet number of that workbook.
		XSSFSheet sheet = workbook.getSheetAt(0);
		//get row and collum number from the sheet.
		readData = sheet.getRow(rowNumber).getCell(collumNumber).toString();
		return readData; //return cell value.

	}
	
	
	//This method will read a list of data from excel file. 
	public ArrayList<String> readExcelfile(int collumn) throws IOException {
		String file = "C:\\Users\\Hossain Sakhaout\\Desktop\\MavenProject\\com.crmpro\\TestData.xlsx\\";
		// First need to create an object of FileInputStream class.
		FileInputStream fileInput = new FileInputStream(file);
		//Create object for an workbook of that file.
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		//Use XSSFSheet to get the sheet of that workbook.
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		//Iterate in the row of the selected sheet.
		Iterator <Row> rowItr = sheet.iterator();
		rowItr.next(); //Skep the first row.
		//Store all data in a list.
		ArrayList<String>list = new ArrayList<String>();
		while(rowItr.hasNext()){
			list.add(rowItr.next().getCell(collumn).getStringCellValue());
		}
		
		return list;
	}
	
	
	
		//This method will handle all windows and pop-up windows.
	public final void windowsHandle(int windowsNumber) {
		String windows = null;
			//Store all windows in a set.
		Set<String>windowHandles = driver.getWindowHandles();
		Iterator <String> Itr_Windows = windowHandles.iterator();  	//Declear Iterator and Iterate on windows.
		for(int windowIndex =1; windowIndex <= windowsNumber; windowIndex++) { 
			windows = Itr_Windows.next();
		}
			//switchTo() method switch window to next window.
		driver.switchTo().window(windows);
	
	}
	
	
	//this method allow move to between frames in a webpage. 
	public void switchTofream(String frameName) {
		driver.switchTo().frame(frameName);
	}
	
	
	
	
	

	
	

}
