package TestExample;

import java.net.MalformedURLException;
import java.net.URL;

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

public class TestExample {
	private static WebDriver driver;
	private static String blogspotUrl, egloosUrl, hubUrl;
	private static String TestBrowser;

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
			driver.manage().window().setSize(new Dimension(1650, 900));
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
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); //���ȼ���
			cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false); // ie text �Է� �ӵ� ����� ���� �κ�
			cap.setCapability("requireWindowFocus", true); // ie text �Է� �ӵ� ����� ���� �κ�
			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); // ie ĳ�� ������ ���� �κ�
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 1000));
		}
	}

	private static void js(String javaScriptSource) {
		executeJavaScript(javaScriptSource);
	}

	@Test(priority = 0)
	public void blogspotTest() {
		open(blogspotUrl);
		/*
		 * open("http://google.com"); $("#lst-ib").setValue("apzz0928.blogspot.com");
		 * $(By.name("btnK")).click(); $(By.linkText("��ȯ-5�ܰ�2 - Blogspot")).click();
		 */
		// �ܺι�� Ŭ��
		$(".banner1").click();
		if (TestBrowser.equals("chrome") || TestBrowser.equals("firefox")) {
			$(By.linkText("�ڼ��� ���� ����")).click();
		} else {
			$(".jump-link").click();
		}
		$(".img1").click();
		open(blogspotUrl);
		$(".banner1").click();
		if (TestBrowser.equals("chrome") || TestBrowser.equals("firefox")) {
			$(By.linkText("�ڼ��� ���� ����")).click();
		} else {
			$(".jump-link").click();
		}
		$(".img2").click();
		open(blogspotUrl);
		// ���ι�� Ŭ��
		int x = 1;
		for (int i = 0; i <= 3; i++) {
			$(".image-attribution").waitUntil(text("�׸� �̹��� ����:"), 3000);
			$(".innerBanner" + x).click();
			System.out.println("Banner i is : " + i + " / x is : " + x);
			x++;
		}
		System.out.println(" -- Banner click end -- ");
		// ������ Ŭ��
		x = 1;
		for (int i = 0; i <= 4; i++) {
			$(".image-attribution").waitUntil(text("�׸� �̹��� ����:"), 3000);
			$(".sub" + x).click();
			System.out.println("marketing i is : " + i + " / x is : " + x);
			x++;
		}
		System.out.println(" -- marketing click end -- ");
		// ���Ͽ콺 Ŭ��
		x = 6;
		for (int i = 0; i <= 2; i++) {
			$(".image-attribution").waitUntil(text("�׸� �̹��� ����:"), 3000);
			$(".sub" + x).click();
			System.out.println("inHouse i is : " + i + " / x is : " + x);
			x++;
		}
		System.out.println(" -- inHouse click end -- ");
		// ��ȯ Ŭ��
		x = 9;
		for (int i = 0; i <= 16; i++) {
			$(".image-attribution").waitUntil(text("�׸� �̹��� ����:"), 3000);
			$(".sub" + x).click();
			System.out.println("change i is : " + i + " / x is : " + x);
			x++;
		}
		System.out.println(" -- change click end -- ");
		// ���� �ٿ�ε�
		if (TestBrowser.equals("chrome")) {
			$(By.linkText("���� �ٿ�ε�")).click();
			$(By.linkText("�ڼ��� ���� ����")).click();
			for (int i = 1; i <= 3; i++) {
				$(".jpg" + i).click();
				System.out.println("download file name is : " + i + ".jpg");
			}
			for (int i = 1; i <= 3; i++) {
				$(".gif" + i).click();
				System.out.println("download file name is : " + i + ".gif");
			}
			for (int i = 1; i <= 3; i++) {
				$(".png" + i).click();
				System.out.println("download file name is : " + i + ".png");
			}
		} else {
			// ie�� HTML5 download tag ������
			// firefox�� �����Ѵٰ� �ۼ��Ǿ� �ִµ� ���� �ٿ��� �ȵ�
			// edge�� �����̸����� ���� ������ �ٿ� �Ұ�(�׷��� �ٿ���� üũ�� �Ƿ���?)
		}
		// �˻�
		String[] searchKeyword = { "Tel", "�ܺι��", "���� �ٿ�ε�", "������", "�Ϲ�", "���̹�", "����", "īī��", "����", "���Ͽ콺", "���̷�",
				"�̸���", "Talk", "��ȯ" };
		$(".touch-icon-button").click();
		for (int i = 0; i <= 13; i++) {
			$(By.name("q")).setValue(searchKeyword[i]);
			System.out.println(searchKeyword[i] + " search result");
			$(".search-action").click();
		}
	}

	@Test(priority = 1)
	public void egloosTest() {
		open(egloosUrl);
		if (TestBrowser.equals("chrome") || TestBrowser.equals("firefox")) {
			$(By.linkText("TestData")).click();
		} else {
			$(".post_title_category").click();
		}

		if (TestBrowser.equals("chrome") || TestBrowser.equals("firefox")) {
			$(By.linkText("�ܺθ�ũ Ȯ�ο�")).click();
		} else {
			js("document.querySelector(\"a[title='�ܺθ�ũ Ȯ�ο�']\").click();");
		}
		$(".banner1").click();
		open(egloosUrl + "6281874");
		// ������ Ŭ��
		for (int i = 1; i <= 5; i++) {
			$(".sub" + i).click();
			open(egloosUrl + "6281874");
			System.out.println("i is " + i);
		}
		System.out.println(" -- marketing click end -- ");
		// ���Ͽ콺 Ŭ��
		for (int i = 6; i <= 8; i++) {
			$(".sub" + i).click();
			open(egloosUrl + "6281874");
			System.out.println("i is " + i);
		}
		System.out.println(" -- inhouse click end -- ");
		// ��ȯ Ŭ��
		for (int i = 9; i <= 25; i++) {
			$(".sub" + i).click();
			open(egloosUrl + "6281874");
			System.out.println("i is " + i);
		}
		System.out.println(" -- change click end -- ");
		if (TestBrowser.equals("chrome")) {
			$(By.linkText("TestData")).click();
			$(By.linkText("file download test")).click();
			for (int i = 1; i <= 7; i++) {
				$(".download" + i).click();
				System.out.println("i is " + i);
			}
			System.out.println(" -- fileDownload click end -- ");
		} else {
			// ie�� HTML5 download tag ������
			// firefox�� �����Ѵٰ� �ۼ��Ǿ� �ִµ� ���� �ٿ��� �ȵ�
			// edge�� �����̸����� ���� ������ �ٿ� �Ұ�(�׷��� �ٿ���� üũ�� �Ƿ���?)
		}
	}

	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}
