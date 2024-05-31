package swing.inventory.project.themes;

import swing.inventory.project.utils.Resource;

public class IconPack {

    public static final String FLAT_ICON = "icons";
    public static final String COLOR_ICON = "icons_2";
    private static String name = FLAT_ICON;

    private IconPack() {}

    public static String getIcon(String path) {
        return Resource.loadStaticImagePath(name + "\\" + path);
    }

    public static String getFlatIcon(String path) {
        return Resource.loadStaticImagePath(FLAT_ICON + "\\" + path);
    }

    public static String getColorIcon(String path) {
        return Resource.loadStaticImagePath(COLOR_ICON + "\\" + path);
    }

    public static String getIcon(String pack, String path) {
        return Resource.loadStaticImagePath(pack + "\\" + path);
    }

    public static void setPack(String pack) {
        IconPack.name = pack;
    }

    public static String getPack() {
        return IconPack.name;
    }

}