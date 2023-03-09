import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String neededPhotoType = "ARW";
        Path pathSDCard = Paths.get("E:\\DCIM\\100MSDCF");
        Path pathDirType1 = Paths.get("D:\\Photography\\Sony a6500\\JPEG's");
        Path pathDirType2 = Paths.get("D:\\Photography\\Sony a6500\\ARW's");

        if (isDirectoryExist(pathSDCard, pathDirType1, pathDirType2)) {
            File dirOriginalFiles = new File(pathSDCard.toString()),
                    dirType1 = new File(pathDirType1.toString()),
                    dirType2 = new File(pathDirType2.toString());

            File originalFile = null;
            File fileOtherType = null;

            int counter = 0;
            for (File photo : dirType1.listFiles()) {
                StringBuilder newName = new StringBuilder(photo.getName());
                newName = new StringBuilder(newName.substring(0, newName.indexOf(".") + 1));
                newName = new StringBuilder(newName.append(neededPhotoType));

                originalFile = new File(dirOriginalFiles.toPath() + "\\" + newName);
                fileOtherType = new File(dirType2.toPath() + "\\" + newName);

                try {
                    Files.copy(originalFile.toPath(), fileOtherType.toPath());
                    counter++;
                    System.out.println("Файлов скопировано: " + counter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.exit(1);
        }
    }

    static boolean isDirectoryExist(Path... paths) {
        boolean result = true;
        for (Path path : paths) {
            if (Files.exists(path)) {
                System.out.println("Directory exist: " + path);
            } else {
                System.out.println("Directory not exist: " + path);
                result = false;
            }
        }
        return result;
    }
}
