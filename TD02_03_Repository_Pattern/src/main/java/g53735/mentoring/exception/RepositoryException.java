package g53735.mentoring.exception;

import java.io.IOException;

/**
 *
 * @author g53735
 */
public class RepositoryException extends IOException {

    /**
     * Creates a new instance of <code>RepositoryException</code> without detail
     * message.
     */
    public RepositoryException() {
        super();
    }

    /**
     * Constructs an instance of <code>RepositoryException</code> with the
     * specified detail message.
     *
     * @param msg message of the exception.
     */
    public RepositoryException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>RepositoryException</code> and wrapped
     * the source exception.
     *
     * @param exception wrapped exception.
     */
    public RepositoryException(Exception exception) {
        super(exception);
    }
}
