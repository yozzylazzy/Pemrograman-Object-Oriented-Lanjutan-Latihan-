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
public class FXML_PilihMatkulController implements Initializable {

    @FXML
    private Button btnexit;
    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btncari;
    @FXML
    private TextField txtisi;
    @FXML
    private TableView<MataKuliahModel> tbmatakuliah;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button akhir;
    @FXML
    private Button btnawal;

    private int hasil = 0;
    private String kodemkhasil = "";

    public int getHasil() {
        return (hasil);
    }

    public String getKodemkHasil() {
        return (kodemkhasil);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbfield.setItems(FXCollections.observableArrayList(
                "KodeMK", "NamaMK", "Praktek", "SKS"));
        cmbfield.getSelectionModel().select(0);
        showData("kodemk", "");
    }

    public void showData(String a, String b) {
        ObservableList<MataKuliahModel> data = FXMLDocumentController.datamatkul.LookUp(a, b);
        if (data.isEmpty()) {
            data = FXMLDocumentController.datamatkul.Load();
            txtisi.setText("");
        }
        if (data != null) {
            tbmatakuliah.getColumns().clear();
            tbmatakuliah.getItems().clear();
            TableColumn col = new TableColumn("KodeMK");
            col.setCellValueFactory(new PropertyValueFactory<MataKuliahModel, String>("KodeMK"));
            tbmatakuliah.getColumns().addAll(col);

            col = new TableColumn("NamaMK");
            col.setCellValueFactory(new PropertyValueFactory<MataKuliahModel, String>("NamaMK"));
            tbmatakuliah.getColumns().addAll(col);
            col = new TableColumn("SKS");
            col.setCellValueFactory(new PropertyValueFactory<MataKuliahModel, Integer>("SKS"));
            tbmatakuliah.getColumns().addAll(col);
            col = new TableColumn("Praktek");
            col.setCellValueFactory(new PropertyValueFactory<MataKuliahModel, Integer>("Praktek"));
            tbmatakuliah.getColumns().addAll(col);
            tbmatakuliah.setItems(data);
            btnawalklik(null);
            txtisi.requestFocus();
        } else {
            Alert x = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            x.showAndWait();
            tbmatakuliah.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbmatakuliah.getSelectionModel().selectFirst();
        tbmatakuliah.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbmatakuliah.getSelectionModel().selectLast();
        tbmatakuliah.requestFocus();
    }

    @FXML
    private void btnsebelumklik(ActionEvent event) {
        tbmatakuliah.getSelectionModel().selectAboveCell();
        tbmatakuliah.requestFocus();
    }

    @FXML
    private void btnsesudahklik(ActionEvent event) {
        tbmatakuliah.getSelectionModel().selectBelowCell();
        tbmatakuliah.requestFocus();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbmatakuliah.getSelectionModel().getSelectedCells().get(0).getRow();
        kodemkhasil = tbmatakuliah.getItems().get(pilihan).getKodeMK();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void cariklik(ActionEvent event){
        showData(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }
}
