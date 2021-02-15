package g53735.mentoring.repository;

import java.util.List;

/**
 *
 * @author g53735
 * @param <T>
 */
public interface Repository<T> {

    public void add(T item);

    public void remove(T item);

    public T get(T item);

    public List<T> getAll();

    public boolean contains(T item);
}
