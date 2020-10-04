package automationUI.pages.system;


import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Set;

public class AnnotationScanner {
    private final Reflections reflection;

    public AnnotationScanner(String packagePath) {
        reflection = new Reflections(packagePath);
    }

    public Set<Class<?>> getClassesAnnotateWith(Class<? extends Annotation> annotation) {
        try {
            return reflection.getTypesAnnotatedWith(annotation);
        } catch (Throwable e) {
            throw new RuntimeException("Не удалось просканировать пакет %s для поиска page object classes", e);
        }
    }
}