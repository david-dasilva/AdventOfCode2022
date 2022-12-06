import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2 {

    private final String input;

    public Day2(String path) {
        try {
            this.input = Files.readString(Path.of(path), StandardCharsets.UTF_8);

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

        List<RoundPart1> rounds = input.lines()
                                       .map(RoundPart1::fromLine)
                                       .toList();

        for (RoundPart1 round : rounds) {
            int roundResult = 0;
            roundResult += round.right.score;
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

    public record RoundPart1(Shape left, Shape right) {
        public static RoundPart1 fromLine(String line) {
            return new RoundPart1(Shape.fromChar(line.charAt(0)), Shape.fromChar(line.charAt(2)));
        }
    }

    public enum Shape {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        public final int score;

        Shape(int score) {
            this.score = score;
        }

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
