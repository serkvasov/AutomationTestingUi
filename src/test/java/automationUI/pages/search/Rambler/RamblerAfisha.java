package automationUI.pages.search.Rambler;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Рамблер Афиша")
public class RamblerAfisha extends BasePage {

    @NameOfElement(value = "Кино")
    public SelenideElement kino = $x("(//*[@href=\"/msk/cinema/\"])[1]");

    @NameOfElement(value = "Афиша")
    public SelenideElement ramblerAfisha = $x("(//a[@href=\"/msk/cinema/\"])[1]");


    public BasePage visiblityCheck() {
        $x("//*[text()='Все развлечения Москвы']").shouldBe(Condition.visible);
        return this;
    }
}
