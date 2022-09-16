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
public class DBMataKuliah {

    private MataKuliahModel data = new MataKuliahModel();

    public MataKuliahModel getMataKuliahModel() {
        return (data);
    }

    public void setMataKuliahModel(MataKuliahModel s) {
        data = s;
    }

    public ObservableList<MataKuliahModel> Load() {
        try {
            ObservableList<MataKuliahModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select KodeMK, NamaMK, SKS, Praktek from matakuliah");

            int i = 1;
            while (rs.next()) {
                MataKuliahModel d = new MataKuliahModel();
                d.setKodeMK(rs.getString("KodeMK"));
                d.setNamaMK(rs.getString("NamaMK"));
                d.setSKS(rs.getInt("SKS"));
                d.setPraktek(rs.getInt("Praktek"));
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
            ResultSet rs = con.statement.executeQuery("Select Count(*) as jml from matakuliah where KodeMK = '" + nomor + "'");
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
                    "delete from matakuliah where KodeMK = ?");
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

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into matakuliah(KodeMK,NamaMK,SKS,Praktek) values (?,?,?,?)");
            con.preparedStatement.setString(1, getMataKuliahModel().getKodeMK());
            con.preparedStatement.setString(2, getMataKuliahModel().getNamaMK());
            con.preparedStatement.setInt(3, getMataKuliahModel().getSKS());
            con.preparedStatement.setInt(4, getMataKuliahModel().getPraktek());
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

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update matakuliah set NamaMK = ?,SKS = ?,Praktek = ? where kodeMK = ?;");
            con.preparedStatement.setString(1, getMataKuliahModel().getNamaMK());
            con.preparedStatement.setInt(2, getMataKuliahModel().getSKS());
            con.preparedStatement.setInt(3, getMataKuliahModel().getPraktek());
            con.preparedStatement.setString(4, getMataKuliahModel().getKodeMK());
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
