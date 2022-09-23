/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu.bar.fxml;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class NilaiModel {
    private String NPM, KodeMK;
    private Date Tanggal;
    private double nilai;
    private int hadir;
    private char na;

    public String getNPM() {
        return NPM;
    }

    public void setNPM(String NPM) {
        this.NPM = NPM;
    }

    public String getKodeMK() {
        return KodeMK;
    }

    public void setKodeMK(String KodeMK) {
        this.KodeMK = KodeMK;
    }

    public Date getTanggal() {
        return Tanggal;
    }

    public void setTanggal(Date Tanggal) {
        this.Tanggal = Tanggal;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public int getHadir() {
        return hadir;
    }

    public void setHadir(int hadir) {
        this.hadir = hadir;
    }
    
    public char getNa(){
        if(nilai>=85){
            na='a';
        } else if (nilai>=75){
            na = 'b';
        } else if (nilai>=60){
            na = 'c';
        } else if (nilai>=40){
            na = 'd';
        } else {
            na = 'e';
        }
        
        return (na);
    }
    
}
