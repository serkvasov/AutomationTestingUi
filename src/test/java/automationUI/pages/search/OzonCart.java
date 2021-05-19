package automationUI.pages.search;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Корзина Озон")
public class OzonCart extends BasePage {

    @NameOfElement(value = "Удалить")
    public SelenideElement delete = $x("//*[@id=\"split-Main-0\"]/descendant::*[contains(text(),'Удалить')]");

    public BasePage visiblityCheck() {
        $x("//*[text()='Корзина']").shouldBe(Condition.exist);
        return this;
    }
}
