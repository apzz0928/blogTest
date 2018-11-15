package TestExample; //������ƮQA�Ҷ� ������ �׽�Ʈ�� �ڵ�ȭ�ϱ����� ����ϴ� ����

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.WebElementsCollection;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.testng.ScreenShooter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TestScript {
	@SuppressWarnings("unused")
	private static String baseUrl, hubUrl, hubPort, TestBrowser, id, pw, pw1, domain, checkMsg;
	private static HttpURLConnection huc;
	private static int respCode;
	private WebDriver driver;
	
	@SuppressWarnings("unused")
	private int invalidLinksCount;

	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browser) throws MalformedURLException {
		baseUrl = "http://new.acecounter.com/admin/login";
		hubUrl = "http://10.77.129.79:5555/wd/hub"; //��Ʈ��ȣ ������ �ٲ㼭 ���������� ����� ��Ʈ��ȣ �����ֱ�
		domain = "apzz";
		pw = "qordlf!@34";
		
		String urlToRemoteWD = hubUrl;
		DesiredCapabilities cap;
		ScreenShooter.captureSuccessfulTests = true;

		if (browser.equals("chrome")) {
			TestBrowser = "chrome";
			/*ChromeOptions options = new ChromeOptions();
			driver = new RemoteWebDriver(new URL(urlToRemoteWD), options);*/
			cap = DesiredCapabilities.chrome();
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 1000));
			driver.manage().window().maximize();
		} else if (browser.equals("firefox")) {
			TestBrowser = "firefox";
			//cap = DesiredCapabilities.firefox();
			//RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			FirefoxOptions options = new FirefoxOptions();
			driver = new RemoteWebDriver(new URL(urlToRemoteWD), options);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 1000));
		} else if (browser.equals("edge")) {
			TestBrowser = "edge";
			/*EdgeOptions options = new EdgeOptions();
			driver = new RemoteWebDriver(new URL(urlToRemoteWD), options);*/
			cap = DesiredCapabilities.edge();
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 1000));
		} else if (browser.equals("internetExplorer")) {
			TestBrowser = "internetExplorer";
			/*InternetExplorerOptions options = new InternetExplorerOptions();
			driver = new RemoteWebDriver(new URL(urlToRemoteWD), options);*/
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); // ���ȼ��� ����
			cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false); // ie text �Է� �ӵ� ����� ���� �κ�
			cap.setCapability("requireWindowFocus", true); // ie text �Է� �ӵ� ����� ���� �κ�
			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); // ie ĳ�� ������ ���� �κ�
			RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD), cap);
			WebDriverRunner.setWebDriver(driver);
			driver.manage().window().setSize(new Dimension(1650, 1000));
		}
	}
	@SuppressWarnings("unused")
	private static void js(String javaScriptSource) {
		Object obj = executeJavaScript(javaScriptSource);
	}
	public static boolean brokenLinkCheck (String urlName, String urlLink){
        try {
            huc = (HttpURLConnection)(new URL(urlLink).openConnection());
            huc.setRequestMethod("HEAD");
            huc.connect();
            respCode = huc.getResponseCode();
            if(respCode >= 400){
            	System.out.println("*** " + urlName +" : Link Status HTTP : " + respCode + " - check Fail ... !@#$%^&*() *** ");
            	close();
            } else {
            	System.out.println("*** " + urlName +" : Link Status HTTP : " + respCode + " - check Success !! *** ");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
    }
  	public static void sleep(long millis) {
  		try {
  			Thread.sleep(millis);
  		} catch (InterruptedException ex) {
  		}
  	}
  	//@Test(priority = 0)
	public void ��������������ȭ_�����������߰�() {
		open("https://new-admin.acecounter.com/admin/login");
		$("#username", 0).setValue("apzz0928");
		$("#password", 0).setValue("qordlf!@34");
		$(".btn-primary").click();
		sleep(1000);
	    $(".form-control", 5).setValue("new.acecounter.com");
		$(".btn-dark").click();
		sleep(1000);
		$(By.linkText("Web Trial")).click();
		switchTo().window(1);
		sleep(2500);
		open("https://new-admin.acecounter.com/setting/contents/url");
		for(int i=120;i>=1;i--) {
			sleep(1000);
			$(".btn-info", 0).click();
			sleep(1000);
			if(i>=100) {
				$("#page-url").setValue("/search" + i + "?A" + i + "=t&B" + i + "=t&C" + i + "=t&D" + i + "=t&E" + i + "=t&F" + i + "=t");
				System.out.println(i);
			} else if(i>=80) {
				$("#page-url").setValue("/search" + i + "?A" + i + "=t&B" + i + "=t&C" + i + "=t&D" + i + "=t&E" + i + "=t");
				System.out.println(i);
			} else if(i>=60) {
				$("#page-url").setValue("/search" + i + "?A" + i + "=t&B" + i + "=t&C" + i + "=t&D" + i + "=t");
				System.out.println(i);
			} else if(i>=40) {
				$("#page-url").setValue("/search" + i + "?A" + i + "=t&B" + i + "=t&C" + i + "=t");
				System.out.println(i);
			} else if(i>=20) {
				$("#page-url").setValue("/search" + i + "?A" + i + "=t&B" + i + "=t");
				System.out.println(i);
			} else if(i>=0) {
				$("#page-url").setValue("/search" + i + "?A" + i + "=t");
				System.out.println(i);
			}
			if(i>=60) {
				$(".w300").setValue("����");
			}
			$("#btn-add").click();
			sleep(1000);
			$(".btn-sm", 7).click();
		}
	}
  	//@Test(priority = 0)
	public void ���ڿ�����() {
		open("http://new.acecounter.com");
		String[] aaa = new String[9];
		aaa[0]	=	"	[�˻�����] ���̹�/������	"	;
		aaa[1]	=	"	[�˻�����] ����/������	"	;
		aaa[2]	=	"	[��Ÿ������] sajomall.co.kr	"	;
		aaa[3]	=	"	[�˻�����] ���̹�/������	"	;
		aaa[4]	=	"	[���������]	"	;
		aaa[5]	=	"	[�˻�����] ���̹�/������	"	;
		aaa[6]	=	"	[��Ÿ������] sajomall.co.kr	"	;
		aaa[7]	=	"	���̷�Ʈ	"	;
		aaa[8]	=	"	[�˻�����] ���̹�/������	"	;




  		for(int i=0;i<=8;i++) {
  			aaa[i] = aaa[i].trim();
  			aaa[i] = aaa[i].replace(" ", "");
  			System.out.println(aaa[i]);
  		}
	}
  	
	@Test(priority = 0)
	public void ��й�ȣ���⺯��_���̾����Ȯ��() {
		String[] pageURL = new String[230];
		String URL = "http://10.77.129.52:8083";
		pageURL[0]	=	"	 /common/front  	"	;
		pageURL[1]	=	"	 /common/front/product/intro  	"	;
		pageURL[2]	=	"	 /common/front/product/info/1  	"	;
		pageURL[3]	=	"	 /common/front/product/feature/site  	"	;
		pageURL[4]	=	"	 /common/front/manual/free  	"	;
		pageURL[5]	=	"	 /common/front/manual/premium  	"	;
		pageURL[6]	=	"	 /common/front/learningCenter/online  	"	;
		pageURL[7]	=	"	 /common/front/learningCenter/offline  	"	;
		pageURL[8]	=	"	 /common/front/learningCenter/offline/applyList  	"	;
		pageURL[9]	=	"	 /common/front/learningCenter/trend  	"	;
		pageURL[10]	=	"	 /common/front/svcCenter/notice  	"	;
		pageURL[11]	=	"	 /common/front/svcCenter/event  	"	;
		pageURL[12]	=	"	 /common/front/svcCenter/faq  	"	;
		pageURL[13]	=	"	 /common/front/svcCenter/inquiry  	"	;
		pageURL[14]	=	"	 /common/front/svcCenter/contact  	"	;
		pageURL[15]	=	"	 /common/front/svcCenter/download  	"	;
		pageURL[16]	=	"	 /common/front/signUp  	"	;
		pageURL[17]	=	"	 /common/front/signUpStep1  	"	;
		pageURL[18]	=	"	 /common/front/login  	"	;
		pageURL[19]	=	"	 /common/front/findAccount  	"	;
		pageURL[20]	=	"	 /common/front/policy  	"	;
		pageURL[21]	=	"	 /common/front/terms  	"	;
		pageURL[22]	=	"	 /stats/statsLiveDashboard  	"	;
		pageURL[23]	=	"	 /stats/getInflowSummary  	"	;
		pageURL[24]	=	"	 /stats/getInflowDetail  	"	;
		pageURL[25]	=	"	 /stats/getInflowTreeMap  	"	;
		pageURL[26]	=	"	 /stats/getInflowSearch  	"	;
		pageURL[27]	=	"	 /stats/getInflowDomain  	"	;
		pageURL[28]	=	"	 /stats/getInflowOver  	"	;
		pageURL[29]	=	"	 /stats/marketing/summary  	"	;
		pageURL[30]	=	"	 /stats/marketing/detail  	"	;
		pageURL[31]	=	"	 /stats/marketing/clickchoice  	"	;
		pageURL[32]	=	"	 /stats/marketing/app/summary  	"	;
		pageURL[33]	=	"	 /stats/marketing/app/detail  	"	;
		pageURL[34]	=	"	 /stats/marketing/app/ltv  	"	;
		pageURL[35]	=	"	 /stats/user/user_stats  	"	;
		pageURL[36]	=	"	 /stats/user/user_active_stats  	"	;
		pageURL[37]	=	"	 /stats/user/user_stats_percent  	"	;
		pageURL[38]	=	"	 /stats/user/user_visit_frequency  	"	;
		pageURL[39]	=	"	 /stats/user/user_system_web  	"	;
		pageURL[40]	=	"	 /stats/user/user_region  	"	;
		pageURL[41]	=	"	 /stats/user/user_member_status  	"	;
		pageURL[42]	=	"	 /stats/user/user_member_favorite  	"	;
		pageURL[43]	=	"	 /stats/user/user_list  	"	;
		pageURL[44]	=	"	 /stats/conv/getConvStatus  	"	;
		pageURL[45]	=	"	 /stats/conv/ConvStep  	"	;
		pageURL[46]	=	"	 /stats/conv/indirect_conversion  	"	;
		pageURL[47]	=	"	 /stats/conv/weighted_conv  	"	;
		pageURL[48]	=	"	 /stats/conv/conv_route  	"	;
		pageURL[49]	=	"	 /stats/getCmcPopularity  	"	;
		pageURL[50]	=	"	 /stats/getCmcBasket  	"	;
		pageURL[51]	=	"	 /stats/getCmcPostscript  	"	;
		pageURL[52]	=	"	 /stats/getCmcCorrelation  	"	;
		pageURL[53]	=	"	 /stats/getCmcPotentialList  	"	;
		pageURL[54]	=	"	 /stats/getCmcPotentialDetail  	"	;
		pageURL[55]	=	"	 /stats/getCmcProductAnal  	"	;
		pageURL[56]	=	"	 /stats/getCmcBuyStatus  	"	;
		pageURL[57]	=	"	 /stats/getCmcPeriod  	"	;
		pageURL[58]	=	"	 /stats/contentsPopularPage  	"	;
		pageURL[59]	=	"	 /stats/contentsGroupPage  	"	;
		pageURL[60]	=	"	 /stats/contentsInlinkPage  	"	;
		pageURL[61]	=	"	 /stats/contentsInternalPage  	"	;
		pageURL[62]	=	"	 /stats/contentsMoveRoute  	"	;
		pageURL[63]	=	"	 /stats/contentsPrenextRoute  	"	;
		pageURL[64]	=	"	 /stats/scenario/contentsScenarioRoute  	"	;
		pageURL[65]	=	"	 /stats/contentsEventLink  	"	;
		pageURL[66]	=	"	 /stats/contentsShare  	"	;
		pageURL[67]	=	"	 /stats/contentsBanner  	"	;
		pageURL[68]	=	"	 /stats/contentsSpeedPerformance  	"	;
		pageURL[69]	=	"	 /stats/contentsError  	"	;
		pageURL[70]	=	"	 /stats/inhouse/viral/impr  	"	;
		pageURL[71]	=	"	 /stats/inhouse/viral/effective  	"	;
		pageURL[72]	=	"	 /stats/inhouse/email  	"	;
		pageURL[73]	=	"	 /stats/inhouse/talk  	"	;
		pageURL[74]	=	"	 /stats/inhouse/appPush  	"	;
		pageURL[75]	=	"	 /stats/kpiReport  	"	;
		pageURL[76]	=	"	 /stats/segment/segmentList  	"	;
		pageURL[77]	=	"	 /setting/appmarketing/  	"	;
		pageURL[78]	=	"	 /setting/appmarketing/marketing  	"	;
		pageURL[79]	=	"	 /setting/inflowmedia/config/userinflow	"	;
		pageURL[80]	=	"	 /common/front"	;
		pageURL[81]	=	"	 /setting/marketing-set1  	"	;
		pageURL[82]	=	"	 /setting/marketing-set2  	"	;
		pageURL[83]	=	"	 /setting/marketing-set3  	"	;
		pageURL[84]	=	"	 /setting/appmarketing/appInfos  	"	;
		pageURL[85]	=	"	 /setting/user/ipconf  	"	;
		pageURL[86]	=	"	 /setting/user/memgroup_setting  	"	;
		pageURL[87]	=	"	 /setting/user_memgroup_add  	"	;
		pageURL[88]	=	"	 /setting/convList  	"	;
		pageURL[89]	=	"	 /setting/convAdd  	"	;
		pageURL[90]	=	"	 /setting/commerce/price  	"	;
		pageURL[91]	=	"	 /setting/commerce/priceForm  	"	;
		pageURL[92]	=	"	 /setting/commerce/grp  	"	;
		pageURL[93]	=	"	 /setting/commerce/grpForm  	"	;
		pageURL[94]	=	"	 /setting/commerce/mainPrice  	"	;
		pageURL[95]	=	"	 /setting/commerce/mainPriceForm  	"	;
		pageURL[96]	=	"	 /setting/commerce/currencyUnit  	"	;
		pageURL[97]	=	"	 /setting/contents/url  	"	;
		pageURL[98]	=	"	 /setting/contents/pageReplacement  	"	;
		pageURL[99]	=	"	 /setting/contents/internalSearch  	"	;
		pageURL[100]	=	"	 /setting/contents/pageGroup  	"	;
		pageURL[101]	=	"	 /setting/contents/inBanner/list  	"	;
		pageURL[102]	=	"	 /setting/contents/inBanner/add  	"	;
		pageURL[103]	=	"	 /common/front"	;
		pageURL[104]	=	"	 /setting/contents/scenario  	"	;
		pageURL[105]	=	"	 /setting/contents/scenario/form  	"	;
		pageURL[106]	=	"	 /setting/contents/download  	"	;
		pageURL[107]	=	"	 /setting/contents/outlinkBanner  	"	;
		pageURL[108]	=	"	 /setting/contents/outlinkBanner/form  	"	;
		pageURL[109]	=	"	 /setting/inhouse/viral/list  	"	;
		pageURL[110]	=	"	 /setting/inhouse/viral/add  	"	;
		pageURL[111]	=	"	 /common/front  	"	;
		pageURL[112]	=	"	 /setting/inhouse/email/list  	"	;
		pageURL[113]	=	"	 /setting/inhouse/email/add  	"	;
		pageURL[114]	=	"	 /setting/inhouse/talk/list  	"	;
		pageURL[115]	=	"	 /setting/inhouse/talk/add  	"	;
		pageURL[116]	=	"	 /setting/inhouse/appPush/list   	"	;
		pageURL[117]	=	"	 /setting/inhouse/appPush/add  	"	;
		pageURL[118]	=	"	 /setting/kpi/list  	"	;
		pageURL[119]	=	"	 /setting/kpi/form?useYn=y  	"	;
		pageURL[120]	=	"	 /setting/reportDown/reserve  	"	;
		pageURL[121]	=	"	 /setting/reportDown/download  	"	;
		pageURL[122]	=	"	 /setting/reportDown/add  	"	;
		pageURL[123]	=	"	 /myMenu/setting  	"	;
		pageURL[124]	=	"	 /menu/sortingServices  	"	;
		pageURL[125]	=	"	 /common/frontIndex  	"	;
		pageURL[126]	=	"	 /manage/script/scriptList  	"	;
		pageURL[127]	=	"	 /manage/script/installApply  	"	;
		pageURL[128]	=	"	 /manage/my_member_modify  	"	;
		pageURL[129]	=	"	 /manage/myCoupon  	"	;
		pageURL[130]	=	"	 /manage/serviceInfo/addService  	"	;
		pageURL[131]	=	"	 /manage/serviceInfo/addView  	"	;
		pageURL[132]	=	"	 /manage/serviceInfo/addIntegralReport  	"	;
		pageURL[133]	=	"	 /manage/serviceInfo/editService  	"	;
		pageURL[134]	=	"	 /manage/serviceInfo/changeService  	"	;
		pageURL[135]	=	"	 /manage/mailing/summaryReport  	"	;
		pageURL[136]	=	"	 /manage/mailing/notifyReport  	"	;
		pageURL[137]	=	"	 /manage/serviceInfo/submanager  	"	;
		pageURL[138]	=	"	 /manage/serviceInfo/leaveService  	"	;
		pageURL[139]	=	"	 /manage/charge/extendCharge  	"	;
		pageURL[140]	=	"	 /manage/charge/additionalCharge  	"	;
		pageURL[141]	=	"	 /manage/charge/paymentHistory  	"	;
		pageURL[142]	=	"	 /manage/charge/paymentBill  	"	;
		pageURL[143]	=	"	 /manage/news/myNoticeList  	"	;
		pageURL[144]	=	"	 /manage/news/myImproveList  	"	;
		pageURL[145]	=	"	 /manage/news/detailView?board_cd=101&post_no=11&row_no=1&  	"	;
		pageURL[146]	=	"	 /manage/news/myAllimList  	"	;
		pageURL[147]	=	"	 /manage/news/myMonitoringList  	"	;
		pageURL[148]	=	"	 /help/stats?menuNo=1  	"	;
		pageURL[149]	=	"	 /help/stats?menuNo=53  	"	;
		pageURL[150]	=	"	 /help/stats?menuNo=54  	"	;
		pageURL[151]	=	"	 /help/stats?menuNo=55  	"	;
		pageURL[152]	=	"	 /help/stats?menuNo=23  	"	;
		pageURL[153]	=	"	 /help/stats?menuNo=24  	"	;
		pageURL[154]	=	"	 /help/stats?menuNo=25  	"	;
		pageURL[155]	=	"	 /help/stats?menuNo=56  	"	;
		pageURL[156]	=	"	 /help/stats?menuNo=57  	"	;
		pageURL[157]	=	"	 /help/stats?menuNo=58  	"	;
		pageURL[158]	=	"	 /help/stats?menuNo=59  	"	;
		pageURL[159]	=	"	 /help/stats?menuNo=60  	"	;
		pageURL[160]	=	"	 /help/stats?menuNo=61  	"	;
		pageURL[161]	=	"	 /help/stats?menuNo=62  	"	;
		pageURL[162]	=	"	 /help/stats?menuNo=63  	"	;
		pageURL[163]	=	"	 /help/stats?menuNo=64  	"	;
		pageURL[164]	=	"	 /help/stats?menuNo=171  	"	;
		pageURL[165]	=	"	 /help/stats?menuNo=29  	"	;
		pageURL[166]	=	"	 /help/stats?menuNo=30  	"	;
		pageURL[167]	=	"	 /help/stats?menuNo=178  	"	;
		pageURL[168]	=	"	 /help/stats?menuNo=179  	"	;
		pageURL[169]	=	"	 /help/stats?menuNo=227  	"	;
		pageURL[170]	=	"	 /help/stats?menuNo=65  	"	;
		pageURL[171]	=	"	 /help/stats?menuNo=66  	"	;
		pageURL[172]	=	"	 /help/stats?menuNo=67  	"	;
		pageURL[173]	=	"	 /help/stats?menuNo=68  	"	;
		pageURL[174]	=	"	 /help/stats?menuNo=69  	"	;
		pageURL[175]	=	"	 /help/stats?menuNo=70  	"	;
		pageURL[176]	=	"	 /help/stats?menuNo=71  	"	;
		pageURL[177]	=	"	 /help/stats?menuNo=72  	"	;
		pageURL[178]	=	"	 /help/stats?menuNo=39  	"	;
		pageURL[179]	=	"	 /help/stats?menuNo=73  	"	;
		pageURL[180]	=	"	 /help/stats?menuNo=74  	"	;
		pageURL[181]	=	"	 /help/stats?menuNo=41  	"	;
		pageURL[182]	=	"	 /help/stats?menuNo=236  	"	;
		pageURL[183]	=	"	 /help/stats?menuNo=237  	"	;
		pageURL[184]	=	"	 /help/stats?menuNo=75  	"	;
		pageURL[185]	=	"	 /help/stats?menuNo=76  	"	;
		pageURL[186]	=	"	 /help/stats?menuNo=77  	"	;
		pageURL[187]	=	"	 /help/stats?menuNo=225  	"	;
		pageURL[188]	=	"	 /help/stats?menuNo=78  	"	;
		pageURL[189]	=	"	 /help/stats?menuNo=79  	"	;
		pageURL[190]	=	"	 /help/stats?menuNo=226  	"	;
		pageURL[191]	=	"	 /help/stats?menuNo=80  	"	;
		pageURL[192]	=	"	 /help/stats?menuNo=81  	"	;
		pageURL[193]	=	"	 /help/stats?menuNo=82  	"	;
		pageURL[194]	=	"	 /help/stats?menuNo=83  	"	;
		pageURL[195]	=	"	 /help/stats?menuNo=173  	"	;
		pageURL[196]	=	"	 /help/stats?menuNo=86  	"	;
		pageURL[197]	=	"	 /help/stats?menuNo=87  	"	;
		pageURL[198]	=	"	 /help/stats?menuNo=48  	"	;
		pageURL[199]	=	"	 /help/stats?menuNo=49  	"	;
		pageURL[200]	=	"	 /help/stats?menuNo=259  	"	;
		pageURL[201]	=	"	 /help/stats?menuNo=10  	"	;
		pageURL[202]	=	"	 /help/stats?menuNo=500  	"	;
		pageURL[203]	=	"	 /help/setting?menuNo=88  	"	;
		pageURL[204]	=	"	 /help/setting?menuNo=89  	"	;
		pageURL[205]	=	"	 /help/setting?menuNo=90  	"	;
		pageURL[206]	=	"	 /help/setting?menuNo=91  	"	;
		pageURL[207]	=	"	 /help/setting?menuNo=174  	"	;
		pageURL[208]	=	"	 /help/setting?menuNo=14  	"	;
		pageURL[209]	=	"	 /help/setting?menuNo=93  	"	;
		pageURL[210]	=	"	 /help/setting?menuNo=94  	"	;
		pageURL[211]	=	"	 /help/setting?menuNo=95  	"	;
		pageURL[212]	=	"	 /help/setting?menuNo=238  	"	;
		pageURL[213]	=	"	 /help/setting?menuNo=96  	"	;
		pageURL[214]	=	"	 /help/setting?menuNo=97  	"	;
		pageURL[215]	=	"	 /help/setting?menuNo=98  	"	;
		pageURL[216]	=	"	 /help/setting?menuNo=175  	"	;
		pageURL[217]	=	"	 /help/setting?menuNo=176  	"	;
		pageURL[218]	=	"	 /help/setting?menuNo=177  	"	;
		pageURL[219]	=	"	 /help/setting?menuNo=100  	"	;
		pageURL[220]	=	"	 /help/setting?menuNo=101  	"	;
		pageURL[221]	=	"	 /help/setting?menuNo=102  	"	;
		pageURL[222]	=	"	 /help/setting?menuNo=258  	"	;
		pageURL[223]	=	"	 /help/setting?menuNo=18  	"	;
		pageURL[224]	=	"	 /help/setting?menuNo=19  	"	;
		pageURL[225]	=	"	 /help/install/  	"	;
		pageURL[226]	=	"	 /help/svcCenter/faqList  	"	;
		pageURL[227]	=	"	 /help/svcCenter/inquiryForm  	"	;
		pageURL[228]	=	"	 /help/svcCenter/viewInquiryList  	"	;
		pageURL[229]	=	"	 /help/svcCenter/viewInquiry?idx=369&board_kind_cd=210  	"	;

		for(int i=0;i<=229;i++) { //URL ���� ����
  			pageURL[i] = pageURL[i].trim().replace(" ", "");
  		}
		int ckPageNum = 0; // �ʼ� ���� �����κ� ���̾� ���� ���� üũ�� ���� ��
		String accStatus = "����";
		String ID = "";
		switch(accStatus) {
		case "����": //144������ Ȯ�ε�
			ID = "apzza10";
			break;
		case "����": //32������ Ȯ�ε�
			ID = "apzz09287";
			break;
		case "3����":
			ID = "apzz09287";
			break;
		case "�ʼ�":
			ID = "apzz09288";
			break;
		}
		open(URL);
		$("#uid").setValue(ID);
		$("#upw").setValue("qordlf!@34");
		$(".btn_login").click();
		sleep(1000);
		for(int i=145;i<=229;i++) { // ������ ����
			open(URL + pageURL[i]);
			ckPageNum = i;
			sleep(1300);
			if(pageURL[i] == pageURL[96]) { //Ŀ�ӽ� ��ȭ���� ������ �ε��ð� ���
				sleep(23000);
			}
			if(pageURL[i] == pageURL[76]) { //QA���� ���׸�Ʈ ������ �������� ����ó��
				
			} else {
				if(accStatus.equals("����") || accStatus.equals("����")) { // ����, ���� ���´� ���̾� ���� X
					sleep(1000);
					js("ace.alert($('h3').text());");
					sleep(1000);
					String layerLoadCheck = $$("p").last().text();
					if(!layerLoadCheck.equals("���̽�ī���� �ֿ��ɰ������� ��ȣ�� ���� ��й�ȣ ���� �ȳ����̽�ī���� ��й�ȣ ����")) {
						System.out.println(accStatus + " ���� / ���̾� *�̳���* ������ �ּ� : " + pageURL[i]);
					} else {
						System.out.println(accStatus + " ���� / ���̾� *����* ������ �ּ� : " + pageURL[i] + "*************");
						driver.quit();
					}
				} else if (accStatus.equals("3����")) { // 3���� ���´� ������������ ���̾� ���� O
					if(pageURL[i] == pageURL[0]) { //3���� ���������� ���̾� ���� Ȯ��
						js("ace.alert($('h3').text());");
						String layerLoadCheck = $$("p").last().text();
						if(layerLoadCheck.equals("���̽�ī���� �ֿ��ɰ������� ��ȣ�� ���� ��й�ȣ ���� �ȳ����̽�ī���� ��й�ȣ ����")) {
							System.out.println(accStatus + " ���� / ���̾� *����* ������ �ּ� : " + pageURL[i]);
						} else {
							System.out.println(accStatus + " ���� / ���̾� *�̳���* ������ �ּ� : " + pageURL[i] + "*************");
							close();
						}
					} else { //3���� ������������ �ƴҶ� ���̾� �̳��� Ȯ��
						js("ace.alert($('h3').text());");
						String layerLoadCheck = $$("p").last().text();
						if(!layerLoadCheck.equals("���̽�ī���� �ֿ��ɰ������� ��ȣ�� ���� ��й�ȣ ���� �ȳ����̽�ī���� ��й�ȣ ����")) {
							System.out.println(accStatus + " ���� / ���̾� *�̳���* ������ �ּ� : " + pageURL[i]);
						} else {
							System.out.println(accStatus + " ���� / ���̾� *����* ������ �ּ� : " + pageURL[i] + "*************");
							close();
						}
					}
					
				} else if(accStatus.equals("�ʼ�")) { // ��й�ȣ �ʼ� ���� ���� Ȯ�ο�
					if(ckPageNum != 0 || ckPageNum <= 21) { // ����Ʈ ������ ���̾� ���� Ȯ��, ���̾� ����Ǹ� Fail
						js("ace.alert($('h3').text());");
						String layerLoadCheck = $$("p").last().text();
						if(!layerLoadCheck.equals("���̽�ī���� �ֿ��ɰ������� ��ȣ�� ���� ��й�ȣ ���� �ȳ����̽�ī���� ��й�ȣ ����")) {
							System.out.println(accStatus + " ���� / ���̾� *�̳���* ����Ʈ ������ �ּ� : " + pageURL[i]);
						} else {
							System.out.println(accStatus + " ���� / ���̾� *����* ����Ʈ ������ �ּ� : " + pageURL[i] + "*************");
							close();
						}
					} else if (ckPageNum == 0 || ckPageNum <= 147){ // ��� ������ ���̾� ���� Ȯ��, ���̾� �̳����̸� Fail
						js("ace.alert($('h3').text());");
						String layerLoadCheck = $$("p").last().text();
						if(layerLoadCheck.equals("���̽�ī���� �ֿ��ɰ������� ��ȣ�� ���� ��й�ȣ ���� �ȳ����̽�ī���� ��й�ȣ ����")) {
							System.out.println(accStatus + " ���� / ���̾� *����* ��� ������ �ּ� : " + pageURL[i]);
						} else {
							System.out.println(accStatus + " ���� / ���̾� *�̳���* ��� ������ �ּ� : " + pageURL[i] + "*************");
							close();
						}
					} else if (ckPageNum != 0 || ckPageNum <= 229) {// ���� ������ ���̾� ���� Ȯ��, ���̾� ����Ǹ� Fail
						js("ace.alert($('h3').text());");
						String layerLoadCheck = $$("p").last().text();
						if(!layerLoadCheck.equals("���̽�ī���� �ֿ��ɰ������� ��ȣ�� ���� ��й�ȣ ���� �ȳ����̽�ī���� ��й�ȣ ����")) {
							System.out.println(accStatus + " ���� / ���̾� *�̳���* ���� ������ �ּ� : " + pageURL[i]);
						} else {
							System.out.println(accStatus + " ���� / ���̾� *����* ���� ������ �ּ� : " + pageURL[i] + "*************");
							close();
						}
					}
				}	
			}
		}
	}
  	
  	//@Test(priority = 0)
	public void ���Ǽ��׸�Ʈ_����() {
		open("http://10.77.129.80:8082/stats/segment/segmentList");
		sleep(1500);
		$("#uid", 0).setValue("apzz09288");
		$("#upw", 0).setValue("qordlf!@34");
		$(".btn_login").click();
		sleep(1500);
		open("http://10.77.129.80:8082/stats/segment/segmentList");
		for(int i=1397;i<=5001;i++) {
			//$(".btn-dark", 0).waitUntil(visible, 10000);
			sleep(500);
			$(".btn-dark", 0).click();
			//$(".fluid-width").waitUntil(visible, 10000);
			sleep(500);
			$(".fluid-width").setValue("����׽�Ʈ_" + i);
			$(".btnSave").click();
			open("http://10.77.129.80:8082/stats/segment/segmentList");
		}
  	}
	
	//@Test(priority = 0)
	public void ��α��ּ�() {
		open("https://new.acecounter.com");
		String[] aaa = new String [154];
		String[] bbb = new String [154];
		aaa[0]=		"	Page�̸�/ 	";
		aaa[1]=		"	��������/2018/01/2018-01-10.html 	";
		aaa[2]=		"	��������/p/test.html 	";
		aaa[3]=		"	/2018/01/2018-01-09.html 	";
		aaa[4]=		"	/b/app-mobile-preview 	";
		aaa[5]=		"	/search/label/Category1 	";
		aaa[6]=		"	/search/label/Category2 	";
		aaa[7]=		"	��������6/b/layout-preview 	";
		aaa[8]=		"	1��2����/b/app-preview 	";
		aaa[9]=		"	��������8/search/label/Category3 	";
		aaa[10]=		"	1��2����/search/label/Category5 	";
		aaa[11]=		"	��������10/search/label/Category4 	";
		aaa[12]=		"	��������11/2018/01/2018-01-10_9.html 	";
		aaa[13]=		"	��������12/2018/01/2018-01-10_10.html#comments 	";
		aaa[14]=		"	��������13/search/label/Category6 	";
		aaa[15]=		"	��������14/search/label/Category6/ 	";
		aaa[16]=		"	��������15/2018/01/2018-01-10_10.html 	";
		aaa[17]=		"	��������16/search 	";
		aaa[18]=		"	��������17/search/label/���̷� 	";
		aaa[19]=		"	��������18/search/label/���� 	";
		aaa[20]=		"	��������19/search/label/������ 	";
		aaa[21]=		"	��������20/search/label/e-mail 	";
		aaa[22]=		"	��������21/search/label/����� 	";
		aaa[23]=		"	��������22/search/label/Ŀ�ӽ� 	";
		aaa[24]=		"	��������23/search/label/�̸��� 	";
		aaa[25]=		"	��������24/search/label/��ȯ 	";
		aaa[26]=		"	��������25/search/label/������ 	";
		aaa[27]=		"	��������26/search/label/���Ͽ콺������ 	";
		aaa[28]=		"	��������27/search/label/Category7/ 	";
		aaa[29]=		"	��������28/search/label/Category8/ 	";
		aaa[30]=		"	��������29/search/label/������-�����귣��˻� 	";
		aaa[31]=		"	��������30/search/label/������-���۾ֵ���� 	";
		aaa[32]=		"	��������31/search/label/������-�Ϲ� 	";
		aaa[33]=		"	��������32/search/label/���Ͽ콺-�̸���/ 	";
		aaa[34]=		"	��������33/search/label/������-īī���� 	";
		aaa[35]=		"	��������34/search/label/���Ͽ콺-���̷�/ 	";
		aaa[36]=		"	��������35/search/label/���Ͽ콺-Talk/ 	";
		aaa[37]=		"	��������36/search/label/������-���̹��귣��˻� 	";
		aaa[38]=		"	/search/label/marketing-normal 	";
		aaa[39]=		"	/search/label/marketing-daumBrand 	";
		aaa[40]=		"	/search/label/marketing-naverBrand 	";
		aaa[41]=		"	/search/label/inHouse-Email 	";
		aaa[42]=		"	/search/label/marketing-google 	";
		aaa[43]=		"	/search/label/inHouse-viral 	";
		aaa[44]=		"	/search/label/marketing-kakaoTalk 	";
		aaa[45]=		"	/search/label/inHouse-Talk 	";
		aaa[46]=		"	��������37/2018/01/blog-post.html 	";
		aaa[47]=		"	/search/label/change-other3 	";
		aaa[48]=		"	/search/label/change-step-2.4 	";
		aaa[49]=		"	 /search/label/change-step-2.1 	";
		aaa[50]=		"	 /search/label/change-step-1.2 	";
		aaa[51]=		"	/search/label/change-step-2.5 	";
		aaa[52]=		"	/search/label/change-step-1.3 	";
		aaa[53]=		"	/search/label/change-step-1.1 	";
		aaa[54]=		"	/search/label/change-request 	";
		aaa[55]=		"	/search/label/change-order 	";
		aaa[56]=		"	/search/label/change-other2 	";
		aaa[57]=		"	/search/label/change-booking 	";
		aaa[58]=		"	/search/label/change-signIn 	";
		aaa[59]=		"	/search/label/change-other1 	";
		aaa[60]=		"	/search/label/change-step-1.0 	";
		aaa[61]=		"	/search/label/change-step-2.2 	";
		aaa[62]=		"	/search/label/change-step-2.3 	";
		aaa[63]=		"	/search/label/change-step-2.0 	";
		aaa[64]=		"	��������38/2018/01/blog-post_60.html 	";
		aaa[65]=		"	��������39/2018/01/blog-post_11.html 	";
		aaa[66]=		"	��������40/search/label/banner-outer 	";
		aaa[67]=		"	��������41/2018/01/blog-post_11.html#comments 	";
		aaa[68]=		"	��������42/2018/01/jpg.html 	";
		aaa[69]=		"	��������43/2018/01/blog-post_71.html 	";
		aaa[70]=		"	��������44/search/label/Tel 	";
		aaa[71]=		"	��������45/asd124 	";
		aaa[72]=		"	��������46/search/label/inHouse-Email/ 	";
		aaa[73]=		"	��������47/search/label/fileDownload 	";
		aaa[74]=		"	��������48/2018/01/apzz0928.blogspot.com/search/label/fileDownload 	";
		aaa[75]=		"	��������49/search/label/apzz0928.blogspot.com/search/label/fileDownload 	";
		aaa[76]=		"	��������50/b/html-preview 	";
		aaa[77]=		"	��������51/search/label/inHouse-Talk/ 	";
		aaa[78]=		"	��������52/2018/01/blog-post_14.html 	";
		aaa[79]=		"	��������53/b/post-preview 	";
		aaa[80]=		"	��������54/2018/05/test.html 	";
		aaa[81]=		"	���ڵ�-����/search/label/���ڵ�-�ֹ� 	";
		aaa[82]=		"	���ڵ�-����/search/label/���ڵ�-���� 	";
		aaa[83]=		"	���ڵ�-�ֹ�/search/label/���ڵ�-���� 	";
		aaa[84]=		"	/search/label/���̷� 	";
		aaa[85]=		"	/search/label/���ڵ�-�ֹ�/ 	";
		aaa[86]=		"	/search/label/���ڵ�-����/ 	";
		aaa[87]=		"	/b/ 	";
		aaa[88]=		"	/search/label/���Ͽ콺-Talk/ 	";
		aaa[89]=		"	/search/label/���Ͽ콺������ 	";
		aaa[90]=		"	/search/label/���Ͽ콺-���̷�/ 	";
		aaa[91]=		"	/search/label/���Ͽ콺-�̸���/ 	";
		aaa[92]=		"	/search?q=aaaaa 	";
		aaa[93]=		"	/search?q=bbbbb 	";
		aaa[94]=		"	/search?q=ccccc 	";
		aaa[95]=		"	/search?q=���ڵ� 	";
		aaa[96]=		"	���ڵ��׽�Ʈ/search/label/�׽�Ʈ 	";
		aaa[97]=		"	/search/label/���� 	";
		aaa[98]=		"	/search?q=1234 	";
		aaa[99]=		"	/search?q=īī�� 	";
		aaa[100]=		"	/search?q=���� 	";
		aaa[101]=		"	/search?q=���̹� 	";
		aaa[102]=		"	/search?q=��ȯ 	";
		aaa[103]=		"	/search?q=�ܺι�� 	";
		aaa[104]=		"	/search?q=���Ͽ콺 	";
		aaa[105]=		"	/search?q=������ 	";
		aaa[106]=		"	/search?q=Tel 	";
		aaa[107]=		"	/search?q=���� 	";
		aaa[108]=		"	/search?q=�̸��� 	";
		aaa[109]=		"	/search?q=���� �ٿ�ε� 	";
		aaa[110]=		"	/search?q=Talk 	";
		aaa[111]=		"	/search?q=�Ϲ� 	";
		aaa[112]=		"	/search?q=���̷� 	";
		aaa[113]=		"	/search?q=�� 	";
		aaa[114]=		"	/search?q=�� 	";
		aaa[115]=		"	/search?q=���ΰ˻� 	";
		aaa[116]=		"	/search?q=�� 	";
		aaa[117]=		"	/search?q=�� 	";
		aaa[118]=		"	/2018/05/ 	";
		aaa[119]=		"	/2018/01/3.html 	";
		aaa[120]=		"	/search?q=���ڵ�-���� 	";
		aaa[121]=		"	/search/label/missingA 	";
		aaa[122]=		"	/2018/01/1.html 	";
		aaa[123]=		"	/search/label/missing/missingC 	";
		aaa[124]=		"	/2018/01/blog-post_77.html 	";
		aaa[125]=		"	/2018/01/ 	";
		aaa[126]=		"	/search/label/missing/missingA 	";
		aaa[127]=		"	/search/label/missing/missingB 	";
		aaa[128]=		"	/2018/01/2.html 	";
		aaa[129]=		"	/search?q=123/456 	";
		aaa[130]=		"	/search?q=1 	";
		aaa[131]=		"	/search/label/1 	";
		aaa[132]=		"	/search?q=ddddd123123 	";
		aaa[133]=		"	/search?q=ddddd 	";
		aaa[134]=		"	/search?q=234 	";
		aaa[135]=		"	/search/label/���Ͽ콺-Talk 	";
		aaa[136]=		"	/search/label/2 	";
		aaa[137]=		"	/search/label/4 	";
		aaa[138]=		"	/search?q=345 	";
		aaa[139]=		"	/search/label/3 	";
		aaa[140]=		"	/search/label/5 	";
		aaa[141]=		"	/search/label/���Ͽ콺-�̸��� 	";
		aaa[142]=		"	/search?q=123 	";
		aaa[143]=		"	/search/label/���Ͽ콺-���̷� 	";
		aaa[144]=		"	/search/label/banner-inner 	";
		aaa[145]=		"	/search?q=banner-outer 	";
		aaa[146]=		"	/search/label/banner-oute 	";
		aaa[147]=		"	/e 	";
		aaa[148]=		"	/1234 	";
		aaa[149]=		"	/2018/01/blog-post_6.html 	";
		aaa[150]=		"	/search?q=A 	";
		aaa[151]=		"	/search/label/���Ͽ콺������/ 	";
		aaa[152]=		"	/-nKFu6GiPnd4/WlgmXclJ8vI/AAAAAAAAE2k/gOoA-qRjcdEkOinccDfBk5HcNhwgSEdFgCEwYBhgL/s1600/1.jpg 	";
		aaa[153]=		"	/index.html 	";
		
		int idx = 0;
		for(int i=0;i<=aaa.length-1;i++) {
			idx = aaa[i].trim().indexOf("/");
			bbb[i] = aaa[i].substring(idx+1);
			System.out.println(bbb[i].trim()+",,,");			
		}
  	}
  	
	//@Test(priority = 0)
	public void ��й�ȣ_�Ⱦ��°Ż���() {
		open("http://10.77.129.52:8083/admin/login");
		int numCk = 0;
		int numCk1 = 6; //����¡ �ѹ���üũ
		$("#username").setValue("apzz0928");
		$("#password").setValue("qordlf!@34");
		$(".btn-primary").click();
		open("http://10.77.129.52:8083/admin/comApply/termination");
		sleep(1000);
		$(By.linkText(numCk1 + "")).scrollTo();
		sleep(500);
		$(By.linkText(numCk1 + "")).click();
		sleep(500);
		js("window.scroll(0,0);");
		sleep(500);
		for(int i=0;i<=192;i++) {
			sleep(500);
			/*$(By.linkText("[�ۼ�]"), 0).scrollTo();
			sleep(500);
			$(".text-danger-darker", 0).scrollTo();
			sleep(500);*/
			$(By.linkText("[�ۼ�]"), 0).click();
			sleep(500);
			String mainWindow = driver.getWindowHandle();
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1=s1.iterator();
			while (i1.hasNext()) {
				String ChildWindow = i1.next();
				if(!mainWindow.equalsIgnoreCase(ChildWindow)) {
					driver.switchTo().window(ChildWindow);
					sleep(1000);
					$(By.id("__BVID__2_")).click();
				    $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='�����'])[1]/following::option[26]")).click();
				    $(By.id("__BVID__3_")).click();
				    $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ó������'])[1]/following::option[3]")).click();
				    $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='��������'])[1]/following::form[1]")).click();
				    $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='���� ó�� ���� �ۼ�'])[1]/following::button[1]")).click();
				    $(By.id("btn-modal-alert-yes")).click();
				    $(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='�˸�'])[2]/following::button[1]")).click();
				    sleep(500);
				}
			    sleep(500);
				driver.switchTo().window(mainWindow);
			    sleep(500);
			}
			sleep(500);
			refresh();
			sleep(500);
			$(By.linkText(numCk1 + "")).scrollTo();
			sleep(500);
			$(By.linkText(numCk1 + "")).click();
			sleep(500);
			js("ace.alert($('.text-dark').length)");
			sleep(1000);
			String numberCheck = $(".modal-body").text();
			if(numberCheck.equals(19)) {
				sleep(500);
				$(".btn-sm", 1).click();
				++numCk1;
			} else {
				sleep(500);
				$(".btn-sm", 1).click();
			}
			sleep(500);
			//++numCk;
			sleep(500);
			$(By.linkText(numCk1 + "")).scrollTo();
			sleep(500);
			$(By.linkText(numCk1 + "")).click();
			sleep(1500);
			js("window.scroll(0,0);");
		}
	}
	
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}

}