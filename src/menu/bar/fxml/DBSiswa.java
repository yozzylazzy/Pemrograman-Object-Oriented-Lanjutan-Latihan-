/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu.bar.fxml;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class DBSiswa {

    private SiswaModel data = new SiswaModel();

    public ObservableList<SiswaModel> LookUp(String fld, String dt){
        try{
            ObservableList<SiswaModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select NPM, Nama, Alamat from siswa where " + fld + " like '%"+dt+"%'");
            int i = 1;
            while(rs.next()){
                SiswaModel d = new SiswaModel();
                d.setNPM(rs.getString("NPM"));
                d.setNama(rs.getString("Nama"));
                d.setAlamat(rs.getString("Alamat"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public SiswaModel getSiswaModel() {
        return (data);
    }

    public void setSiswaModel(SiswaModel s) {
        data = s;
    }

    public ObservableList<SiswaModel> Load() {
        try {
            ObservableList<SiswaModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select NPM, NAMA, ALAMAT from siswa");

            int i = 1;
            while (rs.next()) {
                SiswaModel d = new SiswaModel();
                d.setNPM(rs.getString("NPM"));
                d.setNama(rs.getString("NAMA"));
                d.setAlamat(rs.getString("ALAMAT"));

                System.out.println(rs.getString("NPM") + rs.getString("NAMA") + rs.getString("ALAMAT"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select Count(*) as jml from siswa where NPM = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean Delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            System.out.println(nomor);
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from siswa where NPM = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public boolean insert(){
        boolean berhasil = false; Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into siswa(NPM,Nama,Alamat) values (?,?,?)");
            con.preparedStatement.setString(1, getSiswaModel().getNPM());
            con.preparedStatement.setString(2, getSiswaModel().getNama());
            con.preparedStatement.setString(3, getSiswaModel().getAlamat());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        }catch(Exception e){
            e.printStackTrace();
            berhasil = false;
        } finally{
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = (PreparedStatement) con.dbKoneksi.prepareStatement(
                    "update siswa set Nama = ?, Alamat = ? where NPM = ?;");
            con.preparedStatement.setString(1, getSiswaModel().getNama());
            con.preparedStatement.setString(2, getSiswaModel().getAlamat());
            con.preparedStatement.setString(3, getSiswaModel().getNPM());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
}
