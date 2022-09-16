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
public class FXML_InputMatkulController implements Initializable {

    @FXML
    private Button btnexit;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtsks;
    @FXML
    private TextField txtnamamk;
    @FXML
    private TextField txtkodemk;
    @FXML
    private TextField txtpraktek;

    boolean editdata = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void btnresetklik(ActionEvent event) {
        txtkodemk.setText("");
        txtnamamk.setText("");
        txtsks.setText("");
        txtpraktek.setText("");
    }

    @FXML
    private void btnsimpanklik(ActionEvent event) {
        MataKuliahModel s = new MataKuliahModel();
        s.setKodeMK(txtkodemk.getText());
        s.setNamaMK(txtnamamk.getText());
        s.setSKS(Integer.parseInt(txtsks.getText()));
        s.setPraktek(Integer.parseInt(txtpraktek.getText()));
        FXMLDocumentController.datamatkul.setMataKuliahModel(s);
        if (editdata) {
            if (FXMLDocumentController.datamatkul.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtsiswa.validasi(s.getKodeMK()) <= 0) {
            if (FXMLDocumentController.datamatkul.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnresetklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtkodemk.requestFocus();
        }
    }

    public void execute(MataKuliahModel s) {
        if (!s.getKodeMK().isEmpty()) {
            editdata = true;
            txtnamamk.setText(s.getNamaMK());
            txtpraktek.setText(String.valueOf(s.getPraktek()));
            txtsks.setText(String.valueOf(s.getSKS()));
            txtkodemk.setText(s.getKodeMK());
            txtkodemk.setEditable(false);
            btnreset.setDisable(true);
            txtnamamk.requestFocus();
        }
    }

}
