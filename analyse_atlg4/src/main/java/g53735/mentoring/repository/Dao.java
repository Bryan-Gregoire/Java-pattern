package g53735.mentoring.repository;

import java.util.List;

/**
 *
 * @author g53735
 * @param <T>
 */
public interface Dao<T> {

    public void insert(T item);

    public void delete(T item);

    public void update(T item);

    public T get(T item);

    public List<T> getAll(T item);
}
