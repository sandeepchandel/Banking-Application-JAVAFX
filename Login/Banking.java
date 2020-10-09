
package Login;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Banking extends Application {
    public static Stage stage=null;

    private double xOffset=0;
    private double yOffset=0;
    @Override
    public void start(Stage stage) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Design/design.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX()-xOffset);
            stage.setY(event.getScreenY()-yOffset);
        });
       this.stage=stage;
        stage.show();

}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
