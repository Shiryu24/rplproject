/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fintrack;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Syauqi
 */
public class Controller {
     public void InsertData(String tanggal, int jumlah, boolean jenis, String keterangan) {
        String sql = "INSERT INTO fintrack (tanggal, jumlah, jenis, keterangan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tanggal);
            pstmt.setInt(2, jumlah);
            pstmt.setBoolean(3, jenis);
            pstmt.setString(4, keterangan);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load data from the database
    public DefaultTableModel showData() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Tanggal", "Jumlah Uang", "Jenis Pencatatan", "Keterangan"}, 0);
        String sql = "SELECT * FROM fintrack";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                String tanggal = rs.getString("tanggal");
                int jumlah = rs.getInt("jumlah");
                boolean jenis = rs.getBoolean("jenis");
                String keterangan = rs.getString("keterangan");
                model.addRow(new Object[]{tanggal, jumlah, jenis ? "Pemasukkan" : "Pengeluaran", keterangan});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
}
