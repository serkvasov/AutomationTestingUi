package automationUI;

import automationUI.pages.system.config.TestConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class CustomWebDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
//        org.apache.log4j.PropertyConfigurator.configure("src/test/resources/config/log4j.properties");
        Configuration.reportsFolder = TestConfig.getInstance().reportFolder();
        Configuration.timeout = TestConfig.getInstance().timeout();
        Configuration.browserSize = TestConfig.getInstance().browserSize();
        Configuration.startMaximized = true;
        capabilities.setBrowserName(TestConfig.getInstance().browserType().name().toLowerCase());
        capabilities.setVersion(TestConfig.getInstance().browserVersion());
        capabilities.setCapability("name", getSessioName());
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("screenResolution", "1920x1080x24");

        RemoteWebDriver driver = new RemoteWebDriver(getRemoteHubUrl(), capabilities);
        driver.setFileDetector(new LocalFileDetector());
        return driver;
    }

    private URL getRemoteHubUrl() {
        String remoteHubUrl = TestConfig.getInstance().remoteHubUrl();
        try {
            return new URL(remoteHubUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(String.format("Некорректный адрес selenium hub %s", remoteHubUrl));
        }
    }

    private String getSessioName() {
        return "project_name" + LocalDateTime.now().toString();
    }

}
