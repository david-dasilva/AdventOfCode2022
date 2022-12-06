import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class Day1Test {

    @Test
    void solvePart1Example() {
        // Given
        String example = "src/test/resources/day1.example.txt";
        Day1 day1 = new Day1(example);
        // When
        var actual = day1.solvePart1();
        // Then
        then(actual).isEqualTo(24000L);
    }


    @Test
    void solvePart2Example() {
        // Given
        String example = "src/test/resources/day1.example.txt";
        Day1 day1 = new Day1(example);
        // When
        var actual = day1.solvePart2();
        // Then
        then(actual).isEqualTo(45000L);
    }
}
