package automationUI.pages.search;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.NameOfElement;
import automationUI.pages.system.anotations.PageEntry;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;


@PageEntry(title = "Нажмите «Разрешить» в углу экрана")
public class Notification extends BasePage {

    @NameOfElement(value = "Закрыть")
    public SelenideElement close = $x("//button[@class=\"close\"]");

    public BasePage visiblityCheck() {
        Selenide.switchTo().defaultContent();
        $x("//*[@id='fl-296637']").shouldBe(Condition.exist, Duration.ofSeconds(20));
        SelenideElement frame = $x("//*[@id='fl-296637']");
        Selenide.switchTo().frame(frame);
        return this;
    }
}
