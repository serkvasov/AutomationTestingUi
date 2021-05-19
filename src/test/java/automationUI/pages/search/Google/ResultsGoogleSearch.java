package automationUI.pages.search.Google;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Результаты поиска Гугл")
public class ResultsGoogleSearch extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poisk615 = $x("//input[@title=\"Поиск\"");


    public BasePage visiblityCheck() {
        $(By.id("searchform")).shouldBe(Condition.exist);
        return this;
    }
}
