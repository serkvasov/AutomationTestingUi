package automationUI.pages.system;

import automationUI.pages.BasePage;
import com.codeborne.selenide.Selenide;
import com.google.common.collect.Maps;

import java.lang.reflect.Constructor;
import java.util.Map;

public final class Pages {
    //Страницы, на которых будет проводиться тестирование <Имя, Страница>
    private final Map<String, BasePage> pages;

    //Страница, на которой в текущий момент проводится тестирование
    private BasePage currentPage;

    public Pages() {
        pages = Maps.newConcurrentMap();
    }

    //Возвращает текущую страницу, на которой производится тестирование в текущий момент
    public BasePage getCurrentPage() {
        if (currentPage == null) throw new IllegalStateException("Текущая страница не задана");
        return currentPage.visiblityCheck();
    }

    //Задает текущую страницу по ее имени
    public void setCurrentPage(BasePage page) {
        this.currentPage = page;
    }

    //Получение страницы из "pages" по ее имени
    public BasePage get(String pageName) {
        return Selenide.page(getPageFromPagesByName(pageName)).initialize().visiblityCheck();
    }

    public BasePage getPageFromPagesByName(String pageName) throws IllegalArgumentException {
        BasePage page = pages.get(pageName);
        if (page == null)
            throw new IllegalArgumentException(String.format("<%s> страница не найдена в списке объявленных страниц.", pageName));
        return page;
    }

    //Добавление страницы в "pages" по классу
    public void put(String pageName, Class<? extends BasePage> page) {
        if (page == null)
            throw new IllegalArgumentException("Была передана пустая страница");
        try {
            Constructor<? extends BasePage> constructor = page.getDeclaredConstructor();
            constructor.setAccessible(true);
            BasePage p = page.newInstance();
            pages.put(pageName, p);
        } catch (NoSuchMethodException | InstantiationException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Ошибка при инициализации страницы " + pageName);
        }
    }
}
