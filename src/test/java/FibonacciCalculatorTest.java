import com.example.myfibonacciapp.FibonacciCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciCalculatorTest {

    private final FibonacciCalculator calculator = new FibonacciCalculator();

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void testFibonacciNumbers(long index) {
        //ожидаемые числа Фибоначчи для индексов от 1 до 10
        int[] expectedValues = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        int expectedValue = expectedValues[(int)index - 1];
        assertEquals(expectedValue, calculator.calculate(index));
    }

    @Test
    void testFibonacciNumberForIndex1() {
        assertEquals(1, calculator.calculate(1));
    }

    @Test
    void testFibonacciNumberForIndex2() {
        assertEquals(1, calculator.calculate(2));
    }

    @Test
    void testExceptionForIndexLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(0));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(-1));
    }
}
