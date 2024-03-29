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
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.ScreenShooter;

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
	
	@Test(priority = 999)
	public void import자삭방지() {
		sleep(1000);
	}
	
	//@Test(priority = 2)
	public void 카페24구매apzz0928메인샵() {
		int y=0;
		for(int x=0;x<=1000;x++) {
			for(int i=0;i<=1000;i++) {
				Random generator = new Random();
	            int ID = generator.nextInt(1001);
	            int Num = generator.nextInt(1001);
	            int Url = generator.nextInt(1);
	            /*if(Num % 2 == 0){ //비회원구매 분기 주석처리                	
	            	if(Url == 0) {
	                    open("https://apzz0928.cafe24.com");	
	            	} else if(Url == 1) {
	                    open("http://shop2.apzz0928.cafe24.com");          		
	            	} else if(Url == 2) {
	                    open("http://shop3.apzz0928.cafe24.com");                		
	            	}
	                Num = generator.nextInt(2); //1로 설정하면 첫 번째 수인 0만 나옴
	            	if(Url == 0) {
	            		System.out.println("---" + "일반샵의 " + " 비회원 " + Num + " 이 0이면 카테고리 선택해서 구매, 1이면 검색어로 검색해서 구매");	
	            	} else if(Url == 1) {
	            		System.out.println("---" + "멀티샵1의 " + " 비회원 " + Num + " 이 0이면 카테고리 선택해서 구매, 1이면 검색어로 검색해서 구매");
	            	} else if(Url == 2) {
	            		System.out.println("---" + "멀티샵2의 " + " 비회원 " + Num + " 이 0이면 카테고리 선택해서 구매, 1이면 검색어로 검색해서 구매");
	            	}
	               if(Num == 0) { //카테고리 선택해서 구매
	                    Num = generator.nextInt(3);
	                    //카테고리 클릭해서 상품 구매 페이지 접근
	                    if(Url == 0) { //일반샵과 멀티샵의 메뉴 링크가 달라서 분기
	                        $(".link", Num+14).click();                        	
	                    } else {
	                        $(".link", Num+18).click();
	                    }
	                    $(".xans-product-displaysubcategory").waitUntil(visible, 10000);
	                    $(".xans-product-displaysubcategory", Num).click(); //중분류 선택
	                    $(".prdImg", 2).waitUntil(visible, 10000);
	                    $(".prdImg", Num).click(); //구매 상품 선택
	                    Num = generator.nextInt(2);
	                    if(Num == 0) {
	                        //주문/결제 페이지에서 주문/결제 완료
	                    	$(".btnBuy", 1).waitUntil(visible, 10000);
	                    	$(".btnBuy", 1).click();
	                    	$(".theme1", 1).waitUntil(visible, 10000);
	                    	$(".theme1", 1).click();
	            			sleep(2500);
	                    	$("#rname").waitUntil(visible, 10000);
	                    	$("#rname").setValue("비회원 구매 ID " + ID); //받는사람
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("성내동 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("비회원 구매 나머지 주소 " + ID); //나머지 주소
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
	                        $("#pname").setValue("비회원 구매 입금자명 " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("비회원 카테고리 선택해서 일반 구매 ---");
	                        sleep(2000);
	                    } else if (Num == 1) { 
	                    	//장바구니 추가 후 구매완료
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
	                    	$("#rname").setValue("비회원 구매 ID " + ID); //받는사람
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("성내동 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("비회원 구매 나머지 주소 " + ID); //나머지 주소
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
	                        $("#pname").setValue("비회원 구매 입금자명 " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("비회원 카테고리 선택해서 장바구니 구매 ---");
	                        sleep(2000);
	                    }
	                } else if(Num == 1) { //검색어로 검색해서 구매
	                    $(".btnSearch", 1).click();
	                    sleep(2000);
	                    Num = generator.nextInt(2);
	                    if(Num == 1) {
	                        $("input", 3).setValue("아디다스");
	                        System.out.println("검색어 : 아디다스");
	                    } else if (Num == 0) {
	                        $("input", 3).setValue("나이키");
	                        System.out.println("검색어 : 나이키");
	                    }
	                    $(".btnSearch", 2).click();
	                    Num = generator.nextInt(11);
	                    $("img", Num+3).click();; //상품 클릭
	                    System.out.println(Num + "번째 상품 클릭");
	                    Num = generator.nextInt(2);
	                    if(Num == 0) {
	                        //주문/결제 페이지에서 주문/결제 완료
	                    	$(".btnBuy", 1).waitUntil(visible, 10000);
	                    	$(".btnBuy", 1).click();
	                    	$(".theme1", 1).waitUntil(visible, 10000);
	                    	$(".theme1", 1).click();
	            			sleep(2500);
	                    	$("#rname").waitUntil(visible, 10000);
	                    	$("#rname").setValue("비회원 구매 ID " + ID); //받는사람
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("성내동 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("비회원 구매 나머지 주소 " + ID); //나머지 주소
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
	                        $("#pname").setValue("비회원 구매 입금자명 " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("비회원 상품명 검색해서 일반 구매 ---");
	                        sleep(2000);
	                    } else if (Num == 1) { 
	                    	//장바구니 추가 후 구매완료
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
	                    	$("#rname").setValue("비회원 구매 ID " + ID); //받는사람
	                    	$("#btn_search_rzipcode").click();
	                    	sleep(1500);
	            			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
	            			$("#zboo_keyword").waitUntil(visible, 10000);
	            			$("#zboo_keyword").setValue("성내동 408-3");
	            			$("#zboo_search_btn").click();
	            			sleep(2000);
	            			$(".streetLine").waitUntil(visible, 10000);
	            			$(".streetLine").click();
	            			switchTo().defaultContent();
	            			sleep(1500);
	            			$("#raddr2").setValue("비회원 구매 나머지 주소 " + ID); //나머지 주소
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
	                        $("#pname").setValue("비회원 구매 입금자명 " + ID);
	                        $("#allAgree").click();
	                        $("#btn_payment").click();
	                    	System.out.println("비회원 상품명 검색해서 장바구니 구매 ---");
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
	                System.out.println("test" + ID + " 로그인");
	                sleep(2000);
	                Num = generator.nextInt(2); //1로 설정하면 첫 번째 수인 0만 나옴
	                System.out.println("--- Num_" + Num + " 이 0이면 카테고리 선택해서 구매, 1이면 검색어로 검색해서 구매, 2이면 게시판에 글쓰기");
	                if(Num == 0) { //카테고리 선택해서 구매
	                    Num = generator.nextInt(3);
	                    //카테고리 클릭해서 상품 구매 페이지 접근
	                    if(Url == 0) { //일반샵과 멀티샵의 메뉴 링크가 달라서 분기
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
	                        //주문/결제 페이지에서 주문/결제 완료
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
	                    	System.out.println("회원 카테고리 선택해서 일반 구매 ---");
	                        sleep(2000);	
	                    } else if (Num == 1) { //장바구니 추가 후 구매완료
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
	                    	System.out.println("회원 카테고리 선택해서 장바구니 구매 ---");
	                        sleep(2000);	
	                    }
	                } else if(Num == 1) {//검색어로 검색해서 구매
	                    $(".btnSearch", 1).click();
	                    sleep(2000);
	                    Num = generator.nextInt(2);
	                    if(Num == 1) {
	                        $("input", 3).setValue("아디다스");
	                        System.out.println("검색어 : 아디다스");
	                    } else if (Num == 0) {
	                        $("input", 3).setValue("나이키");
	                        System.out.println("검색어 : 나이키");
	                    }
	                    $(".btnSearch", 2).click();
	                    Num = generator.nextInt(11);
	                    $("img", Num+3).click();;
	                    System.out.println(Num + "번째 상품 클릭");
	                    Num = generator.nextInt(2);
	                    if(Num == 0) { //주문/결제 페이지에서 주문/결제 완료
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
	                    	System.out.println("회원 상품명 검색해서 일반 구매 ---");
	                        sleep(2000);
	                    } else if (Num == 1) { //장바구니 추가 후 구매완료
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
	                    	System.out.println("회원 상품명 검색해서 장바구니 구매 ---");
	                        sleep(2000);	
	                    }
	                    sleep(2000);
	                } else if(Num == 2) {//게시판에 글쓰기
	                    $(".btnCate").click();
	                    $(".link", 8).waitUntil(visible, 10000);
	                    Num = generator.nextInt(3);
	                    if(Num == 0) {//상품 사용후기 
	                        $(".link", 6).waitUntil(visible, 10000);
	                        $(".link", 6).click();
	                        sleep(1500);
	                        //$(".button", 2).waitUntil(visible, 10000);
	                        $(".button", 2).click();
	                        $("#subject").setValue("test" + ID);
	                        switchTo().frame("content_IFRAME"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
	                        $("#content_BODY").setValue("test" + ID);
	                        switchTo().defaultContent();
	                        $(".btnSubmit", 0).scrollIntoView(false);
	                        $(".btnSubmit", 0).click();
	                        sleep(1500);
	                        System.out.println("상품 사용후기 게시판에 글쓰기 ---");
	                    } else if(Num == 1) {//상품 Q&A
	                        $(".link", 7).waitUntil(visible, 10000);
	                        $(".link", 7).click();				
	                        sleep(1500);		
	                        //$(".button", 2).waitUntil(visible, 10000);
	                        $(".button", 2).click();
	                        $("#subject").setValue("test" + ID);
	                        switchTo().frame("content_IFRAME"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
	                        $("#content_BODY").setValue("test" + ID);
	                        switchTo().defaultContent();
	                        $(".btnSubmit", 0).scrollIntoView(false);
	                        $(".btnSubmit", 0).click();
	                        sleep(1500);
	                        System.out.println("상품 Q&A 게시판에 글쓰기 ---");
	                    } else if(Num == 2) {//자유게시판
	                        $(".link", 8).waitUntil(visible, 10000);
	                        $(".link", 8).click();
	                        sleep(2000);
	                        //$(".btnStrong", 0).waitUntil(visible, 10000);
	                        $(".btnStrong", 0).click();
	                        $("#subject").setValue("test" + ID);
	                        switchTo().frame("content_IFRAME"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
	                        $("#content_BODY").setValue("test" + ID);
	                        switchTo().defaultContent();
	                        $(".btnSubmit", 0).scrollIntoView(false);
	                        $(".btnSubmit", 0).click();
	                        sleep(1500);
	                        System.out.println("자유게시판에 글쓰기 ---");
	                    }
	                } else {
	                    System.out.println("--- 로그인 후 에러나서 종료 ---");
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
	            if(Url == 0) { //일반샵과 멀티샵의 메뉴 링크가 달라서 분기
		            System.out.println("--- 일반샵으로 " + y + "번 구매 데이터 쌓음 ---");                      	
	            } else if(Url == 1) {
		            System.out.println("--- 멀티샵1로 " + y + "번 구매 데이터 쌓음 ---");
	            } else if(Url == 2) {
		            System.out.println("--- 멀티샵2로 " + y + "번 구매 데이터 쌓음 ---");
	            }
	            sleep(1000);
	        }
	    }
	}

	//@Test(priority = 0)
	public void 다이티카페24회원가입및검색후구매() {
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
			$("#name").setValue("test" + i + "의 이름");
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
            System.out.println("test" + i + " 계정 회원가입 완료");
			sleep(1500);
			open("https://dighty.cafe24.com/member/modify.html");
			$("#member_id").waitUntil(visible, 10000);
			$("#passwd").setValue("qordlf!@34");
			sleep(1500);
			$("#user_passwd_confirm").setValue("qordlf!@34");
			sleep(1500);
			$("#postBtn").click();
			sleep(1500);
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
			$("#zboo_keyword").waitUntil(visible, 10000);
			$("#zboo_keyword").setValue("성내동 408-3");
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
			confirm("회원정보 수정이 완료되었습니다.");
			System.out.println("test" + i + " 배송지 정보 입력");
			sleep(1500);
			$("#icon_search").click();
			sleep(1500);
			int search = generator.nextInt(3);
			if(search == 0){
				$("#keyword").setValue("인사이트");
				
            } else if(search == 1) {
    			$("#keyword").setValue("앱");
            } else if(search == 2) {
    			$("#keyword").setValue("중고거래");
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
            System.out.println(search + " 상품 페이지 접근");
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
            //상품 개수 선택
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
			$("#bankaccount").selectOption("기업은행:873201 최영권"); 
			sleep(1000);
			$("#chk_purchase_agreement0").click();
			sleep(1000);
			$("#btn_payment").click();
			sleep(1000);
			System.out.println("test" + i + " 구매 완료");
			open("https://dighty.cafe24.com/exec/front/Member/logout");
			sleep(1000);
		}		
	}
	
	//@Test(priority = 0)
	public void 에카_노트_제품타입바꾸기() {
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
				$("#wifi_" + i).selectOption("제공함");
				sleep(500);
				$("#touch_" + i).selectOption("제공함");
				sleep(500);
				$("#insert" + i).click();
				sleep(500);
				confirm("저장하시겠습니까?");
				sleep(1500);
				System.out.println(i + " 번째 행 수정");				
			}
			//open("https://acecounter.com/Thrkfl/mobile_me/index.amz?page=" + x + "&search_type=name&search_msg=%EB%85%B8%ED%8A%B8&sort_val=");
			//System.out.println(x + " 번째 페이지 제품 타입 수정");
			//sleep(1000);
		}		
	}
	
	//@Test(priority = 0)
	public void AC내부이벤트설정추가() {
		open("https://www.acecounter.com/login_pt.html?aWQ9%7B%7C%7D5ilon%7B%82%7C%83u%83%40v%83%82ZGlnaHR5X2MyNCZwdz03NzJiNTQyZjU1NTI1MDM5Njg0ZjZhNTA1MTc0NzY2ZjYzNDYzNjQ0NmMzNTY0NzM3NDZjNTEzZCZtb2RlPWxvZ2luJmNvbm5fZGVzdD1hZG1pbiZkYXRlbGltaXQ9NVlXUnRhVzQlMjUzRA%3D%3D");
		//open("https://www.acecounter.com/login_pt.html?aWQ9%7B%7C%7D5ilon%7B%82%7C%83u%83%40v%83%82YnRlbXBlc3QyX2MyNCZwdz03NzJiNTQyZjU1NTI1MDM5Njg0ZjZhNTA1MTc0NzY2ZjYzNDYzNjQ0NmMzNTY0NzM3NDZjNTEzZCZtb2RlPWxvZ2luJmNvbm5fZGVzdD1hZG1pbiZkYXRlbGltaXQ9NVlXUnRhVzQlMjUzRA%3D%3D");
		sleep(2000);
		open("https://qa.acecounter.com/stat/view/manager5_4_1.amz");		
		for(int i=45;i<=100;i++) {
			sleep(2000);
			$("#promo_name").click();
			System.out.println("이벤트 그룹명 입력창 클릭1");
			sleep(2000);
			$("#promo_name").setValue("");
			sleep(1000);
			$("#promo_name").setValue("test" + i);
			$("#banner_name_1").setValue("test" + i);
			System.out.println("입력값 입력");
			sleep(2000);
			$("#promo_name").click();
			System.out.println("이벤트 그룹명 입력창 클릭2");
			/*
			sleep(10000);
			$("#promo_name").setValue("");
			sleep(1000);
			$("#promo_name").setValue("test" + i);
			System.out.println("이벤트 그룹명 입력");
			$("#promo_name").click();
			System.out.println("입력창 클릭2");
			*/
			$(".btn_responsive", 2).click();
			System.out.println("등록버튼 클릭1");
			sleep(1000);
			confirm("중복된 이벤트 그룹명입니다.\n" + "이벤트 그룹명을 다시 입력하세요.");			
			sleep(12000);
			System.out.println($("#over_chk").text().trim());
			$(".btn_responsive", 2).click();
			System.out.println("등록버튼 클릭2");
			sleep(14000);
			confirm("이벤트 그룹에 1개의 내부 이벤트가 등록되었습니다.");
			System.out.println("alert 확인 처리");
			sleep(2000);
			open("https://qa.acecounter.com/stat/view/manager5_4_1.amz");
			sleep(2000);
		}
	}
	
	//@Test(priority = 0)
	public void 다이티카페24회원가입() {
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
			$("#name").setValue("test" + i + "의 이름");
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
			System.out.println("test" + i + " 가입 완료");
			sleep(1000);
		}		
	}

	//@Test(priority = 1)
	public void 다이티카페24배송지등록() {
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
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
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
			confirm("회원정보 수정이 완료되었습니다.");
			System.out.println("test" + i + " 배송지 정보 입력");
			//로그아웃
			open("https://dighty.cafe24.com/exec/front/Member/logout");
			sleep(1000);
		}		
	}

	@Test(priority = 2)
	public void 다이티카페24로그인후구매() {
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
            int id = generator.nextInt(1999); //999면 1~1000번 까지
			$("#member_id").setValue("test" + id);
			System.out.println("test" + id + " 구매 시작");
			sleep(500);
			$("#member_passwd").setValue("qordlf!@34");
			sleep(500);
			$(".btnLogin_bk").click();
			sleep(500);
			System.out.println("test" + id + " 로그인 완료");
			//여기까지 로그인
			
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
            //상품 상세보기 페이지 접근
			System.out.println("test" + id + " // " + page + " 상품 상세 페이지 접근");
			//상품 개수 선택
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
			System.out.println("test" + id + " // " + page + " 상품 수량 설정");
            sleep(500);
			$(".sub_cart").click();
			System.out.println("test" + id + " 장바구니 담기 완료");
			sleep(500);
			$(".selected").scrollIntoView(true);
			System.out.println("장바구니 페이지 스크롤 이동");
			sleep(1000);
			$(".btnSubmitFix").click();
			sleep(1000);
			//$(".totalArea", 0).scrollIntoView(true);
			refresh();
			sleep(2000);
			$(".gIndent20").scrollIntoView(true);
			System.out.println("주문서작성 페이지 스크롤 이동");
			$("#pname").setValue("test" + id);
			sleep(1500);
			//$("#bankaccount").click();
			//$("#bankaccount").selectOption("기업은행:873201 최영권");
            $("#bankaccount").selectOption(1);
			sleep(500);
			$("#bankaccount").selectOption(1);
			sleep(500);
			$("#chk_purchase_agreement0").click();
			sleep(500);
			$("#btn_payment").click();
			sleep(1000);
			System.out.println("test" + id + " 구매 완료");
			open("https://dighty.cafe24.com/exec/front/Member/logout");
			System.out.println((i+1) + "번째 구매 완료");
		}		
	}
	
	//@Test(priority = 0)
	public void NA스크립트설치확인() {
		//URL과 NAkey 개수에 따라 배열 크기 조절
		String[] URL = new String[157];
		URL[0]="http://solhelmet.co.kr";
		URL[1]="http://www.talktalkacademy.com";
		URL[2]="http://ridenroll.co.kr/";
		URL[3]="https://kids-time.co.kr";
		URL[4]="https://m.kids-time.co.kr";
		URL[5]="https://girlskate.kr";
		URL[6]="http://mtable.kr";
		URL[7]="http://ashirt.kr";
		URL[8]="http://www.wanistory.com";
		URL[9]="http://initials.co.kr";
		URL[10]="http://www.morgancoffee.co.kr";
		URL[11]="https://dlclms.daelim.ac.kr";
		URL[12]="http://yootongnara.com";
		URL[13]="https://mirrorliang.com";
		URL[14]="http://www.modeunok11.com";
		URL[15]="https://illumiel.co.kr";
		URL[16]="https://ordire.shop";
		URL[17]="http://anjunsobang.co.kr";
		URL[18]="https://agreemall.kr";
		URL[19]="https://polakorea.com";
		URL[20]="https://ikta.co.kr";
		URL[21]="http://roserosa.co.kr";
		URL[22]="http://secondstudio.co.kr";
		URL[23]="https://우리유weyou.com";
		URL[24]="http://yootongnara.com";
		URL[25]="http://www.fiedwear.com";
		URL[26]="http://updog.kr";
		URL[27]="https://lalamina.co.kr";
		URL[28]="https://dong25gam.com";
		URL[29]="https://camp1st.co.kr";
		URL[30]="https://www.rightgarden.co.kr";
		URL[31]="https://rebbly.co.kr";
		URL[32]="https://m.margarer.com";
		URL[33]="https://truckfactory.kr";
		URL[34]="http://www.wishpick.co.kr";
		URL[35]="http://tellomall.co.kr";
		URL[36]="http://www.futuredigm.co.kr";
		URL[37]="https://olg.co.kr";
		URL[38]="https://contempoffice.com";
		URL[39]="https://olg.co.kr";
		URL[40]="http://www.ddak-joa.co.kr";
		URL[41]="https://seeonein2.cafe24.com";
		URL[42]="http://l1solution.kr";
		URL[43]="http://웃음꽃요양원.com";
		URL[44]="https://www.tblbike.com";
		URL[45]="https://jubang01024.cafe24.com";
		URL[46]="http://www.casanine.co.kr";
		URL[47]="https://www.instagram.com/sun_pole_studio/";
		URL[48]="http://www.corebody.co.kr";
		URL[49]="http://www.makemakers.co.kr";
		URL[50]="http://www.스타간판.kr";
		URL[51]="https://tenz.kr";
		URL[52]="https://shopamomento.com";
		URL[53]="https://bulkcook.co.kr";
		URL[54]="https://bubbleroom.kr";
		URL[55]="http://eimokdesign.com";
		URL[56]="https://lostmanagementcities.com";
		URL[57]="http://www.bangyudang.com";
		URL[58]="http://m.slmedi.com";
		URL[59]="http://www.powerfulx.com";
		URL[60]="https://m.powerfulx.com";
		URL[61]="http://seeoneshop.com";
		URL[62]="https://m.place.naver.com/restaurant/37814220";
		URL[63]="http://ah.clinic";
		URL[64]="https://makemakers.co.kr";
		URL[65]="https://vagokorea.co.kr";
		URL[66]="https://www.pickpop.co.kr";
		URL[67]="http://ermen.kr";
		URL[68]="https://xn--hu5b25jftau9j.com";
		URL[69]="http://www.groundtree.kr";
		URL[70]="https://m.place.naver.com/restaurant/11823021";
		URL[71]="https://tax-back.kr";
		URL[72]="http://quicksmart.shop";
		URL[73]="https://www.unitmonster.com";
		URL[74]="https://dorogift.com";
		URL[75]="https://evmoab2b.co.kr";
		URL[76]="https://m.miraldeco.store";
		URL[77]="https://www.miraldeco.store";
		URL[78]="http://m.77in.co.kr";
		URL[79]="http://www.shfastener.co.kr";
		URL[80]="https://hiddengarden.kr";
		URL[81]="http://www.pacificrentcar.kr";
		URL[82]="http://www.mymentor.co.kr";
		URL[83]="http://invictuskorea.shop";
		URL[84]="http://www.sin-nong.com";
		URL[85]="https://doldolparty.com";
		URL[86]="https://www.intotheblue.kr";
		URL[87]="http://anyfun.store";
		URL[88]="http://www.doctorwish.co.kr";
		URL[89]="https://gamtancoffee.com";
		URL[90]="https://2square.co.kr";
		URL[91]="http://godofcoffees.com";
		URL[92]="http://m.ijungmall.com";
		URL[93]="https://thesoseol.co.kr";
		URL[94]="https://www.instagram.com/babyhausgimpo/";
		URL[95]="https://dorogift.com";
		URL[96]="http://mayjaymall.com";
		URL[97]="http://www.beadsmaker.com";
		URL[98]="http://time-gallery.co.kr";
		URL[99]="https://healthtec.co.kr";
		URL[100]="http://www.toyota-rentcar.co.kr";
		URL[101]="http://yomimonshop.com";
		URL[102]="https://upliving.co.kr";
		URL[103]="https://pamseshop.co.kr";
		URL[104]="http://huereefestival.com";
		URL[105]="https://www.organicchicken.shop";
		URL[106]="http://hedon.co.kr/";
		URL[107]="https://aminosunmall.kr";
		URL[108]="http://bodyreform.co.kr";
		URL[109]="https://shoppung.co.kr";
		URL[110]="http://lalamina.co.kr";
		URL[111]="https://slonous.co.kr";
		URL[112]="http://www.lavaudieu.co.kr";
		URL[113]="http://www.chitsolnara.co.kr";
		URL[114]="https://www.yh105.kr";
		URL[115]="http://www.coverok.com";
		URL[116]="http://www.bellhelmets.co.kr";
		URL[117]="https://hashbeauty.co.kr";
		URL[118]="https://m.hiddengarden.kr";
		URL[119]="https://yummilab.co.kr";
		URL[120]="https://www.manggle.co.kr";
		URL[121]="https://aminosunmall.kr";
		URL[122]="https://healtamin.co.kr";
		URL[123]="https://cba.imweb.me";
		URL[124]="https://glamr.kr";
		URL[125]="https://m.beautymansour.com";
		URL[126]="https://crefactory.shopby.co.kr";
		URL[127]="https://www.ovice.com/ko";
		URL[128]="https://m.healtamin.co.kr";
		URL[129]="https://camp1st.co.kr";
		URL[130]="https://m.lil-xarad.com";
		URL[131]="https://lil-xarad.com";
		URL[132]="http://www.lavaudieu.co.kr/m";
		URL[133]="https://디지털플란트.com";
		URL[134]="https://earthniqmall.kr";
		URL[135]="http://혜택쓰.kr";
		URL[136]="https://drgela.com";
		URL[137]="http://www.tarrtarr.com";
		URL[138]="https://m.shupong.com";
		URL[139]="http://caremall.kr";
		URL[140]="https://lsr9373.cafe24.com";
		URL[141]="https://www.roedeerclubhaus.com";
		URL[142]="https://www.feelkeen.com";
		URL[143]="https://wlab.co.kr";
		URL[144]="https://www.seller-bridge.com";
		URL[145]="https://likecell.co.kr";
		URL[146]="https://digitalssaem.com";
		URL[147]="https://www.mcfood.net";
		URL[148]="http://www.agonstudio.com";
		URL[149]="https://gradiens.co.kr";
		URL[150]="https://m.saffron-recipe.com";
		URL[151]="http://www.hyup-shin.co.kr";
		URL[152]="https://hautuki.co.kr";
		URL[153]="https://cowayrental.net";
		URL[154]="http://www.skmagic114.net";
		URL[155]="https://yg.modetour.co.kr";
		URL[156]="http://meejee.com";
	
	
		String[] NAkey = new String[157];
		NAkey[0]="s_305439217f87";
		NAkey[1]="s_cdc9fd0564c";
		NAkey[2]="s_4ab77e7acd66";
		NAkey[3]="s_322b9bf9e210";
		NAkey[4]="s_1340dab4031e";
		NAkey[5]="s_1e2a9f7a7d3a";
		NAkey[6]="s_1ffc80df7140";
		NAkey[7]="s_4e5d4552ef88";
		NAkey[8]="s_2d9add6553e";
		NAkey[9]="s_313e7ad9b7b0";
		NAkey[10]="s_856d05967a3";
		NAkey[11]="s_3a5c32a51c18";
		NAkey[12]="s_2bccb7834ae0";
		NAkey[13]="s_1b704dd3573d";
		NAkey[14]="s_35cf1c7c3d84";
		NAkey[15]="s_a288256a236";
		NAkey[16]="s_256fc0e457eb";
		NAkey[17]="s_502a128b863c";
		NAkey[18]="s_10863cbe03d4";
		NAkey[19]="s_2e89c1ed0702";
		NAkey[20]="s_55a57719bdef";
		NAkey[21]="s_27432606208f";
		NAkey[22]="s_594848d7381b";
		NAkey[23]="s_2cb815056c25";
		NAkey[24]="s_2bccb7834ae0";
		NAkey[25]="s_f9d8c4146db";
		NAkey[26]="s_36b8faccf3b2";
		NAkey[27]="s_4abb792f4270";
		NAkey[28]="s_5117d2d2d9b7";
		NAkey[29]="s_ce2e949de01";
		NAkey[30]="s_1b6eb8f0b1d1";
		NAkey[31]="s_49d2873d92a7";
		NAkey[32]="s_5202167e7118";
		NAkey[33]="s_3973b71cee03";
		NAkey[34]="s_3ee8af6059b5";
		NAkey[35]="s_ce20e43236e";
		NAkey[36]="s_3a5c8fd94c66";
		NAkey[37]="s_1512552ef03e";
		NAkey[38]="s_1f1380830e8f";
		NAkey[39]="s_1512552ef03e";
		NAkey[40]="s_29fd979cbe58";
		NAkey[41]="s_a27bece5e1c";
		NAkey[42]="s_511953768237";
		NAkey[43]="s_47182ccaeb54";
		NAkey[44]="s_48e9d9ab3d42";
		NAkey[45]="s_4e5ecac84e19";
		NAkey[46]="s_3c2cfe77d5e0";
		NAkey[47]="";
		NAkey[48]="s_34df5d4cc48f";
		NAkey[49]="s_10862bd52706";
		NAkey[50]="s_276386ac93";
		NAkey[51]="s_29fd948789b2";
		NAkey[52]="s_54bc3a67d926";
		NAkey[53]="s_2ae65eec7a32";
		NAkey[54]="s_2da0b53a2787";
		NAkey[55]="s_22b6e830895f";
		NAkey[56]="s_55a4bfb46ef9";
		NAkey[57]="s_53ccc3c3c36b";
		NAkey[58]="s_67dfabee253";
		NAkey[59]="s_3057e6934a72";
		NAkey[60]="s_3057e6934a72";
		NAkey[61]="s_1f1113f12a50";
		NAkey[62]="";
		NAkey[63]="s_76e0409a6a9";
		NAkey[64]="s_10862bd52706";
		NAkey[65]="s_256db6ab5546";
		NAkey[66]="s_2571630d6c46";
		NAkey[67]="s_53d29e5d5243";
		NAkey[68]="s_76e071d22f9";
		NAkey[69]="s_3caae5bfa17";
		NAkey[70]="";
		NAkey[71]="s_314441669b09";
		NAkey[72]="s_4b38fdc308c";
		NAkey[73]="s_3cabb38a6fb";
		NAkey[74]="s_29146c701f91";
		NAkey[75]="s_305b6fdabcd1";
		NAkey[76]="s_3cabfe0426e";
		NAkey[77]="s_3cabfe0426e";
		NAkey[78]="s_419b5240b26d";
		NAkey[79]="s_b115edacbff";
		NAkey[80]="s_305b6f57613a";
		NAkey[81]="s_93fb79cc342";
		NAkey[82]="s_34e7353c10e2";
		NAkey[83]="s_3315e3697ad5";
		NAkey[84]="s_4abb7ab806bd";
		NAkey[85]="s_419ba1a8f7d0";
		NAkey[86]="s_27432504a86f";
		NAkey[87]="s_1e2ad1d7a158";
		NAkey[88]="s_1257e3761139";
		NAkey[89]="s_1c53c88a1b1e";
		NAkey[90]="s_3b456484c08c";
		NAkey[91]="s_35cec583d6f7";
		NAkey[92]="s_29f81d5d364e";
		NAkey[93]="s_5776f5abd379";
		NAkey[94]="";
		NAkey[95]="s_29146c701f91";
		NAkey[96]="s_856d16f944f";
		NAkey[97]="s_462afd17ac44";
		NAkey[98]="s_150af8609c46";
		NAkey[99]="s_2e89c4002b70";
		NAkey[100]="s_68539ad8d17";
		NAkey[101]="s_322d192533e0";
		NAkey[102]="s_4b291ef9a20";
		NAkey[103]="s_22b24d6758d9";
		NAkey[104]="s_199eb05afda5";
		NAkey[105]="s_ce287f2a00f";
		NAkey[106]="s_3a5c9366ea81";
		NAkey[107]="s_585fcce682a5";
		NAkey[108]="s_3fd18fd9a9fe";
		NAkey[109]="s_282bd5144223";
		NAkey[110]="s_4abb792f4270";
		NAkey[111]="s_4ba40e64a946";
		NAkey[112]="s_bfa26975414";
		NAkey[113]="s_3c26027546f4";
		NAkey[114]="s_4abb899820b7";
		NAkey[115]="s_1d39c4892725";
		NAkey[116]="s_48010912ef13";
		NAkey[117]="s_3a5c553525b2";
		NAkey[118]="s_305b6f57613a";
		NAkey[119]="s_3ca994ce4c3";
		NAkey[120]="s_43736afeb6c4";
		NAkey[121]="s_585fcce682a5";
		NAkey[122]="s_4f4797b407d5";
		NAkey[123]="s_10865e5225f5";
		NAkey[124]="s_51192e54fdca";
		NAkey[125]="s_2743247d65c5";
		NAkey[126]="s_4374e60511e5";
		NAkey[127]="s_53d3d7955f97";
		NAkey[128]="s_4f4797b407d5";
		NAkey[129]="s_ce2e949de01";
		NAkey[130]="s_35d05dbb31e2";
		NAkey[131]="s_35d05dbb31e2";
		NAkey[132]="s_bfa26975414";
		NAkey[133]="s_93fbcc39bb0";
		NAkey[134]="s_76d855368c2";
		NAkey[135]="s_20e551ee0702";
		NAkey[136]="s_2571792746b0";
		NAkey[137]="s_53ced64ac815";
		NAkey[138]="s_15fb5927613b";
		NAkey[139]="s_276c5283a2";
		NAkey[140]="s_445db9142171";
		NAkey[141]="s_2f7298a698d8";
		NAkey[142]="s_48010c3abd7d";
		NAkey[143]="s_290e2d53f0aa";
		NAkey[144]="s_322d19994f52";
		NAkey[145]="s_856b16e7da8";
		NAkey[146]="s_52eb04fc0d34";
		NAkey[147]="s_49ca7f5e754d";
		NAkey[148]="s_17cd06997bbd";
		NAkey[149]="s_ce30f5504a9";
		NAkey[150]="s_462f4fecb23d";
		NAkey[151]="s_57772bba420b";
		NAkey[152]="s_3ee831b7795f";
		NAkey[153]="s_4629e8a23d22";
		NAkey[154]="s_2dd2be7eda4";
		NAkey[155]="s_1f8f72fcfd6";
		NAkey[156]="s_68533022b17";
	
	
		for(int i=0;i<=156;i++) {
			//URL 접근 불가 시 예외처리
			try {
				open(URL[i]);
				System.out.print(i + ". " + URL[i]);
				//URL 형식 체크
				if(URL[i].substring(0, 4).equals("http")) {
					//html의 script 태그 wcs_add 여부 체크
					ElementsCollection scriptsCheck = Selenide.$$("script");
					boolean textFound = false;
			        for (int x = 0; x < scriptsCheck.size(); x++) {
			            if (scriptsCheck.get(x).innerHtml().contains("wcs_add")) {
			                textFound = true;
			                break;
			            }
			        }
			        //html에 wcs_add가 있으면 NAkey 추출해서 체크
			        if (textFound) {
						String HtmlSplit = $("html").innerHtml().trim().replaceAll(" ", "").replaceAll("	", "").replaceAll("\n", "");
						String[] naKeySplit = HtmlSplit.split("wcs_add\\[\"wa\"\\]\\=\"");
						String[] naKeycompare = naKeySplit[1].split("\"");
						if(NAkey[i].equals(naKeycompare[0])) {
							System.out.println("는" + NAkey[i]);
						} else {
							//로그 미출력시 에러 발생 부분 찾기 힘들어서 모두 출력 후 엑셀에서 구분기호로 텍스트 나누기
							System.out.println("☆NAkey가 다릅니다.");
						}
			        } else {
						//로그 미출력시 에러 발생 부분 찾기 힘들어서 모두 출력 후 엑셀에서 구분기호로 텍스트 나누기
			        	System.out.println("☆NA 스크립트가 설치되어 있지 않습니다.");
			        }
				} else {
					//로그 미출력시 에러 발생 부분 찾기 힘들어서 모두 출력 후 엑셀에서 구분기호로 텍스트 나누기
					System.out.println("☆URL 형식이 비정상 입니다.");
				}
			} catch (Exception e) {
				//로그 미출력시 에러 발생 부분 찾기 힘들어서 모두 출력 후 엑셀에서 구분기호로 텍스트 나누기
				System.out.println(i + ". " + URL[i] + "☆URL 접근이 불가합니다.");				
			}
	
		}
	}

	//@Test(priority = 0)
	public void vklog테스트몰회원가입확인() {
		for(int i=0;i<=9;i++) {
			open("http://vklog.loginside.co.kr/member/join.php");
			open("http://naver.com");
			$("#memId").waitUntil(visible, 2000);
			$("#memId").val("qwer" + i);
			$("#newPassword").val("qordlf12");
			$(".check-id").val("qordlf12");
			$("input[name=memNm]").val("rewq" + i + " 이름");
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
			//confirm("로그아웃 완료");
			//sleep(1000);			
			System.out.println("qwer" + i + " 번 가입 완료");
		}		
	}
	//@Test(priority = 0)
	public void vklog테스트몰회원탈퇴확인() {
		for(int i=0;i<=497;i++) {
			open("http://vklog.loginside.co.kr/member/login.php");
			$("#loginId").waitUntil(visible, 2000);
			$("#loginId").val("qwer" + i);
			$("#loginPwd").val("qordlf12");
			$(".l-login").click();
			sleep(2500);		
			$(".container > ul > li", 2).click();
			sleep(1000);
			System.out.println("qwer" + i + " 번 탈퇴 완료");
		}		
	}
	//@Test(priority = 3)
	public void 에카1데이터쌓기() {
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
				System.out.println("apzz0928.blogspot 을 " + x + " 번째 클릭하였습니다. // 페이지명 : " + $(".sub" + x).text().trim());
				$(".sub" + x).click();
			}
			open("http://apzz0928.egloos.com/");
			for(int x=1;x<=6;x++) {
				$("a", (x+5)).waitUntil(visible, 10000);
				$("a", (x+5)).scrollIntoView(false);
				System.out.println("apzz0928.egloos 를 " + x + " 번째 클릭하였습니다. // 페이지명 : " + $("a", (x+5)).text().trim());
				$("a", (x+5)).click();
			}
			open("http://apzz0928.egloos.com/");
			for(int x=1;x<=13;x++) {
				$("a", (x+12)).waitUntil(visible, 10000);
				$("a", (x+12)).scrollIntoView(false);
				System.out.println("apzz0928.egloos 를 " + (x+6) + " 번째 클릭하였습니다. // 페이지명 : " + $("a", (x+12)).text().trim());
				$("a", (x+12)).click();
			}
			*/
			open("http://apzz092888.blogspot.com");
			for(int x=1;x<=24;x++) {
				$(".sub" + x).waitUntil(visible, 10000);
				$(".sub" + x).scrollIntoView(false);
				$(".sub" + x).scrollIntoView(false);
				//System.out.println("apzz092888.blogspot 을 " + x + " 번째 클릭하였습니다. // 페이지명 : " + $(".sub" + x).text().trim());
				$(".sub" + x).click();
			}
			open("http://apzz0928.blogspot.com");
			for(int x=1;x<=40;x++) {
				$(".sub" + x).waitUntil(visible, 10000);
				$(".sub" + x).scrollIntoView(false);
				$(".sub" + x).scrollIntoView(false);
				//System.out.println("apzz0928.blogspot 을 " + x + " 번째 클릭하였습니다. // 페이지명 : " + $(".sub" + x).text().trim());
				$(".sub" + x).click();
			}
			open("https://apzz0928.tistory.com");
			/*
			for(int x=0, y=1;x<=14;x++) {
				if(x % 2 == 0) {
					$("img", x).click();
					switchTo().window(y);
					y++;
					System.out.println("apzz0928.tistory " + y + "번 배너를 클릭하였습니다.");
				}
			}
			*/
			/*
			open("http://apzz09288.egloos.com");
			for(int x=1;x<=7;x++) {
				$(".sub" + x).waitUntil(visible, 10000);
				$(".sub" + x).scrollIntoView(false);
				System.out.println("apzz09288.egloos 를 " + x + " 번째 클릭하였습니다. // 페이지명 : " + $("a", (x+5)).text().trim());
				$(".sub" + x).click();
			}
			open("http://apzz0928.egloos.com");
			*/
		}
	}

	//@Test(priority = 0)
	public void cafe24공통회원가입() {
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
			$("#name").setValue("test" + i + "의 이름");
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
			System.out.println("test" + i + " 가입 완료");
			sleep(1000);
		}		
	}
	
	//@Test(priority = 1)
	public void cafe24기본샵배송지() {
		for(int i=1;i<=6;i++) {
			open("http://shop38.apzz1210.cafe24.com/member/login.html");
			$(".btnLogin").waitUntil(visible, 10000);
			sleep(1500);
			System.out.println("test" + i + " 로그인");
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
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
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
			confirm("회원정보 수정이 완료되었습니다.");
			System.out.println("test" + i + " 배송지 정보 입력");
			//로그아웃
			open("http://shop38.apzz1210.cafe24.com/exec/front/Member/logout");
			sleep(1500);
		}
	}
	
	//@Test(priority = 0)
	public void 카페24배송지등록() {
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
			$("#ma_rcv_title").setValue("멀티샵2 test" + i + "의 배송지명");
			$("#ma_rcv_name").setValue("멀티샵2 test" + i + "의 이름");
			$("#SearchAddress").click();
			sleep(1500);
			switchTo().frame("iframeZipcode"); //iframe id="iframeZipcode" 형식일 경우 프레임 이름은 텍스트만 작성
			$("#zboo_keyword").waitUntil(visible, 10000);
			$("#zboo_keyword").setValue("성내동 408-3");
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
			System.out.println("test" + i + " 배송지 정보 입력");
			//로그아웃
			open("http://shop3.apzz0928.cafe24.com/exec/front/Member/logout");
			sleep(1500);			
		}		
	}
	
	//@Test(priority = 10)
	public void 카페24회원가입() {
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
			$("#name").setValue("멀티샵2 test" + i + "의 이름");
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
			System.out.println("test" + i + " 가입 완료");
			sleep(1000);
		}		
	}
	
	//@Test(priority = 0)
	public void 백오피스뉴스룸등록() {
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
			confirm("등록하시겠습니까?");
			sleep(5000);
		}		
	}
	//@Test(priority = 0)
	public void 방문데이터() {
		for(int i=1;i<=999999;i++) {
			Date number_date = new Date();
			String date = number_format.format(number_date);
			open("https://www.naver.com/");
			js("alert('123')");
			confirm("123");
			open("http://nhnace1.godomall.com/");
			js("alert('123')");
			confirm("123");
			System.out.println(date + " 시간에 (" + i + ") 번 완료");
		}		
	}
	//@Test(priority = 0)
	public void 셀마다운로드체크() {
		open("https://self-market.dighty.com/login");
		$(".btn_full").waitUntil(visible, 10000);
		$(".inp", 0).setValue("apzz0928@nate.com");
		$(".inp", 1).setValue("qordlf12");
		$(".btn_full").click();
		sleep(3000);
		open("https://self-market.dighty.com/history");
		$("td", 23).waitUntil(visible, 10000);
		for(int i=0;i<=99999;i++) {
			if($("td", 18).text().trim().equals("진행중")) {
				Date number_date = new Date();
				String date = number_format.format(number_date);
				System.out.println(date + " 진행중 체크 완료");
				sleep(1000);
				refresh();
			} else if ($("td", 18).text().trim().equals("다운로드")) {
				Date number_date = new Date();
				String date = number_format.format(number_date);
				System.out.println(date + " 다운로드 체크 완료");
				break;
			} else if($("td", 18).text().trim().equals("실패")) {
				Date number_date = new Date();
				String date = number_format.format(number_date);
				System.out.println(date + " 실패 체크 완료");
				break;
			}
		}
	}
	//@Test(priority = 0)
	public void 데이터카탈로그() {
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
	public void 테스트() {
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
	public void RCM데이터쌓기() {
		for(int i=1;i<=5;i++) {
			open("http://apzz0928.blogspot.com");
			$(".cmcm5").waitUntil(visible, 10000);
			if(i == 1 || i == 2 || i == 5) {
				for(int x=1;x<=10;x++) {
					$(".cmcm" + i + " > a > img").waitUntil(visible, 15000);
					js("document.querySelector('.cmcm" + i + " > a').setAttribute('target', '');");
					sleep(500);
					$(".cmcm" + i).click();
					System.out.println("blogspot " + i + "번 배너를 " + x + " 번째 클릭중");
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
					System.out.println("tistory " + i + "번 배너를 " + x + " 번째 클릭중");
					$(".cmcm" + i).waitUntil(visible, 15000);
				}		
			}
			//}
		}
		System.out.println("RCM 데이터 쌓기 끝");
	}
	//@Test(priority = 0)
	public void 다이티백오피스_에이전시등록() {
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
			$(".txt-input", 1).setValue("에이전시명 " + i);
			$(".txt-input", 2).setValue("담당자명 " + i);
			$(".txt-input", 3).setValue("1234");
			$(".txt-input", 4).setValue("4321");
			$(".txt-input", 5).setValue("apzz0928@nhnace.com");
			sleep(1000);
			$(".btn-dark", 0).click();
			sleep(3500);
			$(".txt-input", 6).setValue("추가 안내 문구 " + i);
			sleep(1000);
			$(".btn-primary").click();
			System.out.println("에이전시 등록 " + i);		
			open("http://alpha-admin.dighty.com/customer/agency/form");
			sleep(1500);
		}
	}
	//@Test(priority = 0)
	public void 다이티백오피스_부관리자로그인() {
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
			$(".inp", 1).setValue("부관리자");
			$(".inp", 2).setValue("회사명" + i);
			$(".inp", 3).setValue("직위" + i);
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
			System.out.println("부관리자 등록 " + i);
			open("https://alpha.dighty.com/dashboard/sub-manager/register");
		}
	}
	//@Test(priority = 0)
	public void 다이티서비스관리_부관리자등록() {
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
			$(".inp", 1).setValue("부관리자");
			$(".inp", 2).setValue("회사명" + i);
			$(".inp", 3).setValue("직위" + i);
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
			System.out.println("부관리자 등록 " + i);
			open("https://alpha.dighty.com/dashboard/sub-manager/register");
		}
	}
	//@Test(priority = 0)
	public void 다이티백오피스_SMS발송() {
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
			$(".txt-input", 1).setValue("수신자 이름 " + i);
			$(".txt-input", 2).setValue("메시지명 " + i);
			$(".txt-input", 3).setValue("관리용 메모 " + i);
			$(".ico_radio", btnNum).click();
			sleep(1000);
			if((btnNum+1) % 2 == 0) {
				sleep(1000);
				$(".txt-input", 4).setValue("2020-08-19 " + (btnNum+12) + ":" + (btnNum+btnNum1+10));
				sleep(1000);
				$(".sms-content", 0).setValue(i + "번 내용 장문의 내용" + i + "번 내용 장문의 내용" + i + "번 내용 장문의 내용" + i + "번 내용 장문의 내용" + i + "번 내용 장문의 내용" + i + "번 내용 장문의 내용" );
				$(".txt-input", 5).setValue("제목 " + i);
			} else {
				$(".sms-content", 0).setValue(i + "번 내용");
			}			
			sleep(1000);
			$(".confirm").click();
			confirm("SMS를 발송하시겠습니까?\n확인 버튼을 누르면 발송 처리됩니다.");
			sleep(3500);
			System.out.println("메일 발송 " + i);
			open("http://alpha-admin.dighty.com/contents/sms/send");
			sleep(1500);
		}
	}
	//@Test(priority = 0)
	public void 다이티백오피스_메일발송() {
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
			$(".txt-input", 2).setValue(i + "번 메일");
			$(".confirm").scrollIntoView(true);
			$(".confirm").scrollIntoView(true);
			$(".confirm").click();
			confirm("메일을 발송하시겠습니까?\n확인 버튼을 누르면 바로 발송 처리됩니다.");
			sleep(3500);
			System.out.println("메일 발송 " + i);
		}
	}
	//@Test(priority = 0)
	public void 다이티백오피스_공지사항작성() {
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
			$(".txt-input").setValue("공지사항 제목 " + i);
			$(".ck-editor__editable_inline").setValue("공지사항 내용 " + i);
			sleep(1500);
			$(".confirm").scrollIntoView(true);
			$(".confirm").scrollIntoView(true);
			$(".confirm").click();
		}
	}
	//@Test(priority = 0)
	public void 다이티백오피스_문의하기() {
		open("http://alpha.dighty.com/login");
		sleep(3000);
		$(".btn_full").waitUntil(visible, 10000);
		$(".inp", 0).setValue("dightyapzz");
		System.out.println("아이디입력");
		$(".inp", 1).setValue("qordlf12");
		System.out.println("PW입력");
		$(".btn_full").click();
		System.out.println("로그인 버튼 클릭");
		sleep(1000);
		open("https://alpha.dighty.com/dashboard/inquiry");
		System.out.println("문의 작성 페이지로 이동");
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
			$(By.name("comment")).setValue("내용 입력창" + i);
			$(".w150", 0).scrollIntoView(false);
			$(".w150", 0).scrollIntoView(true);
			$(".w150", 0).click();
			sleep(1000);
			$("#confirmButton").click();
			sleep(3000);
			System.out.println("다이티 문의하기 등록 " + i);
			open("https://alpha.dighty.com/dashboard/inquiry");
			$(".w150", 0).waitUntil(visible, 10000);
		}
	}
	//@Test(priority = 0)
	public void 다이티백오피스_서비스문의() {
		open("http://alpha.dighty.com/");
		sleep(1000);
		$(".btn_ask").scrollIntoView(false);
		for(int i=0;i<=100;i++) {
			$("#form_1").setValue("이름 " + i);
			$("#form_2").setValue("회사명 " + i);
			$("#form_3").setValue(i + "mail@mail.com");
			$("#form_4").setValue("" + i);			
			$("#form_5").setValue("문의내용 " + i);
			$(".chk_img").click();
			sleep(1000);
			$(".btn_ask").click();
			sleep(1000);
			$("#confirmButton").click();
			sleep(1000);
			System.out.println("다이티 서비스문의 등록 " + i);
		}
	}
	//@Test(priority = 0)
	public void 다이티백오피스_상담내역() {
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
			$("#form-item-1").setValue("아이디 " + i);
			$("#form-item-4").selectOption(btnNum);
			$("#form-item-8").setValue("이름 " + i);
			$("#form-item-9").setValue("연락처 " + i);			
			$("#form-item-10").setValue(i + "mail@mail.com");
			$(".ck-editor__editable").setValue("내용 " + i);
			sleep(1000);
			$(".btn-primary").click();
			confirm("상담내역을 등록하시겠습니까?");
			sleep(5000);
			System.out.println("다이티 상담내역 등록 " + i);
		}
	}
	//@Test(priority = 0)
	public void CM임시() { //이거 배너 클릭 숫자 셀수있도록 수정
		for(int i=1;i<=50;i++) {
			open("http://apzz0928.blogspot.com");
			$(".cmcm1").waitUntil(visible, 10000);
			$(".cmcm" + 1 + " > a > img").waitUntil(visible, 15000);
			js("document.querySelector('.cmcm" + 0 + " > a').setAttribute('target', '');");
			sleep(500);
			$(".cmcm" + 1).click();
			System.out.println("blogspot 배너를 " + i + " 번째 클릭중");
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
			System.out.println("tistory 배너를 " + i + " 번째 클릭중");
			$(".cmcm" + 0).waitUntil(visible, 15000);
		}	
		System.out.println("RCM 데이터 쌓기 끝");
	}
	//@Test(priority = 0)
	public void Dighty부관리자삭제() {
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
	public void Dighty부관리자등록() {
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
	public void 서비스관리트렌드보고서배너속도확인() {
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
	public void 서비스관리하단() {
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
	public void 서비스관리하단배너속도확인() {
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
	public void 서비스관리하단1() {
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
	public void rcm라이브확인() {
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
					js("document.cookie = 'ACEUACS=1111111111111111111'"); //타겟 X 세그먼트 X / 1번 배너
				} else if (y==1) {
					js("document.cookie = 'ACEUACS=1585872393527761537'"); //타겟 A 세그먼트 678 / 2번 배너
				} else if (y==2) {
					js("document.cookie = 'ACEUACS=1576739137318761538'"); //타겟 B 세그먼트 680 / 3번 배너
				} else if (y==3) {
					js("document.cookie = 'ACEUACS=1577930839428761538'"); //타겟 C 세그먼트 681 / 4번 배너
				} else if (y==4) {
					js("document.cookie = 'ACEUACS=1581291211937761532'"); //타겟 D 세그먼트 682 / 4, 5, 6번 배너
				} else if (y==5) {
					js("document.cookie = 'ACEUACS=1581464072231761539'"); //타겟 E 세그먼트 682 / 6번 배너
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
				if(title1.equals("1번")) {
					b++;
				} else if (title1.equals("2번")){
					c++;
				} else if (title1.equals("3번")) {
					d++;
				} else if (title1.equals("4번")) {
					e++;
				} else if (title1.equals("5번")){
					f++;
				} else if (title1.equals("6번")) {
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
				System.out.println("1번 배너 노출 | 1번 배너 " + b + "번 / 2번 배너 " + c + "번 / 3번 배너 " + d + "번 / 4번 배너 " + e +  "번 / 5번 배너 " + f + "번 / 6번 배너 " + g + " 기본 배너 노출 횟수 " + h + "번");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==1) {
				System.out.println("2번 배너 노출 | 1번 배너 " + b + "번 / 2번 배너 " + c + "번 / 3번 배너 " + d + "번 / 4번 배너 " + e +  "번 / 5번 배너 " + f + "번 / 6번 배너 " + g + " 기본 배너 노출 횟수 " + h + "번");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==2) {
				System.out.println("3번 배너 노출 | 1번 배너 " + b + "번 / 2번 배너 " + c + "번 / 3번 배너 " + d + "번 / 4번 배너 " + e +  "번 / 5번 배너 " + f + "번 / 6번 배너 " + g + " 기본 배너 노출 횟수 " + h + "번");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==3) {
				System.out.println("4번 배너 노출 | 1번 배너 " + b + "번 / 2번 배너 " + c + "번 / 3번 배너 " + d + "번 / 4번 배너 " + e +  "번 / 5번 배너 " + f + "번 / 6번 배너 " + g + " 기본 배너 노출 횟수 " + h + "번");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==4) {
				System.out.println("4,5,6번 배너 노출 | 1번 배너 " + b + "번 / 2번 배너 " + c + "번 / 3번 배너 " + d + "번 / 4번 배너 " + e +  "번 / 5번 배너 " + f + "번 / 6번 배너 " + g + " 기본 배너 노출 횟수 " + h + "번");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			} else if (y==5) {
				System.out.println("6번 배너 노출 | 1번 배너 " + b + "번 / 2번 배너 " + c + "번 / 3번 배너 " + d + "번 / 4번 배너 " + e +  "번 / 5번 배너 " + f + "번 / 6번 배너 " + g + " 기본 배너 노출 횟수 " + h + "번");
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
	public void RCM세그먼트전송내역추가() {
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
			System.out.println((btnNum+1) + "번째 사용 버튼 클릭");
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
			System.out.println(i + "번째 세그먼트 전송 추가");
			sleep(1000);
			/*
			for(int x=0;x<=999999;x++) {
				$("td", 7).waitUntil(visible, 10000);
				if($("td", 7).text().trim() == "완료") {
					open("https://alpha-am.dighty.com/use");
					System.out.println("완료로 들어옴" + $("td", 7).text().trim());
					break;
				} else if($("td", 7).text().trim() == "실패"){
					System.out.println("세그먼트 전송상태 확인 : 실패");
					System.out.println("실패로 들어옴" + $("td", 7).text().trim());
					break;
				} else {
					sleep(1000);
					System.out.println("완료도 아니고 실패도 아님" + $("td", 7).text().trim());
					refresh();
					System.out.println("세그먼트 전송상태 확인 새로고침 횟수 " + x + "초");
				}
			}*/
		}			
	}
	//@Test(priority = 0)
	public void RCM사이트추가() {
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
			$(".inp", 0).setValue(date + ". " + (i+1) + "번째 사이트");
			$(".inp", 1).setValue("http://" + date1 + ".com" );
			$(".btn_point", 0).click();
			sleep(500);
			System.out.println((i+1) + " 번째 사이트 추가");
			open("http://alpha-rcm.dighty.com/site/detail?agencyid=1");
		}			
	}
	//@Test(priority = 0)
	public void RCM영역추가() {
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
			System.out.println((i+1) + " 번째 영역 추가");
			open("http://alpha-rcm.dighty.com/zone/detail?affiliateid=4");
		}			
	}
	//@Test(priority = 0)
	public void 다이티백오피스_서비스해지() {
		open("http://alpha.dighty.com/login");
		sleep(3000);
		$(".btn_full").waitUntil(visible, 10000);
		$(".inp", 0).setValue("ap20081305");
		System.out.println("아이디입력");
		$(".inp", 1).setValue("qordlf12");
		System.out.println("PW입력");
		$(".btn_full").click();
		System.out.println("로그인 버튼 클릭");
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
				$(".inp", 0).setValue("이름");
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
	public void dighty어드민데이터카드삭제() {
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
			confirm("카드를 삭제하시겠습니까?\n어드민에서 삭제된 카드는 Dighty 사이트에서도 영구 삭제됩니다.");
			sleep(500);
			confirm("삭제되었습니다.");
			sleep(500);
			System.out.println((i+1) + " 번째 데이터 카드 삭제");
			
		}			
	}
	//@Test(priority = 0)
	public void dighty어드민데이터카드등록() {
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
			$("#segmentName").setValue("인기 태그 확인 데이터 카드 " + i);
			btnNum = generator.nextInt(100);
			$("#condition_0").setValue("인기 태그 확인 데이터 카드" + i + " 의 조건1 : " + btnNum);
			btnNum = generator.nextInt(100);
			$("#condition_1").setValue("인기 태그 확인 데이터 카드" + i + " 의 조건2 : " + btnNum);
			btnNum = generator.nextInt(100);
			$("#condition_2").setValue("인기 태그 확인 데이터 카드" + i + " 의 조건3 : " + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_0").setValue("태그" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_1").setValue("태그" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_2").setValue("태그" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_3").setValue("태그" + btnNum);
			btnNum = generator.nextInt(100);
			$("#tag_4").setValue("태그" + btnNum);
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
			confirm("카드를 등록 하시겠습니까?\n등록된 카드는 바로 Dighty 사이트에서 확인할 수 있습니다.");
			System.out.println(i + " 번째 데이터 카드 등록");
			
		}			
	}
	//@Test(priority = 0)
	public void 캠페인매니저캠페인등록() {
		open("http://alpha-rcm.dighty.com/auth/form");
		$(".btn_login").waitUntil(visible, 10000);
		$(".btn_login").click();
		sleep(1000);
		for(int i=1;i<=100;i++) {
			open("http://alpha-rcm.dighty.com/campaign/detail?agencyid=1&clientid=2");	
			$("#temp_input").waitUntil(visible, 10000);
			$("#temp_input").setValue("페이징 확인용 캠페인" + i);
			$(".btn_point").click();
			sleep(500);
			$(".ajs-cancel").waitUntil(visible, 10000);
			$(".ajs-cancel").click();
		}			
	}
	//@Test(priority = 0)
	public void 캠페인매니저배너클릭() {
		for(int i=1,y=1;i<=10;i++) {
			open("http://apzz092888.blogspot.com");
			$(".sub1").waitUntil(visible, 10000);
			open("http://apzz0928.blogspot.com");
			for(int x=1;x<=10;x++) {
				$(".cmcm1 > a > img").waitUntil(visible, 15000);
				js("document.querySelector('.cmcm1 > a').setAttribute('target', '');");
				sleep(500);
				$(".cmcm1").click();
				System.out.println("blogspot CM 배너를 " + i + ", " + x + ", " + y + " 번째 클릭하였습니다.");
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
				System.out.println("egloos CM 배너를 " + i + ", " + x + ", " + y + " 번째 클릭하였습니다.");
				y=y+1;
				$(".cmcm1").waitUntil(visible, 15000);
			}
		}
	}
	//@Test(priority = 0)
	public void 삭제용캠페인매니저배너클릭() {
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
				System.out.println("egloos CM 배너를 " + i + " * " + x + " 번째 클릭하였습니다.");
				$(".cmcm").waitUntil(visible, 15000);
			}
		}
	}
	//@Test(priority = 0)
  	public void 유입수증가() {
  		System.out.println(" ! ----- increaseVisit Start ----- ! ");
		String[] link = {"1", "2", "3", "A", "B", "C", "가", "나", "다", "%EA%B0%80", "%EB%82%98", "%EB%8B%A4"};
		for(int x=0;x<=1;x++) {
			open("http://apzz092888.blogspot.com/");
			$(".sub24").waitUntil(visible, 10000);
			for(int i=1;i<=24;i++) {
				$(".sub" + i).waitUntil(visible, 10000);
				$(".sub" + i).scrollIntoView(false);
				$(".sub" + i).click();
				System.out.println("blogspot 을 " + i + " 번째 클릭하였습니다. // 페이지명 : " + $(".sub" + i).text().trim());
				sleep(500);
			}
			open("http://apzz09288.egloos.com");
			for(int i=0;i<=5;i++) {
				$(".menu" + i).waitUntil(visible, 10000);
				$(".menu" + i).scrollIntoView(false);
				$(".menu" + i).click();
		  		System.out.println( "egloos 를 " + i + " 번째 클릭하였습니다. // 페이지명 : " + $(".menu" + i).text().trim());
			}
			open("http://apzz09288.egloos.com");
			for(int i=0;i<=11;i++) {
				$(By.linkText(link[i])).waitUntil(visible, 10000);
				$(By.linkText(link[i])).scrollIntoView(false);
				$(By.linkText(link[i])).click();
				System.out.println("egloos 를 " + i + " 번째 클릭하였습니다. // 페이지명 : " + $(By.name(link[i])).text().trim());
			}			
		}
  		System.out.println(" ! ----- increaseVisit End ----- ! ");
  	}
	
	//@Test(priority = 0)
	public void 블로그메뉴클릭으로이동하기() {
		open("http://apzz092888.blogspot.com");
		String menuName = "";
		for(int x=1;x<=3;x++) {
			for(int i=1;i<=36;i++) {
				$(".sub" + i).click();
				menuName = $(".sub" + i).text().trim();
				System.out.println("클릭한 메뉴는 sub" + i + " 입니다. // 메뉴명 : " + menuName );
				if(i == 36) {
					open("http://apzz092888.blogspot.com");
				}
				sleep(800);
			}
			System.out.println("전체 메뉴를 총 " + x + "번 클릭하였습니다.");			
		}
	}
	//@Test(priority = 1)
	public void 블로그주소로이동하기() {
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
				System.out.println(URL[i] + "로 이동했습니다.");
				open(domain + "/search?q=" + URL[i]);
				System.out.println(URL[i] + "를 검색했습니다.");
			}	
		}
	}
	//@Test(priority = 2)
	public void 유입출처만들기() {
		for(int x=1;x<=5;x++) {
			open("http://apzz0928.blogspot.com");
			$(".sub43").click();
			System.out.println("blogspot 에서 " + x +"번 유입출처를 기록했습니다.");
			open("http://apzz0928.egloos.com");
			$(".sub43").click();
			System.out.println("egloos 에서 " + x +"번 유입출처를 기록했습니다.");
		}
		for(int y=1;y<=10;y++) {
			open("http://apzz0928.blogspot.com");
			$(".sub43").click();
			System.out.println("blogspot 에서 " + y +"번 유입출처를 기록했습니다.");
		}
	}
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}