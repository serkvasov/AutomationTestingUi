package automationUI.pages.search.Rambler;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Рамблер")
public class RamblerSearch extends BasePage {

    @NameOfElement(value = "Строка поиска")
    public SelenideElement poiskRow = $x("//input[@name=\"query\"]");

    @NameOfElement(value = "Найти")
    public SelenideElement searchRambler = $x("//button[@type=\"submit\"]");

    public BasePage visiblityCheck() {
        $x("//*[@id=\"main\"]").shouldBe(Condition.visible);
        return this;
    }
}
