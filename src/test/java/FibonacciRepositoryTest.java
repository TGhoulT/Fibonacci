import com.example.myfibonacciapp.FibonacciEntity;
import com.example.myfibonacciapp.FibonacciRepository;
import com.example.myfibonacciapp.MyFibonacciAppApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MyFibonacciAppApplication.class)
public class FibonacciRepositoryTest {
    @Autowired
    private FibonacciRepository repository;

    @Test
    public void testSaveAndFindByIndex() {
        int index = 15;
        BigInteger value = BigInteger.valueOf(610);

        // Сохраняем новую запись в базу данных
        FibonacciEntity entity = new FibonacciEntity();
        entity.setIndex(index);
        entity.setValue(value);
        repository.save(entity);

        // Проверяем, что запись сохранена правильно
        Optional<FibonacciEntity> savedEntity = repository.findByIndex(index);
        assertTrue(savedEntity.isPresent());
        assertEquals(value, savedEntity.get().getValue());
    }

    @Test
    public void testFindByIndexWhenNotExists() {
        int nonExistingIndex = 100;

        //проверяем, что при поиске несуществующего индекса возвращается пустой Optional
        Optional<FibonacciEntity> result = repository.findByIndex(nonExistingIndex);
        assertFalse(result.isPresent());
    }

    @Test
    public void testSaveDuplicateIndex() {
        int index = 20;
        BigInteger value1 = BigInteger.valueOf(6765);
        BigInteger value2 = BigInteger.valueOf(12345);

        //сохраняем первую запись с индексом 20
        FibonacciEntity entity1 = new FibonacciEntity();
        entity1.setIndex(index);
        entity1.setValue(value1);
        repository.save(entity1);

        //попытка сохранить вторую запись с тем же индексом
        FibonacciEntity entity2 = new FibonacciEntity();
        entity2.setIndex(index);
        entity2.setValue(value2);

        //ожидаем, что при попытке сохранить вторую запись с тем же индексом будет выброшено исключение
        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(entity2);
        });

        //проверяем, что в базе данных все еще только одна запись с индексом 20 и значением value1
        Optional<FibonacciEntity> savedEntity = repository.findByIndex(index);
        assertTrue(savedEntity.isPresent());
        assertEquals(value1, savedEntity.get().getValue());
    }

}
