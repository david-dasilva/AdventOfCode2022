import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day2 {

    private final String input;
    private List<Round> rounds;

    public Day2(String path) {
        try {
            this.input = Files.readString(Path.of(path), StandardCharsets.UTF_8);
            this.rounds = input.lines()
                               .map(l -> Round.fromLine(l))
                               .toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Day2 day2 = new Day2("src/main/resources/day2.input.txt");
        System.out.println("⭐️" + day2.solvePart1());
        System.out.println("⭐️⭐️" + day2.solvePart2());
    }

    public long solvePart1() {
        long score = 0L;

        for (Round round : rounds) {
            int roundResult = 0;
            switch (round.right) {
                case ROCK -> roundResult += 1;
                case PAPER -> roundResult += 2;
                case SCISSORS -> roundResult += 3;
                default -> throw new IllegalArgumentException();
            }
            if ((round.left == Shape.SCISSORS && round.right == Shape.ROCK) ||
                    (round.left == Shape.PAPER && round.right == Shape.SCISSORS) ||
                    (round.left == Shape.ROCK && round.right == Shape.PAPER)) {
                roundResult += 6;
            } else if (round.left == round.right) {
                roundResult += 3;
            }
            score += roundResult;
        }
        return score;
    }

    public long solvePart2() {

        return 0;
    }

    public record Round(Shape left, Shape right) {
        public static Round fromLine(String line) {
            return new Round(Shape.fromChar(line.charAt(0)), Shape.fromChar(line.charAt(2)));
        }
    }

    public enum Shape {
        ROCK,
        PAPER,
        SCISSORS;

        public static Shape fromChar(char c) {
            return switch(c) {
                case 'A', 'X' -> ROCK;
                case 'B', 'Y' -> PAPER;
                case 'C', 'Z' -> SCISSORS;
                default -> throw new IllegalArgumentException();
            };
        }
    }
}
