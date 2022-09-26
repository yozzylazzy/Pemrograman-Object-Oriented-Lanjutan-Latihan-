/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package menu.bar.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yosef Adrian
 */
public class FXML_PilihSiswaController implements Initializable {

    @FXML
    private TextField txtisi;
    @FXML
    private Button btncari;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btnbatal;
    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button akhir;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnhapussiswa;
    @FXML
    private Button btneditsiswa;
    @FXML
    private Button btntambahsiswa;
    @FXML
    private TableView<SiswaModel> tbsiswa;
    
    private int hasil = 0;
    private String npmhasil="";
    
    public int getHasil(){
        return(hasil);
    }
    
    public String getNpmHasil(){
        return(npmhasil);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbfield.setItems(FXCollections.observableArrayList(
        "NPM","Nama","Alamat"));
        cmbfield.getSelectionModel().select(0);
        showData("npm","");
    }

    public void showData(String a, String b) {
        ObservableList<SiswaModel> data = FXMLDocumentController.dtsiswa.LookUp(a, b);
        if(data.isEmpty()){
            data = FXMLDocumentController.dtsiswa.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbsiswa.getColumns().clear();
            tbsiswa.getItems().clear();
            TableColumn col = new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel, String>("NPM"));
            tbsiswa.getColumns().addAll(col);

            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel, String>("Nama"));
            tbsiswa.getColumns().addAll(col);
            col = new TableColumn("Alamat");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel, String>("Alamat"));
            tbsiswa.getColumns().addAll(col);
            tbsiswa.setItems(data);
        } else {
            Alert x = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            x.showAndWait();
            tbsiswa.getScene().getWindow().hide();;
        }
        btnawalklik(null);
        txtisi.requestFocus();
    }

    @FXML
    private void cariklik(ActionEvent event){
        showData(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbsiswa.getSelectionModel().selectFirst();
        tbsiswa.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbsiswa.getSelectionModel().selectLast();
        tbsiswa.requestFocus();
    }

    @FXML
    private void btnsebelumklik(ActionEvent event) {
        tbsiswa.getSelectionModel().selectAboveCell();
        tbsiswa.requestFocus();
    }

    @FXML
    private void btnsesudahklik(ActionEvent event) {
        tbsiswa.getSelectionModel().selectBelowCell();
        tbsiswa.requestFocus();
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbsiswa.getSelectionModel().getSelectedCells().get(0).getRow();
        npmhasil = tbsiswa.getItems().get(pilihan).getNPM();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void btnhapusklik(ActionEvent event) {
    }

    @FXML
    private void btneditklik(ActionEvent event) {
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
    }

}
