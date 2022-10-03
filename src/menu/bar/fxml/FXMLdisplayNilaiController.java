/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package menu.bar.fxml;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Observable;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLdisplayNilaiController implements Initializable {

    @FXML
    private TableView<NilaiModel> tbnilai;
    @FXML
    private Button btntambahsiswa;
    @FXML
    private Button btneditsiswa;
    @FXML
    private Button btnhapussiswa;
    @FXML
    private Button btnawal;
    @FXML
    private Button akhir;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnexit;
    @FXML
    private TextField txttotal;
    @FXML
    private TextField txtbobot;
    @FXML
    private TextField txtip;
    @FXML
    private TextField txtavg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData();
    }

    public void showData() {
        ObservableList<NilaiModel> data = FXMLDocumentController.datanilai.Load();
        if (data != null) {
            tbnilai.getColumns().clear();
            tbnilai.getItems().clear();

            TableColumn col = new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("NPM"));
            tbnilai.getColumns().addAll(col);

            col = new TableColumn("NamaSiswa");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("namasiswa"));
            tbnilai.getColumns().addAll(col);

            col = new TableColumn("NamaMK");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("namamk"));
            tbnilai.getColumns().addAll(col);
            tbnilai.setItems(data);

            col = new TableColumn("SKS");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("sks"));
            tbnilai.getColumns().addAll(col);
            tbnilai.setItems(data);

            col = new TableColumn("Praktek");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("praktek"));
            tbnilai.getColumns().addAll(col);
            tbnilai.setItems(data);

            col = new TableColumn("KodeMK");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("KodeMK"));
            tbnilai.getColumns().addAll(col);

            col = new TableColumn("Tanggal");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, Date>("Tanggal"));
            tbnilai.getColumns().addAll(col);

            col = new TableColumn("Nilai");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, Double>("Nilai"));
            tbnilai.getColumns().addAll(col);

            col = new TableColumn("Hadir");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, Integer>("Hadir"));
            tbnilai.getColumns().addAll(col);

            col = new TableColumn("NA");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("Na"));
            tbnilai.getColumns().addAll(col);
            tbnilai.setItems(data);

            int total = 0;
            int bobot = 0, sks=0;
            double nilaitotal = 0;
            for (int i = 0; i < tbnilai.getItems().size(); i++) {
                NilaiModel n = tbnilai.getItems().get(i);
                total += n.getHadir();
                if (n.getNa() == 'a') {
                    bobot += (4*n.getSks());
                } else if (n.getNa() == 'b') {
                    bobot += (3*n.getSks());
                } else if (n.getNa() == 'c') {
                    bobot += (2*n.getSks());
                } else if (n.getNa() == 'd') {
                    bobot += (1*n.getSks());
                } else {
                    bobot += 0;
                }
                sks += n.getSks();
                nilaitotal += n.getNilai();
            }
            double ip = bobot/sks;
            txtbobot.setText(String.valueOf(bobot));
            txtip.setText(String.valueOf(ip));
            txtavg.setText(String.valueOf(nilaitotal/tbnilai.getItems().size()));
            txttotal.setText(String.valueOf(total));
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Kosong", ButtonType.OK);
            a.showAndWait();
            tbnilai.getScene().getWindow().hide();
        }
    }

    @FXML
    private void btnexitklik(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void btnawalklik(ActionEvent event) {
        tbnilai.getSelectionModel().selectFirst();
        tbnilai.requestFocus();
    }

    @FXML
    private void btnakhirklik(ActionEvent event) {
        tbnilai.getSelectionModel().selectLast();
        tbnilai.requestFocus();
    }

    @FXML
    private void btnsebelumklik(ActionEvent event) {
        tbnilai.getSelectionModel().selectAboveCell();
        tbnilai.requestFocus();
    }

    @FXML
    private void btnsesudahklik(ActionEvent event) {
        tbnilai.getSelectionModel().selectBelowCell();
        tbnilai.requestFocus();
    }

    @FXML
    private void btntambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            showData();
            btnawalklik(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        btnawalklik(event);
    }

    @FXML
    private void btneditklik(ActionEvent event) {
        NilaiModel s = new NilaiModel();
        s = tbnilai.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputNilaiController isidt = loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        btnawalklik(event);
    }

    @FXML
    private void btnhapusklik(ActionEvent event) {
        NilaiModel s = new NilaiModel();
        s = tbnilai.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Ingin Dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            // System.out.print(s);
            if (FXMLDocumentController.datamatkul.Delete(s.getKodeMK())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showData();
        }
    }
}
