package automationUI.pages;


import automationUI.pages.system.anotations.NameOfElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public abstract class BasePage extends ElementsContainer {
    private Map<String, Object> namedElements;

    public abstract BasePage visiblityCheck();

    @Override
    public void setSelf(@Nonnull SelenideElement self) {
        super.setSelf(self);
        initialize();
    }

    @Nonnull
    public BasePage initialize() {
        namedElements = readNamedElements();
        return this;
    }

    @Nonnull
    public SelenideElement getElement(@Nonnull String cucumberElementName) {
        return (SelenideElement) Optional.ofNullable(namedElements.get(cucumberElementName))
                .orElseThrow(() -> new IllegalArgumentException(format("Список %s не описан на странице %s", cucumberElementName, this.getClass().getName())));
    }

    @Nonnull
    public ElementsCollection getCollection(@Nonnull String cucumberElementName) {
        return (ElementsCollection) Optional.ofNullable(namedElements.get(cucumberElementName))
                .orElseThrow(() -> new IllegalArgumentException(format("Список %s не описан на странице %s", cucumberElementName, this.getClass().getName())));
    }

    @Nonnull
    public BasePage getBlock(@Nonnull String blockName) {
        BasePage basePage = (BasePage) Optional.ofNullable(namedElements.get(blockName))
                .orElseThrow(() -> new IllegalArgumentException(format("Список %s не описан на странице %s", blockName, this.getClass().getName())));
        basePage.initialize();
        return basePage;
    }

    @Nonnull
    public List<BasePage> getBlocksList(@Nonnull String listBlockName) {
        Object value = namedElements.get(listBlockName);
        if (!(value instanceof List)) {
            throw new IllegalArgumentException(format("Список %s не описан на странице %s", listBlockName, this.getClass().getName()));
        }
        Stream<Object> s = ((List) value).stream();
        return s.map(BasePage::castToBasePage).collect(toList());
    }

    public Map<String, Object> readNamedElements() {
        checkNamedAnnotations();
        return Arrays.stream(getClass().getDeclaredFields()).filter(f -> f.getDeclaredAnnotation(NameOfElement.class) != null)
                .peek(this::checkFieldType).collect(toMap(f -> f.getDeclaredAnnotation(NameOfElement.class).value(), this::extractFieldValueViaReflection));
    }

    public void checkFieldType(Field f) {
        if (!SelenideElement.class.isAssignableFrom(f.getType()) && !BasePage.class.isAssignableFrom(f.getType())) {
            checkCollectionFieldType(f);
        }
    }

    private void checkCollectionFieldType(Field f) {
        if (ElementsCollection.class.isAssignableFrom(f.getType())) {
            return;
        } else if (List.class.isAssignableFrom(f.getType())) {
            ParameterizedType listType = (ParameterizedType) f.getGenericType();
            Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
            if (SelenideElement.class.isAssignableFrom(listClass) || BasePage.class.isAssignableFrom(listClass)) {
                return;
            }
        }
        throw new IllegalStateException(
                format("Поле с аннотацией @NameOfElement должно иметь тип SelenideElement или List<SelenideElement>.\n" +
                        "Если поле описывает блок, оно должно принадлежать классу, унаследованному от BasePage." +
                        "Найдено поле с типом %s", f.getType()));
    }

    private void checkNamedAnnotations() {
        List<String> list = Arrays.stream(getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(NameOfElement.class) != null)
                .map(f -> f.getDeclaredAnnotation(NameOfElement.class).value())
                .collect(Collectors.toList());
        Set<String> duplications = list.stream().filter(i -> Collections.frequency(list, i) > 1).collect(Collectors.toSet());
        if (duplications.size() != 0) {
            String values = String.join(", ", duplications);
            throw new IllegalArgumentException(String.format("Найдено несколько одинаковых аннотаций @NameOfElement(\"%s\") в классе %s", values, this.getClass().getName()));
        }
    }

    private static BasePage castToBasePage(Object object) {
        if (object instanceof BasePage) {
            return (BasePage) object;
        }
        return null;
    }

    private Object extractFieldValueViaReflection(Field field) {
        return extractFieldValue(field, this);
    }

    private Object extractFieldValue(Field field, Object owner) {
        field.setAccessible(true);
        try {
            return field.get(owner);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}
