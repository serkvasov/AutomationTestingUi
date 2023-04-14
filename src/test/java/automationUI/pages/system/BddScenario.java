package automationUI.pages.system;

import automationUI.pages.BasePage;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.Scenario;

public enum BddScenario {
    INSTANCE;

    private final BddEnvironment environment = new BddEnvironment();
    private final String CURRENT_PAGE = "CURRENT_PAGE";

    public BasePage getCurrentPage() {
        return getVar(CURRENT_PAGE);
    }

    public void setCurrentPage(BasePage page) {
        setVar(CURRENT_PAGE, page);
    }

    public Scenario getScenario() {
        return environment.getScenario();
    }

    /**
     * Получение списка страниц
     */
    public Pages getPages() {
        return this.environment.getPages();
    }

    public BasePage getPage(String name) {
        return Selenide.page(environment.getPage(name));
    }


    /**
     * Получение страницы по классу (проверка отображения элементов страницы не выполняется)
     *
     * @param clazz - класс страницы, которую необходимо получить
     */
    public <T extends BasePage> T getPage(Class<T> clazz) {
        return Pages.getPage(clazz, true);
    }

    /**
     * Получение страницы по классу и имени (оба параметра должны совпадать)
     *
     * @param clazz - класс страницы, которую необходимо получить
     * @param name  - название страницы, заданное в аннотации @Name
     */
    public <T extends BasePage> T getPage(Class<T> clazz, String name) {
        return environment.getPage(clazz, name);
    }

    public <T> T getVar(String name) {
        return (T) environment.getValue(name);
    }

    public void setVar(String name, Object object) {
        environment.put(name, object);
    }

    /**
     * Получение всех переменных из пула "variables" в классе AkitaEnvironment
     */
    public Stash getVars() {
        return environment.getVars();
    }
}


