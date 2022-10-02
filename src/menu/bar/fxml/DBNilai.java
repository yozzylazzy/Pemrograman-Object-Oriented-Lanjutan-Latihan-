/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu.bar.fxml;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class DBNilai {

    private NilaiModel data = new NilaiModel();

    public NilaiModel getNilaiModel() {
        return (data);
    }

    public void setNilaiModel(NilaiModel s) {
        data = s;
    }

    public ObservableList<NilaiModel> Load() {
        try {
            ObservableList<NilaiModel> TableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = 
                    con.statement.executeQuery("Select n.NPM, Nama as namasiswa, n.KodeMK, tanggal, nilai, hadir, namaMK, sks, praktek "
                            + " from nilai n inner join siswa s on n.npm=s.npm" +
                            " inner join matakuliah m on n.KodeMK = m.KodeMK");

            int i = 1;
            while (rs.next()) {
                NilaiModel d = new NilaiModel();
                d.setNPM(rs.getString("NPM"));
                d.setKodeMK(rs.getString("KodeMK"));
                d.setTanggal(rs.getDate("Tanggal"));
                d.setNilai(rs.getDouble("Nilai"));
                d.setHadir(rs.getInt("Hadir"));
                d.setNamasiswa(rs.getString("namasiswa"));
                d.setNamamk(rs.getString("namaMK"));
                d.setSks(rs.getInt("sks"));
                d.setPraktek(rs.getInt("praktek"));
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
    
     public int validasi(String a, String b) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select Count(*) as jml from nilai where NPM = '" + a + "' AND KodeMK = '" + b + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean Delete(String a, String b) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            //System.out.println(a);
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "delete from siswa where NPM = ? AND KodeMK = ?");
            con.preparedStatement.setString(1, a);
            con.preparedStatement.setString(2, b);
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into nilai(NPM,KodeMK,Tanggal, Nilai, Hadir) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getNilaiModel().getNPM());
            con.preparedStatement.setString(2, getNilaiModel().getKodeMK());
            con.preparedStatement.setDate(3, getNilaiModel().getTanggal());
            con.preparedStatement.setDouble(4, getNilaiModel().getNilai());
            con.preparedStatement.setInt(5, getNilaiModel().getHadir());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update nilai set Tanggal = ?, Nilai = ?, Hadir = ? where NPM = ? AND kodeMK = ?");
            con.preparedStatement.setDate(1, getNilaiModel().getTanggal());
            con.preparedStatement.setDouble(2, getNilaiModel().getNilai());
            con.preparedStatement.setInt(3, getNilaiModel().getHadir());
            con.preparedStatement.setString(4, getNilaiModel().getNPM());
            con.preparedStatement.setString(5, getNilaiModel().getKodeMK());;
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
