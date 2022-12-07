import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class Day3Test {
    @Test
    void solvePart1Example() {
        // Given
        String example = "src/test/resources/day3.example.txt";
        Day3 day3 = new Day3(example);
        // When
        var actual = day3.solvePart1();
        // Then
        then(actual).isEqualTo(157L);
    }


    @Test
    void solvePart2Example() {
        // Given
        String example = "src/test/resources/day3.example.txt";
        Day3 day3 = new Day3(example);
        // When
        var actual = day3.solvePart2();
        // Then
        then(actual).isEqualTo(0L);
    }
}