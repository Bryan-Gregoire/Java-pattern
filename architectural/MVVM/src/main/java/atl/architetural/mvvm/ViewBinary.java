package atl.architetural.mvvm;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class ViewBinary {

    private Text text;

    public ViewBinary(Stage primaryStage) {
        System.out.println("DEBUG | VIEW2      | Construction");

        text = new Text();

        HBox box = new HBox(20, text);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        Stage secondStage = new Stage();
        secondStage.setMinHeight(100);
        secondStage.setMinWidth(100);
        double centerXPosition = primaryStage.getX() + primaryStage.getWidth();
        double centerYPosition = primaryStage.getY() + primaryStage.getHeight() / 2d;
        secondStage.setX(centerXPosition);
        secondStage.setY(centerYPosition);
        secondStage.setScene(scene);
        secondStage.show();
    }

    public void bind(ViewModel viewModel) {
        System.out.println("DEBUG | VIEWBINARY     | Ajoute le binding entre la vue binaire et le viewmodel");
        text.textProperty().bind(viewModel.getValueBinary());
    }

}
