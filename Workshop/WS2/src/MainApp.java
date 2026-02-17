import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.WindowConfig;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Vehicle Fleet Management");
        primaryStage.setScene(scene);

        primaryStage.setHeight(WindowConfig.HEIGHT);
        primaryStage.setMinWidth(WindowConfig.WIDTH);
        primaryStage.setMinHeight(WindowConfig.HEIGHT);
        primaryStage.setMaxWidth(WindowConfig.WIDTH);
        primaryStage.setMaxHeight(WindowConfig.HEIGHT);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
