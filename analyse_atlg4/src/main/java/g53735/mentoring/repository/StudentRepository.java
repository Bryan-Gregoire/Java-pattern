package g53735.mentoring.repository;

import g53735.mentoring.dto.StudentDto;
import java.util.List;

/**
 *
 * @author g53735
 */
public class StudentRepository implements Repository<StudentDto> {

    StudentDao dao;

    public StudentRepository() {
        this.dao = new StudentDao();
    }

    @Override
    public void add(StudentDto item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (contains(item)) {
            this.dao.update(item);
        } else {
            this.dao.insert(item);
        }
    }

    @Override
    public void remove(StudentDto item) {
        if (item == null || this.dao.get(item) == null) {
            throw new IllegalArgumentException();
        }
        this.dao.delete(item);
    }

    @Override
    public StudentDto get(StudentDto item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        return this.dao.get(item);
    }

    @Override
    public List<StudentDto> getAll() {
        return this.dao.getAll();
    }

    @Override
    public boolean contains(StudentDto item) {
        return this.dao.get(item) != null;
    }
}
