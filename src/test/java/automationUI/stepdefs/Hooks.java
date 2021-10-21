package automationUI.stepdefs;

import automationUI.CustomWebDriverProvider;

import automationUI.pages.system.config.ExecutionType;
import automationUI.pages.system.config.RunType;
import automationUI.pages.system.config.TestConfig;
import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.kklisura.cdt.protocol.commands.Network;
import com.github.kklisura.cdt.protocol.types.network.Response;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.impl.ChromeServiceImpl;
import com.github.kklisura.cdt.services.types.ChromeTab;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Hooks {
    private final Logger LOG = LoggerFactory.getLogger(Hooks.class);
    private static final String ALLURE_LISTENER = "AllureSelenide";
//    RemoteWebDriver driver;
//    ChromeDevToolsService cdpService;
//    static List<ResponseInfo> responses;

    @Before
    public void SetUp() throws IOException {
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

//    static class ResponseInfo {
//        final String url;
//        final int status;
//
//        ResponseInfo(String url, int status) {
//            this.url = url;
//            this.status = status;
//        }
//
//        public String toString() {
//            return String.format("%s -> %s", url, status);
//        }
//    }


    public void setUpSelenide() {
        System.setProperty("webdriver.chrome.driver", TestConfig.getInstance().driverPath());
        SelenideLogger.addListener(ALLURE_LISTENER, new AllureSelenide().screenshots(true).savePageSource(false));
        //Подсветка элементов на веб странице. Разблокировать для включения.
//        WebDriverRunner.addListener(new Highlighter());
        ExecutionType executionType = TestConfig.getInstance().executionType();
        if (executionType == ExecutionType.PARALLEL) {
            Configuration.browser = CustomWebDriverProvider.class.getName();
        }
//        Configuration.proxyEnabled = true;

        Configuration.reportsFolder = TestConfig.getInstance().reportFolder();
        Configuration.timeout = TestConfig.getInstance().timeout();
//        Configuration.baseUrl = TestConfig.getInstance().googleUrl();
        Configuration.startMaximized = true;
//        Configuration.headless = true;
        Configuration.browser = TestConfig.getInstance().browserType().name().toLowerCase();
        RunType env = TestConfig.getInstance().runType();

        //настройка ChromeDevTools
//        Selenide.open();
//        driver = (RemoteWebDriver) WebDriverRunner.getWebDriver();
//        Capabilities caps = driver.getCapabilities();
//        String debuggerAddress = (String) ((Map<String, Object>) caps.getCapability("goog:chromeOptions")).get("debuggerAddress");
//        int debuggerPort = Integer.parseInt(debuggerAddress.split(":")[1]);
//
//        ChromeService chromeService = new ChromeServiceImpl(debuggerPort);
//        ChromeTab pageTab = chromeService.getTabs().stream().filter(tab -> tab.getType().equals("page")).findFirst().get();
//        cdpService = chromeService.createDevToolsService(pageTab);
//        responses = new ArrayList<>();
//        Network network = cdpService.getNetwork();
//        network.onResponseReceived(event -> {
//            Response res = event.getResponse();
//            responses.add(new ResponseInfo(res.getUrl(), res.getStatus()));
//        });
//        network.enable();

        switch (env) {
            case REMOTE:
                String proj = "automationUI" + LocalDateTime.now().toString();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                Configuration.browserSize = TestConfig.getInstance().browserSize();

                Configuration.browserCapabilities = capabilities;

                capabilities.setCapability("name", proj);
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("83.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                capabilities.acceptInsecureCerts();

                Configuration.remote = TestConfig.getInstance().remoteHubUrl();
                Configuration.browserCapabilities = capabilities;
                break;

            case LOCAL:
//                WebDriverManager скачает сам драйвера. Если нет, то раскоментировать.
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
