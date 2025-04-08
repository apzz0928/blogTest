package TestExample;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.Selectors.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ElementsCollection;

public class BlogTest {
    Date number_date = new Date();
    SimpleDateFormat number_format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    SimpleDateFormat number_format1 = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
    String date = number_format.format(number_date);
    String date1 = number_format1.format(number_date);

    @Parameters("browser")
    @BeforeClass
    public void beforeTest(String browser) throws MalformedURLException {
        WebDriver driver = getDriver(browser);
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().setSize(new Dimension(1800, 1080));
    }

    private WebDriver getDriver(String browser) throws MalformedURLException {
    	//String hubUrl = "http://172.31.176.1:5555/wd/hub";
        String hubUrl = "http://10.77.129.169:5555/wd/hub";
        WebDriver driver; // WebDriver ��ü�� �ʱ�ȭ�մϴ�.

        switch (browser.toLowerCase()) { // ������ �̸��� �ҹ��ڷ� ��ȯ�Ͽ� �б� ó��
            case "chrome":
                // ChromeDriver ��θ� ��������� ����
                System.setProperty("webdriver.chrome.driver", "D:\\000. Selenium\\Driver\\chromedriver-win64\\chromedriver.exe"); 
                
                // Chrome �ɼ� ����
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*"); // ���� ������ ���
                chromeOptions.addArguments("--disable-web-security");  // WebSocket ���� �ذ�
                chromeOptions.addArguments("--no-sandbox");            // ����ڽ� ���� ȸ��
                driver = new ChromeDriver(chromeOptions); // ������ �ɼ����� ChromeDriver ����
                break;

            case "edge":
                // EdgeDriver ��θ� ��������� ����
                System.setProperty("webdriver.edge.driver", "D:\\000. Selenium\\Driver\\edgedriver_win64\\msedgedriver.exe"); 
                
                // Edge �ɼ� ���� (�⺻ ���� ���)
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*"); // ���� ������ ���
                edgeOptions.addArguments("--disable-web-security");  // WebSocket ���� �ذ�
                edgeOptions.addArguments("--no-sandbox");            // ����ڽ� ���� ȸ��
                driver = new EdgeDriver(edgeOptions); // ������ �ɼ����� EdgeDriver ����
                break;
                /*
            case "firefox": //�����߻��ؼ� ������		
                // FirefoxDriver ��θ� ��������� ����
                System.setProperty("webdriver.gecko.driver", "D:\\000. Selenium\\Driver\\geckodriver-win64\\geckodriver.exe"); 
                
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*"); // ���� ������ ���
                firefoxOptions.addArguments("--disable-web-security");  // WebSocket ���� �ذ�
                firefoxOptions.addArguments("--no-sandbox");            // ����ڽ� ���� ȸ��
                // firefoxOptions.addArguments("--headless"); // �ּ� ó���Ͽ� ������� �ʵ��� ��

                driver = new FirefoxDriver(firefoxOptions);
                break;
                */

            default:
                // �������� �ʴ� ������ ��û �� ���ܸ� �����ϴ�.
                throw new IllegalArgumentException("Unsupported browser: " + browser); 
        }

        return driver; // ������ WebDriver ��ü�� ��ȯ�մϴ�.
    }

	private static void js(String javaScriptSource) {
		executeJavaScript(javaScriptSource);
	}
	
	//@Test(priority = 999)
	public void import�ڻ����() {
		sleep(1000);
	}
	
	@Test(priority = 1)
	public void �ҼȺ���⺻���Ȯ��() {
		//$("#member_id").shouldBe(visible, Duration.ofSeconds(5));
		Configuration.timeout = 10000; //should, shouldBe �⺻ timeout 10�ʷ� ����
		open("https://socialbiz-c.nhndata.com/login");
		$(".link").shouldBe(enabled);
		$(".link").click();
		switchTo().window(1);
		$("#email").shouldBe(enabled);
		$("#email").setValue("apzz0928");
		$("#pass").sendKeys("qordlf14@#");
		$("#loginbutton").click();
		switchTo().window(0);
		$(".col.label", 0).shouldBe(enabled, Duration.ofSeconds(120));
		System.out.println("�ҼȺ��� �α��� �Ϸ�");
		$(".q-dialog__backdrop").should(disappear);
		$(".q-item__label", 1).shouldBe(visible);
		$(".q-item__label", 1).click();
		$(".q-item__label", 2).shouldBe(enabled);
		for(int i=2;i<=7;i++) {
			$(".q-item__label", i).click();
			$(".col.label", 0).shouldBe(enabled);
		}
		System.out.println("��ú��� 1Depth Ȯ��");
		$(".q-item__label", 8).click(); //��õ ���ø� Ŭ��
		$(".title", 1).shouldBe(visible); //���� ���� ���� ���
		$(".title", 1).click(); //���� ���� ���� Ŭ��
		$(".q-btn--no-uppercase.w100", 0).shouldBe(enabled);
		$(".q-img__image--loaded", 3).shouldBe(enabled);
		$(".q-ml-md.w200", 0).click();
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//���� �Է�â ���
		$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//�޽��� ���� ��ư ���
		Date number_date = new Date();	//�ð� �ǽð����� �ٽ� ����
		String date1 = number_format1.format(number_date);
		$(".q-field__native.q-placeholder", 0).setValue(date1 + " ��õ ���ø� ���� ���� ����");
		$(".q-ml-md.w150", 0).click();	//���� �� �Խ�
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);	//confirm Ȯ�� ��ư ���
		$(".q-ml-sm.min-w100", 0).click();	//confirm Ȯ�� ��ư Ŭ��
		$(".q-notification", 0).should(disappear);	//toast alert ����������� ���
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled); 
		System.out.println("��õ ���ø� ���� ���� ���� ���");
		$(".w75.bg-white", 0).shouldBe(enabled);	//���� ��ư ���
		$(".w75.bg-white", 0).click();	//���� ��ư Ŭ��
		$(".q-spinner").should(disappear);	//���ǳ� ���
		$(".q-inner-loading").should(disappear); //���ǳ� �� ���̾� ���
		//$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//�޽��� ���� ��ư ���
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//���� �Է�â ���
		$(".q-field__native.q-placeholder", 0).sendKeys(" ����");
		$(".q-ml-md.w150", 0).click(); //���� �� �Խ�
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);
		$(".q-ml-sm.min-w100", 0).click(); // confirm Ŭ��
		$(".q-notification", 0).should(disappear);	//toast alert ����������� ���
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//���������̼� ���
		System.out.println("���� ���� ���� ����");
		$(".w75.bg-white", 1).shouldBe(enabled);	//���� ��ư ���
		$(".w75.bg-white", 1).click();	//���� ��ư Ŭ��
		$(".q-spinner").should(disappear);	//���ǳ� ����������� ���
		$(".q-inner-loading").should(disappear); //���ǳ� �� ���̾� ���
		//$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//�޽��� ���� ��ư ���
		$(".q-ml-md.w150", 0).click(); //���� �� �Խ� ��ư Ŭ��
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);	//confirm Ȯ�� ��ư ���
		$(".q-ml-sm.min-w100", 0).click(); // confirm Ŭ��
		$(".q-notification", 0).should(disappear);	//toast alert ����������� ���
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//���������̼� ���
		System.out.println("���� ���� ���� ����");
		$(".q-item__label", 8).click(); //��õ ���ø� �޴� Ŭ��
		$(".title", 2).shouldBe(visible); //��õ ���ø��� DM �ڵ� ���� ���
		$(".title", 2).click();	//��õ ���ø��� DM �ڵ� ���� Ŭ��
		$(".q-btn--no-uppercase.w100", 0).shouldBe(enabled); //���ø� �ε����� ���
		$(".q-img__image--loaded", 3).shouldBe(enabled); //������ ���� �ε����� ���
		$(".q-anchor--skip.justify-center.row", 7).shouldBe(enabled); //���ø� �ε����� ���
		$(".q-ml-md.w200", 0).click(); //��õ ���ø� ����ϱ� ��ư Ŭ��
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//���� �Է�â ���
		$(".q-btn--no-uppercase.q-ml-md.w150", 0).shouldBe(enabled);	//���� �� �Խ� ��ư ���
		$(".q-field__native.q-placeholder", 0).setValue(date1 + " ��õ ���ø� DM �ڵ� ����");
		$(".q-ml-md.w150", 0).click();	//���� �� �Խ�
		$(".q-notification", 0).should(disappear);	//toast alert ����������� ���
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled); 
		System.out.println("��õ ���ø� DM �ڵ� ����");
		$(".w75.bg-white", 0).shouldBe(enabled);	//���� ��ư ���
		$(".w75.bg-white", 0).click();	//���� ��ư Ŭ��
		$(".q-spinner").should(disappear);	//���ǳ� ���
		$(".q-inner-loading").should(disappear); //���ǳ� �� ���̾� ���
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//���� �Է�â ���
		$(".q-field__native.q-placeholder", 0).sendKeys(" ����");
		$(".q-ml-md.w150", 0).click(); //���� �� �Խ�
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled); // confirm ���
		$(".q-ml-sm.min-w100", 0).click(); // confirm Ŭ��
		$(".q-notification", 0).should(disappear);	//toast alert ����������� ���
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//���������̼� ���
		System.out.println("DM �ڵ� ���� ����");
		$(".w75.bg-white", 1).shouldBe(enabled);	//���� ��ư ���
		$(".w75.bg-white", 1).click();	//���� ��ư Ŭ��
		$(".q-spinner").should(disappear);	//���ǳ� ����������� ���
		$(".q-inner-loading").should(disappear); //���ǳ� �� ���̾� ���
		$(".q-ml-md.w150", 0).click(); //���� �� �Խ� ��ư Ŭ��
		$(".q-notification", 0).should(disappear);	//toast alert ����������� ���
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//���������̼� ���
		System.out.println("DM �ڵ� ���� ����");
		$(".q-item__label", 8).click(); //��õ ���ø� Ŭ��
		$(".title", 3).shouldBe(visible); //�޽��� �޴� ���
		$(".title", 3).click(); //�޽��� �޴� Ŭ��
		$(".q-btn--no-uppercase.w100", 6).shouldBe(enabled); //���ø� �ε� ���
		$(".q-img__image--loaded", 3).shouldBe(enabled); //������ ���� �ε� ���
		$(".q-ml-md.w200", 0).click(); //��õ ���ø� ����ϱ� Ŭ��
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//���� �Է�â ���
		$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//�޽��� ���� ��ư ���
		$(".q-field__native.q-placeholder", 0).setValue(date1 + " ��õ ���ø� �޽��� �޴�");
		$(".remove-button", 6).click(); //���� ��ư Ŭ��
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).shouldBe(enabled);
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).click();
		$(".remove-button", 1).click(); //���� ��ư Ŭ��
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).shouldBe(enabled);
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).click();
		$(".q-ml-md.w150", 0).click();	//���� �� �Խ�
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);	//confirm Ȯ�� ��ư ���
		$(".q-ml-sm.min-w100", 0).click();	//confirm Ȯ�� ��ư Ŭ��
		$(".q-notification", 0).should(disappear);	//toast alert ����������� ���
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled); 
		System.out.println("��õ ���ø� �޽��� �޴� ���");
	}
	
	//@Test(priority = 2)
	public void ����Ƽī��24�α����ı���() {
		for(int i=0;i<=200000;i++) {
	        Random generator = new Random();
	        int num = generator.nextInt(17);
	        if(num == 0){
				open("https://dighty.cafe24.com?src=adwords&kw=000002");
	        } else if(num == 1) {
				open("https://dighty.cafe24.com?src=neoclick&kw=00000F");
	        } else if(num == 2) {
				open("https://dighty.cafe24.com?src=naver_ccs&kw=000003");
	        } else if(num == 3) {
				open("https://dighty.cafe24.com?src=naver_ns&kw=000005");
	        } else if(num == 4) {
				open("https://dighty.cafe24.com?src=naver_ll&kw=000006");
	        } else if(num == 5) {
				open("https://dighty.cafe24.com?src=naver_np&kw=000007");
	        } else if(num == 6) {
				open("https://dighty.cafe24.com?src=naver_br&kw=00000C");
	        } else if(num == 7) {
				open("https://dighty.cafe24.com?src=nate_da&kw=000004");
	        } else if(num == 8) {
				open("https://dighty.cafe24.com?src=nate_sl&kw=000008");
	        } else if(num == 9) {
				open("https://dighty.cafe24.com?src=nate_sb&kw=000009");
	        } else if(num == 10) {
				open("https://dighty.cafe24.com?src=dnet_cb&kw=00000A");
	        } else if(num == 11) {
				open("https://dighty.cafe24.com?src=daum_ll&kw=00000B");
	        } else if(num == 12) {
				open("https://dighty.cafe24.com?src=daum_br&kw=00000D");
	        } else if(num == 13) {
				open("https://dighty.cafe24.com?src=wnut_ql&kw=000001");
	        } else if(num == 14) {
				open("https://dighty.cafe24.com?src=wnut_pl&kw=00000E");
	        } else if(num == 15) {
				open("https://dighty.cafe24.com?src=wnut_gl&kw=000010");
	        } else if(num == 16) {
				open("https://dighty.cafe24.com?src=wnut_wl&kw=000011");
	        } else if(num == 17) {
				open("https://dighty.cafe24.com");
	        }
	        
			sleep(1000);
			$(".xans-layout-statelogoff > a", 0).click();
			sleep(500);
			$("#member_id").shouldBe(visible, Duration.ofSeconds(5));
	        int id = generator.nextInt(1999); //999�� 1~1000�� ����
			$("#member_id").setValue("test" + id);
			System.out.println("test" + id + " ���� ����");
			sleep(1000);
			$("#member_passwd").setValue("qordlf!@34");
			sleep(1000);
			$(".btnLogin_bk").click();
			sleep(1000);
			System.out.println("test" + id + " �α��� �Ϸ�");
			//������� �α���
			
	        int page = generator.nextInt(7);		
	        if(page == 0){
	        	open("https://dighty.cafe24.com/product/%ED%8C%A8%EC%85%98-%EC%97%85%EC%A2%85-%EC%9D%B8%EC%82%AC%EC%9D%B4%ED%8A%B8-%EB%A6%AC%ED%8F%AC%ED%8A%B8-%EB%B0%A9%EA%B5%AC%EC%84%9D-%ED%8C%A8%ED%94%BC/12/category/24/display/1/");
	        } else if(page == 1) {
	        	open("https://dighty.cafe24.com/product/%EC%8A%A4%ED%8F%AC%EC%B8%A0%EC%83%9D%ED%99%9C%EC%B2%B4%EC%9C%A1-%EC%9D%B8%EC%82%AC%EC%9D%B4%ED%8A%B8-%EB%A6%AC%ED%8F%AC%ED%8A%B8-%EB%95%80%ED%9D%98%EB%A6%AC%EB%8A%94-%EC%82%AC%EB%9E%8C%EB%93%A4/10/category/24/display/1/");
	        } else if(page == 2) {
	        	open("https://dighty.cafe24.com/product/%EC%B7%A8%EB%AF%B8-%EC%97%85%EC%A2%85-%EC%9D%B8%EC%82%AC%EC%9D%B4%ED%8A%B8-%EB%A6%AC%ED%8F%AC%ED%8A%B8-in-my-freetime/9/category/24/display/1/");
	        } else if(page == 3) {
	        	open("https://dighty.cafe24.com/product/2021%EB%85%84-8%EC%9B%94-%EC%84%A4%EC%B9%98%EC%88%98-%EC%83%81%EC%8A%B9%EC%95%B1-%EC%B0%A8%ED%8A%B8/17/category/25/display/1/");
	        } else if(page == 4) {
	        	open("https://dighty.cafe24.com/product/%EC%B9%B4%EC%B9%B4%EC%98%A4-%EB%84%A4%EC%9D%B4%EB%B2%84-%EC%BD%98%ED%85%90%EC%B8%A0-%ED%94%8C%EB%9E%AB%ED%8F%BC-%EC%95%B1%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%B6%84%EC%84%9D/16/category/25/display/1/");
	        } else if(page == 5) {
	        	open("https://dighty.cafe24.com/product/%EC%B9%B4%EC%B9%B4%EC%98%A4-%EB%84%A4%EC%9D%B4%EB%B2%84-%EC%BD%98%ED%85%90%EC%B8%A0-%ED%94%8C%EB%9E%AB%ED%8F%BC-%EC%95%B1%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%B6%84%EC%84%9D/14/category/25/display/1/");
	        } else if(page == 6) {
	        	open("https://dighty.cafe24.com/product/%EC%A4%91%EA%B3%A0%EA%B1%B0%EB%9E%98-%EC%9D%B4%EC%9A%A9%ED%95%A0-%EB%95%8C-%EC%9D%84-%EC%BC%9C%EB%A9%B4-1020-%EB%A5%BC-%ED%82%A4%EB%A9%B4-3040/13/category/25/display/1/");
	        }
	        //��ǰ �󼼺��� ������ ����
			System.out.println("test" + id + " // " + page + " ��ǰ �� ������ ����");
			//��ǰ ���� ����
			page = generator.nextInt(7);
			sleep(500);
	        if(page == 0){
	        	
	        } else if(page == 1) {
				$(".QuantityUp").click();
				sleep(100);
	        } else if(page == 2) {
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
	        } else if(page == 3) {
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
	        } else if(page == 4) {
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
	        } else if(page == 5) {
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
	        } else if(page == 6) {
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
				$(".QuantityUp").click();
				sleep(100);
	        }
			System.out.println("test" + id + " // " + page + " ��ǰ ���� ����");
	        sleep(500);
			$(".sub_cart").click();
			System.out.println("test" + id + " ��ٱ��� ��� �Ϸ�");
			sleep(500);
			$(".selected").scrollIntoView(true);
			System.out.println("��ٱ��� ������ ��ũ�� �̵�");
			sleep(1000);
			$(".btnSubmitFix").click();
			sleep(1000);
			$(".totalArea", 0).scrollIntoView(true);
			refresh();
			sleep(3500);
			$(".gIndent20").scrollIntoView(true);
			System.out.println("�ֹ����ۼ� ������ ��ũ�� �̵�");
			$("#pname").setValue("test" + id);
			sleep(1500);
			//$("#bankaccount").click();
			//$("#bankaccount").selectOption("�������:873201 �ֿ���");
	        $("#bankaccount").selectOption(1);
			sleep(500);
			$("#bankaccount").selectOption(1);
			sleep(500);
			$("#chk_purchase_agreement0").click();
			sleep(500);
			$("#btn_payment").click();
			sleep(1000);
			System.out.println("test" + id + " ���� �Ϸ�");
			open("https://dighty.cafe24.com/exec/front/Member/logout");
			System.out.println((i+1) + "��° ���� �Ϸ�");
		}		
	}

	
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}