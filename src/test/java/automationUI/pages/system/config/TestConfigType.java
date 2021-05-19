package automationUI.pages.system.config;


import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:${DSConfigFile}",
        "classpath:config/app.properties",
        "system:properties",
        "system:env"
})
public interface TestConfigType extends Config {
    @Key("run.type")
    @DefaultValue("local")
    RunType runType();

    @Key("run.execute")
    @DefaultValue("single")
    ExecutionType executionType();

    @Key("selenide.browser")
    @DefaultValue("Chrome")
    BrowserType browserType();

    @Key("users.path")
    String getUsersPath();

    @Key("clients.path")
    String getClientsPath();

    @Key("selenide.browserVersion")
    @DefaultValue("41")
    String browserVersion();

    @Key("selenide.remote")
    @DefaultValue("http://lacalhost:4444/wd/hub")
    String remoteHubUrl();

    @Key("selenide.reportsFolder")
    @DefaultValue("target/reports")
    String reportFolder();

    @Key("selenide.path.driver")
    @DefaultValue("src/test/resources/webdriver/chromedriver.exe")
    String driverPath();

    @Key("selenide.timeout")
    @DefaultValue("20000")
    Long timeout();

    @Key("selenide.browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("selenide.startMaximized")
    @DefaultValue("true")
    Boolean startMaximized();

    @Key("yandex.url")
    String yandexUrl();

    @Key("rambler.url")
    String ramblerUrl();

    @Key("google.url")
    String googleUrl();

    @Key("pages.package")
    @DefaultValue("automationUI")
    String pageObjectsPackege();
}
