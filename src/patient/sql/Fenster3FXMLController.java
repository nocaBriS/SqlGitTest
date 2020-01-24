/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.sql;

import Model.Konfession;
import Model.land;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author bambl
 */
public class Fenster3FXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField abkz;
    @FXML
    private TextField land;
    @FXML
    private TableView<land> tableView;
    @FXML
    private TableColumn<String, String> abkzRow;
    @FXML
    private TableColumn<String, String> landRow;

    Connection db;
    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private Button button2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            db = DriverManager.getConnection("jdbc:derby://localhost:1527/Patient", "Pat", "Pat");

            // TODO
            ObservableList<land> list = FXCollections.observableArrayList();

            String getSQL = "Select * FROM land";

            Statement f = db.createStatement();
            ResultSet set = f.executeQuery(getSQL);
            while (set.next()) {
                list.add(new land(set.getString(1), set.getString(2)));

            }
            tableView.setItems(list);
            abkzRow.setCellValueFactory(
                    new PropertyValueFactory<String, String>("abkz"));
            landRow.setCellValueFactory(
                    new PropertyValueFactory<String, String>("land"));
        } catch (SQLException ex) {
            Logger.getLogger(Fenster3FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void button(ActionEvent event) {

        Fenster3 stage = (Fenster3) anchor.getScene().getWindow();
        MainWindow fenster2 = stage.main;
        TextField l = (TextField) fenster2.getScene().lookup("#landabkz");
        l.setText(tableView.getSelectionModel().getSelectedItem().getAbkz());

        TextField t = (TextField) fenster2.getScene().lookup("#landfield");
        t.setText(tableView.getSelectionModel().getSelectedItem().getLand());
        stage.hide();

    }

    @FXML
    private void addBtn(ActionEvent event) throws SQLException {
        String add = "INSERT INTO land(abkz,land) Values(?,?)";
        PreparedStatement preps = db.prepareStatement(add);
        preps.setString(1, abkz.getText());
        preps.setString(2, land.getText());
        preps.executeUpdate();
        tableView.getItems().add(new land(abkz.getText(), land.getText()));
    }

    @FXML
    private void delBtn(ActionEvent event) throws SQLException {
        String del = "DELETE FROM land WHERE abkz LIKE ?";
        PreparedStatement preps = db.prepareStatement(del);
        preps.setString(1, tableView.getSelectionModel().getSelectedItem().getAbkz());
        preps.executeUpdate();
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
    }

}
