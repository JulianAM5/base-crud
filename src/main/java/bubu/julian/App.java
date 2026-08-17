package bubu.julian;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Hello world!
 */
public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new BorderPane(), 1280, 720));
        stage.show();
	}
}
