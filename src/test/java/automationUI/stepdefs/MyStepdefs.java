package automationUI.stepdefs;

import automationUI.pages.BasePage;

import static automationUI.stepdefs.Hooks.responses;

import automationUI.pages.system.BaseSteps;
import automationUI.pages.system.anotations.PageEntry;
import automationUI.pages.system.config.TestConfig;
import automationUI.pages.system.testData.ClientsData;
import com.browserup.harreader.model.HarEntry;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MyStepdefs extends BaseSteps {
    private static final Logger LOG = LoggerFactory.getLogger(MyStepdefs.class);


    @Дано("^открытие поисковика \"([^\"]*)\"$")
    public void openPoisk(String poiskovik) {
        String poisk = null;
        switch (poiskovik) {
            case "Яндекс":
                poisk = TestConfig.getInstance().yandexUrl();
                break;
            case "Рамблер":
                poisk = TestConfig.getInstance().ramblerUrl();
                break;
            case "Гугл":
                poisk = TestConfig.getInstance().googleUrl();
                break;
        }
        Selenide.open(poisk);
    }

    @Тогда("^открывается страница \"([^\"]*)\"$")
    public void openPage(String pageName) {
//        toWaitSeconds("2");
//        System.out.println(getWebDriver().getWindowHandles());
//        ArrayList<String> newTab = new ArrayList<String>(getWebDriver().getWindowHandles());
//        if (newTab.size()>1) {
//            String oldTab = getWebDriver().getWindowHandle();
//            newTab.remove(oldTab);
//            // change focus to new tab
//            getWebDriver().switchTo().window(newTab.get(0));
//        }

        if (getWebDriver().getWindowHandles().size() > 0) {
            getWebDriver().getWindowHandles().stream().forEach((winHandle) -> {
                getWebDriver().switchTo().window(winHandle);
            });
        }
        BasePage page = scenario.get(pageName);
        String pageTitle = Selenide.page(page.getClass().getAnnotation(PageEntry.class)).title();
        Assert.assertEquals("Страница не найдена " + pageName, pageName, pageTitle);
        scenario.setCurrentPage(page);
        LOG.info("СТраница открыта " + pageName);
    }


    @Дано("^пользователь в поле \"([^\"]*)\" вводит значение \"([^\"]*)\"$")
    public void search(String elem, String val) {
        scenario.getCurrentPage().getElement(elem).sendKeys(val);
    }

    @Дано("^пользователь ждет \"([^\"]*)\" секунд$")
    public void toWaitSeconds(String seconds) {
        Selenide.sleep(Integer.parseInt(seconds) * 1000);
    }

    @Дано("^пользователь нажимает кнопку \"([^\"]*)\"$")
    public void clickButton(String buttonName) {
        scenario.getCurrentPage().getElement(buttonName).click();
    }

    @Дано("^пользователь переходит по строке \"([^\"]*)\"$")
    public void choiceRow(String row) {
        int i = Integer.parseInt(row);
        $x("").click();
    }

    @Дано("^пользователь в блоке \"([^\"]*)\" нажимает кнопку \"([^\"]*)\"$")
    public void clickOnButtomInBlock(String block, String buttom) {
        scenario.getCurrentPage().getBlock(block).getElement(buttom).click();
    }

    @Дано("^пользователь получает логи Network с помощью прокси$")
    public void getNetworkByProxy() {
        List<HarEntry> entries = getSelenideProxy().getProxy().getHar().getLog().getEntries();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(entries.get(i).getRequest().getUrl());
            System.out.println(entries.get(i).getResponse().getStatus());
        }
    }

    @Дано("^пользователь получает логи Network с помощью ChromeDevTools$")
    public void getNetworkByChromeDevTools() {
        responses.stream().filter(res -> res.status != 200).forEach(System.out::println);
    }

    @Дано("^пользователь берет параметр клиента \"([^\"]*)\" из файла JSON$")
    public String getParamFromJSON(String param) {
        String value = param;
        ClientsData clientsData = new ClientsData();
        String role = "client1";
        Method method;
        try {
            method = clientsData.returnClient(role).getClass().getMethod(param);
            value = (String) method.invoke(clientsData.returnClient(role));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }


    @Дано("^пользователь перехватывает запрос через прокси1$")
    public void userGetProxy() {
        SelenideProxyServer proxyServer = getSelenideProxy();
        System.out.println(proxyServer.isStarted());
        System.out.println(proxyServer.getProxy().getHar().getLog().getEntries().size());
        for (int i = 0; i < proxyServer.getProxy().getHar().getLog().getEntries().size(); i++) {
            System.out.println("getEntries" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i));
            System.out.println("getEntries" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getComment());
            System.out.println("getEntries" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getConnection());
            System.out.println("getEntries" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getPageref());
            System.out.println("getEntries" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getServerIPAddress());
            System.out.println("getEntries" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getRequest().getMethod());
            System.out.println("getEntries" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getRequest().getHeaders());
            System.out.println("getEntries - getStatusText" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getResponse().getStatusText());
            System.out.println("getEntries - getStatus" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getResponse().getStatus());
            System.out.println("getEntries - size" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getResponse().getHeaders().size());
            if (proxyServer.getProxy().getHar().getLog().getEntries().get(i).getResponse().getHeaders().size() > 0) {
                System.out.println("getEntries - get(0)" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getResponse().
                        getHeaders().get(0));
                System.out.println("getEntries - getName" + i + " " + proxyServer.getProxy().getHar().getLog().getEntries().get(i).getResponse().getHeaders().get(0).getName());
            }
        }
//        System.out.println(proxyServer.getProxy().getHar().getLog().getEntries().stream().findAny().get().getRequest().getHeaders().size());
//        System.out.println(proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getHeaders().stream().findFirst());
//        System.out.println(proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getHeaders().get(0).getName());
//        System.out.println(proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getHeaders().get(0).getValue());
//        System.out.println("getMethod "+proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getMethod());
        System.out.println("getComment " + proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getComment());
        System.out.println("getHeaders " + proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getHeaders());
        System.out.println("getPostData " + proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getPostData().getText());
        System.out.println("getQueryString " + proxyServer.getProxy().getHar().getLog().getEntries().get(2).getRequest().getQueryString());
        System.out.println("getResponse " + proxyServer.getProxy().getHar().getLog().getEntries().get(2).getResponse());
        System.out.println("getMimeType " + proxyServer.getProxy().getHar().getLog().getEntries().get(2).getResponse().getContent().getMimeType());
        System.out.println("getContent " + proxyServer.getProxy().getHar().getLog().getEntries().get(2).getResponse().getContent().getText());
        System.out.println(proxyServer.getProxy().getHar().getLog().getEntries().size());

    }

    @Дано("^тест таблицы$")
    public void table(DataTable table) {
        List<Map<String, String>> map = table.asMaps();
        for (Map.Entry<String, String> entry : map.get(0).entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            switch (key) {
                case "Заголовок 1":
                    //Действие реализовать
                    break;
                case "Заголовок 2":
                    //Действие реализовать
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестное значение " + key);
            }
        }

    }

}
