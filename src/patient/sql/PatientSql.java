/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.sql;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bambl
 */
public class PatientSql extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        MainWindow window1 = new MainWindow();
        Konf window2 = new Konf();
        Fenster3 window3 = new Fenster3();

        window1.KonfWindow = window2;
        window2.main = window1;
        
        window1.Window3 = window3;
        window3.main = window1;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
