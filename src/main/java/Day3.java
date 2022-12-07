import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day3 {

    private final String input;

    public Day3(String path) {
        try {
            this.input = Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3("src/main/resources/day3.input.txt");
        System.out.println("⭐️" + day3.solvePart1());
        System.out.println("⭐️⭐️" + day3.solvePart2());
    }

    public long solvePart1() {
        return -1;
    }

    public long solvePart2() {
        return -1;
    }


}
