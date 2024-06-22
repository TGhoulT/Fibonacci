import com.example.myfibonacciapp.FibonacciCalculator;
import com.example.myfibonacciapp.FibonacciEntity;
import com.example.myfibonacciapp.FibonacciRepository;
import com.example.myfibonacciapp.FibonacciService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FibonacciServiceTest {

    @Mock
    private FibonacciRepository repository;

    @Mock
    private FibonacciCalculator calculator;

    @InjectMocks
    private FibonacciService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFibonacciNumberFromDatabase() {
        int index = 10;
        BigInteger expectedValue = BigInteger.valueOf(55);

        //мОкаю репозиторий для возвращения объекта FibonacciEntity с ожидаемым значением
        FibonacciEntity entity = new FibonacciEntity();
        entity.setIndex(index);
        entity.setValue(expectedValue);
        when(repository.findByIndex(index)).thenReturn(Optional.of(entity));

        BigInteger result = service.getFibonacciNumber(index);

        //проверяю, что значение было получено из базы данных
        assertEquals(expectedValue, result);
        verify(repository, times(1)).findByIndex(index);
        verify(calculator, times(0)).calculate(anyLong());
    }

    @Test
    void testGetFibonacciNumberAndSaveToDatabase() {
        int index = 10;
        BigInteger expectedValue = BigInteger.valueOf(55);

        //мОкаю репозиторий для возвращения пустого Optional
        when(repository.findByIndex(index)).thenReturn(Optional.empty());

        BigInteger result = service.getFibonacciNumber(index);

        //проверяю, что значение было вычислено и сохранено в базу данных
        assertEquals(expectedValue, result);
        verify(repository, times(1)).findByIndex(index);
        verify(repository, times(1)).save(any(FibonacciEntity.class));
    }

    @Test
    void testGetFibonacciNumberWithInvalidIndex() {
        int index = 0;

        //проверяю, что для недопустимого индекса выбрасывается исключение
        assertThrows(IllegalArgumentException.class, () -> service.getFibonacciNumber(index));
    }
}
