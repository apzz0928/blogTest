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
	
	//@Test(priority = 2)
	public void blogspotTest() {
		open(blogspotUrl);
		/*
		 * open("http://google.com"); $("#lst-ib").setValue("apzz0928.blogspot.com");
		 * $(By.name("btnK")).click(); $(By.linkText("��ȯ-5�ܰ�2 - Blogspot")).click();
		 */
		// �ܺι�� Ŭ��
		/*String pp = "APZZ092";
		String qq = "";
		
		qq = $(".profile-link").text();
		qq.trim();
		
		if(qq.equals(pp)) {
			System.out.println(qq + "ã�Ҵ�!!!!!!" + pp);
			System.out.println(qq + "ã�Ҵ�!!!!!!");
		} else {
			closeWebDriver();
		}
		$(".banner1").click();
		if (TestBrowser.equals("chrome") || TestBrowser.equals("firefox")) {
			$(By.linkText("�ڼ��� ���� ����")).click();
		} else {
			js("$('.jump-link').click();");
			//$(".jump-link").click();
		}*/
		for(int c=0;c<=30;c++) {
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
			System.out.println("blogspotTest : " + c + "����");
		}
		
	}

	//@Test(priority = 3)
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
	//@Test(priority = 0)
	public void blogspot_��ȯ_URL����() {
		for(int i=0;i<=90;i++) {
			open("http://apzz0928.blogspot.com/search/label/Category1");
			open("http://apzz0928.blogspot.com/search/label/Category2");
			open("http://apzz0928.blogspot.com/search/label/Category3");
			open("http://apzz0928.blogspot.com/search/label/Category4");
			open("http://apzz0928.blogspot.com/search/label/Category5");
			open("http://apzz0928.blogspot.com/search/label/Category6");
			open("http://apzz0928.blogspot.com/search?q=aaaaa");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%ED%95%98%EC%9A%B0%EC%8A%A4%EB%A7%88%EC%BC%80%ED%8C%85"); //���Ͽ콺������
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%ED%95%98%EC%9A%B0%EC%8A%A4-%EB%B0%94%EC%9D%B4%EB%9F%B4"); //���Ͽ콺-���̷�
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%ED%95%98%EC%9A%B0%EC%8A%A4-%EC%9D%B4%EB%A9%94%EC%9D%BC"); //���Ͽ콺-�̸���
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%ED%95%98%EC%9A%B0%EC%8A%A4-Talk"); //���Ͽ콺-Talk
			open("http://apzz0928.blogspot.com/2018/01/2018-01-10_9.html");
			open("http://apzz0928.blogspot.com/search/label/1"); //1
			open("http://apzz0928.blogspot.com/search/label/2"); //2
			open("http://apzz0928.blogspot.com/search/label/3"); //3
			open("http://apzz0928.blogspot.com/search/label/4"); //4
			open("http://apzz0928.blogspot.com/search/label/5"); //5
			open("http://apzz0928.blogspot.com/search?q=aaaaa");
			open("http://apzz0928.blogspot.com/search?q=bbbbb");
			open("http://apzz0928.blogspot.com/search?q=ccccc");
			open("http://apzz0928.blogspot.com/search?q=ddddd123123");
			open("http://apzz0928.blogspot.com/search?q=123");
			open("http://apzz0928.blogspot.com/search?q=234");
			open("http://apzz0928.blogspot.com/search?q=345");
			System.out.println("blogspot_��ȯ_URL���� " + (i+1) + " ��°");
		}
	}
	//@Test(priority = 1)
	public void egloos_��ȯ_URL����() {
		for(int i=0;i<=500;i++) {
			open("http://apzz0928.egloos.com");
			open("http://apzz0928.egloos.com/category/123");
			open("http://apzz0928.egloos.com/category/234");
			open("http://apzz0928.egloos.com/category/345");
			open("http://apzz0928.egloos.com/category/selenium"); 
			open("http://apzz0928.egloos.com/category/QA");
			open("http://apzz0928.egloos.com/category/TestData"); //1
			open("http://apzz0928.egloos.com/category/123asd");
			open("http://apzz0928.egloos.com/category/234asd");
			open("http://apzz0928.egloos.com/category/345asd");
			open("http://apzz0928.egloos.com/search?q=1"); //2
			open("http://apzz0928.egloos.com/search?q=2"); //3
			open("http://apzz0928.egloos.com/search?q=3"); //4
			open("http://apzz0928.egloos.com/search?q=4"); //5
			open("http://apzz0928.egloos.com/search?q=5");
			System.out.println("egloos_��ȯ_URL���� " + (i+1) + " ��°");
		}
	}
	//@Test(priority = 3)
	public void ���ڵ��׽�Ʈ_0() {
		for(int i=0;i<=30;i++) {
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%A3%BC%EB%AC%B8"); //���ڵ�-�ֹ�
			open("http://apzz0928.egloos.com/category/%25EA%25B0%2580"); //��
			open("https://new.acecounter.com/common/front");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EA%B0%80%EC%9E%85"); //���ڵ�-����
			open("http://apzz0928.egloos.com/category/%25EB%2582%2598"); //��
			open("https://new.acecounter.com/common/front");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%98%88%EC%95%BD"); //���ڵ�-����
			open("http://apzz0928.egloos.com/category/%25EB%258B%25A4"); //��
			open("https://new.acecounter.com/common/front");
			open("http://apzz0928.egloos.com/category/%25EA%25B0%2580%25EB%2582%2598");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%98%88%EC%95%BD?nac_md=kakao_ta&nac_cpi=61&nac_sm=%EA%B2%80%EC%83%89%EC%96%B4-%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%86%A1-%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%98%88%EC%95%BD");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%A3%BC%EB%AC%B8?apzz0928.blogs nac_md=naver_br&nac_cpi=60&nac_kw=%EA%B2%80%EC%83%89%EC%96%B4-%EB%84%A4%EC%9D%B4%EB%B2%84-%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%A3%BC%EB%AC%B8");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EA%B0%80%EC%9E%85?apzz0928.b nac_md=daum_br&nac_cpi=59&nac_kw=%EA%B2%80%EC%83%89%EC%96%B4-%EC%9D%B8%EC%BD%94%EB%94%A9-%EA%B0%80%EC%9E%85");
			//System.out.println("���ڵ� ��ȯ, ����, ���� �������� �� " + (i+1) + "���� �湮�߽��ϴ�.");
			System.out.println("���ڵ��׽�Ʈ_0 :  " + (i+1) + "����");
		}
	}
	//@Test(priority = 4)
	public void ���ڵ��׽�Ʈ_1() {
		for(int i=0;i<=30;i++) {
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%A3%BC%EB%AC%B8");
			open("http://apzz0928.blogspot.com/");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EA%B0%80%EC%9E%85");
			open("http://apzz0928.blogspot.com/");
			open("http://apzz0928.blogspot.com/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%98%88%EC%95%BD");
			open("http://apzz0928.blogspot.com/");
			open("http://apzz0928.egloos.com/category/%25EA%25B0%2580");
			open("http://apzz0928.egloos.com");
			open("http://apzz0928.egloos.com/category/%25EB%2582%2598");
			open("http://apzz0928.egloos.com");
			open("http://apzz0928.egloos.com/category/%25EB%258B%25A4");
			open("http://apzz0928.egloos.com");
			open("http://apzz0928.egloos.com/category/%25EA%25B0%2580%25EB%2582%2598");
			//System.out.println("������ - ����� �̵���ο� ����/���� �������� �������� �������� �� " + (i+1) + "���� �湮�߽��ϴ�.");
			System.out.println("���ڵ��׽�Ʈ_1 :  " + (i+1) + "����");
		}
	}
	
	@Test(priority = 0)
	public void �����ͱ����ڵ弳��naver() {
		for(int i=1;i<=10;i++) { //������ ���� ����0
			open("https://www.naver.com/");
			open("http://apzz0928.blogspot.com/search/label/marketing-normal?nac_md=normal_mkt&nac_cpi=view500222-1&nac_sm=view_____mkt_____0");
			open("http://apzz0928.egloos.com");
			System.out.println("naver ������ ���� ���� 0�� : " + i + "��° ����");
		}
		for(int i=1;i<=9;i++) { //���̷�
			open("https://www.naver.com/");
			open("http://apzz0928.blogspot.com/search/label/inHouse-viral?nac_md=viral_mkt&nac_cpi=view500222-2&nac_sm=view_____viral");
			open("http://apzz0928.egloos.com");
			System.out.println("naver ���Ͽ콺������-���̷� : " + i + "��° ����");
		}
		for(int i=1;i<=8;i++) { //�̸���
			open("https://www.naver.com/");
			open("http://apzz0928.blogspot.com/search/label/inHouse-Email?nac_md=email_mkt&nac_cpi=view500222-3");
			open("http://apzz0928.egloos.com");
			System.out.println("naver ���Ͽ콺������-�̸��� : " + i + "��° ����");
		}
		for(int i=1;i<=7;i++) { //Talk
			open("https://www.naver.com/");
			open("http://apzz0928.blogspot.com/search/label/inHouse-Talk?nac_md=sms_mkt&nac_cpi=view500222-4&nac_sm=view_____talk");
			open("http://apzz0928.egloos.com");
			System.out.println("naver ���Ͽ콺������-Talk : " + i + "��° ����");
		}
		for(int i=1;i<=6;i++) { //������ > ���ι�� ����
			open("https://www.naver.com/");
			open("http://apzz0928.blogspot.com/search/label/banner-inner?nac_inbc=view500222-1&nac_inbs=view_____innerbanner");
			open("http://apzz0928.egloos.com");
			System.out.println("naver ���ι�� ���� : " + i + "��° ����");
		}
		for(int i=1;i<=5;i++) { //������ > �ܺι�� ����
			open("http://apzz0928.egloos.com");
			$(".banner-outer").scrollTo();
			$(".banner-outer").click();
			System.out.println("naver �ܺι�� ���� : " + i + "��° ����");
		}
	}
	@Test(priority = 1)
	public void �����ͱ����ڵ弳��daum() {
		for(int i=1;i<=10;i++) { //������ ���� ����0
			open("https://www.daum.net/");
			open("http://apzz0928.blogspot.com/search/label/marketing-normal?nac_md=normal_mkt&nac_cpi=view500222-1&nac_sm=view_____mkt_____0");
			open("http://apzz0928.egloos.com");
			System.out.println("daum ������ ���� ���� 0�� : " + i + "��° ����");
		}
		for(int i=1;i<=9;i++) { //���̷�
			open("https://www.daum.net/");
			open("http://apzz0928.blogspot.com/search/label/inHouse-viral?nac_md=viral_mkt&nac_cpi=view500222-2&nac_sm=view_____viral");
			open("http://apzz0928.egloos.com");
			System.out.println("daum ���Ͽ콺������-���̷� : " + i + "��° ����");
		}
		for(int i=1;i<=8;i++) { //�̸���
			open("https://www.daum.net/");
			open("http://apzz0928.blogspot.com/search/label/inHouse-Email?nac_md=email_mkt&nac_cpi=view500222-3");
			open("http://apzz0928.egloos.com");
			System.out.println("daum ���Ͽ콺������-�̸��� : " + i + "��° ����");
		}
		for(int i=1;i<=7;i++) { //Talk
			open("https://www.daum.net/");
			open("http://apzz0928.blogspot.com/search/label/inHouse-Talk?nac_md=sms_mkt&nac_cpi=view500222-4&nac_sm=view_____talk");
			open("http://apzz0928.egloos.com");
			System.out.println("daum ���Ͽ콺������-Talk : " + i + "��° ����");
		}
		for(int i=1;i<=6;i++) { //������ > ���ι�� ����
			open("https://www.daum.net/");
			open("http://apzz0928.blogspot.com/search/label/banner-inner?nac_inbc=view500222-1&nac_inbs=view_____innerbanner");
			open("http://apzz0928.egloos.com");
			System.out.println("daum ���ι�� ���� : " + i + "��° ����");
		}
		for(int i=1;i<=5;i++) { //������ > �ܺι�� ����
			open("http://apzz0928.egloos.com");
			$(".banner-outer").scrollTo();
			$(".banner-outer").click();
			System.out.println("daum �ܺι�� ���� : " + i + "��° ����");
		}
	}
	@Test(priority = 2)
	public void �����ͱ����ڵ弳��bookmark() {
		for(int i=1;i<=10;i++) { //������ ���� ����0
			open("http://apzz0928.blogspot.com/search/label/marketing-normal?nac_md=normal_mkt&nac_cpi=view500222-1&nac_sm=view_____mkt_____0");
			open("http://apzz0928.egloos.com");
			System.out.println("bookmark ������ ���� ���� 0�� : " + i + "��° ����");
		}
		for(int i=1;i<=9;i++) { //���̷�
			open("http://apzz0928.blogspot.com/search/label/inHouse-viral?nac_md=viral_mkt&nac_cpi=view500222-2&nac_sm=view_____viral");
			open("http://apzz0928.egloos.com");
			System.out.println("bookmark ���Ͽ콺������-���̷� : " + i + "��° ����");
		}
		for(int i=1;i<=8;i++) { //�̸���
			open("http://apzz0928.blogspot.com/search/label/inHouse-Email?nac_md=email_mkt&nac_cpi=view500222-3");
			open("http://apzz0928.egloos.com");
			System.out.println("bookmark ���Ͽ콺������-�̸��� : " + i + "��° ����");
		}
		for(int i=1;i<=7;i++) { //Talk
			open("http://apzz0928.blogspot.com/search/label/inHouse-Talk?nac_md=sms_mkt&nac_cpi=view500222-4&nac_sm=view_____talk");
			open("http://apzz0928.egloos.com");
			System.out.println("bookmark ���Ͽ콺������-Talk : " + i + "��° ����");
		}
		for(int i=1;i<=6;i++) { //������ > ���ι�� ����
			open("http://apzz0928.blogspot.com/search/label/banner-inner?nac_inbc=view500222-1&nac_inbs=view_____innerbanner");
			open("http://apzz0928.egloos.com");
			System.out.println("bookmark ���ι�� ���� : " + i + "��° ����");
		}
		for(int i=1;i<=5;i++) { //������ > �ܺι�� ����
			open("http://apzz0928.egloos.com");
			$(".banner-outer").scrollTo();
			$(".banner-outer").click();
			System.out.println("bookmark �ܺι�� ���� : " + i + "��° ����");
		}
	}
	@Test(priority = 3)
	public void �����ͱ����ڵ弳��1http() {
		for(int i=1;i<=10;i++) { //������ ���� ����0
			open("https://blog.naver.com/apzz0928/221374542306");
			sleep(1000);
			open("http://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub1").waitUntil(visible, 10000);
			$(".sub1").scrollTo();
			$(".sub1").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���̹���α� ������ ���� ���� 0�� : " + i + "��° ����");
		}
		for(int i=1;i<=9;i++) { //���̷�
			open("https://blog.naver.com/apzz0928/221374542306");
			sleep(1000);
			open("http://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub3").waitUntil(visible, 10000);
			$(".sub3").scrollTo();
			$(".sub3").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���̹���α� ���Ͽ콺������-���̷� : " + i + "��° ����");
		}
		for(int i=1;i<=8;i++) { //�̸���
			open("https://blog.naver.com/apzz0928/221374542306");
			sleep(1000);
			open("http://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub4").waitUntil(visible, 10000);
			$(".sub4").scrollTo();
			$(".sub4").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���̹���α� ���Ͽ콺������-�̸��� : " + i + "��° ����");
		}
		for(int i=1;i<=7;i++) { //Talk
			open("https://blog.naver.com/apzz0928/221374542306");
			sleep(1000);
			open("http://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub5").waitUntil(visible, 10000);
			$(".sub5").scrollTo();
			$(".sub5").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���̹���α� ���Ͽ콺������-Talk : " + i + "��° ����");
		}
		for(int i=1;i<=6;i++) { //������ > ���ι�� ����
			open("https://blog.naver.com/apzz0928/221374542306");
			sleep(1000);
			open("http://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub6").waitUntil(visible, 10000);
			$(".sub6").scrollTo();
			$(".sub6").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���̹���α� ���ι�� ���� : " + i + "��° ����");
		}
		for(int i=1;i<=5;i++) { //������ > �ܺι�� ����
			open("http://apzz0928.egloos.com");
			$(".banner-outer").waitUntil(visible, 10000);
			$(".banner-outer").scrollTo();
			$(".banner-outer").click();
			System.out.println("���̹���α� �ܺι�� ���� : " + i + "��° ����");
		}
	}
	@Test(priority = 4)
	public void �����ͱ����ڵ弳��1https() {
		for(int i=1;i<=10;i++) { //������ ���� ����0
			open("https://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub1").scrollTo();
			$(".sub1").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("������ ���� ���� 0�� : " + i + "��° ����");
		}
		for(int i=1;i<=9;i++) { //���̷�
			open("https://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub3").scrollTo();
			$(".sub3").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���Ͽ콺������-���̷� : " + i + "��° ����");
		}
		for(int i=1;i<=8;i++) { //�̸���
			open("https://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub4").scrollTo();
			$(".sub4").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���Ͽ콺������-�̸��� : " + i + "��° ����");
		}
		for(int i=1;i<=7;i++) { //Talk
			open("https://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub5").scrollTo();
			$(".sub5").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���Ͽ콺������-Talk : " + i + "��° ����");
		}
		for(int i=1;i<=6;i++) { //������ > ���ι�� ����
			open("https://apzz0928.blogspot.com");
			sleep(1000);
			$(".sub6").scrollTo();
			$(".sub6").click();
			sleep(1000);
			open("http://apzz0928.egloos.com");
			System.out.println("���ι�� ���� : " + i + "��° ����");
		}
		for(int i=1;i<=5;i++) { //������ > �ܺι�� ����
			open("https://apzz0928.blogspot.com");
			$(".banner-outer").scrollTo();
			$(".banner-outer").click();
			open("http://apzz0928.egloos.com");
			System.out.println("�ܺι�� ���� : " + i + "��° ����");
		}
	}
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}
