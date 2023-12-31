package g53735.mentoring.repository;

import g53735.mentoring.dto.Dto;
import g53735.mentoring.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author g53735
 * @param <K>
 * @param <T>
 */
public interface Repository<K, T extends Dto<K>> {

    public void add(T item) throws RepositoryException;

    public void remove(K key) throws RepositoryException;

    public T get(K key) throws RepositoryException;

    public List<T> getAll() throws RepositoryException;

    public boolean contains(K key) throws RepositoryException;
}
