package helpers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.safari.SafariOptions;
import config.Project;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.browserVersion;

public class DriverSettings {

    public static void configure() {

        String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1";

        Configuration.browser = Project.config.browser();
        browserVersion = Project.config.browserVersion();
        Configuration.browserSize = Project.config.browserSize();


        if (Project.isRemoteWebDriver()) {
            Configuration.remote = Project.config.remoteDriverUrl();
        }

        if (Configuration.browser.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-silent-push");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--lang=en-en");

            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

            if (Project.isWebMobile()) { // for chrome only
                System.setProperty("chromeoptions.mobileEmulation", "deviceName=iPhone XR");
                chromeOptions.addArguments("--user-agent=" + userAgent);
            }

            Configuration.browserCapabilities = chromeOptions;
        }

        if (Configuration.browser.equals("safari")) {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setCapability("browserVersion", browserVersion);
            System.setProperty("safarioptions.mobileEmulation", "deviceName="+Project.config.browserMobileView());
        }

    }
}

