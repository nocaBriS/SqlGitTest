/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.sql;

import Model.Konfession;
import Model.geschlecht;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author bambl
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private TextField patidField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField vornameField;
    @FXML
    private TextField gebnameField;
    @FXML
    private TextField TitelField;
    @FXML
    private TextField namenszusatzField;
    @FXML
    private TextField gebdatumField;
    @FXML
    private TextField gebortField;
    @FXML
    private TextField familienField;
    @FXML
    private TextField plzField;
    @FXML
    private TextField strasseField;
    @FXML
    private TextField vorwField;
    @FXML
    private TextField telefonField;
    @FXML
    private TextField ortField;
    @FXML
    private ChoiceBox<geschlecht> geschlechtField;

    Connection db;
    ResultSet set;
    Konfession konf;

    @FXML
    private Button findButton;
    @FXML
    private Button quitButton;
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField konfessionField;
    @FXML
    private TextField landabkz;
    @FXML
    private TextField landfield;
    @FXML
    private Label FehlerLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            db = DriverManager.getConnection("jdbc:derby://localhost:1527/Patient", "Pat", "Pat");
            // TODO

            for (int i = 0; i < geschlecht.values().length; i++) //Geschlecht Combobox
            {
                geschlechtField.getItems().add(geschlecht.values()[i]);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        plzField.textProperty().addListener((observable, oldValue, newValue) -> {

            String findOrt1 = "SELECT * FROM ort WHERE PLZ Between ?*1000 AND ?*1000+999 ORDER BY plz asc";
            String findOrt2 = "SELECT * FROM ort WHERE PLZ Between ?*100 AND ?*1000+99 ORDER BY plz asc";
            String findOrt3 = "SELECT * FROM ort WHERE PLZ Between ?*10 AND ?*10+9 ORDER BY plz asc";
            String findOrt4 = "SELECT * FROM ort WHERE PLZ = ? ORDER BY plz asc";

            if (plzField.getText() != null) {

                if (plzField.getText().length() == 1) {
                    try {
                        PreparedStatement pps = db.prepareStatement(findOrt1);
                        pps.setInt(1, Integer.parseInt(plzField.getText()));
                        pps.setInt(2, Integer.parseInt(plzField.getText()));
                        ResultSet set = pps.executeQuery();

                        set.next();
                        ortField.setText(set.getString(2));
                    } catch (NumberFormatException | SQLException ex) {
                        ortField.setText("Nicht gefunden");
                    }
                } else if (plzField.getText().length() == 2) {
                    try {
                        PreparedStatement pps = db.prepareStatement(findOrt2);
                        pps.setInt(1, Integer.parseInt(plzField.getText()));
                        pps.setInt(2, Integer.parseInt(plzField.getText()));
                        ResultSet set = pps.executeQuery();

                        set.next();
                        ortField.setText(set.getString(2));
                    } catch (NumberFormatException | SQLException ex) {
                        ortField.setText("Nicht gefunden");
                    }
                } else if (plzField.getText().length() == 3) {
                    try {
                        PreparedStatement pps = db.prepareStatement(findOrt3);
                        pps.setInt(1, Integer.parseInt(plzField.getText()));
                        pps.setInt(2, Integer.parseInt(plzField.getText()));
                        ResultSet set = pps.executeQuery();

                        set.next();
                        ortField.setText(set.getString(2));
                    } catch (NumberFormatException | SQLException ex) {
                        ortField.setText("Nicht gefunden");
                    }
                } else if (plzField.getText().length() == 4) {
                    try {
                        PreparedStatement pps = db.prepareStatement(findOrt4);
                        pps.setInt(1, Integer.parseInt(plzField.getText()));
                        ResultSet set = pps.executeQuery();

                        set.next();
                        ortField.setText(set.getString("ort"));
                    } catch (NumberFormatException | SQLException ex) {
                        ortField.setText("Nicht gefunden");
                    }
                } else {
                    ortField.setText("Nicht gefunden");
                }
            }

        });

    }

    @FXML
    private void findPat(ActionEvent event) throws InvocationTargetException, SQLException {

        String getSQL = "SELECT * FROM Patient WHERE patid = ?";
        PreparedStatement pps = db.prepareStatement(getSQL);
        System.out.println(Integer.parseInt(patidField.getText()));
        pps.setInt(1, Integer.parseInt(patidField.getText()));
        set = pps.executeQuery();
        set.next();

        nameField.setText(set.getString("NAME"));
        nameField.setDisable(true);
        vornameField.setText(set.getString("VORNAME"));
        vornameField.setDisable(true);
        gebnameField.setText(set.getString("GEBURTSNAME"));
        TitelField.setText(set.getString("TITEL"));
        namenszusatzField.setText(set.getString("NAMENSZUSATS")); //mi zahts nd nom ändern
        gebdatumField.setText(set.getString("GEBURTSDATUM"));
        gebdatumField.setDisable(true);
        gebortField.setText(set.getString("GEBURTSORT"));
        geschlechtField.setValue(geschlecht.byValue(set.getString("GESCHLECHT")));
        geschlechtField.setDisable(true);
        landabkz.setText(set.getString("STAATSANGEHÖRIGKEIT").substring(0, 2));
        landfield.setText(set.getString("STAATSANGEHÖRIGKEIT").substring(3));

        plzField.setText(set.getString("PLZ"));
        strasseField.setText(set.getString("STRASSE"));
        vorwField.setText(set.getString("NUMMER").substring(0, 4));
        telefonField.setText(set.getString("NUMMER").substring(4, 11));
        konfessionField.setText(set.getString("KONFESSION"));
        familienField.setText(set.getString("FAMILIENSTAND"));
    }

    @FXML
    private void quit(ActionEvent event) throws SQLException {
        patidField.clear();
        nameField.clear();
        nameField.setDisable(false);

        vornameField.clear();
        vornameField.setDisable(false);

        gebnameField.clear();
        TitelField.clear();
        namenszusatzField.clear();
        gebdatumField.clear();
        gebdatumField.setDisable(false);

        geschlechtField.setValue(null);
        geschlechtField.setDisable(false);

        plzField.clear();
        strasseField.clear();
        telefonField.clear();
        vorwField.clear();
        ortField.clear();
        familienField.clear();
        konfessionField.clear();
        landabkz.clear();
        landfield.clear();
        gebortField.clear();
    }

    @FXML
    private void addButton(ActionEvent event) throws SQLException {

        String getSQL = "SELECT Count(PATID) FROM patient WHERE PATID =" + Integer.parseInt(patidField.getText());
        Statement f = db.createStatement();
        ResultSet idSet = f.executeQuery(getSQL);
        idSet.next();

        if (idSet.getInt(1) > 0) {

            //Update
            String update = "UPDATE patient SET patid = ?,name =?, vorname =?, Geburtsname=?,Titel=?,Namenszusats=?,Geburtsdatum=?,Geschlecht=?,Staatsangehörigkeit=?,Plz=?,Strasse=?,Nummer=?,Konfession=?,Familienstand=?,Geburtsort=? WHERE patid=?";

            PreparedStatement pUpdate = db.prepareStatement(update);
            pUpdate.setInt(1, Integer.parseInt(patidField.getText()));
            pUpdate.setString(2, nameField.getText());
            pUpdate.setString(3, vornameField.getText());
            pUpdate.setString(4, gebnameField.getText());
            pUpdate.setString(5, TitelField.getText());
            pUpdate.setString(6, namenszusatzField.getText());
            pUpdate.setString(7, gebdatumField.getText());
            pUpdate.setString(8, geschlechtField.getValue().getLabel());
            pUpdate.setString(9, landabkz.getText() + " " + landfield.getText());
            pUpdate.setInt(10, Integer.parseInt(plzField.getText()));
            pUpdate.setString(11, strasseField.getText());
            pUpdate.setString(12, vorwField.getText() + telefonField.getText());
            pUpdate.setString(13, konfessionField.getText());
            pUpdate.setString(14, familienField.getText());
            pUpdate.setString(15, gebortField.getText());
            pUpdate.setInt(16, Integer.parseInt(patidField.getText()));

            if (pUpdate.executeUpdate() > 0) {
                System.out.println("Update erfolgreich");
            } else {
                System.out.println("Update Fehler");
            }

        } else {
            //new

            String insert = "INSERT INTO patient(patid,name,vorname,Geburtsname,titel,namenszusats,geburtsdatum,geschlecht,staatsangehörigkeit,plz,strasse,nummer,konfession,familienstand,geburtsort) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pInsert = db.prepareStatement(insert);
            pInsert.setInt(1, Integer.parseInt(patidField.getText()));
            pInsert.setString(2, nameField.getText());
            pInsert.setString(3, vornameField.getText());
            pInsert.setString(4, gebnameField.getText());
            pInsert.setString(5, TitelField.getText());
            pInsert.setString(6, namenszusatzField.getText());
            pInsert.setString(7, gebdatumField.getText());
            pInsert.setString(8, geschlechtField.getValue().getLabel());
            pInsert.setString(9, landabkz.getText() + " " + landfield.getText());
            pInsert.setInt(10, Integer.parseInt(plzField.getText()));
            pInsert.setString(11, strasseField.getText());
            pInsert.setString(12, vorwField.getText() + telefonField.getText());
            pInsert.setString(13, konfessionField.getText());
            pInsert.setString(14, familienField.getText());
            pInsert.setString(15, gebortField.getText());
            if (pInsert.executeUpdate() > 0) {
                System.out.println("Insert erfolgreich");
            } else {
                System.out.println("Insert Fehler");
            }
        }

    }

    @FXML
    private void konfClick(MouseEvent event) throws IOException {

        MainWindow stage = (MainWindow) anchor.getScene().getWindow();
        Konf fenster2 = stage.KonfWindow;
        fenster2.show();

    }

    @FXML
    private void landClick(MouseEvent event) {

        MainWindow stage = (MainWindow) anchor.getScene().getWindow();
        Fenster3 fenster3 = stage.Window3;
        fenster3.show();
    }

}
