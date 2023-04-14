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

    /**
     * Получение страницы по классу с возможностью выполнить проверку отображения элементов страницы
     *
     * @param clazz                   - класс страницы, которую необходимо получить
     * @param checkIfElementsAppeared - флаг, определяющий проверку отображения элементов на странице
     */
    public static <T extends BasePage> T getPage(Class<T> clazz, boolean checkIfElementsAppeared) {
        T page = Selenide.page(clazz);
        if (checkIfElementsAppeared) {
            page.initialize().visiblityCheck();
        }
        return page;
    }

    //Возвращает текущую страницу, на которой производится тестирование в текущий момент
    public BasePage getCurrentPage() {
        if (currentPage == null) throw new IllegalStateException("Текущая страница не задана");
        return currentPage.initialize().visiblityCheck();
    }

    //Задает текущую страницу по ее имени
    public void setCurrentPage(BasePage page) {
        this.currentPage = page;
    }

    //Получение страницы из "pages" по ее имени
    public BasePage get(String pageName) {
        return Selenide.page(getPageFromPagesByName(pageName)).initialize().visiblityCheck();
    }

    /**
     * Получение страницы по классу
     */
    @SuppressWarnings("unchecked")
    public <T extends BasePage> T get(Class<T> clazz, String name) {
        BasePage page = Selenide.page(getPageFromPagesByName(name)).initialize().visiblityCheck();

        if (!clazz.isInstance(page)) {
            throw new IllegalStateException(name + " page is not a instance of " + clazz + ". Named page is a " + page);
        }
        return (T) page;
    }

    private Map<String, BasePage> getPageMapInstanceInternal() {
        return pages;
    }

    private BasePage getPageFromPagesByName(String pageName) throws IllegalArgumentException {
        BasePage page = getPageMapInstanceInternal().get(pageName);
        if (page == null)
            throw new IllegalArgumentException(pageName + " page is not declared in a list of available pages");
        return page;
    }

    /**
     * Добавление инстанциированной страницы в "pages" с проверкой на NULL
     */
    public <T extends BasePage> void put(String pageName, T page) throws IllegalArgumentException {
        if (page == null)
            throw new IllegalArgumentException("Была передана пустая страница");
        pages.put(pageName, page);
    }


    /**
     * Добавление страницы в "pages" по классу
     */
    public void put(String pageName, Class<? extends BasePage> page) {
        if (page == null)
            throw new IllegalArgumentException("Была передана пустая страница");
        try {
            Constructor<? extends BasePage> constructor = page.getDeclaredConstructor();
            constructor.setAccessible(true);
            BasePage p = page.newInstance();
            pages.put(pageName, p);
        } catch (NoSuchMethodException | InstantiationException | SecurityException | IllegalAccessException e) {
            throw new RuntimeException("Ошибка при инициализации страницы " + pageName);
        }
    }

    @Override
    public String toString() {
        return "Pages{" +
                "pages=" + pages +
                ",currentPage=" + currentPage +
                "}";
    }
}
