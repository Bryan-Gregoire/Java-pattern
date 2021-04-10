package g53735.mentoring.repository;

import g53735.mentoring.dto.StudentDto;
import g53735.mentoring.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author g53735
 */
public class StudentRepository implements Repository<Integer, StudentDto> {

    StudentDao dao;

    public StudentRepository() throws RepositoryException {
        this.dao = new StudentDao();
    }

    public StudentRepository(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public void add(StudentDto item) throws RepositoryException {
        if (contains(item.getKey())) {
            this.dao.update(item);
        } else {
            this.dao.insert(item);
        }
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        this.dao.delete(key);
    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException {
        return this.dao.get(key);
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException {
        return this.dao.getAll();
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return this.dao.get(key) != null;
    }
}
