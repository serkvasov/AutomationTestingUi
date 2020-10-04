package automationUI.pages.search.Yandex;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Яндекс.Маркет")
public class YandexMarket extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement strokaPoiska = $x("//input[@id=\"header-search\"]");

    @NameOfElement(value = "Найти")
    public SelenideElement poiskKnopka = $x("//button[@type=\"submit\"]");

    public BasePage visiblityCheck() {
//        $x("(//span[@class=\"logo__text\"])[2]").shouldHave(Condition.text("Маркет"));
        return this;
    }
}
