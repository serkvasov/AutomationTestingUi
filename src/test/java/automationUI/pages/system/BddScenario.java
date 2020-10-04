package automationUI.pages.system;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.PageEntry;
import automationUI.pages.system.config.TestConfig;
import com.codeborne.selenide.Selenide;

import java.util.Arrays;
import java.util.Set;

public class BddScenario {
    private static final BddScenario instance = new BddScenario();
    private final Pages pages = new Pages();
    private final String pagesPackage;

    private BddScenario() {
        pagesPackage = TestConfig.getInstance().pageObjectsPackege();
        initPages();
    }

    public static BddScenario getInstance() {
        return instance;
    }

    public BasePage getCurrentPage() {
        return pages.getCurrentPage();
    }

    public void setCurrentPage(BasePage page) {
        if (page == null) {
            throw new IllegalArgumentException("Происходит переход на несуществующую страницу. " +
                    "Проверьте аннотации @PageEntry у используемых страниц");
        }
        pages.setCurrentPage(page);
    }

    public BasePage get(String pageName) {
        return Selenide.page(pages.get(pageName)).visiblityCheck();
    }

    private void initPages() {
        Set<Class<?>> classes = new AnnotationScanner(pagesPackage).getClassesAnnotateWith(PageEntry.class);
        classes.stream().map(it -> {
            if (BasePage.class.isAssignableFrom(it)) {
                return (Class<? extends BasePage>) it;
            } else {
                throw new IllegalStateException("Класс " + it.getName() + " должен наследоваться от BasePage");
            }
        }).forEach(clazz -> pages.put(getClassAnnotationValue(clazz), clazz));
    }

    private String getClassAnnotationValue(Class<?> clazz) {
        return Arrays.stream(clazz.getAnnotationsByType(PageEntry.class)).findAny().map(PageEntry::title)
                .orElseThrow(() -> new AssertionError("Не найдены аннотации BasePage.NameOfElement в классе " + clazz.getName()));
    }
}
