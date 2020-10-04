package automationUI.stepdefs;

import automationUI.pages.BasePage;
import automationUI.pages.system.BaseSteps;
import automationUI.pages.system.anotations.PageEntry;
import automationUI.pages.system.config.TestConfig;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;
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

}
