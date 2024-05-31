package swing.inventory.project.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppData {

    private static final String DATAPATH = Resource.loadPathResource("appData\\data.properties");
    
    private AppData() {}

    public static Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        try {
            FileInputStream loader = new FileInputStream(DATAPATH);
            Properties properties = new Properties();
            properties.load(loader);
            loader.close();
            for (String key : properties.stringPropertyNames()) {
                map.put(key, properties.get(key).toString());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        if(map.isEmpty()) {
            map.put(AppDataKey.ISFULLSCREEN, "false");
            map.put(AppDataKey.ICONPACK, "FLAT_ICON");
            save(map);
        }
        return map;
    }

    public static void save(Map<String, String> map) {
        Properties properties = new Properties();
        for(Map.Entry<String, String> entry: map.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        try {
            FileOutputStream writer = new FileOutputStream(DATAPATH);
            properties.store(writer, null);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
