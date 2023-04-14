package automationUI.pages.search;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Эльдорадо")
public class Eldorado extends BasePage {

    @NameOfElement(value = "Корзина")
    public SelenideElement basket = $x("(//*[@href=\"/personal/basket.php\"])[1]");

    @NameOfElement(value = "Корзина с покупками")
    public SelenideElement basketWithProducts = $x("(//*[@href=\"/personal/basket.php\"])[1]");

    @NameOfElement(value = "В корзину")
    public SelenideElement addToBasket = $x("//*[@data-dy=\"buttonText\"]");

    @NameOfElement(value = "Добавить в корзину")
    public SelenideElement addToBasket1 = $x("//*[@data-dy=\"buttonText\"]/parent::button");

    @NameOfElement(value = "Найти")
    public SelenideElement buttonSearch = $x("//button[@type=\"submit\"][1]");

    @NameOfElement(value = "Строка поиска")
    public SelenideElement rowSearch = $(By.name("search"));

    public BasePage visiblityCheck() {
        Selenide.switchTo().defaultContent();
//        $x("//*[@alt='Логотип Эльдорадо']").shouldBe(Condition.exist);
        return this;
    }
}
