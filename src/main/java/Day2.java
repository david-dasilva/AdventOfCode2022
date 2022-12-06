import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

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
        long score = 0L;

        List<RoundPart2> rounds = input.lines()
                                       .map(RoundPart2::fromLine)
                                       .toList();

        for (RoundPart2 round : rounds) {
            int roundResult = 0;

            roundResult += round.result.score;

            Shape whatIShouldPlay = RiggedMatch.tablePart2.get(round.result).get(round.opponent);
            roundResult += whatIShouldPlay.score;


            score += roundResult;
        }

        return score;
    }

    public static class RiggedMatch {
        // Maps of "what the opponent played" -> "what I should play"
        private static final Map<Shape, Shape> win = Map.of(Shape.PAPER, Shape.SCISSORS,
                Shape.ROCK, Shape.PAPER,
                Shape.SCISSORS, Shape.ROCK);
        private static final Map<Shape, Shape> draw = Map.of(Shape.ROCK, Shape.ROCK,
                Shape.PAPER, Shape.PAPER,
                Shape.SCISSORS, Shape.SCISSORS);
        private static final Map<Shape, Shape> lose = Map.of(Shape.PAPER, Shape.ROCK,
                Shape.ROCK, Shape.SCISSORS,
                Shape.SCISSORS, Shape.PAPER);
        public static Map<Result, Map<Shape, Shape>> tablePart2 = Map.of(Result.WIN, win,
                Result.DRAW, draw,
                Result.LOSE, lose);
    }

    public record RoundPart1(Shape left, Shape right) {
        public static RoundPart1 fromLine(String line) {
            return new RoundPart1(Shape.fromChar(line.charAt(0)), Shape.fromChar(line.charAt(2)));
        }
    }

    public record RoundPart2(Shape opponent, Result result) {
        public static RoundPart2 fromLine(String line) {
            return new RoundPart2(Shape.fromChar(line.charAt(0)), Result.fromChar(line.charAt(2)));
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

    public enum Result {
        WIN(6),
        DRAW(3),
        LOSE(0);

        public final int score;

        Result(int score) {
            this.score = score;
        }

        public static Result fromChar(char c) {
            return switch(c) {
                case 'X' -> LOSE;
                case 'Y' -> DRAW;
                case 'Z' -> WIN;
                default -> throw new IllegalArgumentException();
            };
        }
    }
}
