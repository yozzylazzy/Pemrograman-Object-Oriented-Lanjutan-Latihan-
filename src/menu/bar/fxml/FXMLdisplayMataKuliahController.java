/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package menu.bar.fxml;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLdisplayMataKuliahController implements Initializable {

    @FXML
    private TableView<MataKuliahModel> tbmatakuliah;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showData();
    }    
    
    public void showData(){
         ObservableList<MataKuliahModel> data = FXMLDocumentController.datamatkul.Load();
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
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
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
    private void btntambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMatkul.fxml"));
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
        MataKuliahModel s = new MataKuliahModel();
        s = tbmatakuliah.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMatkul.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputMatkulController isidt = loader.getController();
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
        MataKuliahModel s = new MataKuliahModel();
        s = tbmatakuliah.getSelectionModel().getSelectedItem();
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
