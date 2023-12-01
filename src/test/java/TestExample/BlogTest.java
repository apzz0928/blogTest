package TestExample;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.ScreenShooter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BlogTest {
	private static WebDriver driver;
	private static String egloosUrl, hubUrl;
	private static String TestBrowser;

	Date number_date = new Date();
	SimpleDateFormat number_format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	
	SimpleDateFormat number_format1 = new SimpleDateFormat("yyyyMMddhhmmss");
	String date1 = number_format.format(number_date);
	
	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browser) throws MalformedURLException {
		egloosUrl = "http://apzz0928.egloos.com/";
		//hubUrl = "http://172.18.176.1:5555/wd/hub";
		hubUrl = "http://10.77.129.169:5555/wd/hub";

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
			driver.manage().window().setSize(new Dimension(1650, 1000));
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
	
	@Test(priority = 999)
	public void import�ڻ����() {
		sleep(1000);
	}
	
	//@Test(priority = 2)
	public void ī��24����apzz0928���μ�() {
		int y=0;
		for(int x=0;x<=1000;x++) {
			for(int i=0;i<=1000;i++) {
				Random generator = new Random();
	            int ID = generator.nextInt(1001);
	            int Num = generator.nextInt(1001);
	            int Url = generator.nextInt(1);
	            /*if(Num % 2 == 0){ //��ȸ������ �б� �ּ�ó��                	
	            	if(Url == 0) {
	                    open("https://apzz0928.cafe24.com");	
	            	} else if(Url == 1) {
	                    open("http://shop2.apzz0928.cafe24.com");          		
	            	} else if(Url == 2) {
	                    open("http://shop3.apzz0928.cafe24.com");                		
	            	}
	                Num = generator.nextInt(2); //1�� �����ϸ� ù ��° ���� 0�� ����
	            	if(Url == 0) {
	            		System.out.println("---" + "�Ϲݼ��� " + " ��ȸ�� " + Num + " �� 0�̸� ī�װ� �����ؼ� ����, 1�̸� �˻���� �˻��ؼ� ����");	
	            	} else if(Url == 1) {
	            		System.out.println("---" + "��Ƽ��1�� " + " ��ȸ�� " + Num + " �� 0�̸� ī�װ� �����ؼ� ����, 1�̸� �˻���� �˻��ؼ� ����");
	            	} else if(Url == 2) {
	            		System.out.println("---" + "��Ƽ��2�� " + " ��ȸ�� " + Num + " �� 0�̸� ī�װ� �����ؼ� ����, 1�̸� �˻���� �˻��ؼ� ����");
	            	}
	               if(Num == 0) { //ī�װ� �����ؼ� ����
	                    Num = generator.nextInt(3);
	                    //ī�װ� Ŭ���ؼ� ��ǰ ���� ������ ����
	                    if(Url == 0) { //�Ϲݼ��� ��Ƽ���� �޴� ��ũ�� �޶� �б�
	                        $(".link", Num+14).click();                        	
	                    } else {
	                        $(".link", Num+18).click();
	                    }
	                    $(".xans-product-displaysubcategory").waitUntil(visible, 10000);
	                    $(".xans-product-displaysubcategory", Num).click(); //�ߺз� ����
	                    $(".prdImg", 2).waitUntil(visible, 10000);
	                    $(".prdImg", Num).click(); //���� ��ǰ ����
	                    Num = generator.nextInt(2);
	                    if(Num == 0) {
	                        //�ֹ�/���� ���������� �ֹ�/���� �Ϸ�
	                    	$(".btnBuy", 1).waitUntil(visible, 10000);
	                    	$(".btnBuy", 1).click();
	                    	$(".theme1", 1).waitUntil(visible, 10000);
	                    	$(".theme1", 1).click();
	            			sleep(2500);
	                    	$("#rname").waitUntil(visible, 10000);
	                    	$("#rname").setValue("��ȸ�� ���� ID " + ID); //�޴»��
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("������ 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("��ȸ�� ���� ������ �ּ� " + ID); //������ �ּ�
	            			$("#rphone2_2").setValue("0000");
	            			if(ID > -1 && ID < 10) {
	                			$("#rphone2_3").setValue("000" + ID);
	            			} else if(ID > 9 && ID < 100) {
	                			$("#rphone2_3").setValue("00" + ID);
	            			} else if(ID > 99 && ID < 1000) {
	                			$("#rphone2_3").setValue("0" + ID);
	            			} else {
	            				$("#rphone2_3").setValue("" + ID);
	            			}
	            			$("#oemail1").setValue("testmail" + ID);
	            			$("#oemail2").setValue("mail.com");
	                    	$("#order_password").setValue("qordlf!@34");
	                    	$("#order_password_confirm").setValue("qordlf!@34");
	                    	$("#btn_payment").scrollIntoView(false);
	                    	sleep(1000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("��ȸ�� ���� �Ա��ڸ� " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("��ȸ�� ī�װ� �����ؼ� �Ϲ� ���� ---");
	                        sleep(2000);
	                    } else if (Num == 1) { 
	                    	//��ٱ��� �߰� �� ���ſϷ�
	                    	$(".actionCart", 1).waitUntil(visible, 10000);
	                    	$(".actionCart", 1).click();
	                    	sleep(1500);
	                    	$(".theme2", 5).waitUntil(visible, 10000);
	                    	$(".theme2", 5).click();
	                    	sleep(1500);
	                    	$(".theme1", 1).waitUntil(visible, 10000);
	                    	$(".theme1", 1).click();
	            			sleep(2500);
	                    	$("#rname").waitUntil(visible, 10000);
	                    	$("#rname").setValue("��ȸ�� ���� ID " + ID); //�޴»��
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("������ 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("��ȸ�� ���� ������ �ּ� " + ID); //������ �ּ�
	            			$("#rphone2_2").setValue("0000");
	            			if(ID > -1 && ID < 10) {
	                			$("#rphone2_3").setValue("000" + ID);
	            			} else if(ID > 9 && ID < 100) {
	                			$("#rphone2_3").setValue("00" + ID);
	            			} else if(ID > 99 && ID < 1000) {
	                			$("#rphone2_3").setValue("0" + ID);
	            			} else {
	            				$("#rphone2_3").setValue("" + ID);
	            			}
	            			$("#oemail1").setValue("testmail" + ID);
	            			$("#oemail2").setValue("mail.com");
	                    	$("#order_password").setValue("qordlf!@34");
	                    	$("#order_password_confirm").setValue("qordlf!@34");
	                    	$("#btn_payment").scrollIntoView(false);
	                    	sleep(1000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("��ȸ�� ���� �Ա��ڸ� " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("��ȸ�� ī�װ� �����ؼ� ��ٱ��� ���� ---");
	                        sleep(2000);
	                    }
	                } else if(Num == 1) { //�˻���� �˻��ؼ� ����
	                    $(".btnSearch", 1).click();
	                    sleep(2000);
	                    Num = generator.nextInt(2);
	                    if(Num == 1) {
	                        $("input", 3).setValue("�Ƶ�ٽ�");
	                        System.out.println("�˻��� : �Ƶ�ٽ�");
	                    } else if (Num == 0) {
	                        $("input", 3).setValue("����Ű");
	                        System.out.println("�˻��� : ����Ű");
	                    }
	                    $(".btnSearch", 2).click();
	                    Num = generator.nextInt(11);
	                    $("img", Num+3).click();; //��ǰ Ŭ��
	                    System.out.println(Num + "��° ��ǰ Ŭ��");
	                    Num = generator.nextInt(2);
	                    if(Num == 0) {
	                        //�ֹ�/���� ���������� �ֹ�/���� �Ϸ�
	                    	$(".btnBuy", 1).waitUntil(visible, 10000);
	                    	$(".btnBuy", 1).click();
	                    	$(".theme1", 1).waitUntil(visible, 10000);
	                    	$(".theme1", 1).click();
	            			sleep(2500);
	                    	$("#rname").waitUntil(visible, 10000);
	                    	$("#rname").setValue("��ȸ�� ���� ID " + ID); //�޴»��
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("������ 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("��ȸ�� ���� ������ �ּ� " + ID); //������ �ּ�
	            			$("#rphone2_2").setValue("0000");
	            			if(ID > -1 && ID < 10) {
	                			$("#rphone2_3").setValue("000" + ID);
	            			} else if(ID > 9 && ID < 100) {
	                			$("#rphone2_3").setValue("00" + ID);
	            			} else if(ID > 99 && ID < 1000) {
	                			$("#rphone2_3").setValue("0" + ID);
	            			} else {
	            				$("#rphone2_3").setValue("" + ID);
	            			}
	            			$("#oemail1").setValue("testmail" + ID);
	            			$("#oemail2").setValue("mail.com");
	                    	$("#order_password").setValue("qordlf!@34");
	                    	$("#order_password_confirm").setValue("qordlf!@34");
	                    	$("#btn_payment").scrollIntoView(false);
	                    	sleep(1000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("��ȸ�� ���� �Ա��ڸ� " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("��ȸ�� ��ǰ�� �˻��ؼ� �Ϲ� ���� ---");
	                        sleep(2000);
	                    } else if (Num == 1) { 
	                    	//��ٱ��� �߰� �� ���ſϷ�
	                    	$(".actionCart", 1).waitUntil(visible, 10000);
	                    	$(".actionCart", 1).click();
	                    	sleep(1500);
	                    	$(".theme2", 5).waitUntil(visible, 10000);
	                    	$(".theme2", 5).click();
	                    	sleep(1500);
	                    	$(".theme1", 1).waitUntil(visible, 10000);
	                    	$(".theme1", 1).click();
	            			sleep(2500);
	                    	$("#rname").waitUntil(visible, 10000);
	                    	$("#rname").setValue("��ȸ�� ���� ID " + ID); //�޴»��
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("������ 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("��ȸ�� ���� ������ �ּ� " + ID); //������ �ּ�
	            			$("#rphone2_2").setValue("0000");
	            			if(ID > -1 && ID < 10) {
	                			$("#rphone2_3").setValue("000" + ID);
	            			} else if(ID > 9 && ID < 100) {
	                			$("#rphone2_3").setValue("00" + ID);
	            			} else if(ID > 99 && ID < 1000) {
	                			$("#rphone2_3").setValue("0" + ID);
	            			} else {
	            				$("#rphone2_3").setValue("" + ID);
	            			}
	            			$("#oemail1").setValue("testmail" + ID);
	            			$("#oemail2").setValue("mail.com");
	                    	$("#order_password").setValue("qordlf!@34");
	                    	$("#order_password_confirm").setValue("qordlf!@34");
	                    	$("#btn_payment").scrollIntoView(false);
	                    	sleep(1000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("��ȸ�� ���� �Ա��ڸ� " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("��ȸ�� ��ǰ�� �˻��ؼ� ��ٱ��� ���� ---");
	                        sleep(2000);
	                    }
	                }
	            }  else {*/
	            	if(Url == 0) {
	                    open("http://apzz0928.cafe24.com/member/login.html");	
	            	} else if(Url == 1) {
	                    open("http://shop2.apzz0928.cafe24.com/member/login.html");          		
	            	} else if(Url == 2) {
	                    open("http://shop3.apzz0928.cafe24.com/member/login.html");                		
	            	}
	                $(".btnLogin").waitUntil(visible, 10000);
	                $("#member_id").setValue("test" + ID);
	                $("#member_passwd").setValue("qordlf!@34");
	                $(".btnLogin").scrollIntoView(false);
	                $(".btnLogin").click();
	                System.out.println("test" + ID + " �α���");
	                sleep(2000);
	                Num = generator.nextInt(2); //1�� �����ϸ� ù ��° ���� 0�� ����
	                System.out.println("--- Num_" + Num + " �� 0�̸� ī�װ� �����ؼ� ����, 1�̸� �˻���� �˻��ؼ� ����, 2�̸� �Խ��ǿ� �۾���");
	                if(Num == 0) { //ī�װ� �����ؼ� ����
	                    Num = generator.nextInt(3);
	                    //ī�װ� Ŭ���ؼ� ��ǰ ���� ������ ����
	                    if(Url == 0) { //�Ϲݼ��� ��Ƽ���� �޴� ��ũ�� �޶� �б�
	                        $(".link", Num+14).click();                        	
	                    } else {
	                        $(".link", Num+18).click();
	                    }
	                    $(".xans-product-displaysubcategory").waitUntil(visible, 10000);
	                    $(".xans-product-displaysubcategory", Num).click();
	                    $(".prdImg", 2).waitUntil(visible, 10000);
	                    $(".prdImg", Num).click();
	                    Num = generator.nextInt(2);
	                    if(Num == 0) {
	                        //�ֹ�/���� ���������� �ֹ�/���� �Ϸ�
	                        $(".btnBuy", 1).waitUntil(visible, 10000);
	                        $(".btnBuy", 1).click();
	                        sleep(2000);
	                        //$("#btn_payment").waitUntil(visible, 10000);
	                        $("#bankaccount").scrollIntoView(true);
	                        sleep(2000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("test" + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("ȸ�� ī�װ� �����ؼ� �Ϲ� ���� ---");
	                        sleep(2000);	
	                    } else if (Num == 1) { //��ٱ��� �߰� �� ���ſϷ�
	                        $(".actionCart").waitUntil(visible, 10000);
	                        $(".actionCart", 1).click();
	                        sleep(2500);
	                        $(".theme2", 1).waitUntil(visible, 10000);
	                        $(".theme2", 1).click();
	                        sleep(2000);
	                        //$("#btn_payment").waitUntil(visible, 10000);						
	                        $("#bankaccount").scrollIntoView(true);
	                        sleep(2000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("test" + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("ȸ�� ī�װ� �����ؼ� ��ٱ��� ���� ---");
	                        sleep(2000);	
	                    }
	                } else if(Num == 1) {//�˻���� �˻��ؼ� ����
	                    $(".btnSearch", 1).click();
	                    sleep(2000);
	                    Num = generator.nextInt(2);
	                    if(Num == 1) {
	                        $("input", 3).setValue("�Ƶ�ٽ�");
	                        System.out.println("�˻��� : �Ƶ�ٽ�");
	                    } else if (Num == 0) {
	                        $("input", 3).setValue("����Ű");
	                        System.out.println("�˻��� : ����Ű");
	                    }
	                    $(".btnSearch", 2).click();
	                    Num = generator.nextInt(11);
	                    $("img", Num+3).click();;
	                    System.out.println(Num + "��° ��ǰ Ŭ��");
	                    Num = generator.nextInt(2);
	                    if(Num == 0) { //�ֹ�/���� ���������� �ֹ�/���� �Ϸ�
	                        $(".btnBuy", 1).waitUntil(visible, 10000);
	                        $(".btnBuy", 1).click();
	                        sleep(2000);
	                        //$("#btn_payment").waitUntil(visible, 10000);
	                        $("#bankaccount").scrollIntoView(true);
	                        sleep(2000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("test" + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("ȸ�� ��ǰ�� �˻��ؼ� �Ϲ� ���� ---");
	                        sleep(2000);
	                    } else if (Num == 1) { //��ٱ��� �߰� �� ���ſϷ�
	                        $(".actionCart").waitUntil(visible, 10000);
	                        $(".actionCart", 1).click();
	                        sleep(2500);
	                        $(".theme2", 1).waitUntil(visible, 10000);
	                        $(".theme2", 1).click();
	                        sleep(2000);
	                        //$("#btn_payment").waitUntil(visible, 10000);
	                        $("#bankaccount").scrollIntoView(true);
	                        sleep(2000);
	                        $("#bankaccount").selectOption(1);
	                        $("#pname").setValue("test" + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("ȸ�� ��ǰ�� �˻��ؼ� ��ٱ��� ���� ---");
	                        sleep(2000);	
	                    }
	                    sleep(2000);
	                } else if(Num == 2) {//�Խ��ǿ� �۾���
	                    $(".btnCate").click();
	                    $(".link", 8).waitUntil(visible, 10000);
	                    Num = generator.nextInt(3);
	                    if(Num == 0) {//��ǰ ����ı� 
	                        $(".link", 6).waitUntil(visible, 10000);
	                        $(".link", 6).click();
	                        sleep(1500);
	                        //$(".button", 2).waitUntil(visible, 10000);
	                        $(".button", 2).click();
	                        $("#subject").setValue("test" + ID);
	                        switchTo().frame("content_IFRAME"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
	                        $("#content_BODY").setValue("test" + ID);
	                        switchTo().defaultContent();
	                        $(".btnSubmit", 0).scrollIntoView(false);
	                        $(".btnSubmit", 0).click();
	                        sleep(1500);
	                        System.out.println("��ǰ ����ı� �Խ��ǿ� �۾��� ---");
	                    } else if(Num == 1) {//��ǰ Q&A
	                        $(".link", 7).waitUntil(visible, 10000);
	                        $(".link", 7).click();				
	                        sleep(1500);		
	                        //$(".button", 2).waitUntil(visible, 10000);
	                        $(".button", 2).click();
	                        $("#subject").setValue("test" + ID);
	                        switchTo().frame("content_IFRAME"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
	                        $("#content_BODY").setValue("test" + ID);
	                        switchTo().defaultContent();
	                        $(".btnSubmit", 0).scrollIntoView(false);
	                        $(".btnSubmit", 0).click();
	                        sleep(1500);
	                        System.out.println("��ǰ Q&A �Խ��ǿ� �۾��� ---");
	                    } else if(Num == 2) {//�����Խ���
	                        $(".link", 8).waitUntil(visible, 10000);
	                        $(".link", 8).click();
	                        sleep(2000);
	                        //$(".btnStrong", 0).waitUntil(visible, 10000);
	                        $(".btnStrong", 0).click();
	                        $("#subject").setValue("test" + ID);
	                        switchTo().frame("content_IFRAME"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
	                        $("#content_BODY").setValue("test" + ID);
	                        switchTo().defaultContent();
	                        $(".btnSubmit", 0).scrollIntoView(false);
	                        $(".btnSubmit", 0).click();
	                        sleep(1500);
	                        System.out.println("�����Խ��ǿ� �۾��� ---");
	                    }
	                } else {
	                    System.out.println("--- �α��� �� �������� ���� ---");
	                    break;
	                }
	            //}
	            sleep(1500);
	        	if(Url == 0) {
	                open("https://apzz0928.cafe24.com/exec/front/Member/logout");	
	        	} else if(Url == 1) {
	                open("http://shop2.apzz0928.cafe24.com/exec/front/Member/logout");          		
	        	} else if(Url == 2) {
	                open("http://shop3.apzz0928.cafe24.com/exec/front/Member/logout");                		
	        	}
	            y = y+1;
	            if(Url == 0) { //�Ϲݼ��� ��Ƽ���� �޴� ��ũ�� �޶� �б�
		            System.out.println("--- �Ϲݼ����� " + y + "�� ���� ������ ���� ---");                      	
	            } else if(Url == 1) {
		            System.out.println("--- ��Ƽ��1�� " + y + "�� ���� ������ ���� ---");
	            } else if(Url == 2) {
		            System.out.println("--- ��Ƽ��2�� " + y + "�� ���� ������ ���� ---");
	            }
	            sleep(1000);
	        }
	    }
	}

	//@Test(priority = 0)
	public void ����Ƽī��24ȸ�����Թװ˻��ı���() {
		for(int i=656;i<=675;i++) {
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
			sleep(1500);
			$(".xans-layout-statelogoff > a", 2).click();
			sleep(1500);
			$("p > label", 1).click();
			sleep(1500);
			$("#member_id").waitUntil(visible, 10000);
			$("#member_id").setValue("test" + i);
			sleep(1500);
			$("#passwd").setValue("qordlf!@34");
			sleep(1500);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(1500);
			$("#name").setValue("test" + i + "�� �̸�");
			sleep(1500);
			$("#mobile2").setValue("0000");
			sleep(1500);
			$("#mobile3").setValue("0" + i + "");
			sleep(1500);
			$("#email1").setValue("test" + i + "@mail.com");
			sleep(1500);
			$(".btnSubmitFix").scrollIntoView(false);
			sleep(1500);
			$(".btnSubmitFix").click();
			sleep(1500);
			$(".btnLogin_bk").click();
            System.out.println("test" + i + " ���� ȸ������ �Ϸ�");
			sleep(1500);
			open("https://dighty.cafe24.com/member/modify.html");
			$("#member_id").waitUntil(visible, 10000);
			$("#passwd").setValue("qordlf!@34");
			sleep(1500);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(1500);
			$("#postBtn").click();
			sleep(1500);
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
			$("#zboo_keyword").waitUntil(visible, 10000);
			$("#zboo_keyword").setValue("������ 408-3");
			sleep(1500);
			$("#zboo_search_btn").click();
			sleep(1500);
			$(".streetLine").waitUntil(visible, 10000);
			$(".streetLine").click();
			switchTo().defaultContent();
			sleep(1500);
			$("#addr2").setValue("test" + i);
			sleep(1500);
			$("#phone2").setValue("0000");
			sleep(1500);
			$("#phone3").setValue("0" + i);
			$(".btnSubmitFix", 0).scrollIntoView(true);
			//$("#ma_main_flag0").click();
			sleep(1500);
			$(".btnSubmitFix", 0).click();
			sleep(1500);
			confirm("ȸ������ ������ �Ϸ�Ǿ����ϴ�.");
			System.out.println("test" + i + " ����� ���� �Է�");
			sleep(1500);
			$("#icon_search").click();
			sleep(1500);
			int search = generator.nextInt(3);
			if(search == 0){
				$("#keyword").setValue("�λ���Ʈ");
				
            } else if(search == 1) {
    			$("#keyword").setValue("��");
            } else if(search == 2) {
    			$("#keyword").setValue("�߰�ŷ�");
            }
			sleep(1500);
			$(".search_btn").click();
			sleep(1500);
			if(search == 2) {
				$(".grid3").scrollIntoView(true);
				$(".grid3 > li", 0).click();
			} else {
				$(".grid3").scrollIntoView(true);
				$(".grid3 > li", search).click();				
			}
            int page = generator.nextInt(7);
            System.out.println(search + " ��ǰ ������ ����");
            sleep(1500);
            if(page == 0){
            	
            } else if(page == 1) {
    			$(".QuantityUp").click();
    			sleep(1000);
            } else if(page == 2) {
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
            } else if(page == 3) {
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
            } else if(page == 4) {
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
            } else if(page == 5) {
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
            } else if(page == 6) {
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
    			$(".QuantityUp").click();
    			sleep(1000);
            }
            //��ǰ ���� ����
			sleep(1500);
			$(".sub_cart").click();
			sleep(1500);
			$(".btnSubmitFix").scrollIntoView(false);
			sleep(1000);
			$(".btnSubmitFix").click();
			sleep(1000);
			$("#pname").waitUntil(visible, 10000);
			$("#pname").scrollIntoView(true);
			$("#pname").setValue("test" + i);
			sleep(1000);
			$("#bankaccount").scrollIntoView(false);
			$("#bankaccount").selectOption("�������:873201 �ֿ���"); 
			sleep(1000);
			$("#chk_purchase_agreement0").click();
			sleep(1000);
			$("#btn_payment").click();
			sleep(1000);
			System.out.println("test" + i + " ���� �Ϸ�");
			open("https://dighty.cafe24.com/exec/front/Member/logout");
			sleep(1000);
		}		
	}
	
	//@Test(priority = 0)
	public void ��ī_��Ʈ_��ǰŸ�Թٲٱ�() {
		open("https://acecounter.com/Thrkfl/");
		$("input", 0).setValue("apzz0928");
		$("input", 2).setValue("qordlf12#$");
		$("input", 1).click();
		open("https://acecounter.com/Thrkfl/mobile_me/index.amz?device=&new_name=&sort_val=&search_type=name&search_msg=MUPAD");		
		sleep(1000);
		for(int x=1;x<=10;x++) {
			for(int i=0;i<=16;i++) {
				$("#modify" + i).click();
				sleep(500);
				$("#brand_" + i).selectOption("iMUZ");
				sleep(500);
				$("#device_type_" + i).selectOption("Tablet PC");
				sleep(500);
				$("#wifi_" + i).selectOption("������");
				sleep(500);
				$("#touch_" + i).selectOption("������");
				sleep(500);
				$("#insert" + i).click();
				sleep(500);
				confirm("�����Ͻðڽ��ϱ�?");
				sleep(1500);
				System.out.println(i + " ��° �� ����");				
			}
			//open("https://acecounter.com/Thrkfl/mobile_me/index.amz?page=" + x + "&search_type=name&search_msg=%EB%85%B8%ED%8A%B8&sort_val=");
			//System.out.println(x + " ��° ������ ��ǰ Ÿ�� ����");
			//sleep(1000);
		}		
	}
	
	//@Test(priority = 0)
	public void AC�����̺�Ʈ�����߰�() {
		open("https://www.acecounter.com/login_pt.html?aWQ9%7B%7C%7D5ilon%7B%82%7C%83u%83%40v%83%82ZGlnaHR5X2MyNCZwdz03NzJiNTQyZjU1NTI1MDM5Njg0ZjZhNTA1MTc0NzY2ZjYzNDYzNjQ0NmMzNTY0NzM3NDZjNTEzZCZtb2RlPWxvZ2luJmNvbm5fZGVzdD1hZG1pbiZkYXRlbGltaXQ9NVlXUnRhVzQlMjUzRA%3D%3D");
		//open("https://www.acecounter.com/login_pt.html?aWQ9%7B%7C%7D5ilon%7B%82%7C%83u%83%40v%83%82YnRlbXBlc3QyX2MyNCZwdz03NzJiNTQyZjU1NTI1MDM5Njg0ZjZhNTA1MTc0NzY2ZjYzNDYzNjQ0NmMzNTY0NzM3NDZjNTEzZCZtb2RlPWxvZ2luJmNvbm5fZGVzdD1hZG1pbiZkYXRlbGltaXQ9NVlXUnRhVzQlMjUzRA%3D%3D");
		sleep(2000);
		open("https://qa.acecounter.com/stat/view/manager5_4_1.amz");		
		for(int i=45;i<=100;i++) {
			sleep(2000);
			$("#promo_name").click();
			System.out.println("�̺�Ʈ �׷�� �Է�â Ŭ��1");
			sleep(2000);
			$("#promo_name").setValue("");
			sleep(1000);
			$("#promo_name").setValue("test" + i);
			$("#banner_name_1").setValue("test" + i);
			System.out.println("�Է°� �Է�");
			sleep(2000);
			$("#promo_name").click();
			System.out.println("�̺�Ʈ �׷�� �Է�â Ŭ��2");
			/*
			sleep(10000);
			$("#promo_name").setValue("");
			sleep(1000);
			$("#promo_name").setValue("test" + i);
			System.out.println("�̺�Ʈ �׷�� �Է�");
			$("#promo_name").click();
			System.out.println("�Է�â Ŭ��2");
			*/
			$(".btn_responsive", 2).click();
			System.out.println("��Ϲ�ư Ŭ��1");
			sleep(1000);
			confirm("�ߺ��� �̺�Ʈ �׷���Դϴ�.\n" + "�̺�Ʈ �׷���� �ٽ� �Է��ϼ���.");			
			sleep(12000);
			System.out.println($("#over_chk").text().trim());
			$(".btn_responsive", 2).click();
			System.out.println("��Ϲ�ư Ŭ��2");
			sleep(14000);
			confirm("�̺�Ʈ �׷쿡 1���� ���� �̺�Ʈ�� ��ϵǾ����ϴ�.");
			System.out.println("alert Ȯ�� ó��");
			sleep(2000);
			open("https://qa.acecounter.com/stat/view/manager5_4_1.amz");
			sleep(2000);
		}
	}
	
	//@Test(priority = 0)
	public void ����Ƽī��24ȸ������() {
		for(int i=1761;i<=1770;i++) {
			open("https://dighty.cafe24.com/member/join.html");
			sleep(1000);
			$("p > label", 1).click();
			sleep(1000);
			$("#member_id").waitUntil(visible, 10000);
			$("#member_id").setValue("test" + i);
			sleep(1000);
			$("#passwd").setValue("qordlf!@34");
			sleep(1000);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(1000);
			$("#name").setValue("test" + i + "�� �̸�");
			sleep(1000);
			$("#mobile2").setValue("0000");
			sleep(1000);
			$("#mobile3").setValue("0" + i + "");
			sleep(1000);
			$("#email1").setValue("test" + i + "@mail.com");
			sleep(1000);
			$(".btnSubmitFix").scrollIntoView(false);
			sleep(1000);
			$(".btnSubmitFix").click();
			sleep(1000);
			open("https://dighty.cafe24.com/exec/front/Member/logout");
			sleep(1000);
			System.out.println("test" + i + " ���� �Ϸ�");
			sleep(1000);
		}		
	}

	//@Test(priority = 1)
	public void ����Ƽī��24��������() {
		for(int i=0;i<=9;i++) {
			open("https://dighty.cafe24.com/member/login.html");
			sleep(1000);
			$("#member_id").setValue("test" + i);
			sleep(1000);
			$("#member_passwd").setValue("qordlf!@34");
			$(".btnLogin_bk").scrollIntoView(false);
			sleep(1000);
			$(".btnLogin_bk").click();
			sleep(1500);
			open("https://dighty.cafe24.com/member/modify.html");
			$("#member_id").waitUntil(visible, 10000);
			$("#passwd").setValue("qordlf!@34");
			sleep(1000);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(1000);
			$("#postBtn").click();
			sleep(1000);
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
			$("#zboo_keyword").waitUntil(visible, 10000);
			$("#zboo_keyword").setValue("123");
			sleep(1000);
			$("#zboo_search_btn").click();
			sleep(1000);
			$(".streetLine", 0).waitUntil(visible, 10000);
			$(".streetLine", 0).click();
			switchTo().defaultContent();
			sleep(1000);
			$("#addr2").setValue("test" + i);
			sleep(1000);
			$("#phone2").setValue("0000");
			sleep(1000);
			$("#phone3").setValue("" + i);
			sleep(1000);
			$("#mobile2").setValue("0000");
			sleep(1000);
			$("#mobile3").setValue("" + i);
			$(".btnSubmitFix", 0).scrollIntoView(true);
			sleep(1000);
			$(".btnSubmitFix", 0).click();
			sleep(1000);
			confirm("ȸ������ ������ �Ϸ�Ǿ����ϴ�.");
			System.out.println("test" + i + " ����� ���� �Է�");
			//�α׾ƿ�
			open("https://dighty.cafe24.com/exec/front/Member/logout");
			sleep(1000);
		}		
	}

	//@Test(priority = 2)
	public void ����Ƽī��24�α����ı���() {
		for(int i=0;i<=10000;i++) {
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
            
			sleep(500);
			$(".xans-layout-statelogoff > a", 0).click();
			sleep(500);
			$("#member_id").waitUntil(visible, 5000);
            int id = generator.nextInt(1999); //999�� 1~1000�� ����
			$("#member_id").setValue("test" + id);
			System.out.println("test" + id + " ���� ����");
			sleep(500);
			$("#member_passwd").setValue("qordlf!@34");
			sleep(500);
			$(".btnLogin_bk").click();
			sleep(500);
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
			//$(".totalArea", 0).scrollIntoView(true);
			refresh();
			sleep(2000);
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
	
	//@Test(priority = 0)
	public void vklog�׽�Ʈ��ȸ������Ȯ��() {
		for(int i=0;i<=9;i++) {
			open("http://vklog.loginside.co.kr/member/join.php");
			open("http://naver.com");
			$("#memId").waitUntil(visible, 2000);
			$("#memId").val("qwer" + i);
			$("#newPassword").val("qordlf12");
			$(".check-id").val("qordlf12");
			$("input[name=memNm]").val("rewq" + i + " �̸�");
			$("input[name=email]").val("rewq" + i + "@mail.com");
			$("input[name=cellPhone1]").val("010");
			$("input[name=cellPhone2]").val("0000");
			$("input[name=cellPhone3]").val("0000");
			$("input[name=birth1]").val("2023");
			$("input[name=birth2]").val("01");
			$("input[name=birth3]").val("01");
			$("#age").val("00");
			sleep(1000);
			$("label", 0).click();
			$("label", 2).click();
			$("label", 4).click();
			$("label", 6).click();
			$("label", 8).click();
			sleep(1000);
			$(".btn-join").click();
			$("#btnLogin").waitUntil(visible, 2000);
			$("#btnLogin").click();
			$("#loginId").waitUntil(visible, 2000);
			$("#loginId").val("qwer" + i);
			$("#loginPwd").val("qordlf12");
			$(".l-login").click();
			sleep(2500);
			//open("http://vklog.loginside.co.kr/member/login_out.php");
			//confirm("�α׾ƿ� �Ϸ�");
			//sleep(1000);			
			System.out.println("qwer" + i + " �� ���� �Ϸ�");
		}		
	}
	//@Test(priority = 0)
	public void vklog�׽�Ʈ��ȸ��Ż��Ȯ��() {
		for(int i=0;i<=497;i++) {
			open("http://vklog.loginside.co.kr/member/login.php");
			$("#loginId").waitUntil(visible, 2000);
			$("#loginId").val("qwer" + i);
			$("#loginPwd").val("qordlf12");
			$(".l-login").click();
			sleep(2500);		
			$(".container > ul > li", 2).click();
			sleep(1000);
			System.out.println("qwer" + i + " �� Ż�� �Ϸ�");
		}		
	}
	@Test(priority = 0)
	public void NA��ũ��Ʈ��ġȮ��() {
		//URL�� NAkey ������ ���� �迭 ũ�� ����
		String[] URL = new String[182];
		URL[0] = "https://www.mudsanghoe.com";
		URL[1] = "https://www.jpm-pet.co.kr";
		URL[2] = "https://bongbongsm.com";
		URL[3] = "http://www.hanyhome.com";
		URL[4] = "http://www.sinmama.co.kr";
		URL[5] = "http://www.samhyeon.com/m";
		URL[6] = "http://wishartshop.com";
		URL[7] = "https://troistouch.com";
		URL[8] = "http://m.sookdoc.com";
		URL[9] = "https://laalegria.co.kr";
		URL[10] = "http://m.mygolfball.co.kr";
		URL[11] = "http://printerzone.kr/";
		URL[12] = "http://www.popmart.co.kr";
		URL[13] = "http://www.ninanomarket.com";
		URL[14] = "https://lenicgolf.com";
		URL[15] = "http://stackandfold.com";
		URL[16] = "http://stackfold.kr";
		URL[17] = "http://www.mygolfball.co.kr";
		URL[18] = "http://www.hongsamedical.com";
		URL[19] = "https://mifmall.com";
		URL[20] = "http://beanpro.co.kr";
		URL[21] = "http://pibupibu.co.kr";
		URL[22] = "http://����24.kr";
		URL[23] = "http://����24.site";
		URL[24] = "http://����24.shop";
		URL[25] = "http://smart-rentalmall.com";
		URL[26] = "http://erounmart.kr";
		URL[27] = "http://www.gayachess.co.kr";
		URL[28] = "https://bodynpick.com";
		URL[29] = "http://www.wonderwalk.co.kr";
		URL[30] = "https://dtailpuppy.com";
		URL[31] = "http://www.bingsudang.com";
		URL[32] = "https://kikn.co.kr";
		URL[33] = "http://www.isece.com";
		URL[34] = "https://juliannedaisy.com";
		URL[35] = "http://www.instagram.com/volv_studio/";
		URL[36] = "http://www.meongsimhwan.co.kr";
		URL[37] = "https://mong23.kr";
		URL[38] = "http://www.kcl.re.kr";
		URL[39] = "https://����̳ױ����ġ.com";
		URL[40] = "http://printerzone.kr";
		URL[41] = "https://ehfactory.co.kr";
		URL[42] = "http://fudams.com";
		URL[43] = "http://����24.store";
		URL[44] = "http://www.����24.site";
		URL[45] = "http://www.ytkor.com";
		URL[46] = "https://���ʲ��ǴپȰ�.kr";
		URL[47] = "http://m.mojelly.com";
		URL[48] = "https://www.18ober.com";
		URL[49] = "https://cellchic.co.kr";
		URL[50] = "http://istmall.co.kr";
		URL[51] = "http://mytoyshop.co.kr";
		URL[52] = "http://coaroo.co.kr";
		URL[53] = "https://prootskin.kr";
		URL[54] = "http://ddm153.co.kr";
		URL[55] = "http://m.ddm153.co.kr";
		URL[56] = "http://duducase.com";
		URL[57] = "http://www.masshop.co.kr";
		URL[58] = "https://beigemute.com";
		URL[59] = "http://a7korea.com";
		URL[60] = "https://lazysociety.co.kr";
		URL[61] = "http://www.����24.shop";
		URL[62] = "http://m.bellki.com";
		URL[63] = "http://daemyeong.co.kr";
		URL[64] = "https://www.deepsecret.co.kr";
		URL[65] = "http://www.dabomcns.net";
		URL[66] = "https://korea.tiandy.com";
		URL[67] = "http://balmainhaircouture.co.kr";
		URL[68] = "https://nutripang.com";
		URL[69] = "http://seoyneoy.co.kr";
		URL[70] = "http://m.moat.kr";
		URL[71] = "https://neatfood.shop";
		URL[72] = "http://www.bns79.com";
		URL[73] = "http://daebakcoffee.co.kr";
		URL[74] = "https://gemmarina.co.kr";
		URL[75] = "http://www.noclaim.co.kr";
		URL[76] = "http://uncover.co.kr";
		URL[77] = "http://uncover.co.kr";
		URL[78] = "http://www.hookss.co.kr";
		URL[79] = "http://www.buddle.kr";
		URL[80] = "http://www.moat.kr";
		URL[81] = "http://www.����24.store";
		URL[82] = "https://eoskorea.co.kr";
		URL[83] = "http://daebakcoffee.co.kr";
		URL[84] = "http://vialmiracle.co.kr";
		URL[85] = "http://eoskorea.co.kr";
		URL[86] = "http://���Ǫ��.com";
		URL[87] = "http://����Į����.com";
		URL[88] = "http://www.����24.kr";
		URL[89] = "https://www.heary.kr";
		URL[90] = "http://www.daolinspace.com";
		URL[91] = "https://shopping.naver.com/window/style/store/101983622";
		URL[92] = "http://www.lemate.co.kr";
		URL[93] = "http://lemate.co.kr";
		URL[94] = "https://elisha20.cafe24.com";
		URL[95] = "https://gomcoffee.com";
		URL[96] = "http://sookdoc.com";
		URL[97] = "http://www.dr20project.com";
		URL[98] = "http://m.drritz.co.kr";
		URL[99] = "http://blankby.kr";
		URL[100] = "https://booking.naver.com/booking/5/bizes/368458";
		URL[101] = "https://laalegria.co.kr";
		URL[102] = "http://ncnnt.com";
		URL[103] = "https://mandugongbang.com";
		URL[104] = "http://daldaldome.com";
		URL[105] = "https://en.tiandy.com";
		URL[106] = "https://m.studio-mauve.kr";
		URL[107] = "http://m.gaebobjangsu.com";
		URL[108] = "http://www.popbeadsdome.com";
		URL[109] = "http://gym80.co.kr";
		URL[110] = "http://www.fudams.com";
		URL[111] = "http://for103.com";
		URL[112] = "http://pop-beads.com";
		URL[113] = "https://m.ncnnt.com";
		URL[114] = "http://brainmindclinic.com";
		URL[115] = "https://sobangjae.co.kr";
		URL[116] = "http://rluvwoo.com";
		URL[117] = "http://www.pop-beads.com/m/";
		URL[118] = "https://ukay.kr";
		URL[119] = "https://studio-mauve.kr";
		URL[120] = "http://���.com";
		URL[121] = "http://www.bck.co.kr";
		URL[122] = "https://hansung09.toolpark.kr";
		URL[123] = "http://truepick.co.kr";
		URL[124] = "http://culebox.co.kr";
		URL[125] = "http://www.gaebobjangsu.com";
		URL[126] = "http://xiom.store";
		URL[127] = "https://mandugongbang.com";
		URL[128] = "http://m.elsom.shop";
		URL[129] = "http://hellobulk.co.kr";
		URL[130] = "http://elsom.shop";
		URL[131] = "http://drritz.co.kr";
		URL[132] = "https://m.ehfactory.co.kr";
		URL[133] = "http://m.gomcoffee.com";
		URL[134] = "https://greenpia.shop";
		URL[135] = "http://m.���Ǫ��.com";
		URL[136] = "http://www.dabomcns.kr";
		URL[137] = "https://wevolns.shop";
		URL[138] = "http://jariro-store.com";
		URL[139] = "https://chalboribread.com";
		URL[140] = "https://lostmanagementcities.com";
		URL[141] = "http://www.casano.co.kr";
		URL[142] = "http://einsme.co.kr";
		URL[143] = "https://www.vova.co.kr";
		URL[144] = "http://budhi-mudra.com";
		URL[145] = "http://openbucks.shop";
		URL[146] = "https://viinscent.com";
		URL[147] = "http://roenstudio.co.kr";
		URL[148] = "http://www.embelec.co.kr";
		URL[149] = "http://goodtong.kr";
		URL[150] = "http://www.unidlab.co.kr";
		URL[151] = "http://www.basrak.co.kr";
		URL[152] = "http://goodtong.co.kr";
		URL[153] = "http://m.balmainhairkorea.co.kr";
		URL[154] = "https://my-bike.co.kr";
		URL[155] = "https://jungyukdamda.co.kr";
		URL[156] = "https://m.troistouch.com";
		URL[157] = "http://roenstudio.kr";
		URL[158] = "http://www.kyclub.co.kr";
		URL[159] = "http://sale78.cafe24.com";
		URL[160] = "https://cookingson.co.kr";
		URL[161] = "http://pacey.co.kr";
		URL[162] = "https://gleer.co.kr";
		URL[163] = "http://m.basrak.co.kr";
		URL[164] = "https://roenstudio2.cafe24.com/main.html";
		URL[165] = "https://front.maketicket.co.kr/ticket/GD2318673";
		URL[166] = "http://hpw0401.cafe24.com";
		URL[167] = "http://m.openbucks.shop";
		URL[168] = "http://www.jmella.com";
		URL[169] = "https://poopoogalssa.com";
		URL[170] = "https://vkvkffk.cafe24.com";
		URL[171] = "https://maholn.com";
		URL[172] = "http://www.mcnfit.co.kr";
		URL[173] = "http://openbucks.shop";
		URL[174] = "http://www.m.jmella.com";
		URL[175] = "http://bluegram.kr";
		URL[176] = "http://doorirang.com";
		URL[177] = "http://xn--2o2b17h38d97fg8m7sg.com";
		URL[178] = "http://www.mottenbailey.com";
		URL[179] = "https://my-bike.co.kr";
		URL[180] = "https://moment-fragrance.com";
		URL[181] = "http://wwww.sote.co.kr";

		String[] NAkey = new String[182];
		NAkey[0] = "s_4d7494b38f59";
		NAkey[1] = "s_20e53c544606";
		NAkey[2] = "s_40ba550b1f4f";
		NAkey[3] = "s_322d00f82910";
		NAkey[4] = "s_49d299bb64fa";
		NAkey[5] = "s_16dce5232d5a";
		NAkey[6] = "s_282be30055d2";
		NAkey[7] = "s_239fc2f1bf36";
		NAkey[8] = "s_1d3ebe869243";
		NAkey[9] = "s_52eae0d9030f";
		NAkey[10] = "s_568c2e6b2584";
		NAkey[11] = "s_53cde2c4b443";
		NAkey[12] = "s_5115b786b855";
		NAkey[13] = "s_a2405224c69";
		NAkey[14] = "s_239f45d5ef26";
		NAkey[15] = "s_33157f02ade6";
		NAkey[16] = "s_33157f02ade6";
		NAkey[17] = "s_568c2e6b2584";
		NAkey[18] = "s_17c4c8aac6ae";
		NAkey[19] = "s_24888a3d47f2";
		NAkey[20] = "s_462f011031ee";
		NAkey[21] = "s_51feadc63a7e";
		NAkey[22] = "s_1257f0fbaf70";
		NAkey[23] = "s_15fb438fa9b3";
		NAkey[24] = "s_4374cbcc499c";
		NAkey[25] = "s_33fc64e453c5";
		NAkey[26] = "s_4ababfc34797";
		NAkey[27] = "s_18ad9d4fd5d3";
		NAkey[28] = "s_1ffc6585dc3b";
		NAkey[29] = "s_4c8d138c0db8";
		NAkey[30] = "s_3c2e26221f8b";
		NAkey[31] = "s_2e89b1fcc9ec";
		NAkey[32] = "s_2571421bf6cc";
		NAkey[33] = "s_1a8076e6929a";
		NAkey[34] = "s_1d41c73bebe8";
		NAkey[35] = "";
		NAkey[36] = "s_37a1fbc723fb";
		NAkey[37] = "s_3b4550d1c3f3";
		NAkey[38] = "s_4457bcf29846";
		NAkey[39] = "s_eb49d39c9a3";
		NAkey[40] = "s_53cde2c4b443";
		NAkey[41] = "s_53d3bc4f3081";
		NAkey[42] = "s_b113614b444";
		NAkey[43] = "s_685249923e9";
		NAkey[44] = "s_15fb438fa9b3";
		NAkey[45] = "s_40b97a199e52";
		NAkey[46] = "s_49d29937be3a";
		NAkey[47] = "s_17c4ffdccf01";
		NAkey[48] = "s_4f46addbaecc";
		NAkey[49] = "s_3dff55543b44";
		NAkey[50] = "s_3c2dff66138c";
		NAkey[51] = "s_3fcfc276c897";
		NAkey[52] = "s_256d66d71e1c";
		NAkey[53] = "s_16e3fb3ce6ef";
		NAkey[54] = "s_282bdfefbfe4";
		NAkey[55] = "s_282bdfefbfe4";
		NAkey[56] = "s_eafece2746f";
		NAkey[57] = "s_239fba422892";
		NAkey[58] = "s_199e8ad45730";
		NAkey[59] = "s_1e2ab5220c77";
		NAkey[60] = "s_3c6d9d29901";
		NAkey[61] = "s_4374cbcc499c";
		NAkey[62] = "s_599e6e4b1e8";
		NAkey[63] = "s_1d3f3cdde705";
		NAkey[64] = "s_511904100fe6";
		NAkey[65] = "s_54bc98a5f64f";
		NAkey[66] = "s_b11506bcc73";
		NAkey[67] = "s_5116b91bdb6a";
		NAkey[68] = "s_3315ca3aec2a";
		NAkey[69] = "s_37a19d0d0786";
		NAkey[70] = "s_853d4b62b9f";
		NAkey[71] = "s_1f8f17c66cf";
		NAkey[72] = "s_5946c9db6b8e";
		NAkey[73] = "s_3315c9c1533b";
		NAkey[74] = "s_31436f5b8113";
		NAkey[75] = "s_49cbb8f8fee4";
		NAkey[76] = "s_b0f97105ad3";
		NAkey[77] = "s_b0f97105ad3";
		NAkey[78] = "s_576eedcd24ac";
		NAkey[79] = "s_322d040b8fb3";
		NAkey[80] = "s_853d4b62b9f";
		NAkey[81] = "s_685249923e9";
		NAkey[82] = "s_1f1353a74f68";
		NAkey[83] = "s_3315c9c1533b";
		NAkey[84] = "s_10bfa850bc9";
		NAkey[85] = "s_1f1353a74f68";
		NAkey[86] = "s_151271e88c4e";
		NAkey[87] = "s_1257f593adc6";
		NAkey[88] = "s_1257f0fbaf70";
		NAkey[89] = "s_5a3118f9bf09";
		NAkey[90] = "s_4b37c54e5b8";
		NAkey[91] = "";
		NAkey[92] = "s_17ccde617501";
		NAkey[93] = "s_17ccde617501";
		NAkey[94] = "s_54bc91777478";
		NAkey[95] = "s_dcbce59c4d3";
		NAkey[96] = "s_1d3ebe869243";
		NAkey[97] = "s_22b5369c7418";
		NAkey[98] = "s_18b5cbb893e9";
		NAkey[99] = "s_5116bead74ee";
		NAkey[100] = "";
		NAkey[101] = "s_52eae0d9030f";
		NAkey[102] = "s_20e33e72b1d5";
		NAkey[103] = "s_36b45c616b55";
		NAkey[104] = "s_55a56ed81dee";
		NAkey[105] = "s_239fbdd4754d";
		NAkey[106] = "s_f9d6cca5ef7";
		NAkey[107] = "s_1c572c5ca44a";
		NAkey[108] = "s_b0a6557bb19";
		NAkey[109] = "s_4800f69cd3ba";
		NAkey[110] = "s_b113614b444";
		NAkey[111] = "s_1d41ebb0c2e8";
		NAkey[112] = "s_b0a6557bb19";
		NAkey[113] = "s_20e33e72b1d5";
		NAkey[114] = "s_dcbccc274f3";
		NAkey[115] = "s_1a876dc93600";
		NAkey[116] = "s_239faef62a5f";
		NAkey[117] = "s_b0a6557bb19";
		NAkey[118] = "s_511905365225";
		NAkey[119] = "s_f9d6cca5ef7";
		NAkey[120] = "s_767e2c3dfc3";
		NAkey[121] = "s_49d29f49f445";
		NAkey[122] = "s_3314bad6bdeb";
		NAkey[123] = "s_2f70ee4cf1fc";
		NAkey[124] = "s_2da033ed88eb";
		NAkey[125] = "s_1c572c5ca44a";
		NAkey[126] = "s_471669b34be3";
		NAkey[127] = "s_36b45c616b55";
		NAkey[128] = "s_5030119733ac";
		NAkey[129] = "s_1340bd6c89cf";
		NAkey[130] = "s_5030119733ac";
		NAkey[131] = "s_bfa2ab19f80";
		NAkey[132] = "s_53d3bc4f3081";
		NAkey[133] = "s_dcbce59c4d3";
		NAkey[134] = "s_5948c172da4a";
		NAkey[135] = "s_151271e88c4e";
		NAkey[136] = "s_b11506bd328";
		NAkey[137] = "s_4d7354ea89b0";
		NAkey[138] = "s_388ac8a5ed18";
		NAkey[139] = "s_dc9a4bbdad9";
		NAkey[140] = "s_55a4bfb46ef9";
		NAkey[141] = "s_471800f88fac";
		NAkey[142] = "s_b1153841cd1";
		NAkey[143] = "s_1f136d7a6f84";
		NAkey[144] = "s_21cdaa70b297";
		NAkey[145] = "s_1ffb79a15d23";
		NAkey[146] = "s_239fbf6953d7";
		NAkey[147] = "s_4f47a2679abf";
		NAkey[148] = "s_45467f4f0df4";
		NAkey[149] = "s_17ccf6973ae2";
		NAkey[150] = "s_37a2079bc564";
		NAkey[151] = "s_4b9fc7a6c5fb";
		NAkey[152] = "s_1b6befbb1791";
		NAkey[153] = "s_14290d98f4f4";
		NAkey[154] = "s_598e4f9f67e";
		NAkey[155] = "s_b1153816c20";
		NAkey[156] = "s_239fc2f1bf36";
		NAkey[157] = "s_585ff0da4316";
		NAkey[158] = "s_6852bbbca0c";
		NAkey[159] = "s_502f7b561dc6";
		NAkey[160] = "s_4f47a1eab159";
		NAkey[161] = "s_305b647c0e2e";
		NAkey[162] = "s_41a21ab8b453";
		NAkey[163] = "s_4b9fc7a6c5fb";
		NAkey[164] = "s_6852ed27f86";
		NAkey[165] = "s_116f268ecec1";
		NAkey[166] = "s_3dffd99f0256";
		NAkey[167] = "s_1ffb79a15d23";
		NAkey[168] = "s_3fcff3ee5ffa";
		NAkey[169] = "s_37a1393004ec";
		NAkey[170] = "s_265a25290cac";
		NAkey[171] = "s_4f479fde24b7";
		NAkey[172] = "s_a2827d14e00";
		NAkey[173] = "s_1ffb79a15d23";
		NAkey[174] = "s_3fcff3ee5ffa";
		NAkey[175] = "s_445befc14ea4";
		NAkey[176] = "s_3c2e2d3f1435";
		NAkey[177] = "s_f9d7cc1e10c";
		NAkey[178] = "s_eb02bc9977a";
		NAkey[179] = "s_598e4f9f67e";
		NAkey[180] = "s_5a319a252eaf";
		NAkey[181] = "s_462f5378a22d";

		for(int i=0;i<=181;i++) {
			try {
				open(URL[i]);
				System.out.print(i + ". " + URL[i]);
				//URL ���� üũ
				if(URL[i].substring(0, 4).equals("http")) {
					//html�� script �±� wcs_add ���� üũ (������ NA ��ġ��ü�� X)
					ElementsCollection scriptsCheck = Selenide.$$("script");
					boolean textFound = false;
			        for (int x = 0; x < scriptsCheck.size(); x++) {
			            if (scriptsCheck.get(x).innerHtml().contains("wcs_add")) {
			                textFound = true;
			                break;
			            }
			        }
			        //html�� wcs_add�� ������ NAkey�� NA���ΰ� �����ϰ� ����Ǿ����� üũ
			        if (textFound) {
						String HtmlSplit = $("html").innerHtml().trim().replaceAll(" ", "").replaceAll("	", "").replaceAll("\n", "");
						String[] naKeySplit = HtmlSplit.split("wcs_add\\[\"wa\"\\]\\=\"");
						String[] naKeycompare = naKeySplit[1].split("\"");
						if(NAkey[i].equals(naKeycompare[0])) {
							System.out.println("��" + NAkey[i]);
						} else {
							//System.out.println(" �� NA ACCOUNT ID�� ���ų� ���� ����� �ٸ��ϴ�. �������� Ȯ�����ּ��� !!!!!!!");
							//�ƿ� �α׸� ������� ������ ��� ������ ������ ã�� ���� �켱 �� ��� ���� ���Ͽ��� ���б�ȣ�� �ؽ�Ʈ ������ �Ϸ��� ����
							System.out.println("��NAkey�� �ٸ��ϴ�.");
						}
			        } else {
			            //System.out.println(" �� NA ��ũ��Ʈ�� ��ġ�Ǿ� ���� �ʽ��ϴ�.");
			        	//�ƿ� �α׸� ������� ������ ��� ������ ������ ã�� ���� �켱 �� ��� ���� ���Ͽ��� ���б�ȣ�� �ؽ�Ʈ ������ �Ϸ��� ����
			        	System.out.println("��NA ��ũ��Ʈ�� ��ġ�Ǿ� ���� �ʽ��ϴ�.");
			        }
				} else {
					System.out.println("��URL ������ ������ �Դϴ�.");
				}
			} catch (Exception e) {
				System.out.println("��URL ������ �Ұ��մϴ�.");				
			}

		}
	}
	
	//@Test(priority = 3)
	public void ��ī1�����ͽױ�() {
		for(int i=0;i<=1;i++) {			
			open("https://apzz0928.tistory.com");
			for(int x=1;x<=8;x++) {
				refresh();
			}
			open("http://apzz0928.egloos.com");
			for(int x=1;x<=8;x++) {
				refresh();
			}
			
			
			/*
			open("http://apzz0928.blogspot.com");;
			for(int x=1;x<=24;x++) {
				$(".sub" + x).waitUntil(visible, 10000);
				$(".sub" + x).scrollIntoView(false);
				System.out.println("apzz0928.blogspot �� " + x + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $(".sub" + x).text().trim());
				$(".sub" + x).click();
			}
			open("http://apzz0928.egloos.com/");
			for(int x=1;x<=6;x++) {
				$("a", (x+5)).waitUntil(visible, 10000);
				$("a", (x+5)).scrollIntoView(false);
				System.out.println("apzz0928.egloos �� " + x + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $("a", (x+5)).text().trim());
				$("a", (x+5)).click();
			}
			open("http://apzz0928.egloos.com/");
			for(int x=1;x<=13;x++) {
				$("a", (x+12)).waitUntil(visible, 10000);
				$("a", (x+12)).scrollIntoView(false);
				System.out.println("apzz0928.egloos �� " + (x+6) + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $("a", (x+12)).text().trim());
				$("a", (x+12)).click();
			}
			*/
			open("http://apzz092888.blogspot.com");
			for(int x=1;x<=24;x++) {
				$(".sub" + x).waitUntil(visible, 10000);
				$(".sub" + x).scrollIntoView(false);
				$(".sub" + x).scrollIntoView(false);
				//System.out.println("apzz092888.blogspot �� " + x + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $(".sub" + x).text().trim());
				$(".sub" + x).click();
			}
			open("http://apzz0928.blogspot.com");
			for(int x=1;x<=40;x++) {
				$(".sub" + x).waitUntil(visible, 10000);
				$(".sub" + x).scrollIntoView(false);
				$(".sub" + x).scrollIntoView(false);
				//System.out.println("apzz0928.blogspot �� " + x + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $(".sub" + x).text().trim());
				$(".sub" + x).click();
			}
			open("https://apzz0928.tistory.com");
			/*
			for(int x=0, y=1;x<=14;x++) {
				if(x % 2 == 0) {
					$("img", x).click();
					switchTo().window(y);
					y++;
					System.out.println("apzz0928.tistory " + y + "�� ��ʸ� Ŭ���Ͽ����ϴ�.");
				}
			}
			*/
			/*
			open("http://apzz09288.egloos.com");
			for(int x=1;x<=7;x++) {
				$(".sub" + x).waitUntil(visible, 10000);
				$(".sub" + x).scrollIntoView(false);
				System.out.println("apzz09288.egloos �� " + x + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $("a", (x+5)).text().trim());
				$(".sub" + x).click();
			}
			open("http://apzz0928.egloos.com");
			*/
		}
	}

	//@Test(priority = 0)
	public void cafe24����ȸ������() {
		for(int i=1;i<=10;i++) {
			open("http://apzzab.cafe24.com/member/join.html");
			sleep(1000);
			$("#member_id").waitUntil(visible, 10000);
			$("#member_id").setValue("apzzab" + i);
			sleep(1000);
			$("#passwd").setValue("qordlf!@34");
			sleep(1000);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(1000);
			$("#name").setValue("test" + i + "�� �̸�");
			sleep(1500);
			$("#mobile2").setValue("0000");
			sleep(1500);
			$("#mobile3").setValue("0000");
			sleep(1000);
			$("#email1").setValue("test" + i + "@mail.com");
			sleep(1000);
			$(".btnSubmitFix").scrollIntoView(false);
			sleep(1000);
			$(".btnSubmitFix").click();
			sleep(1000);
			open("http://apzzab.cafe24.com/exec/front/Member/logout");
			System.out.println("test" + i + " ���� �Ϸ�");
			sleep(1000);
		}		
	}
	
	//@Test(priority = 1)
	public void cafe24�⺻�������() {
		for(int i=1;i<=6;i++) {
			open("http://shop38.apzz1210.cafe24.com/member/login.html");
			$(".btnLogin").waitUntil(visible, 10000);
			sleep(1500);
			System.out.println("test" + i + " �α���");
			$("#member_id").setValue("test" + i);
			sleep(1500);
			$("#member_passwd").setValue("qordlf!@34");
			$(".btnLogin").scrollIntoView(false);
			sleep(1500);
			$(".btnLogin").click();
			sleep(2500);
			open("http://shop38.apzz1210.cafe24.com/member/modify.html");
			$("#member_id").waitUntil(visible, 10000);
			$("#passwd").setValue("qordlf!@34");
			sleep(1500);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(1500);
			$("#postBtn").click();
			sleep(1500);
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
			$("#zboo_keyword").waitUntil(visible, 10000);
			$("#zboo_keyword").setValue("123");
			sleep(1500);
			$("#zboo_search_btn").click();
			sleep(1500);
			$(".streetLine", 0).waitUntil(visible, 10000);
			$(".streetLine", 0).click();
			switchTo().defaultContent();
			sleep(1500);
			$("#addr2").setValue("test" + i);
			sleep(1500);
			$("#phone2").setValue("0000");
			sleep(1500);
			$("#phone3").setValue("0000");
			$(".btnSubmit", 0).scrollIntoView(true);
			//$("#ma_main_flag0").click();
			sleep(1500);
			$(".btnSubmit", 0).click();
			sleep(1500);
			confirm("ȸ������ ������ �Ϸ�Ǿ����ϴ�.");
			System.out.println("test" + i + " ����� ���� �Է�");
			//�α׾ƿ�
			open("http://shop38.apzz1210.cafe24.com/exec/front/Member/logout");
			sleep(1500);
		}
	}
	
	//@Test(priority = 0)
	public void ī��24��������() {
		for(int i=0;i<=1000;i++) {
			open("http://shop3.apzz0928.cafe24.com/member/login.html");
			//open("http://apzz0928.cafe24.com/member/login.html");
			$(".btnLogin").waitUntil(visible, 10000);
			sleep(1000);
			$("#member_id").setValue("test" + i);
			$("#member_passwd").setValue("qordlf!@34");
			$(".btnLogin").scrollIntoView(false);
			sleep(1000);
			$(".btnLogin").click();
			sleep(2500);
			open("http://shop3.apzz0928.cafe24.com/myshop/addr/register.html");
			$(".btnSubmit").waitUntil(visible, 10000);
			$("#ma_rcv_title").setValue("��Ƽ��2 test" + i + "�� �������");
			$("#ma_rcv_name").setValue("��Ƽ��2 test" + i + "�� �̸�");
			$("#SearchAddress").click();
			sleep(1500);
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" ������ ��� ������ �̸��� �ؽ�Ʈ�� �ۼ�
			$("#zboo_keyword").waitUntil(visible, 10000);
			$("#zboo_keyword").setValue("������ 408-3");
			$("#zboo_search_btn").click();
			sleep(1500);
			$(".streetLine").waitUntil(visible, 10000);
			$(".streetLine").click();
			switchTo().defaultContent();
			sleep(1500);
			$("#address_addr2").setValue("test" + i);
			$("#ma_rcv_mobile_no2").setValue("0000");
			$("#ma_rcv_mobile_no3").setValue("0000");
			//$("#ma_rcv_mobile_no3").setValue("000" + i);
			$("#ma_main_flag0").click();
			sleep(1500);
			$(".btnSubmit").click();
			sleep(1500);
			System.out.println("test" + i + " ����� ���� �Է�");
			//�α׾ƿ�
			open("http://shop3.apzz0928.cafe24.com/exec/front/Member/logout");
			sleep(1500);			
		}		
	}
	
	//@Test(priority = 10)
	public void ī��24ȸ������() {
		for(int i=626;i<=1000;i++) {
			open("http://shop3.apzz0928.cafe24.com/member/agreement.html");
			//open("https://apzz0928.cafe24.com/member/agreement.html");
			sleep(800);
			$("#sAgreeAllChecked").waitUntil(visible, 10000);
			$("#sAgreeAllChecked").click();
			sleep(800);
			$(".btnSubmit").scrollIntoView(false);
			$(".btnSubmit").click();
			sleep(800);
			$("#member_id").waitUntil(visible, 10000);
			$("#member_id").setValue("test" + i);
			sleep(800);
			$("#passwd").setValue("qordlf!@34");
			sleep(800);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(800);
			$("#name").setValue("��Ƽ��2 test" + i + "�� �̸�");
			sleep(800);
			$("#mobile2").setValue("0000");
			sleep(800);
			$("#mobile3").setValue("0000");
			//$("#mobile3").setValue("000" + i + "");
			sleep(800);
			$("#email1").setValue("test" + i + "@mail.com");
			sleep(800);
			$(".btnSubmit").scrollIntoView(false);
			sleep(800);
			$(".btnSubmit").click();
			sleep(800);
			$(".btnEm").waitUntil(visible, 10000);
			open("http://shop3.apzz0928.cafe24.com/exec/front/Member/logout");
			//open("https://apzz0928.cafe24.com/exec/front/Member/logout");
			sleep(1500);
			System.out.println("test" + i + " ���� �Ϸ�");
			sleep(1000);
		}		
	}
	
	//@Test(priority = 0)
	public void ����ǽ���������() {
		for(int i=0;i<=50;i++) {
			open("http://10.160.231.210:8383/login");
			$(".btn_login").waitUntil(visible, 10000);
			$(".inp", 0).setValue("user");
			$(".inp", 0).setValue("password");
			$(".btn_login").click();
			$(".btn-dark").waitUntil(visible, 10000);
			$("http://10.160.231.210:8383/contents/newsroom");
			$(".primary").waitUntil(visible, 10000);
			$(".primary").click();
			$(".cancel").waitUntil(visible, 10000);
			$(".radio-label", 1).click();
			$(".radio-label", 3).click();
			$(".txt-input", 0).setValue("" + i);
			$(".txt-input", 1).setValue("" + i);
			sleep(5000);
			$(".confirm").scrollIntoView(true);
			$(".confirm").click();
			confirm("����Ͻðڽ��ϱ�?");
			sleep(5000);
		}		
	}
	//@Test(priority = 0)
	public void �湮������() {
		for(int i=1;i<=999999;i++) {
			Date number_date = new Date();
			String date = number_format.format(number_date);
			open("https://www.naver.com/");
			js("alert('123')");
			confirm("123");
			open("http://nhnace1.godomall.com/");
			js("alert('123')");
			confirm("123");
			System.out.println(date + " �ð��� (" + i + ") �� �Ϸ�");
		}		
	}
	//@Test(priority = 0)
	public void �����ٿ�ε�üũ() {
		open("https://self-market.dighty.com/login");
		$(".btn_full").waitUntil(visible, 10000);
		$(".inp", 0).setValue("apzz0928@nate.com");
		$(".inp", 1).setValue("qordlf12");
		$(".btn_full").click();
		sleep(3000);
		open("https://self-market.dighty.com/history");
		$("td", 23).waitUntil(visible, 10000);
		for(int i=0;i<=99999;i++) {
			if($("td", 18).text().trim().equals("������")) {
				Date number_date = new Date();
				String date = number_format.format(number_date);
				System.out.println(date + " ������ üũ �Ϸ�");
				sleep(1000);
				refresh();
			} else if ($("td", 18).text().trim().equals("�ٿ�ε�")) {
				Date number_date = new Date();
				String date = number_format.format(number_date);
				System.out.println(date + " �ٿ�ε� üũ �Ϸ�");
				break;
			} else if($("td", 18).text().trim().equals("����")) {
				Date number_date = new Date();
				String date = number_format.format(number_date);
				System.out.println(date + " ���� üũ �Ϸ�");
				break;
			}
		}
	}
	//@Test(priority = 0)
	public void ������īŻ�α�() {
		open("https://catalog.game.dighty.com/login");
		$(".btn_full").waitUntil(visible, 10000);
		$(".inp", 0).setValue("cdy@nhnace.com");
		$(".inp", 1).setValue("testpassword");
		$(".btn_full").click();
		$(".inp_serch").waitUntil(visible, 10000);
		for(int i=0;i<=1000;i++) {
			open("https://catalog.game.dighty.com/search/2/1");
			sleep(300);
			refresh();
			sleep(300);
		}
	}
	//@Test(priority = 0)
	public void �׽�Ʈ() {
		open("http://10.160.231.210:8087/login");
		$("input", 0).setValue("apzz0928@naver.com");
		$("input", 1).setValue("qordlf12");
		$(".btn_full").click();
		$(".btn-success").waitUntil(visible, 10000);
		$(".btn-success").click();
		$(".target", 0).waitUntil(visible, 10000);
		$(".target", 0).click();
		$("span", 5).waitUntil(visible, 10000);
		Random generator = new Random();
		int btnNum = generator.nextInt(4);
		btnNum = generator.nextInt(4);

		$("span", 5).click();
		sleep(50000);
		$(".ico_radio", btnNum).click();
		
	}
	
	//@Test(priority = 1)
	public void RCM�����ͽױ�() {
		for(int i=1;i<=5;i++) {
			open("http://apzz0928.blogspot.com");
			$(".cmcm5").waitUntil(visible, 10000);
			if(i == 1 || i == 2 || i == 5) {
				for(int x=1;x<=10;x++) {
					$(".cmcm" + i + " > a > img").waitUntil(visible, 15000);
					js("document.querySelector('.cmcm" + i + " > a').setAttribute('target', '');");
					sleep(500);
					$(".cmcm" + i).click();
					System.out.println("blogspot " + i + "�� ��ʸ� " + x + " ��° Ŭ����");
					$(".cmcm" + i).waitUntil(visible, 15000);
				}	
			}
			open("http://apzz0928.egloos.com");
			open("https://apzz0928.tistory.com/");
			$(".cmcm1").waitUntil(visible, 10000);
			//if(i == 1 || i == 2 || i == 3) {
			if(i==1) {
				for(int x=1;x<=10;x++) {
					$(".cmcm" + i + " > a > img").waitUntil(visible, 15000);
					js("document.querySelector('.cmcm" + i + " > a').setAttribute('target', '');");
					sleep(500);
					$(".cmcm" + i).click();
					System.out.println("tistory " + i + "�� ��ʸ� " + x + " ��° Ŭ����");
					$(".cmcm" + i).waitUntil(visible, 15000);
				}		
			}
			//}
		}
		System.out.println("RCM ������ �ױ� ��");
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_�������õ��() {
		open("http://alpha-admin.dighty.com/login");
		$(".btn_login").waitUntil(visible, 10000);
		$(".inp", 0).setValue("apzz0928");
		$(".inp", 1).setValue("qordlf!@34");
		$(".btn_login").click();
		sleep(3000);
		open("http://alpha-admin.dighty.com/customer/agency/form");
		sleep(800);
		Random generator = new Random();
		int btnNum = generator.nextInt(4);
		for(int i=4;i<=100;i++) {
			btnNum = generator.nextInt(4);
			$(".ico_radio", btnNum).click();
			$(".txt-input", 0).setValue("test" + i);
			$(".txt-input", 1).setValue("�������ø� " + i);
			$(".txt-input", 2).setValue("����ڸ� " + i);
			$(".txt-input", 3).setValue("1234");
			$(".txt-input", 4).setValue("4321");
			$(".txt-input", 5).setValue("apzz0928@nhnace.com");
			sleep(1000);
			$(".btn-dark", 0).click();
			sleep(3500);
			$(".txt-input", 6).setValue("�߰� �ȳ� ���� " + i);
			sleep(1000);
			$(".btn-primary").click();
			System.out.println("�������� ��� " + i);		
			open("http://alpha-admin.dighty.com/customer/agency/form");
			sleep(1500);
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_�ΰ����ڷα���() {
		open("http://alpha.dighty.com/login");
		$(".btn_point").waitUntil(visible, 10000);
		$(".inp", 0).setValue("ap20200824b");
		$(".inp", 1).setValue("qordlf12");
		$(".btn_point").click();
		sleep(3000);
		open("https://alpha.dighty.com/dashboard/sub-manager/register");
		sleep(800);
		Random generator = new Random();
		int btnNum = generator.nextInt(2);
		for(int i=0;i<=50;i++) {
			$(".inp", 0).setValue("ap20200824s" + i);
			sleep(500);
			$(".tbl_btn", 0).click();
			sleep(1000);
			$("#confirmButton").click();
			sleep(1000);
			$(".inp", 1).setValue("�ΰ�����");
			$(".inp", 2).setValue("ȸ���" + i);
			$(".inp", 3).setValue("����" + i);
			$(".inp", 4).setValue("0000");
			$(".inp", 5).setValue("0000");
			$(".inp", 6).setValue("mail" + i + "@mail.com" + i);
			sleep(500);
			$(".tbl_btn", 1).click();
			sleep(1000);
			$("#confirmButton").click();
			sleep(1000);
			$(".inp_label", btnNum).click();
			sleep(500);
			$(".tbl_btn", 2).scrollIntoView(true);
			$(".tbl_btn", 2).scrollIntoView(true);
			sleep(500);
			$(".tbl_btn", 2).click();
			sleep(3500);			
			btnNum = generator.nextInt(2);
			System.out.println("�ΰ����� ��� " + i);
			open("https://alpha.dighty.com/dashboard/sub-manager/register");
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ���񽺰���_�ΰ����ڵ��() {
		open("http://alpha.dighty.com/login");
		$(".btn_point").waitUntil(visible, 10000);
		$(".inp", 0).setValue("ap20200824b");
		$(".inp", 1).setValue("qordlf12");
		$(".btn_point").click();
		sleep(3000);
		open("https://alpha.dighty.com/dashboard/sub-manager/register");
		sleep(800);
		Random generator = new Random();
		int btnNum = generator.nextInt(2);
		for(int i=0;i<=50;i++) {
			$(".inp", 0).setValue("ap20200824s" + i);
			sleep(500);
			$(".active", 0).click();
			sleep(500);
			$("#confirmButton").click();		
			$(".inp", 1).setValue("�ΰ�����");
			$(".inp", 2).setValue("ȸ���" + i);
			$(".inp", 3).setValue("����" + i);
			$(".inp", 4).setValue("0000");
			$(".inp", 5).setValue("0000");
			$(".inp", 6).setValue("mail" + i + "@mail.com" + i);
			sleep(500);
			$(".active", 0).click();
			sleep(500);
			$("#confirmButton").click();
			sleep(500);
			$(".inp_label", btnNum).click();
			sleep(500);
			$(".active", 0).click();
			sleep(3500);			
			btnNum = generator.nextInt(2);
			System.out.println("�ΰ����� ��� " + i);
			open("https://alpha.dighty.com/dashboard/sub-manager/register");
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_SMS�߼�() {
		open("http://alpha-admin.dighty.com/login");
		$(".btn_login").waitUntil(visible, 10000);
		$(".inp", 0).setValue("apzz0928");
		$(".inp", 1).setValue("qordlf!@34");
		$(".btn_login").click();
		sleep(3000);
		open("http://alpha-admin.dighty.com/contents/sms/send");
		sleep(800);
		Random generator = new Random();
		int btnNum = generator.nextInt(2);
		int btnNum1 = generator.nextInt(58);
		for(int i=0;i<=100;i++) {
			btnNum = generator.nextInt(2);
			btnNum1 = generator.nextInt(48);
			$(".txt-input", 0).setValue("01000000000");
			$(".txt-input", 1).setValue("������ �̸� " + i);
			$(".txt-input", 2).setValue("�޽����� " + i);
			$(".txt-input", 3).setValue("������ �޸� " + i);
			$(".ico_radio", btnNum).click();
			sleep(1000);
			if((btnNum+1) % 2 == 0) {
				sleep(1000);
				$(".txt-input", 4).setValue("2020-08-19 " + (btnNum+12) + ":" + (btnNum+btnNum1+10));
				sleep(1000);
				$(".sms-content", 0).setValue(i + "�� ���� �幮�� ����" + i + "�� ���� �幮�� ����" + i + "�� ���� �幮�� ����" + i + "�� ���� �幮�� ����" + i + "�� ���� �幮�� ����" + i + "�� ���� �幮�� ����" );
				$(".txt-input", 5).setValue("���� " + i);
			} else {
				$(".sms-content", 0).setValue(i + "�� ����");
			}			
			sleep(1000);
			$(".confirm").click();
			confirm("SMS�� �߼��Ͻðڽ��ϱ�?\nȮ�� ��ư�� ������ �߼� ó���˴ϴ�.");
			sleep(3500);
			System.out.println("���� �߼� " + i);
			open("http://alpha-admin.dighty.com/contents/sms/send");
			sleep(1500);
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_���Ϲ߼�() {
		open("http://alpha-admin.dighty.com/login");
		$(".btn_login").waitUntil(visible, 10000);
		$(".inp", 0).setValue("apzz0928");
		$(".inp", 1).setValue("qordlf!@34");
		$(".btn_login").click();
		sleep(3000);
		open("http://alpha-admin.dighty.com/contents/mail");
		sleep(800);
		for(int i=46;i<=100;i++) {
			$(".point", 0).click();
			$(".confirm").waitUntil(visible, 10000);
			$(".txt-input", 2).setValue(i + "�� ����");
			$(".confirm").scrollIntoView(true);
			$(".confirm").scrollIntoView(true);
			$(".confirm").click();
			confirm("������ �߼��Ͻðڽ��ϱ�?\nȮ�� ��ư�� ������ �ٷ� �߼� ó���˴ϴ�.");
			sleep(3500);
			System.out.println("���� �߼� " + i);
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_���������ۼ�() {
		open("http://alpha-admin.dighty.com/login");
		$(".btn_login").waitUntil(visible, 10000);
		$(".inp", 0).setValue("apzz0928");
		$(".inp", 1).setValue("qordlf!@34");
		$(".btn_login").click();
		sleep(3000);
		Random generator = new Random();
		int btnNum = generator.nextInt(3);
		for(int i=222;i<=500;i++) {
			open("http://alpha-admin.dighty.com/contents/notice/form");
			btnNum = generator.nextInt(3);
			sleep(800);
			$(".ico_radio", btnNum).click();
			sleep(500);
			btnNum = generator.nextInt(2);
			sleep(500);
			$(".radio-label", (btnNum+3)).click();
			btnNum = generator.nextInt(2);
			sleep(500);
			if((btnNum+1) % 2 == 0) {
				//$(".custom-control-label", btnNum).click();
			} else {
				$(".custom-control-label", 0).click();
				$(".custom-control-label", 1).click();
			}
			sleep(500);
			$(".txt-input").setValue("�������� ���� " + i);
			$(".ck-editor__editable_inline").setValue("�������� ���� " + i);
			sleep(1500);
			$(".confirm").scrollIntoView(true);
			$(".confirm").scrollIntoView(true);
			$(".confirm").click();
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_�����ϱ�() {
		open("http://alpha.dighty.com/login");
		sleep(3000);
		$(".btn_full").waitUntil(visible, 10000);
		$(".inp", 0).setValue("dightyapzz");
		System.out.println("���̵��Է�");
		$(".inp", 1).setValue("qordlf12");
		System.out.println("PW�Է�");
		$(".btn_full").click();
		System.out.println("�α��� ��ư Ŭ��");
		sleep(1000);
		open("https://alpha.dighty.com/dashboard/inquiry");
		System.out.println("���� �ۼ� �������� �̵�");
		sleep(2000);
		for(int i=0;i<=100;i++) {
			Random generator = new Random();
			int btnNum = generator.nextInt(3);
			$(".tbl_btn1", btnNum).click();
			sleep(500);
			btnNum = generator.nextInt(2);
			$(".tbl_btn1", btnNum+3).click();
			sleep(500);
			$(".inp", 3).setValue("01097430928");
			if(i%2 == 0) {
				$(".ico_chk", 0).click();
				$(".ico_chk", 1).click();
			} else {
				$(".ico_chk", btnNum).click();
			}
			$(".inp", 4).setValue(i + " - " + $(".tbl_btn1.on", 0).text() + " - " + $(".tbl_btn1.on", 1).text());
			$(By.name("comment")).setValue("���� �Է�â" + i);
			$(".w150", 0).scrollIntoView(false);
			$(".w150", 0).scrollIntoView(true);
			$(".w150", 0).click();
			sleep(1000);
			$("#confirmButton").click();
			sleep(3000);
			System.out.println("����Ƽ �����ϱ� ��� " + i);
			open("https://alpha.dighty.com/dashboard/inquiry");
			$(".w150", 0).waitUntil(visible, 10000);
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_���񽺹���() {
		open("http://alpha.dighty.com/");
		sleep(1000);
		$(".btn_ask").scrollIntoView(false);
		for(int i=0;i<=100;i++) {
			$("#form_1").setValue("�̸� " + i);
			$("#form_2").setValue("ȸ��� " + i);
			$("#form_3").setValue(i + "mail@mail.com");
			$("#form_4").setValue("" + i);			
			$("#form_5").setValue("���ǳ��� " + i);
			$(".chk_img").click();
			sleep(1000);
			$(".btn_ask").click();
			sleep(1000);
			$("#confirmButton").click();
			sleep(1000);
			System.out.println("����Ƽ ���񽺹��� ��� " + i);
		}
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_��㳻��() {
		open("http://alpha-admin.dighty.com/login");
		$(".btn_login").waitUntil(visible, 10000);
		$(".inp", 0).setValue("apzz0928");
		$(".inp", 1).setValue("qordlf!@34");
		$(".btn_login").click();
		sleep(3000);
		open("http://alpha-admin.dighty.com/customer/counseling");
		$(".btn-dark").waitUntil(visible, 10000);
		for(int i=35;i<=100;i++) {
			Random generator = new Random();
			int btnNum = generator.nextInt(3);
			$(".btn-dark", 1).click();
			$(".btn-primary").waitUntil(visible, 10000);
			$("#form-item-1").setValue("���̵� " + i);
			$("#form-item-4").selectOption(btnNum);
			$("#form-item-8").setValue("�̸� " + i);
			$("#form-item-9").setValue("����ó " + i);			
			$("#form-item-10").setValue(i + "mail@mail.com");
			$(".ck-editor__editable").setValue("���� " + i);
			sleep(1000);
			$(".btn-primary").click();
			confirm("��㳻���� ����Ͻðڽ��ϱ�?");
			sleep(5000);
			System.out.println("����Ƽ ��㳻�� ��� " + i);
		}
	}
	//@Test(priority = 0)
	public void CM�ӽ�() { //�̰� ��� Ŭ�� ���� �����ֵ��� ����
		for(int i=1;i<=50;i++) {
			open("http://apzz0928.blogspot.com");
			$(".cmcm1").waitUntil(visible, 10000);
			$(".cmcm" + 1 + " > a > img").waitUntil(visible, 15000);
			js("document.querySelector('.cmcm" + 0 + " > a').setAttribute('target', '');");
			sleep(500);
			$(".cmcm" + 1).click();
			System.out.println("blogspot ��ʸ� " + i + " ��° Ŭ����");
			$(".cmcm" + 1).waitUntil(visible, 15000);
		}
		for(int i=1;i<=50;i++) {
			open("http://apzz0928.tistory.com");
			sleep(4500);
			$(".cmcm8").waitUntil(visible, 10000);
			$(".cmcm" + 0 + " > a > img").waitUntil(visible, 15000);
			js("document.querySelector('.cmcm" + 0 + " > a').setAttribute('target', '');");
			sleep(500);
			$(".cmcm" + 0).click();
			System.out.println("tistory ��ʸ� " + i + " ��° Ŭ����");
			$(".cmcm" + 0).waitUntil(visible, 15000);
		}	
		System.out.println("RCM ������ �ױ� ��");
	}
	//@Test(priority = 0)
	public void Dighty�ΰ����ڻ���() {
	    open("https://dighty.com/login");
	    sleep(2000);
	    $(".inp", 0).setValue("minusplus");
	    $(".inp", 1).setValue("minusplus!");
	    $(".btn_point").click();
	    sleep(2500);
	    open("https://www.dighty.com/dashboard/sub-manager");
	    for(int i=0;i<=91;i++) {
	    	$("td", 8).click();
	    	sleep(500);
	    	$("#confirmButton").click();
	    	sleep(500);
	    	refresh();
	    	$("td", 8).waitUntil(visible, 10000);
	    }
	}
	//@Test(priority = 0)
	public void Dighty�ΰ����ڵ��() {
	    open("https://dighty.com/login");
	    sleep(2000);
	    $(".inp", 0).setValue("minusplus");
	    $(".inp", 1).setValue("minusplus!");
	    $(".btn_point").click();
	    sleep(2500);
	    open("https://www.dighty.com/dashboard/sub-manager/register");
	    for(int i=25;i<=100;i++) {
	    	$(".inp", 0).setValue("testid" + i);
	    	sleep(500);
	    	$(".active", 0).click();
	    	sleep(500);
	    	$("#confirmButton").click();
	    	$(".inp", 1).setValue("testid");   	
	    	$(".inp", 2).setValue("testid" + i);
	    	$(".inp", 4).setValue("9999");
	    	$(".inp", 5).setValue("9999");
	    	$(".inp", 6).setValue("testid" + i + "@mail.com");
	    	sleep(500);
	    	$(".active", 0).click();
	    	sleep(500);
	    	$("#confirmButton").click();
	    	$(".ico_chk", 1).click();
	    	sleep(500);
	    	$(".w150", 0).scrollIntoView(false);
	    	$(".w150", 0).scrollIntoView(true);
	    	$(".w150", 0).click();
	    	sleep(500);
		    open("https://www.dighty.com/dashboard/sub-manager/register");
	    }
	}
	//@Test(priority = 0)
	public void ���񽺰���Ʈ���庸����ʼӵ�Ȯ��() {
	    open("https://www.acecounter.com/www2/main.amz");
	    $(".text").setValue("apzz09288");
	    $(".password").setValue("qordlf!@34");
	    $(".login_btn").click();
	    $(".btn_gray.ml5").click();
	    $(".footer_logo").waitUntil(visible, 1000);
	    $("#leftMenu > p > a > img", 3).click();
	    switchTo().window(1);
	    $(".sub_info").click();
	}
	//@Test(priority = 0)
	public void ���񽺰����ϴ�() {
	    open("https://www.acecounter.com/www2/main.amz");
	    $(".text").setValue("apzz09288");
	    $(".password").setValue("qordlf!@34");
	    $(".login_btn").click();
	    $(".btn_gray.ml5").click();
	    $(".footer_logo").waitUntil(visible, 1000);
	    js("window.open('https://www.acecounter.com/www2/education/trendReportList.amz');");
	    switchTo().window(1);
	    $(".sub_info").click();
	}
	//@Test(priority = 0)
	public void ���񽺰����ϴܹ�ʼӵ�Ȯ��() {
		open("https://www.acecounter.com/www2/main.amz");
		$(".text").setValue("apzz09288");
		$(".password").setValue("qordlf!@34");
		$(".login_btn").click();
		$(".btn_gray.ml5").click();
		$(".footer_logo").waitUntil(visible, 1000);
		$("#leftMenu > p > a > img", 3).click();
		switchTo().window(1);
		$(".sub_info").click();
	}
	//@Test(priority = 0)
	public void ���񽺰����ϴ�1() {
		open("https://www.acecounter.com/www2/main.amz");
		$(".text").setValue("apzz09288");
		$(".password").setValue("qordlf!@34");
		$(".login_btn").click();
		$(".btn_gray.ml5").click();
		$(".footer_logo").waitUntil(visible, 1000);
		js("window.open('https://www.acecounter.com/www2/education/trendReportDetail.amz?rno=191');");
		switchTo().window(1);
		$(".sub_info").click();
	}
	//@Test(priority = 0)
	public void rcm���̺�Ȯ��() {
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		String title1 = "";

		//open("https://apzz0928.tistory.com");
		open("http://apzz0928.blogspot.com");
		for(int y=0;y<=5;y++) {
			for(int x=0;x<=1999;x++) {
				/*
				for(int q=0;q<=10;q++) {
					if($(By.tagName("title")).text().equals("TISTORY")) {
						sleep(1000);
						refresh();
					}	
				}*/
				$(".cmcm5").waitUntil(visible, 10000);
				if(y==0) {
					js("document.cookie = 'ACEUACS=1111111111111111111'"); //Ÿ�� X ���׸�Ʈ X / 1�� ���
				} else if (y==1) {
					js("document.cookie = 'ACEUACS=1585872393527761537'"); //Ÿ�� A ���׸�Ʈ 678 / 2�� ���
				} else if (y==2) {
					js("document.cookie = 'ACEUACS=1576739137318761538'"); //Ÿ�� B ���׸�Ʈ 680 / 3�� ���
				} else if (y==3) {
					js("document.cookie = 'ACEUACS=1577930839428761538'"); //Ÿ�� C ���׸�Ʈ 681 / 4�� ���
				} else if (y==4) {
					js("document.cookie = 'ACEUACS=1581291211937761532'"); //Ÿ�� D ���׸�Ʈ 682 / 4, 5, 6�� ���
				} else if (y==5) {
					js("document.cookie = 'ACEUACS=1581464072231761539'"); //Ÿ�� E ���׸�Ʈ 682 / 6�� ���
				}
				/*
				for(int q=0;q<=10;q++) {
					if($(By.tagName("title")).text().equals("TISTORY")) {
						sleep(1000);
						refresh();
					}
				}
				*/
				refresh();
				title1 = $(".cmcm1 > a > img").getAttribute("title");
				if(title1.equals("1��")) {
					b++;
				} else if (title1.equals("2��")){
					c++;
				} else if (title1.equals("3��")) {
					d++;
				} else if (title1.equals("4��")) {
					e++;
				} else if (title1.equals("5��")){
					f++;
				} else if (title1.equals("6��")) {
					g++;					
				} else {
					h++;
				}
				js("document.querySelector('.cmcm1 > a').setAttribute('target', '');");
				$(".cmcm1").scrollIntoView(false);
				$(".cmcm1").click();
			}
			if(y==0) {
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("1�� ��� ���� | 1�� ��� " + b + "�� / 2�� ��� " + c + "�� / 3�� ��� " + d + "�� / 4�� ��� " + e +  "�� / 5�� ��� " + f + "�� / 6�� ��� " + g + " �⺻ ��� ���� Ƚ�� " + h + "��");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==1) {
				System.out.println("2�� ��� ���� | 1�� ��� " + b + "�� / 2�� ��� " + c + "�� / 3�� ��� " + d + "�� / 4�� ��� " + e +  "�� / 5�� ��� " + f + "�� / 6�� ��� " + g + " �⺻ ��� ���� Ƚ�� " + h + "��");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==2) {
				System.out.println("3�� ��� ���� | 1�� ��� " + b + "�� / 2�� ��� " + c + "�� / 3�� ��� " + d + "�� / 4�� ��� " + e +  "�� / 5�� ��� " + f + "�� / 6�� ��� " + g + " �⺻ ��� ���� Ƚ�� " + h + "��");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==3) {
				System.out.println("4�� ��� ���� | 1�� ��� " + b + "�� / 2�� ��� " + c + "�� / 3�� ��� " + d + "�� / 4�� ��� " + e +  "�� / 5�� ��� " + f + "�� / 6�� ��� " + g + " �⺻ ��� ���� Ƚ�� " + h + "��");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==4) {
				System.out.println("4,5,6�� ��� ���� | 1�� ��� " + b + "�� / 2�� ��� " + c + "�� / 3�� ��� " + d + "�� / 4�� ��� " + e +  "�� / 5�� ��� " + f + "�� / 6�� ��� " + g + " �⺻ ��� ���� Ƚ�� " + h + "��");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==5) {
				System.out.println("6�� ��� ���� | 1�� ��� " + b + "�� / 2�� ��� " + c + "�� / 3�� ��� " + d + "�� / 4�� ��� " + e +  "�� / 5�� ��� " + f + "�� / 6�� ��� " + g + " �⺻ ��� ���� Ƚ�� " + h + "��");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			}
			b = 0;
			c = 0;
			d = 0;
			e = 0;
			f = 0;
			g = 0;
			h = 0;
		}
	}
	//@Test(priority = 0)
	public void RCM���׸�Ʈ���۳����߰�() {
		open("https://alpha.dighty.com/login?redirectURL=https://alpha-am.dighty.com");
		$(".inp", 0).setValue("amzsoft");
		$(".inp", 1).setValue("ace@5420");
		$(".btn_point").click();
		sleep(3000);
		open("https://alpha-am.dighty.com/use");
		Random generator = new Random();
		for(int i=1;i<=301;i++) {
			int btnNum = generator.nextInt(3);
			$(".btn-am-success", (btnNum+1)).click();
			System.out.println((btnNum+1) + "��° ��� ��ư Ŭ��");
			sleep(1000);
			$(".btn_select", 2).click();
			sleep(1000);
			$(".is_opened > ul > li > a", 2).click();
			sleep(2500);
			$(".btn-am-success", 12).waitUntil(visible, 10000);
			$(".btn-am-success", 12).click();
			sleep(1000);
			$(".btn-am-primary").click();
			sleep(1000);
			open("https://alpha-am.dighty.com/use");
			sleep(1000);
			System.out.println(i + "��° ���׸�Ʈ ���� �߰�");
			sleep(1000);
			/*
			for(int x=0;x<=999999;x++) {
				$("td", 7).waitUntil(visible, 10000);
				if($("td", 7).text().trim() == "�Ϸ�") {
					open("https://alpha-am.dighty.com/use");
					System.out.println("�Ϸ�� ����" + $("td", 7).text().trim());
					break;
				} else if($("td", 7).text().trim() == "����"){
					System.out.println("���׸�Ʈ ���ۻ��� Ȯ�� : ����");
					System.out.println("���з� ����" + $("td", 7).text().trim());
					break;
				} else {
					sleep(1000);
					System.out.println("�Ϸᵵ �ƴϰ� ���е� �ƴ�" + $("td", 7).text().trim());
					refresh();
					System.out.println("���׸�Ʈ ���ۻ��� Ȯ�� ���ΰ�ħ Ƚ�� " + x + "��");
				}
			}*/
		}			
	}
	//@Test(priority = 0)
	public void RCM����Ʈ�߰�() {
		open("http://alpha-rcm.dighty.com/zone/list?affiliateid=4&pagetype=existing&offset=10");
		$(".close-btn", 0).waitUntil(visible, 10000);
		$(".close-btn", 0).click();
		
		$(".inp", 0).setValue("amzsoft");
		$(".inp", 1).setValue("ace@5420");
		$(".btn_point").click();
		sleep(3000);
		open("http://alpha-rcm.dighty.com/site/detail?agencyid=1");
		open("http://alpha-rcm.dighty.com/site/detail?agencyid=1");
		for(int i=1;i<=500;i++) {
			Date number_date = new Date();
			SimpleDateFormat number_format = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
			String date = number_format.format(number_date);
			SimpleDateFormat number_format1 = new SimpleDateFormat("yyyyMMddhhmmss");
			String date1 = number_format1.format(number_date);
			$(".inp", 0).setValue(date + ". " + (i+1) + "��° ����Ʈ");
			$(".inp", 1).setValue("http://" + date1 + ".com" );
			$(".btn_point", 0).click();
			sleep(500);
			System.out.println((i+1) + " ��° ����Ʈ �߰�");
			open("http://alpha-rcm.dighty.com/site/detail?agencyid=1");
		}			
	}
	//@Test(priority = 0)
	public void RCM�����߰�() {
		open("http://alpha-rcm.dighty.com/zone/list?affiliateid=4&pagetype=existing&offset=10");
		$(".close-btn", 0).waitUntil(visible, 10000);
		$(".close-btn", 0).click();
		
		$(".inp", 0).setValue("amzsoft");
		$(".inp", 1).setValue("ace@5420");
		$(".btn_point").click();
		sleep(3000);
		open("http://alpha-rcm.dighty.com/zone/detail?affiliateid=4");
		open("http://alpha-rcm.dighty.com/zone/detail?affiliateid=4");
		for(int i=750;i<=1000;i++) {
			$(".inp", 0).setValue("test "+i);
			$(".inp", 2).setValue("170");
			$(".inp", 3).setValue("60");
			$(".btn_point", 0).click();
			sleep(300);
			System.out.println((i+1) + " ��° ���� �߰�");
			open("http://alpha-rcm.dighty.com/zone/detail?affiliateid=4");
		}			
	}
	//@Test(priority = 0)
	public void ����Ƽ����ǽ�_��������() {
		open("http://alpha.dighty.com/login");
		sleep(3000);
		$(".btn_full").waitUntil(visible, 10000);
		$(".inp", 0).setValue("ap20081305");
		System.out.println("���̵��Է�");
		$(".inp", 1).setValue("qordlf12");
		System.out.println("PW�Է�");
		$(".btn_full").click();
		System.out.println("�α��� ��ư Ŭ��");
		sleep(1000);
		for(int i=0;i<=100;i++) {
			open("https://alpha.dighty.com/dashboard/services/terminate");
			sleep(800);
			$(".inp").waitUntil(visible, 10000);
			$(".inp").setValue("qordlf12");
			$(".active").click();
			$(".l_ico_check", 0).waitUntil(visible, 10000);
			$(".l_ico_check", 0).click();
			sleep(500);
			$(".active").click();	
			sleep(1000);
			$(".ico_chk", 1).scrollIntoView(false);
			$(".ico_chk", 1).scrollIntoView(false);
			if(i % 2 == 0) {
				$(".ico_chk", 0).click();
			} else {
				$(".inp", 0).setValue("�̸�");
				$(".inp", 1).setValue("9743");
				$(".inp", 2).setValue("0928");
				$(".inp", 3).setValue(i + "mail@mail.com");
				sleep(1000);
			}
			$(".ico_chk", 1).scrollIntoView(true);
			$(".ico_chk", 1).scrollIntoView(true);
			$(".ico_chk", 1).click();
			sleep(500);
			$(".active", 1).scrollIntoView(true);
			$(".active", 1).scrollIntoView(true);
			$(".active", 1).click();
			$("#confirmButton").waitUntil(visible, 10000);
			$("#confirmButton").click();
			sleep(1000);
			$("#confirmButton").click();
			$(".btn_gray", 0).waitUntil(visible, 10000);
			$(".btn_gray", 0).click();
			$("#confirmButton").click();
			sleep(1000);
			$("#confirmButton").click();
			$(".btn_point", 1).waitUntil(visible, 10000);
		}
	}

	//@Test(priority = 0)
	public void dighty���ε�����ī�����() {
		open("http://10.160.231.210:8384/login");
		$(".inp", 0).waitUntil(visible, 10000);
		$(".inp", 0).setValue("user");
		$(".inp", 1).setValue("password");
		$(".btn_login").click();
		sleep(1000);
		open("http://10.160.231.210:8384/contents/datacard");
		for(int i=0;i<=252;i++) {
			$(".btn-outline-secondary", 0).waitUntil(visible, 10000);
			$(".btn-outline-secondary", 0).click();
			confirm("ī�带 �����Ͻðڽ��ϱ�?\n���ο��� ������ ī��� Dighty ����Ʈ������ ���� �����˴ϴ�.");
			sleep(500);
			confirm("�����Ǿ����ϴ�.");
			sleep(500);
			System.out.println((i+1) + " ��° ������ ī�� ����");
			
		}			
	}
	//@Test(priority = 0)
	public void dighty���ε�����ī����() {
		open("http://10.160.231.210:8384/login");
		$(".inp", 0).waitUntil(visible, 10000);
		$(".inp", 0).setValue("user");
		$(".inp", 1).setValue("password");
		$(".btn_login").click();
		sleep(1000);
		Random generator = new Random();
		for(int i=262;i<=500;i++) {
			open("http://10.160.231.210:8384/contents/datacard/form");	
			$(".confirm").waitUntil(visible, 10000);
			int btnNum = generator.nextInt(2);
			$(".ico_radio", btnNum+2).click();
			$("#segmentName").setValue("�α� �±� Ȯ�� ������ ī�� " + i);
			btnNum = generator.nextInt(100);
			$("#condition_0").setValue("�α� �±� Ȯ�� ������ ī��" + i + " �� ����1 : " + btnNum);
			btnNum = generator.nextInt(100);
			$("#condition_1").setValue("�α� �±� Ȯ�� ������ ī��" + i + " �� ����2 : " + btnNum);
			btnNum = generator.nextInt(100);
			$("#condition_2").setValue("�α� �±� Ȯ�� ������ ī��" + i + " �� ����3 : " + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_0").setValue("�±�" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_1").setValue("�±�" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_2").setValue("�±�" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_3").setValue("�±�" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_4").setValue("�±�" + btnNum);
			$(".ico_radio", 6).click();
			btnNum = generator.nextInt(999999999);
			$(".plr5", 0).waitUntil(visible, 10000);
			$(".plr5", 0).setValue(btnNum + "");
			$(".ico_radio", 8).click();
			btnNum = generator.nextInt(999999999);
			$(".plr5", 1).waitUntil(visible, 10000);
			$(".plr5", 1).setValue(btnNum + "");
			sleep(300);
			$(".confirm").click();
			confirm("ī�带 ��� �Ͻðڽ��ϱ�?\n��ϵ� ī��� �ٷ� Dighty ����Ʈ���� Ȯ���� �� �ֽ��ϴ�.");
			System.out.println(i + " ��° ������ ī�� ���");
			
		}			
	}
	//@Test(priority = 0)
	public void ķ���θŴ���ķ���ε��() {
		open("http://alpha-rcm.dighty.com/auth/form");
		$(".btn_login").waitUntil(visible, 10000);
		$(".btn_login").click();
		sleep(1000);
		for(int i=1;i<=100;i++) {
			open("http://alpha-rcm.dighty.com/campaign/detail?agencyid=1&clientid=2");	
			$("#temp_input").waitUntil(visible, 10000);
			$("#temp_input").setValue("����¡ Ȯ�ο� ķ����" + i);
			$(".btn_point").click();
			sleep(500);
			$(".ajs-cancel").waitUntil(visible, 10000);
			$(".ajs-cancel").click();
		}			
	}
	//@Test(priority = 0)
	public void ķ���θŴ������Ŭ��() {
		for(int i=1,y=1;i<=10;i++) {
			open("http://apzz092888.blogspot.com");
			$(".sub1").waitUntil(visible, 10000);
			open("http://apzz0928.blogspot.com");
			for(int x=1;x<=10;x++) {
				$(".cmcm1 > a > img").waitUntil(visible, 15000);
				js("document.querySelector('.cmcm1 > a').setAttribute('target', '');");
				sleep(500);
				$(".cmcm1").click();
				System.out.println("blogspot CM ��ʸ� " + i + ", " + x + ", " + y + " ��° Ŭ���Ͽ����ϴ�.");
				y=y+1;
				$(".cmcm1").waitUntil(visible, 15000);
			}
			open("http://apzz09288.egloos.com");
			$(".menu6").waitUntil(visible, 10000);
			open("http://apzz0928.egloos.com");
			for(int x=1;x<=10;x++) {
				$(".cmcm1 > a > img").waitUntil(visible, 15000);
				js("document.querySelector('.cmcm1 > a').setAttribute('target', '');");
				sleep(500);
				$(".cmcm1").click();
				System.out.println("egloos CM ��ʸ� " + i + ", " + x + ", " + y + " ��° Ŭ���Ͽ����ϴ�.");
				y=y+1;
				$(".cmcm1").waitUntil(visible, 15000);
			}
		}
	}
	//@Test(priority = 0)
	public void ������ķ���θŴ������Ŭ��() {
		for(int i=0;i<=100;i++) {
			open("http://apzz0928.egloos.com/");
			for(int x=1;x<=1001;x++) {
				$(".cmcm6").waitUntil(visible, 15000);
				$(".cmcm6").scrollIntoView(false);
				js("document.querySelector('.cmcm6 > a').setAttribute('target', '');");
				sleep(500);
				$(".cmcm6").click();
				$(".cmcm7").waitUntil(visible, 15000);
				$(".cmcm7").scrollIntoView(false);
				js("document.querySelector('.cmcm7 > a').setAttribute('target', '');");
				sleep(500);
				$(".cmcm7").click();
				$(".cmcm8").waitUntil(visible, 15000);
				$(".cmcm8").scrollIntoView(false);
				js("document.querySelector('.cmcm8 > a').setAttribute('target', '');");
				sleep(500);
				$(".cmcm8").click();
				$(".cmcm9").waitUntil(visible, 15000);
				$(".cmcm9").scrollIntoView(false);
				js("document.querySelector('.cmcm9 > a').setAttribute('target', '');");
				sleep(500);
				$(".cmcm9").click();
				System.out.println("egloos CM ��ʸ� " + i + " * " + x + " ��° Ŭ���Ͽ����ϴ�.");
				$(".cmcm").waitUntil(visible, 15000);
			}
		}
	}
	//@Test(priority = 0)
  	public void ���Լ�����() {
  		System.out.println(" ! ----- increaseVisit Start ----- ! ");
		String[] link = {"1", "2", "3", "A", "B", "C", "��", "��", "��", "%EA%B0%80", "%EB%82%98", "%EB%8B%A4"};
		for(int x=0;x<=1;x++) {
			open("http://apzz092888.blogspot.com/");
			$(".sub24").waitUntil(visible, 10000);
			for(int i=1;i<=24;i++) {
				$(".sub" + i).waitUntil(visible, 10000);
				$(".sub" + i).scrollIntoView(false);
				$(".sub" + i).click();
				System.out.println("blogspot �� " + i + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $(".sub" + i).text().trim());
				sleep(500);
			}
			open("http://apzz09288.egloos.com");
			for(int i=0;i<=5;i++) {
				$(".menu" + i).waitUntil(visible, 10000);
				$(".menu" + i).scrollIntoView(false);
				$(".menu" + i).click();
		  		System.out.println( "egloos �� " + i + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $(".menu" + i).text().trim());
			}
			open("http://apzz09288.egloos.com");
			for(int i=0;i<=11;i++) {
				$(By.linkText(link[i])).waitUntil(visible, 10000);
				$(By.linkText(link[i])).scrollIntoView(false);
				$(By.linkText(link[i])).click();
				System.out.println("egloos �� " + i + " ��° Ŭ���Ͽ����ϴ�. // �������� : " + $(By.name(link[i])).text().trim());
			}			
		}
  		System.out.println(" ! ----- increaseVisit End ----- ! ");
  	}
	
	//@Test(priority = 0)
	public void ��α׸޴�Ŭ�������̵��ϱ�() {
		open("http://apzz092888.blogspot.com");
		String menuName = "";
		for(int x=1;x<=3;x++) {
			for(int i=1;i<=36;i++) {
				$(".sub" + i).click();
				menuName = $(".sub" + i).text().trim();
				System.out.println("Ŭ���� �޴��� sub" + i + " �Դϴ�. // �޴��� : " + menuName );
				if(i == 36) {
					open("http://apzz092888.blogspot.com");
				}
				sleep(800);
			}
			System.out.println("��ü �޴��� �� " + x + "�� Ŭ���Ͽ����ϴ�.");			
		}
	}
	//@Test(priority = 1)
	public void ��α��ּҷ��̵��ϱ�() {
		open("http://apzz092888.blogspot.com");
		String[] URL = {"/search/label/Tel?nac_md=normal_mkt&nac_cpi=40&nac_sm=tel_link"
				, "/search/label/banner-outer?nac_md=normal_mkt&nac_cpi=39&nac_sm=banner-outer"
				, "/search/label/banner-inner?nac_inbc=680&nac_inbs=banner-inner"
				, "/search/label/fileDownload?nac_md=normal_mkt&nac_cpi=42&nac_sm=file%20down"
				, "/search/label/marketing-normal?nac_md=normal_mkt&nac_cpi=3398&nac_sm=normal-marketing"
				, "/search/label/marketing-naverBrand?nac_md=naver_br&nac_cpi=3399&nac_kw=naverbrand-marketing"
				, "/search/label/marketing-daumBrand?nac_md=daum_br&nac_cpi=3400&nac_kw=daumbrand-marketing"
				, "/search/label/marketing-kakaoTalk?nac_md=kakao_ta&nac_cpi=3401&nac_sm=kakaotalk-marketing"
				, "/search/label/marketing-google?nac_md=google_ad&nac_cpi=3402&nac_kw=google-marketing"
				, "/search/label/inHouse-viral/?nac_md=viral_mkt&nac_cpi=3276&nac_sm=inhouse-viral"
				, "/search/label/inHouse-Email?nac_md=email_mkt&nac_cpi=3281"
				, "/search/label/inHouse-Talk/?nac_md=sms_mkt&nac_cpi=3280&nac_sm=inhouse-talk"
				, "/search/label/change-order"
				, "/search/label/change-signIn"
				, "/search/label/change-booking"
				, "/search/label/change-request"
				, "/search/label/change-other1"
				, "/search/label/change-other2"
				, "/search/label/change-other3"
				, "/search/label/change-step-1.0"
				, "/search/label/change-step-1.1"
				, "/search/label/change-step-1.2"
				, "/search/label/change-step-1.3"
				, "/search/label/change-step-2.0"
				, "/search/label/change-step-2.1"
				, "/search/label/change-step-2.2"
				, "/search/label/change-step-2.3"
				, "/search/label/change-step-2.4"
				, "/search/label/change-step-2.5"
				, "/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%A3%BC%EB%AC%B8"
				, "/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EA%B0%80%EC%9E%85"
				, "/search/label/%EC%9D%B8%EC%BD%94%EB%94%A9-%EC%98%88%EC%95%BD"
				, "/search/label/missing/missingA"
				, "/search/label/missing/missingB"
				, "/search/label/missing/missingC"};
		String domain = "http://apzz092888.blogspot.com";
		for(int x=0;x<=3;x++) {
			for(int i=4;i<=15;i++) {
				open(domain+URL[i]);
				System.out.println(URL[i] + "�� �̵��߽��ϴ�.");
				open(domain + "/search?q=" + URL[i]);
				System.out.println(URL[i] + "�� �˻��߽��ϴ�.");
			}	
		}
	}
	//@Test(priority = 2)
	public void ������ó�����() {
		for(int x=1;x<=5;x++) {
			open("http://apzz0928.blogspot.com");
			$(".sub43").click();
			System.out.println("blogspot ���� " + x +"�� ������ó�� ����߽��ϴ�.");
			open("http://apzz0928.egloos.com");
			$(".sub43").click();
			System.out.println("egloos ���� " + x +"�� ������ó�� ����߽��ϴ�.");
		}
		for(int y=1;y<=10;y++) {
			open("http://apzz0928.blogspot.com");
			$(".sub43").click();
			System.out.println("blogspot ���� " + y +"�� ������ó�� ����߽��ϴ�.");
		}
	}
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}