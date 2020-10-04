package automationUI.stepdefs;

import automationUI.CustomWebDriverProvider;

import automationUI.pages.system.config.ExecutionType;
import automationUI.pages.system.config.RunType;
import automationUI.pages.system.config.TestConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


public class Hooks {
    private final Logger LOG = LoggerFactory.getLogger(Hooks.class);
    private static final String ALLURE_LISTENER = "AllureSelenide";


    @Before
    public void SetUp() {
        setUpSelenide();
    }

    @After
    public void tearDown() {
        SelenideLogger.removeListener(ALLURE_LISTENER);
        if (WebDriverRunner.hasWebDriverStarted()) {
            LOG.info("Закрываем браузер");
            Selenide.closeWebDriver();
        }
    }


    public void setUpSelenide() {
        SelenideLogger.addListener(ALLURE_LISTENER, new AllureSelenide().screenshots(true).savePageSource(false));
        ExecutionType executionType = TestConfig.getInstance().executionType();
        if (executionType == ExecutionType.PARALLEL) {
            Configuration.browser = CustomWebDriverProvider.class.getName();
        }
        Configuration.reportsFolder = TestConfig.getInstance().reportFolder();
        Configuration.timeout = TestConfig.getInstance().timeout();
        Configuration.baseUrl = TestConfig.getInstance().googleUrl();
        Configuration.startMaximized = true;
//        Configuration.headless = true;
        Configuration.browser = TestConfig.getInstance().browserType().name().toLowerCase();
        RunType env = TestConfig.getInstance().runType();
        switch (env) {
            case REMOTE:
                String proj = "automationUI" + LocalDateTime.now().toString();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                Configuration.browserCapabilities = capabilities;
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                capabilities.setCapability("name", proj);
                capabilities.setVersion(TestConfig.getInstance().browserVersion());
                Configuration.remote = TestConfig.getInstance().remoteHubUrl();
                Configuration.browserCapabilities = capabilities;
                break;

            case LOCAL:
//                WebDriverManager скачает сам драйвера. Если нет, то расскрментировать.
//                if (TestConfig.getInstance().browserType() == BrowserType.CHROME) {
//                    System.setProperty("webdriver.chrome.driver", TestConfig.getInstance().driverPath());
//                } else if (
//                        TestConfig.getInstance().browserType() == BrowserType.IE) {
//                    System.setProperty("webdriver.ie.driver", TestConfig.getInstance().driverPath());
//                } else {
//                    throw new RuntimeException("Неизвестный драйвер");
//                }
                break;
        }
    }
}
