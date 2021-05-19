package automationUI.pages.search;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Озон")
public class Ozon extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poisk = $x("//input[@placeholder=\"Искать на Ozon\"]");

    @NameOfElement(value = "В корзину")
    public SelenideElement buy = $x("//*[@data-widget=\"searchResultsV2\"]/descendant::div[text()='В корзину']/../parent::button");

    @NameOfElement(value = "Корзина")
    public SelenideElement cart = $x("//*[@data-widget=\"cart\"]");

    public BasePage visiblityCheck() {
        $x("//input[@placeholder=\"Искать на Ozon\"]").shouldBe(Condition.exist);
        return this;
    }
}
