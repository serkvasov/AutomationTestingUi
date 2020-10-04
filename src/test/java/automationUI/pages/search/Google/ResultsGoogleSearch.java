package automationUI.pages.search.Google;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Результаты поиска Гугл")
public class ResultsGoogleSearch extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poisk615 = $x("//input[@title=\"Поиск\"");


    public BasePage visiblityCheck() {
        $x("//img[@alt=\"Google\"]").shouldBe(Condition.visible);
        return this;
    }
}
