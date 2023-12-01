package TestExample;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import com.codeborne.selenide.testng.ScreenShooter;

public class DightyAdmin {
	private static WebDriver driver;
	private static String blogspotUrl, egloosUrl, hubUrl;
	private static String TestBrowser;

	Date date = new Date();
    SimpleDateFormat date_format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    String id_date = date_format.format(date);
	
	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browser) throws MalformedURLException {
		blogspotUrl = "http://apzz0928.blogspot.com/";
		egloosUrl = "http://apzz0928.egloos.com/";
		hubUrl = "http://10.77.129.79:5555/wd/hub";

		String urlToRemoteWD = hubUrl;
		DesiredCapabilities cap;
		ScreenShooter.captureSuccessfulTests = false;

		if (browser.equals("chrome")) {
			TestBrowser = "chrome";
			cap = DesiredCapabilities.chrome();
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 1080));
		} else if (browser.equals("firefox")) {
			TestBrowser = "firefox";
			cap = DesiredCapabilities.firefox();
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 900));
		} else if (browser.equals("edge")) {
			TestBrowser = "edge";
			cap = DesiredCapabilities.edge();
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 900));
		} else if (browser.equals("internetExplorer")) {
			TestBrowser = "internetExplorer";
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); //보안설정
			cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false); // ie text 입력 속도 향상을 위한 부분
			cap.setCapability("requireWindowFocus", true); // ie text 입력 속도 향상을 위한 부분
			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); // ie 캐시 삭제를 위한 부분
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 1000));
		}
	}
	private static void js(String javaScriptSource) {
		executeJavaScript(javaScriptSource);
	}
	//@Test(priority = 0)
  	public void DightyAdmin상담내역() {
  		open("https://admin.dighty.com/login");
  		$(".inp", 0).setValue("apzz0928");
  		$(".inp", 1).setValue("qordlf!@34");
  		$(".btn_login").click();
  		$(".btn-primary", 0).waitUntil(visible, 10000);
  		open("https://admin.dighty.com/customer/counseling/30");
  		sleep(3000);
  		for(int i=1001;i<=1100;i++) {
  			$(".btn-width-10").click();
  			$(".btn-width-10", 2).waitUntil(visible, 10000);
  			js("document.querySelectorAll('p')[2].innerText = '" + i + " // " + id_date + " // apzz0928@nate.com'");
  			sleep(500);
  			js("document.querySelectorAll('.btn-width-10')[1].click();");
  			sleep(500);
  			confirm("상담내역을 등록하시겠습니까?");
  			sleep(500);
  			confirm("등록되었습니다.");
  			sleep(500);
  			refresh();
  			$(".btn-width-10").waitUntil(visible, 10000);
  		}
  	}
	//@Test(priority = 0)
  	public void DightyAdmin서비스문의() {
  		open("https://dighty.com/");
  		$(".btn_ask", 0).scrollIntoView(false);
  		for(int i=5;i<=1000;i++) {
  	  		$(".form_control", 0).setValue("최영권");
  	  		$(".form_control", 1).setValue("nhnace");
  	  		$(".form_control", 2).setValue("apzz0928@nate.com");
  	  		$(".form_control", 3).setValue("010-0000-0000");
  	  		$(".form_control", 4).setValue(i + "");
  	  		$(".chk_img").click();
  	  		sleep(500);
  	  		$(".btn_ask", 0).click();
  	  		sleep(1000);
  	  		$("#confirmButton").click();
  	  		sleep(1000);
  	  		System.out.println(i + " 번째 등록");
  		}
  	}
	@Test(priority = 0)
  	public void DightyAdmin공지사항등록() {
  		open("https://admin.dighty.com/login");
  		$(".inp", 0).setValue("apzz0928");
  		$(".inp", 1).setValue("qordlf!@34");
  		$(".btn_login").click();
  		$(".btn-primary", 0).waitUntil(visible, 10000);
  		open("https://admin.dighty.com/contents/notice/form");
  		sleep(3000);
  		for(int i=101;i<=1100;i++) {
  			Date date = new Date();
  		    SimpleDateFormat date_format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
  		    String id_date = date_format.format(date);
  			$(".nuxt-link-active", 4).waitUntil(visible, 10000);
  			$(".txt-input", 0).setValue(i + " // " + id_date + " // apzz0928@nate.com");
  			js("document.querySelectorAll('p')[1].innerText = '" + i + " // " + id_date + " // apzz0928@nate.com'");
  			sleep(500);
  			js("window.scrollTo(0,9999)");
  			sleep(500);
  			$(".confirm", 0).click();
  			sleep(500);
  			confirm("공지사항이 등록 되었습니다.");
  			sleep(500);
  			open("https://admin.dighty.com/contents/notice/form");
  		}
  	}
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}