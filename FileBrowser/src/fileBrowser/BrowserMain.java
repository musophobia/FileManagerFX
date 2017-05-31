
package fileBrowser;

import javafx.application.Application;
import javafx.stage.Stage;

public class BrowserMain extends Application {
    Stage window;
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        SingleTonP singl=new SingleTonP();
        window.setTitle("File Explorer");
        window.setScene(singl.scene);
        window.show();
      
    }
}
