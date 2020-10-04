package automationUI.pages.search.Yandex;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Блок поиска Яндекс")
public class YandexSearchBlock extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poisk4165 = $x("//input[@aria-label=\"Запрос\"]");

    @NameOfElement(value = "Найти")
    public SelenideElement search4985165 = $x("//button[@type=\"submit\"]");

    public BasePage visiblityCheck() {
        $x("//link[@title='Яндекс']").shouldBe(Condition.exist);
        return this;
    }

}
