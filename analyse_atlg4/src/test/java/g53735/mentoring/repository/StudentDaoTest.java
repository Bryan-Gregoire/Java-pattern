package g53735.mentoring.repository;

import g53735.mentoring.dto.StudentDto;
import g53735.mentoring.exception.RepositoryException;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Utilisateur
 */
public class StudentDaoTest {

    private final StudentDto bob;
    private final StudentDto patrick;

    private static final int KEY = 12345;

    private static final String FILE_URL = "test_repo_students.txt";
    private static final String FILE_URL_EMPTY = "resource_test_empty.txt";
    private static final String FILE_URL_GET_ALL = "resource_test_fix.txt";

    //private final String url = getClass().getClassLoader()
    //.getResource(FILE_URL).getFile();
    private final String url = new File(Objects.requireNonNull(getClass()
            .getClassLoader().getResource(FILE_URL)).getFile()).getPath();

    private final String url_empty = new File(Objects.requireNonNull(getClass()
            .getClassLoader().getResource(FILE_URL_EMPTY))
            .getFile()).getPath();

    private final String url_GetAll = new File(Objects.requireNonNull(getClass()
            .getClassLoader().getResource(FILE_URL_GET_ALL))
            .getFile()).getPath();

    private final List<StudentDto> all;

    public StudentDaoTest() {
        System.out.println("==== StudentDaoTest Constructor =====");
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = List.of(new StudentDto(64_931, "Olsen", "Maggy"),
                new StudentDto(73_780, "Frost", "Phoebe"),
                new StudentDto(94_853, "Ortega", "Skyler"),
                new StudentDto(93_371, "Blankenship", "Byron"),
                new StudentDto(82_227, "Cote", "Molly"),
                bob);

//        url = getClass().getClassLoader()
//                .getResource(FILE_URL)
//                .getFile();
    }

    /**
     * Test of get method, of class StudentDao.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("Test get");

        StudentDao instance = new StudentDao(url);

        StudentDto expResult = bob;
        StudentDto result = instance.get(KEY);

        assertEquals(expResult, result);
    }

    @Test
    public void testGetNotExist() throws Exception {
        System.out.println("testSelectNotExist");
        //Arrange
        StudentDao dao = new StudentDao(url);
        //Action
        StudentDto result = dao.get(patrick.getKey());
        //Assert
        assertNull(result);
    }

    @Test
    public void testGetIncorrectParameter() throws Exception {
        System.out.println("testSelectIncorrectParameter");
        //Arrange
        StudentDao dao = new StudentDao(url);
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            dao.get(incorrect);
        });
    }

    @Test
    public void testGetWhenFileNotFound() throws Exception {
        System.out.println("testSelectWhenFileNotFound");
        //Arrange
        String urll = "test/NoFile.txt";
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            StudentDao dao = new StudentDao(urll);
            dao.get(KEY);
        });
    }

    /**
     * Test of insert method, of class StudentDao.
     */
    @Test
    public void testInsertExist() throws Exception {
        System.out.println(" Test insert exist");

        StudentDao dao = new StudentDao(url);
        StudentDto item = new StudentDto(53735, "Bryan", "Grégoire");
        dao.insert(item);

        assertEquals(dao.get(item.getKey()),
                new StudentDto(53735, "Bryan", "Grégoire"));
    }

    /**
     * Test of insert method, of class StudentDao.
     */
    @Test
    public void testInsertNotExist() throws Exception {
        System.out.println(" Test insert not exist");
        //Pas compris ce test

        StudentDao dao = new StudentDao(url);
        StudentDto item = new StudentDto(777, "Nostrof", "Kostrof");
        dao.insert(item);

        assertEquals(dao.get(item.getKey()),
                new StudentDto(777, "Nostrof", "Kostrof"));
    }

    /**
     * Test of insert method, of class StudentDao.
     */
    @Test
    public void testInsertInvalidParam() throws Exception {
        System.out.println(" Test insert invalid param");

        StudentDao dao = new StudentDao(url);
        assertThrows(RepositoryException.class, () -> {
            dao.insert(null);
        });

    }

    /**
     * Test of insert method, of class StudentDao.
     */
    @Test
    public void testInsertWhenFileNotFound() throws Exception {
        System.out.println(" Test insert when file not found");

        String url = "test/NoFile.txt";
        assertThrows(RepositoryException.class, () -> {
            StudentDao dao = new StudentDao(url);
            dao.insert(bob);
        });

    }

    /**
     * Test of delete method, of class StudentDao.
     */
    @Test
    public void testDelete() throws Exception {
        StudentDao dao = new StudentDao(url);
        dao.delete(64931);
        assertNull(dao.get(64931));
    }

    /**
     * Test of delete method, of class StudentDao.
     */
    @Test
    public void testDeleteNotExist() throws Exception {
        StudentDao dao = new StudentDao(url);
        dao.delete(73780);
        assertNull(dao.get(73780));
    }

    /**
     * Test of delete method, of class StudentDao.
     */
    @Test
    public void testDeleteInvalidParam() throws Exception {
        StudentDao dao = new StudentDao(url);
        assertThrows(RepositoryException.class, () -> {
            dao.delete(null);
        });
    }

    /**
     * Test of delete method, of class StudentDao.
     */
    @Test
    public void testDeleteWhenFileNotFound() throws Exception {
        String url = "test/NoFile.txt";
        assertThrows(RepositoryException.class, () -> {
            StudentDao dao = new StudentDao(url);
            dao.delete(64931);
        });
    }

    /**
     * Test of update method, of class StudentDao.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        StudentDto item = new StudentDto(94_853, "dondolski", "kraufski");
        StudentDao dao = new StudentDao(url);
        dao.update(item);
        assertEquals(dao.get(item.getKey()),
                new StudentDto(94_853, "dondolski", "kraufski"));
    }

    /**
     * Test of update method, of class StudentDao.
     */
    @Test
    public void testUpdateNotExist() throws Exception {
        System.out.println("update not exist");
        StudentDto item = new StudentDto(300, "astos", "daros");
        StudentDao dao = new StudentDao(url);
        dao.update(item);
        assertEquals(dao.get(item.getKey()),
                new StudentDto(300, "astos", "daros"));

    }

    /**
     * Test of update method, of class StudentDao.
     */
    @Test
    public void testUpdateInvalidParam() throws Exception {
        System.out.println("update invalid param");

        StudentDao dao = new StudentDao(url);
        assertThrows(RepositoryException.class, () -> {
            dao.update(null);
        });
    }

    /**
     * Test of update method, of class StudentDao.
     */
    @Test
    public void testUpdateWhenFileNotFound() throws Exception {
        String url = "test/NoFile.txt";
        assertThrows(RepositoryException.class, () -> {
            StudentDao dao = new StudentDao(url);
            dao.update(new StudentDto(73780, "Swag", "Man"));
        });
    }

    /**
     * Test of getAll method, of class StudentDao.
     */
    @Test
    public void testGetAllExist() throws Exception {
        System.out.println("getAll exist");
        StudentDao dao = new StudentDao(url_GetAll);
        assertEquals(dao.getAll(), all);
    }

    /**
     * Test of getAll method, of class StudentDao.
     */
    @Test
    public void testGetAllNotExist() throws Exception {
        System.out.println("getAll not exist");
        List<StudentDto> students = new LinkedList<>();
        StudentDao dao = new StudentDao(url_empty);
        assertEquals(dao.getAll(), students);
    }

    /**
     * Test of getAll method, of class StudentDao.
     */
    @Test
    public void testGetAllWhenFileNotFound() throws Exception {
        System.out.println("test getAll when file not found");
        //Arrange
        String url = "test/NoFile.txt";
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            StudentDao dao = new StudentDao(url);
            dao.getAll();
        });
    }
}
