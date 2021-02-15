package TD2_Repository_Pattern;

import g53735.mentoring.dto.StudentDto;
import g53735.mentoring.repository.StudentRepository;
import java.io.IOException;
import java.util.List;

public class Mentoring {

    public Mentoring() {
    }

    public static void main(String[] args) {

        try {
            ConfigManager.getInstance().load();
        } catch (IOException e) {
            System.out.println("Chargement de la configuration impossible "
                    + e.getMessage());
        }

//        String author = ConfigManager.getInstance().getProperties("app.author");
//        System.out.println("Auteur : " + author);
        StudentRepository std = new StudentRepository();
        StudentDto shahed = new StudentDto(52046, "Chahed", "Akeche");
        StudentDto dylan = new StudentDto(54027, "Dylan", "Bricar");
        std.add(new StudentDto(53735, "Bryan", "Grégoire"));
        std.add(new StudentDto(54627, "Jeremie", "Seshie"));
        std.add(new StudentDto(54637, "Bilal", "Zidi"));
        std.add(shahed);
        std.add(dylan);

        System.out.println("Je veux la matricule de chahed : ");
        System.out.println(std.get(shahed));

        System.out.println("Ma liste de doubleur sauf dylan : ");
        List<StudentDto> etudiants = std.getAll();
        for (StudentDto etudiant : etudiants) {
            System.out.println(etudiant);
        }

        std.remove(dylan);
        System.out.println("Ma liste apres avoir supprimer dylan : ");
        List<StudentDto> estudiante = std.getAll();
        for (StudentDto etudiant : estudiante) {
            System.out.println(etudiant);
        }

        StudentDto caca = new StudentDto(54637, "caca", "Bouddha");
        std.add(caca);
        System.out.println("Ma liste apres avoir add : ");
        List<StudentDto> jefe = std.getAll();
        for (StudentDto etudiant : jefe) {
            System.out.println(etudiant);
        }
    }
}
