/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package menu.bar.fxml;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_InputNilaiController implements Initializable {

    @FXML
    private TextField txtnilai;
    @FXML
    private TextField txtnpm;
    @FXML
    private TextField txtkodemk;
    private TextField txttanggal;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnreset;
    @FXML
    private Button btnexit;
    private TextField txthadir;
    @FXML
    private DatePicker datepick;

    boolean editdata = false;
    @FXML
    private Slider sldhadir;
    @FXML
    private Text txtsld;
    @FXML
    private Button btnpilihsiswa;
    @FXML
    private Button btnpilihmatkul;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sldhadir.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> changed, Number oldVal, Number newVal) {
                txtsld.setText(String.valueOf(newVal.intValue()));
            }
        });
    }

    @FXML
    private void btnsimpanklik(ActionEvent event) {
        NilaiModel s = new NilaiModel();
        s.setNPM(txtnpm.getText());
        s.setKodeMK(txtkodemk.getText());
        s.setTanggal(Date.valueOf(datepick.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        s.setNilai(Double.parseDouble(txtnilai.getText()));
        s.setHadir((int) sldhadir.getValue());
        FXMLDocumentController.datanilai.setNilaiModel(s);
        if (editdata) {
            if (FXMLDocumentController.datanilai.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.datanilai.validasi(s.getNPM(), s.getKodeMK()) <= 0) {
            if (FXMLDocumentController.datanilai.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                btnexitklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void btnresetklik(ActionEvent event) {
        txtnpm.setText("");
        txtkodemk.setText("");
        datepick.setValue(null);
        txtnilai.setText("");
        txthadir.setText("");
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    public void execute(NilaiModel s) {
        if (!s.getNPM().isEmpty() && !s.getKodeMK().isEmpty()) {
            editdata = true;
            txtnpm.setText(s.getNPM());
            Date a = (Date) s.getTanggal();
            a.toLocalDate();
            txtkodemk.setText(s.getKodeMK());
            datepick.setValue(s.getTanggal().toLocalDate());
            sldhadir.setValue(s.getHadir());
            txtnilai.setText(String.valueOf(s.getNilai()));
            txtnpm.setDisable(true);
            txtkodemk.setDisable(true);
            datepick.requestFocus();
            btnpilihmatkul.setDisable(true);
            btnpilihsiswa.setDisable(true);
        }
    }

    @FXML
    private void pilihsiswaklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihSiswa.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PilihSiswaController isidt = (FXML_PilihSiswaController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtnpm.setText(isidt.getNpmHasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void pilihmatkulklik(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihMatkul.fxml"));
            Parent root = (Parent) loader.load();
            FXML_PilihMatkulController isidt = (FXML_PilihMatkulController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtkodemk.setText(isidt.getKodemkHasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
