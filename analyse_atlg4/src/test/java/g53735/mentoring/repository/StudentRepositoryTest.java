package g53735.mentoring.repository;

import g53735.mentoring.dto.StudentDto;
import g53735.mentoring.exception.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author g53735
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StudentRepositoryTest {

    @Mock
    private StudentDao mock;

    private final StudentDto bob;

    private final StudentDto patrick;

    private static final int KEY = 12_345;

    private final List<StudentDto> all;

    public StudentRepositoryTest() {
        System.out.println("StudentRepositoryTest Constructor");
        //Test data
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = new ArrayList<>();
        all.add(bob);
        all.add(patrick);
    }

    @BeforeEach
    void init() throws RepositoryException {
        System.out.println("==== BEFORE EACH =====");
        //Mock behaviour
        Mockito.lenient().when(mock.get(bob.getKey())).thenReturn(bob);
        Mockito.lenient().when(mock.get(patrick.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.getAll()).thenReturn(all);
        Mockito.lenient().when(mock.get(null)).thenThrow(RepositoryException.class);
    }

    @Test
    public void testGetExist() throws Exception {
        System.out.println("testGetExist");
        //Arrange
        StudentDto expected = bob;
        StudentRepository repository = new StudentRepository(mock);
        //Action
        StudentDto result = repository.get(KEY);
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).get(KEY);
    }

    @Test
    public void testGetNotExist() throws Exception {
        System.out.println("test get not exist");
        //Arrange
        StudentDto expected = null;
        StudentRepository repository = new StudentRepository(mock);
        //Action
        StudentDto result = repository.get(99999);
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).get(99999);
    }

    @Test
    public void testGetInvalidParam() throws Exception {
        System.out.println("test Get Invalid Parameter");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Assert
        assertThrows(RepositoryException.class, () -> {
            repository.get(null);
        });

        Mockito.verify(mock, times(1)).get(null);
    }

    @Test
    public void testAddWhenExisting() throws Exception {
        System.out.println("testAddWhenExisting");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        repository.add(bob);
        //Assert
        Mockito.verify(mock, times(1)).get(KEY);
        Mockito.verify(mock, times(1)).update(bob);
        Mockito.verify(mock, times(0)).insert(any(StudentDto.class));
    }

    @Test
    public void testAddWhenNotExisting() throws Exception {
        System.out.println("testAddWhenExisting");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        repository.add(patrick);
        //Assert
        Mockito.verify(mock, times(1)).get(patrick.getKey());
        Mockito.verify(mock, times(0)).update(patrick);
        Mockito.verify(mock, times(1)).insert(any(StudentDto.class));
    }

    @Test
    public void testRemoveWhenExisting() throws Exception {
        System.out.println("test remove when existing");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        repository.remove(KEY);
        //Assert
        Mockito.verify(mock, times(1)).delete(bob.getKey());
    }

    @Test
    public void testRemoveWhenNotExisting() throws Exception {
        System.out.println("test remove when not existing");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        repository.remove(patrick.getKey());
        //Assert
        Mockito.verify(mock, times(1)).delete(patrick.getKey());
    }

    @Test
    public void testGetAllExist() throws Exception {
        System.out.println("test get All");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        repository.getAll();
        //Assert
        Mockito.verify(mock, times(1)).getAll();
    }

    @Test
    public void testContainsWhenExist() throws Exception {
        System.out.println("test get All");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        boolean result = repository.contains(KEY);
        //Assert
        assertTrue(result);
        Mockito.verify(mock, times(1)).get(KEY);
    }

    @Test
    public void testContainsWhenNotExist() throws Exception {
        System.out.println("test get All");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        boolean result = repository.contains(patrick.getKey());
        //Assert
        assertFalse(result);
        Mockito.verify(mock, times(1)).get(patrick.getKey());
    }
}
