import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            Map<String,Long> map = Files.walk(Paths.get("/home/lekh/IdeaProjects/Test/src"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toMap(File::getAbsolutePath, File::length));
                    //.forEach(System.out::println);
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
