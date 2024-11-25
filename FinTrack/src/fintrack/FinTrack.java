/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fintrack;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Syauqi
 */
public class FinTrack {
    private String tanggal;
    private int jumlah;
    private boolean jenis;
    private String keterangan;
    
    public FinTrack(String tanggal, int jumlah, boolean jenis, String keterangan){
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.jenis = jenis;
        this.keterangan = keterangan;
    }
    
    public String getTanggal(){
        return tanggal;
    }
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
    public int getJumlah(){
        return jumlah;
    }
    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }
    public boolean isJenis(){
        return jenis;
    }
    public void setJenis(boolean jenis){
        this.jenis = jenis;
    }
    public String getKeterangan(){
        return keterangan;
    }
    public void setKeterangan(String keterangan){
        this.keterangan = keterangan;
    }
    
    public void saveToDatabase() {
        String sql = "INSERT INTO fintrack (tanggal, jumlah, jenis, keterangan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, this.tanggal);
            pstmt.setInt(2, this.jumlah);
            pstmt.setBoolean(3, this.jenis);
            pstmt.setString(4, this.keterangan);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
