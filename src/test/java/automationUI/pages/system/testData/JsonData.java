package automationUI.pages.system.testData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class JsonData<T> {
    private final T type;
    private Gson gson = new GsonBuilder().create();

    public JsonData(T type) {
        this.type = type;
    }

    public T getData(String filePath) {
        try (Reader reader = new FileReader(new File(filePath).getAbsolutePath())) {
            return (T) gson.fromJson(reader, type.getClass());
        } catch (Throwable th) {
            throw new RuntimeException("Can't parse data for: " + filePath, th);
        }
    }
}
