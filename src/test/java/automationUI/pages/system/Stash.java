package automationUI.pages.system;

import com.google.common.collect.Maps;

import java.util.Map;

public class Stash {
    private final Map<String, Object> VAULT = Maps.newHashMap();

    public  Map<String, Object> asMap() {
        return VAULT;
    }

    public  void putValue(String key, Object value) {
        VAULT.put(key, value);
    }

    public  <T> T remove(String key) {
        return (T) VAULT.remove(key);
    }

    public  <T> T getValue(String key) {
        return (T) VAULT.get(key);
    }

    public  <T> void clear() {
        VAULT.clear();
    }
}
