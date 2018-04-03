package org.openqa.selenium.example;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteSeleniumExample {
	public static void main(String[] args) throws MalformedURLException {

		String SeleniumHost = "http://localhost:4444/wd/hub";
		String UrlToCheck = "http://google.fr";
		String OutputScreenshot = "C:\\screenshot.png";
		
		URL url = new URL(SeleniumHost);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		WebDriver driver = new RemoteWebDriver(url, capabilities);

		// And now use this to visit provided URL
		driver.get(UrlToCheck);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("Page title is: " + driver.getTitle());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File(OutputScreenshot));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Close the browser
		driver.quit();
	}

}