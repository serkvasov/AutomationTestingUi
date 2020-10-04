package automationUI.pages.search.Rambler;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.PageEntry;


@PageEntry(title = "Киноафиша Москвы")
public class KinoAfisha extends BasePage {

//    @NameOfElement(value = "Кино")
//    public SelenideElement kino = $x("//li[@class=\" menu-item\"]/a[@href=\"/msk/cinema/\"]");
//poisk
//    @NameOfElement(value = "Афиша")
//    public SelenideElement ramblerAfisha = $x("(//a[@href=\"/msk/cinema/\"])[1]");


    public BasePage visiblityCheck() {
//        $x("//span[text()='Киноафиша Москвы']").shouldBe(Condition.visible);
        return this;
    }
}
