package automationUI.pages.search.Yandex;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Результаты поиска Яндекс")
public class ResultsYandexSearch extends BasePage {

    @NameOfElement(value = "Яндекс Маркет")
    public SelenideElement yandexMarket = $x("//a[@accesskey=\"1\"]");


    public BasePage visiblityCheck() {
//        $x("//div[@class='service service_name_search']/descendant::span").shouldHave(Condition.text("Поиск"));
        return this;
    }

}
