import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class Day2Test {

    @Test
    void solvePart1Example() {
        // Given
        String example = "src/test/resources/day2.example.txt";
        Day2 day2 = new Day2(example);
        // When
        var actual = day2.solvePart1();
        // Then
        then(actual).isEqualTo(15L);
    }


    @Test
    void solvePart2Example() {
        // Given
        String example = "src/test/resources/day2.example.txt";
        Day2 day2 = new Day2(example);
        // When
        var actual = day2.solvePart2();
        // Then
        then(actual).isEqualTo(12L);
    }
}
