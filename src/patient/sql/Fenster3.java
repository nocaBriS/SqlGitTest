/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.sql;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bambl
 */
public class Fenster3  extends Stage{
    
        MainWindow main;
    
    public Fenster3() throws IOException {
        super();
        Parent root = FXMLLoader.load(getClass()
                .getResource("Fenster3FXML.fxml"));
        Scene scene = new Scene(root);
        this.setScene(scene);
    }
    
}
