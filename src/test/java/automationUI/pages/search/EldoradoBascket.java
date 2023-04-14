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
import static com.codeborne.selenide.WebDriverConditions.url;


@PageEntry(title = "Корзина Эльдорадо")
public class EldoradoBascket extends BasePage {

    @NameOfElement(value = "Удалить")
    public SelenideElement deleteFromBasket = $x("(//*[contains(@class,\"q-basketBlockClouser-button\")])");

    @NameOfElement(value = "Всего к оплате")
    public SelenideElement totalPrice = $(By.id("total_price_id"));

    public BasePage visiblityCheck() {
        Selenide.webdriver().shouldHave(url("https://www.eldorado.ru/personal/basket.php"));
        return this;
    }
}
