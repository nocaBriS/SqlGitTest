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
public class Konf extends Stage{
    
    MainWindow main;
    
    public Konf() throws IOException {
        super();
        Parent root = FXMLLoader.load(getClass()
                .getResource("KonfFXML.fxml"));
        Scene scene = new Scene(root);
        this.setScene(scene);
    }
    
}
