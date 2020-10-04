package automationUI.pages.search.Rambler;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Результаты поиска Рамблер")
public class ResultsRamblerSearch extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poisk46516 = $x("//input[@aria-label=\"Поисковый запрос\"]");

    @NameOfElement(value = "Афиша")
    public SelenideElement ramblerAfisha = $x("//h2/a[@href=\"https://www.afisha.ru/\"]");

    public BasePage visiblityCheck() {
//        $x("//a[@title=\"Рамблер\"]").shouldBe(Condition.visible);
        return this;
    }

}
