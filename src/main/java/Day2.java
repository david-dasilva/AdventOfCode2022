import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Day2 {

    private final String input;
    private final List<String> rounds;

    // Maps of "what I have played", "what the result is"
    private static final Map<Shape, Integer> rock = Map.of(Shape.ROCK, Result.DRAW.score,
            Shape.PAPER, Result.WIN.score,
            Shape.SCISSORS, Result.LOSE.score);

    private static final Map<Shape, Integer> paper = Map.of(Shape.PAPER, Result.DRAW.score,
            Shape.SCISSORS, Result.WIN.score,
            Shape.ROCK, Result.LOSE.score);

    private static final Map<Shape, Integer> scissors = Map.of(Shape.SCISSORS, Result.DRAW.score,
            Shape.ROCK, Result.WIN.score,
            Shape.PAPER, Result.LOSE.score);
    public static Map<Shape, Map<Shape, Integer>> resultForShapes = Map.of(Shape.ROCK, rock,
            Shape.PAPER, paper,
            Shape.SCISSORS, scissors);

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
    public static Map<Result, Map<Shape, Shape>> whatToPlayTo = Map.of(Result.WIN, win,
            Result.DRAW, draw,
            Result.LOSE, lose);

    public Day2(String path) {
        try {
            this.input = Files.readString(Path.of(path), StandardCharsets.UTF_8);
            this.rounds = input.lines().toList();

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

        List<String> rounds = input.lines().toList();

        for (String round : rounds) {
            int roundResult = 0;

            Shape opponent = Shape.fromChar(round.charAt(0));
            Shape me = Shape.fromChar(round.charAt(2));

            roundResult += me.score;
            roundResult += resultForShapes.get(opponent).get(me);

            score += roundResult;
        }
        return score;
    }

    public long solvePart2() {
        long score = 0L;



        for (String round : rounds) {
            int roundResult = 0;
            Shape opponent = Shape.fromChar(round.charAt(0));
            Result result = Result.fromChar(round.charAt(2));
            roundResult += result.score;
            Shape whatIShouldPlay = whatToPlayTo.get(result).get(opponent);
            roundResult += whatIShouldPlay.score;
            score += roundResult;
        }

        return score;
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
