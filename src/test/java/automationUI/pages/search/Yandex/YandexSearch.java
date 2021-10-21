package automationUI.pages.search.Yandex;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Яндекс")
public class YandexSearch extends BasePage {

    @NameOfElement(value = "Блок поиска Яндекс")
    YandexSearchBlock yandexSearchBlock = new YandexSearchBlock();

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poisk4165 = $x("//input[@aria-label=\"Запрос\"]");

    public BasePage visiblityCheck() {
        $x("//link[@title='Яндекс']").shouldBe(Condition.exist);
        return this;
    }

}
