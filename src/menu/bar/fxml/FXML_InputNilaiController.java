/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package menu.bar.fxml;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sldhadir.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number>changed, Number oldVal, Number newVal){
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
        }
    }

}
