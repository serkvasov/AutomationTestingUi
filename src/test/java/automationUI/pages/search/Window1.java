package automationUI.pages.search;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverConditions.url;


@PageEntry(title = "окно Не упускайте выгоду!")
public class Window1 extends BasePage {

    @NameOfElement(value = "Да")
    public SelenideElement buttonYes = $x("//*[@class=\"Notification-button Notification-buttonAllow js-turn-on\"]");

    @NameOfElement(value = "Нет")
    public SelenideElement buttonNo = $x("//*[@data-fl-track=\"click-button-no\"]");

    @NameOfElement(value = "Закрыть")
    public SelenideElement close = $x("//*[@class=\"close\"]");

    public BasePage visiblityCheck() {
        return this;
    }
}
