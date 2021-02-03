package TD2_Repository_Pattern;

import java.io.File;
import java.io.IOException;

public class Mentoring {

    public Mentoring() {
    }

    public static void main(String[] args) {
        Mentoring mentoring = new Mentoring();
        mentoring.checkPath();

        try {
            ConfigManager.getInstance().load();
        } catch (IOException e) {
            System.out.println("Chargement de la configuration impossible " + e.getMessage());
        }

        String author = ConfigManager.getInstance().getProperties("app.author");
        System.out.println("Auteur : " + author);
    }

    public void checkPath() {
        System.out.println("Chemin courant \t" + new File(".").getAbsolutePath()); // Donne le chemin absolu du fichier que j'ai appelé ".".
        System.out.println("Chemin classe \t"
                + this.getClass().getResource(".").getPath()); //le répertoire de la classe en cours d’exécution ;
        System.out.println("Chemin jar \t" + new File(getClass().getClassLoader().getResource(".").getFile())); // Donne le chemin ou se trouve le jar ou ou il devrait se trouver.
    }
}
