package automationUI.pages.system;

import java.util.HashMap;
import java.util.Map;

public class Stash {
    private static final Map<String, Object> VAULT = new HashMap<>();

    private Stash() {
        throw new IllegalAccessError("Utility class");
    }

    public static Map<String, Object> asMap() {
        return VAULT;
    }

    public static void put(String key, Object value) {
        VAULT.put(key, value);
    }

    public static <T> T remove(String key) {
        return (T) VAULT.remove(key);
    }

    public static <T> T getvalue(String key) {
        return (T) VAULT.get(key);
    }

    public static <T> void clear() {
        VAULT.clear();
    }
}
