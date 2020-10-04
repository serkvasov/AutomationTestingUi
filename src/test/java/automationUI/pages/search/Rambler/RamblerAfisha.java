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
    public SelenideElement kino = $x("//li[@class=\" menu-item\"]/a[@href=\"/msk/cinema/\"]");

    @NameOfElement(value = "Афиша")
    public SelenideElement ramblerAfisha = $x("(//a[@href=\"/msk/cinema/\"])[1]");


    public BasePage visiblityCheck() {
//        $x("//a[@data-track-show=\"Afisha Logo onShow\"]").shouldBe(Condition.visible);
        return this;
    }
}
