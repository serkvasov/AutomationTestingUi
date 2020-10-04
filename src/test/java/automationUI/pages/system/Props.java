//package automationUI.pages.system;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Properties;
//
//public class Props {
//    private static final Logger LOG = LoggerFactory.getLogger(Props.class);
//
//    private static Props instance;
//    private static Properties properties;
//
//    private Props() throws Exception {
//        initProperties();
//    }
//
//    private static void initProperties() throws Exception {
//        String sConfigFile = System.getProperty("sConfigFile", "config/app.properties");
//        properties = new Properties();
//        try (InputStream streamFromResources = Props.class.getClassLoader().getResourceAsStream(sConfigFile)) {
//            InputStreamReader isr = new InputStreamReader(streamFromResources, "UTF-8");
//            properties.load(isr);
//        } catch (IOException | NullPointerException e){
//            throw new Exception("не удалось считать проперти ", e);
//        }
//    }
//
//    public static synchronized Props getInstance() throws Exception {
//        if (instance == null) {
//            instance = new Props();
//        }
//        return instance;
//    }
//
//    private String getProp(String name) throws Exception {
//        String val = getProps().getProperty(name,"");
//        if (val.isEmpty()) {
//            LOG.debug(" переменная в файле не найдена", name);
//        }
//        return val.trim();
//    }
//
//    public static Properties getProps() throws Exception {
//        initProperties();
//        return properties;
//    }
//
//    public static String get(String prop) throws Exception {
//        return getInstance().getProp(prop);
//    }
//
//    public String get(String prop, String defaultValue) throws Exception {
//        String value = getInstance().getProp(prop);
//        if (value.isEmpty()) {
//            return defaultValue;
//        }
//        return value;
//    }
//}
