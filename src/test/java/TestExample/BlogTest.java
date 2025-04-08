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
        WebDriver driver; // WebDriver 객체를 초기화합니다.

        switch (browser.toLowerCase()) { // 브라우저 이름을 소문자로 변환하여 분기 처리
            case "chrome":
                // ChromeDriver 경로를 명시적으로 설정
                System.setProperty("webdriver.chrome.driver", "D:\\000. Selenium\\Driver\\chromedriver-win64\\chromedriver.exe"); 
                
                // Chrome 옵션 설정
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*"); // 원격 오리진 허용
                chromeOptions.addArguments("--disable-web-security");  // WebSocket 문제 해결
                chromeOptions.addArguments("--no-sandbox");            // 샌드박스 문제 회피
                driver = new ChromeDriver(chromeOptions); // 설정된 옵션으로 ChromeDriver 생성
                break;

            case "edge":
                // EdgeDriver 경로를 명시적으로 설정
                System.setProperty("webdriver.edge.driver", "D:\\000. Selenium\\Driver\\edgedriver_win64\\msedgedriver.exe"); 
                
                // Edge 옵션 설정 (기본 설정 사용)
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*"); // 원격 오리진 허용
                edgeOptions.addArguments("--disable-web-security");  // WebSocket 문제 해결
                edgeOptions.addArguments("--no-sandbox");            // 샌드박스 문제 회피
                driver = new EdgeDriver(edgeOptions); // 설정된 옵션으로 EdgeDriver 생성
                break;
                /*
            case "firefox": //에러발생해서 수정필		
                // FirefoxDriver 경로를 명시적으로 설정
                System.setProperty("webdriver.gecko.driver", "D:\\000. Selenium\\Driver\\geckodriver-win64\\geckodriver.exe"); 
                
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*"); // 원격 오리진 허용
                firefoxOptions.addArguments("--disable-web-security");  // WebSocket 문제 해결
                firefoxOptions.addArguments("--no-sandbox");            // 샌드박스 문제 회피
                // firefoxOptions.addArguments("--headless"); // 주석 처리하여 사용하지 않도록 함

                driver = new FirefoxDriver(firefoxOptions);
                break;
                */

            default:
                // 지원하지 않는 브라우저 요청 시 예외를 던집니다.
                throw new IllegalArgumentException("Unsupported browser: " + browser); 
        }

        return driver; // 생성된 WebDriver 객체를 반환합니다.
    }

	private static void js(String javaScriptSource) {
		executeJavaScript(javaScriptSource);
	}
	
	//@Test(priority = 999)
	public void import자삭방지() {
		sleep(1000);
	}
	
	@Test(priority = 1)
	public void 소셜비즈기본기능확인() {
		//$("#member_id").shouldBe(visible, Duration.ofSeconds(5));
		Configuration.timeout = 10000; //should, shouldBe 기본 timeout 10초로 설정
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
		System.out.println("소셜비즈 로그인 완료");
		$(".q-dialog__backdrop").should(disappear);
		$(".q-item__label", 1).shouldBe(visible);
		$(".q-item__label", 1).click();
		$(".q-item__label", 2).shouldBe(enabled);
		for(int i=2;i<=7;i++) {
			$(".q-item__label", i).click();
			$(".col.label", 0).shouldBe(enabled);
		}
		System.out.println("대시보드 1Depth 확인");
		$(".q-item__label", 8).click(); //추천 템플릿 클릭
		$(".title", 1).shouldBe(visible); //자주 묻는 질문 대기
		$(".title", 1).click(); //자주 묻는 질문 클릭
		$(".q-btn--no-uppercase.w100", 0).shouldBe(enabled);
		$(".q-img__image--loaded", 3).shouldBe(enabled);
		$(".q-ml-md.w200", 0).click();
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//제목 입력창 대기
		$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//메시지 설정 버튼 대기
		Date number_date = new Date();	//시간 실시간으로 다시 선언
		String date1 = number_format1.format(number_date);
		$(".q-field__native.q-placeholder", 0).setValue(date1 + " 추천 템플릿 자주 묻는 질문");
		$(".q-ml-md.w150", 0).click();	//저장 후 게시
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);	//confirm 확인 버튼 대기
		$(".q-ml-sm.min-w100", 0).click();	//confirm 확인 버튼 클릭
		$(".q-notification", 0).should(disappear);	//toast alert 사라질때까지 대기
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled); 
		System.out.println("추천 템플릿 자주 묻는 질문 등록");
		$(".w75.bg-white", 0).shouldBe(enabled);	//편집 버튼 대기
		$(".w75.bg-white", 0).click();	//편집 버튼 클릭
		$(".q-spinner").should(disappear);	//스피너 대기
		$(".q-inner-loading").should(disappear); //스피너 뒤 레이어 대기
		//$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//메시지 설정 버튼 대기
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//제목 입력창 대기
		$(".q-field__native.q-placeholder", 0).sendKeys(" 수정");
		$(".q-ml-md.w150", 0).click(); //저장 후 게시
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);
		$(".q-ml-sm.min-w100", 0).click(); // confirm 클릭
		$(".q-notification", 0).should(disappear);	//toast alert 사라질때까지 대기
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//페이지네이션 대기
		System.out.println("자주 묻는 질문 수정");
		$(".w75.bg-white", 1).shouldBe(enabled);	//복사 버튼 대기
		$(".w75.bg-white", 1).click();	//복사 버튼 클릭
		$(".q-spinner").should(disappear);	//스피너 사라질때까지 대기
		$(".q-inner-loading").should(disappear); //스피너 뒤 레이어 대기
		//$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//메시지 설정 버튼 대기
		$(".q-ml-md.w150", 0).click(); //저장 후 게시 버튼 클릭
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);	//confirm 확인 버튼 대기
		$(".q-ml-sm.min-w100", 0).click(); // confirm 클릭
		$(".q-notification", 0).should(disappear);	//toast alert 사라질때까지 대기
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//페이지네이션 대기
		System.out.println("자주 묻는 질문 복사");
		$(".q-item__label", 8).click(); //추천 템플릿 메뉴 클릭
		$(".title", 2).shouldBe(visible); //추천 템플릿의 DM 자동 답장 대기
		$(".title", 2).click();	//추천 템플릿의 DM 자동 답장 클릭
		$(".q-btn--no-uppercase.w100", 0).shouldBe(enabled); //템플릿 로딩까지 대기
		$(".q-img__image--loaded", 3).shouldBe(enabled); //프로필 사진 로딩까지 대기
		$(".q-anchor--skip.justify-center.row", 7).shouldBe(enabled); //템플릿 로딩까지 대기
		$(".q-ml-md.w200", 0).click(); //추천 템플릿 사용하기 버튼 클릭
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//제목 입력창 대기
		$(".q-btn--no-uppercase.q-ml-md.w150", 0).shouldBe(enabled);	//저장 후 게시 버튼 대기
		$(".q-field__native.q-placeholder", 0).setValue(date1 + " 추천 템플릿 DM 자동 답장");
		$(".q-ml-md.w150", 0).click();	//저장 후 게시
		$(".q-notification", 0).should(disappear);	//toast alert 사라질때까지 대기
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled); 
		System.out.println("추천 템플릿 DM 자동 답장");
		$(".w75.bg-white", 0).shouldBe(enabled);	//편집 버튼 대기
		$(".w75.bg-white", 0).click();	//편집 버튼 클릭
		$(".q-spinner").should(disappear);	//스피너 대기
		$(".q-inner-loading").should(disappear); //스피너 뒤 레이어 대기
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//제목 입력창 대기
		$(".q-field__native.q-placeholder", 0).sendKeys(" 수정");
		$(".q-ml-md.w150", 0).click(); //저장 후 게시
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled); // confirm 대기
		$(".q-ml-sm.min-w100", 0).click(); // confirm 클릭
		$(".q-notification", 0).should(disappear);	//toast alert 사라질때까지 대기
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//페이지네이션 대기
		System.out.println("DM 자동 답장 수정");
		$(".w75.bg-white", 1).shouldBe(enabled);	//복사 버튼 대기
		$(".w75.bg-white", 1).click();	//복사 버튼 클릭
		$(".q-spinner").should(disappear);	//스피너 사라질때까지 대기
		$(".q-inner-loading").should(disappear); //스피너 뒤 레이어 대기
		$(".q-ml-md.w150", 0).click(); //저장 후 게시 버튼 클릭
		$(".q-notification", 0).should(disappear);	//toast alert 사라질때까지 대기
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled);	//페이지네이션 대기
		System.out.println("DM 자동 답장 복사");
		$(".q-item__label", 8).click(); //추천 템플릿 클릭
		$(".title", 3).shouldBe(visible); //메시지 메뉴 대기
		$(".title", 3).click(); //메시지 메뉴 클릭
		$(".q-btn--no-uppercase.w100", 6).shouldBe(enabled); //템플릿 로딩 대기
		$(".q-img__image--loaded", 3).shouldBe(enabled); //프로필 사진 로딩 대기
		$(".q-ml-md.w200", 0).click(); //추천 템플릿 사용하기 클릭
		$(".q-field__native.q-placeholder", 0).shouldBe(enabled);	//제목 입력창 대기
		$(".q-mx-sm.dull.w120", 0).shouldBe(enabled);	//메시지 설정 버튼 대기
		$(".q-field__native.q-placeholder", 0).setValue(date1 + " 추천 템플릿 메시지 메뉴");
		$(".remove-button", 6).click(); //삭제 버튼 클릭
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).shouldBe(enabled);
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).click();
		$(".remove-button", 1).click(); //삭제 버튼 클릭
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).shouldBe(enabled);
		$(".q-btn--no-uppercase.q-ml-sm.min-w100", 0).click();
		$(".q-ml-md.w150", 0).click();	//저장 후 게시
		$(".q-ml-sm.min-w100", 0).shouldBe(enabled);	//confirm 확인 버튼 대기
		$(".q-ml-sm.min-w100", 0).click();	//confirm 확인 버튼 클릭
		$(".q-notification", 0).should(disappear);	//toast alert 사라질때까지 대기
		$(".row.justify-center.q-my-md", 0).shouldBe(enabled); 
		System.out.println("추천 템플릿 메시지 메뉴 등록");
	}
	
	//@Test(priority = 2)
	public void 다이티카페24로그인후구매() {
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
	        int id = generator.nextInt(1999); //999면 1~1000번 까지
			$("#member_id").setValue("test" + id);
			System.out.println("test" + id + " 구매 시작");
			sleep(1000);
			$("#member_passwd").setValue("qordlf!@34");
			sleep(1000);
			$(".btnLogin_bk").click();
			sleep(1000);
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
			$(".totalArea", 0).scrollIntoView(true);
			refresh();
			sleep(3500);
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

	
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}