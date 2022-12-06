import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day1 {


    private final String input;
    private final List<Long> caloriesByElf;
    public Day1(String path) {
        try {
            this.input = Files.readString(Path.of(path), StandardCharsets.UTF_8);
            this.caloriesByElf = Arrays.stream(input.split("\n\n"))
                    .map(elf -> elf.lines()
                                   .map(Long::parseLong)
                                   .reduce(0L, Long::sum))
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1("src/main/resources/day1.input.txt");
        System.out.println("⭐️" + day1.solvePart1());
        System.out.println("⭐️⭐️" + day1.solvePart2());
    }

    public long solvePart1() {
        return caloriesByElf.stream()
                            .max(Long::compareTo)
                            .get();

    }

    public long solvePart2() {
        return caloriesByElf.stream()
                            .sorted(Comparator.reverseOrder())
                            .limit(3)
                            .reduce(0L, Long::sum);
    }
}
