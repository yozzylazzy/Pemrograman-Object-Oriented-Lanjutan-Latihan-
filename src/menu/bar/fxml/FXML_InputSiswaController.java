/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package menu.bar.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_InputSiswaController implements Initializable {

    @FXML
    private TextField txtinput;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnexit;

    boolean editdata = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnsimpanklik(ActionEvent event) {
        SiswaModel s = new SiswaModel();
        s.setNPM(txtinput.getText());
        s.setNama(txtnama.getText());
        s.setAlamat(txtalamat.getText());
        FXMLDocumentController.dtsiswa.setSiswaModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtsiswa.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtsiswa.validasi(s.getNPM()) <= 0) {
            if (FXMLDocumentController.dtsiswa.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnresetklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtinput.requestFocus();
        }
    }

    @FXML
    private void btnresetklik(ActionEvent event
    ) {
        txtinput.setText("");
        txtnama.setText("");
        txtalamat.setText("");

    }

    @FXML
    private void btnexitklik(ActionEvent event
    ) {
        btnexit.getScene().getWindow().hide();
    }

    public void execute(SiswaModel s) {
        if (!s.getNPM().isEmpty()) {
            editdata = true;
            txtnama.setText(s.getNama());
            txtinput.setText(s.getNPM());
            txtalamat.setText(s.getAlamat());
            txtinput.setEditable(false);
            btnreset.setDisable(true);
            txtnama.requestFocus();
        }
    }

}
