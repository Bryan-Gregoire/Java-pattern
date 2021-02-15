package g53735.mentoring.repository;

import TD2_Repository_Pattern.ConfigManager;
import g53735.mentoring.dto.StudentDto;
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
public class StudentDao implements Dao<StudentDto> {

    Path path;

    public StudentDao() {
        try {
            ConfigManager.getInstance().load();
            path = Paths.get(ConfigManager.getInstance()
                    .getProperties("file.url"));
        } catch (IOException e) {
            System.out.println("Erreur de chargement du fichier properties "
                    + e.getMessage());
        }
    }

    @Override
    public void insert(StudentDto item) {
        try {
            StringBuilder etudiant = new StringBuilder();
            etudiant.append(item.getMatricule()).append(",")
                    .append(item.getFirstName()).append(",")
                    .append(item.getLastName()).append("\n");
            Files.writeString(path, etudiant, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier "
                    + e.getMessage());
        }
    }

    @Override
    public void delete(StudentDto item) {
        if (item == null || get(item) == null) {
            throw new IllegalArgumentException();
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
                    -> !var.contains(Integer.toString(item.getMatricule())))
                    .collect(Collectors.joining("\n"));
            list += "\n";
            Files.writeString(path, list);
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier "
                    + e.getMessage());
        }
    }

    @Override
    public void update(StudentDto item) {
        this.delete(item);
        this.insert(item);
    }

    @Override
    public StudentDto get(StudentDto item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        try {

            Stream<String> etu = Files.lines(path);

            StringBuilder etudiant = new StringBuilder();
            etudiant.append(item.getMatricule()).append(",")
                    .append(item.getFirstName()).append(",")
                    .append(item.getLastName());
            boolean find = etu.anyMatch(var
                    -> var.equalsIgnoreCase(etudiant.toString()));
            return find ? item : null;
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier "
                    + e.getMessage());
        }
        return null;
    }

    @Override
    public List<StudentDto> getAll() {
        List<StudentDto> std = new ArrayList<StudentDto>();
        try {
            std = Files.lines(path).map(fct -> fct.split(","))
                    .map(fct2 -> new StudentDto(Integer.parseInt(fct2[0]), fct2[1], fct2[2])).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier "
                    + e.getMessage());
        }
        return std;
    }

}
