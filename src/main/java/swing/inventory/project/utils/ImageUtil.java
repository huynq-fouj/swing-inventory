package swing.inventory.project.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {
    
    private static final Logger logger = Logger.getLogger(ImageUtil.class.getName());
    public static final String[] EXTS = {"png", "jpg", "jpeg", "jfif"};

    private ImageUtil() {}

    public static ImageIcon resize(ImageIcon img, int width, int height) {
        Image image = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public static BufferedImage resize(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    public static boolean checkImage(File file) {
        String filename = file.getName();
        String s = filename.substring(filename.lastIndexOf(".") + 1);
        List<String> list = Arrays.asList(EXTS);
        return list.contains(s);
    }

    public static String saveImage(BufferedImage image, String extension) {
        if(image != null) {
            try {
                String folder = Resource.loadUploadImagePath("");
                String fileName = System.currentTimeMillis() + "_" + Math.round(Math.random() * 1000) + "." + extension;
                File output = new File(folder + fileName);
                boolean check = ImageIO.write(image, extension, output);
                if(check) {
                    logger.info(() -> "Save file " + fileName + " to " + folder);
                    return fileName;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String saveImage(BufferedImage image) {
        return saveImage(image, "png");
    }

    public static void removeImage(String fileName) {
        if(fileName != null && !fileName.equals("")) {
            String path = Resource.loadUploadImagePath(fileName);
            File file = new File(path);
            if(file.delete()) logger.info(() -> "Deleted file: " + path);
            else logger.warning(() -> "Can not delete file: " + path);
        }
    }

    public static boolean isEmpty(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] rgbs = new int[w * h];
        img.getRGB(0, 0, w, h, rgbs, 0, w);

        for (int i = 0; i < w * h; i++) {
            if (rgbs[i] != -4861744) {
                return false;
            }
        }

        return true;
    }

}
