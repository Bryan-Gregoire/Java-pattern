package g53735.mentoring.repository;

import g53735.mentoring.config.ConfigManager;
import g53735.mentoring.dto.StudentDto;
import g53735.mentoring.exception.RepositoryException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author g53735
 */
public class StudentDao implements Dao<Integer, StudentDto> {

    Path path;

    public StudentDao() throws RepositoryException {
        try {
            ConfigManager.getInstance().load();
            path = Paths.get(ConfigManager.getInstance()
                    .getProperties("file.url"));
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }

    StudentDao(String uri) {
        this.path = Paths.get(uri);
    }

    @Override
    public void insert(StudentDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucun élement en paramètre " + item);
        }
        try {
            StringBuilder etudiant = new StringBuilder();
            etudiant.append(item.getKey()).append(",")
                    .append(item.getFirstName()).append(",")
                    .append(item.getLastName()).append("\n");
            Files.writeString(path, etudiant, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucun élément en paramètre ");
        }
        try {
//            StringBuilder etudiant = new StringBuilder();
//            etudiant.append(item.getMatricule()).append(",")
//                    .append(item.getFirstName()).append(",")
//                    .append(item.getLastName());
//
//            String list = Files.lines(path).filter(var
//                    -> !var.equalsIgnoreCase(etudiant.toString()))
//                    .collect(Collectors.joining("\n"));
//            Files.writeString(path, list);
//        } catch (IOException e) {
//            System.out.println("Erreur de lecture du fichier "
//                    + e.getMessage());
//        }

            String list = Files.lines(path).filter(var
                    -> !var.contains(Integer.toString(key)))
                    .collect(Collectors.joining("\n"));
            list += "\n";
            Files.writeString(path, list);
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(StudentDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucun élement en paramètre " + item);
        }
        this.delete(item.getKey());
        this.insert(item);
    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucun élément en paramètre ");
        }
        try {
            var line = Files.lines(path)
                    .filter(s -> s.startsWith(key + ""))
                    .findFirst();
            if (line.isPresent()) {
                String foundLine = line.get();
                var dataList = Stream.of(foundLine.split(","))
                        .collect(Collectors.toList());
                return new StudentDto(Integer.parseInt(dataList.get(0)),
                        dataList.get(1), dataList.get(2));
            }
            return null;
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException {
        List<StudentDto> std = new ArrayList<>();
        try {
            std = Files.lines(path).map(fct -> fct.split(","))
                    .map(fct2 -> new StudentDto(Integer.parseInt(fct2[0]),
                    fct2[1], fct2[2])).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RepositoryException(e);
        }
        return std;
    }

}
