package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
public ExtentSparkReporter sparkReporter;
public ExtentReports extent;
public ExtentTest test;

String repName; 
public void onStart(ITestContext testContext) {
	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	repName = "Test-Report-" + timeStamp + ".html";
	sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
	
	sparkReporter.config().setDocumentTitle("opencart Automation Report");
	sparkReporter.config().setReportName("opencart Functional Testing");
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	
	String 	os = testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os);
	
	String 	browser = testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());
	}
}

public void onTestSuccess(ITestResult result) {
	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.PASS,result.getName()+" got successfully executed");
}

public void onTestFailure(ITestResult result) {
	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	
	test.log(Status.FAIL,result.getName()+" got failed");
	test.log(Status.INFO,result.getThrowable().getMessage());
	
	try {
		String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
	} catch (Exception e1) {
		
		e1.printStackTrace();
	}
}

public void onTestSkip(ITestResult result) {
	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	
	test.log(Status.SKIP,result.getName()+" got skipped");
	test.log(Status.INFO,result.getThrowable().getMessage());
}

public void onFinish(ITestContext testContext) {
	extent.flush();
	
	String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
	File extentReport = new File(pathOfExtentReport);
	
	try {
		Desktop.getDesktop().browse(extentReport.toURI());
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
	
/*	try {
		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googleemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("pavan@gmail.com","password"));
		email.setSSLOnConnect(true);
		email.setFrom("pavan@gmail.com");
		email.setSubject("Test Results");
		email.setMsg("Please find Attached Report.....");
		email.addTo("kavin8701@gmmail.com");
		email.attach(url, "extent report", "please check report.....");
		email.send();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}

*/







/*
public String captureScreen(String tname)throws IOException {
String timeStamp=new SimpleDateFormat("yyyyMMddhhss").format(new Date());
TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);

String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
File targetFile=new File(targetFilePath);
sourceFile.renameTo(targetFile);

return targetFilePath;
}
*/