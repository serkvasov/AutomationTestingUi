package automationUI.pages.search.Google;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Гугл")
public class GoogleSearch extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poiskRowGoogle = $x("//input[@title=\"Поиск\"]");

    public BasePage visiblityCheck() {
        $(By.name("q")).shouldBe(Condition.exist);
        return this;
    }
}
