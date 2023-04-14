package automationUI.pages.search.Rambler;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Киноафиша Москвы")
public class KinoAfisha extends BasePage {

    @NameOfElement(value = "Расписание фильмов")
    public SelenideElement kino = $x("(//*[@href=\"/msk/schedule_cinema/\"])[1]");

    public BasePage visiblityCheck() {
        $x("//span[text()='Киноафиша Москвы']").shouldBe(Condition.visible);
        return this;
    }
}
