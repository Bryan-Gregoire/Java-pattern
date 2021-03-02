package g53735.mentoring.repository;

import g53735.mentoring.dto.Dto;
import g53735.mentoring.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author g53735
 * @param <T>
 */
public interface Dao<K, T extends Dto<K>> {

    public void insert(T item) throws RepositoryException;

    public void delete(K key) throws RepositoryException;

    public void update(T item) throws RepositoryException;

    public T get(K key) throws RepositoryException;

    public List<T> getAll() throws RepositoryException;
}
