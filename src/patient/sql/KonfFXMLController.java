/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.sql;

import Model.Konfession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bambl
 */
public class KonfFXMLController implements Initializable {

    @FXML
    private TableColumn<String, String> abkzRow;
    @FXML
    private TableColumn<String, String> konfRow;
    @FXML
    private Button konfButton;

    Connection db;

    @FXML
    private TableView<Konfession> tableView;
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField abkzField;
    @FXML
    private TextField konfField;
    @FXML
    private Button konfButton1;
    @FXML
    private Button konfButton11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            db = DriverManager.getConnection("jdbc:derby://localhost:1527/Patient", "Pat", "Pat");

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(KonfFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Konfession> list = FXCollections.observableArrayList();

        String getSQL = "Select * FROM konfession";
        try {
            Statement f = db.createStatement();
            ResultSet set = f.executeQuery(getSQL);
            while (set.next()) {
                System.out.println(set.getString(1) + " - " + set.getString(2));
                list.add(new Konfession(set.getString(1), set.getString(2)));

            }
            tableView.setItems(list);
            abkzRow.setCellValueFactory(
                    new PropertyValueFactory<String, String>("akbz"));
            konfRow.setCellValueFactory(
                    new PropertyValueFactory<String, String>("Konfession"));
            tableView.getSelectionModel().select(0);

        } catch (SQLException ex) {
            Logger.getLogger(KonfFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Button(ActionEvent event) throws IOException {

        Konf stage = (Konf) anchor.getScene().getWindow();
        MainWindow fenster2 = stage.main;
        TextField l = (TextField) fenster2.getScene().lookup("#konfessionField");
        l.setText(tableView.getSelectionModel().getSelectedItem().getAkbz() + " " + tableView.getSelectionModel().getSelectedItem().getKonfession());
        stage.hide();

    }

    @FXML
    private void addButton(ActionEvent event) throws SQLException {

        String add = "INSERT INTO konfession(abkz,konfession) Values(?,?)";
        PreparedStatement preps = db.prepareStatement(add);
        preps.setString(1, abkzField.getText());
        preps.setString(2, konfField.getText());
        preps.executeUpdate();
        tableView.getItems().add(new Konfession(abkzField.getText(), konfField.getText()));
    }

    @FXML
    private void DelButton(ActionEvent event) throws SQLException {

        String del = "DELETE FROM konfession WHERE abkz LIKE ?";
        PreparedStatement preps = db.prepareStatement(del);
        preps.setString(1, tableView.getSelectionModel().getSelectedItem().getAkbz());
        preps.executeUpdate();
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
    }

}
