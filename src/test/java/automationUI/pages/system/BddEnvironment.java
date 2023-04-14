package automationUI.pages.system;

import automationUI.pages.BasePage;
import automationUI.pages.system.anotations.PageEntry;
import automationUI.pages.system.config.TestConfig;
import io.cucumber.java.Scenario;

import java.util.Arrays;
import java.util.Set;

public class BddEnvironment {

    /**
     * Переменные, объявленные пользователем внутри сценария
     * ThreadLocal обеспечивает отсутствие коллизий при многопоточном запуске
     */
    private ThreadLocal<Stash> stash = new ThreadLocal<>();
    /**
     * Список веб-страниц, заданных пользователем, доступных для использования в сценариях
     */
    private String packageName;

    private Pages pages = new Pages();

    private Scenario scenario;

    public BddEnvironment() {
        packageName = TestConfig.getInstance().pageObjectsPackege();
        initPages();
    }

    /**
     * Метод ищет классы, аннотированные "BasePage.Name",
     * добавляя ссылки на эти классы в поле "pages"
     */
    @SuppressWarnings("unchecked")
    private void initPages() {
        Set<Class<?>> classes = new AnnotationScanner(packageName).getClassesAnnotateWith(PageEntry.class);
        classes
                .stream()
                .map(it -> {
                    if (BasePage.class.isAssignableFrom(it)) {
                        return (Class<? extends BasePage>) it;
                    } else {
                        throw new IllegalStateException("Класс " + it.getName() + " должен наследоваться от BasePage");
                    }
                })
                .forEach(clazz -> pages.put(getClassAnnotationValue(clazz), clazz));
    }

    //    /**
//     * Вспомогательный метод, получает значение аннотации "BasePage.NameOfElement" для класса
//     *
//     * @param класс, который должен быть аннотирован "BasePage.NameOfElement"
//     * @return значение аннотации "BasePage.Name" для класса
//     */
    private String getClassAnnotationValue(Class<?> clazz) {
        return Arrays.stream(clazz.getAnnotationsByType(PageEntry.class))
                .findAny()
                .map(PageEntry::title)
                .orElseThrow(() -> new AssertionError("Не найдены аннотации BasePage.NameOfElement в классе " + clazz.getName()));
    }

    public Scenario getScenario() {
        return scenario;
    }

    public Pages getPages() {
        return pages;
    }

    public BasePage getPage(String name) {
        return pages.get(name);
    }

    public <T extends BasePage> T getPage(Class<T> clazz, String name) {
        return pages.get(clazz, name);
    }

    public Stash getVars() {
        return getVariables();
    }

    public <T> T getValue(String name) {
        return getVariables().getValue(name);
    }

    public void put(String name, Object object) {
        getVariables().putValue(name, object);
    }

    private Stash getVariables() {
        if (stash.get() == null) {
            stash.set(new Stash());
        }
        return stash.get();
    }
}