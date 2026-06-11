package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;
import model.ModelKamar;

public class ControllerKamar {
    
    // 1. FUNGSI UNTUK MENAMPILKAN DATA KE JTABLE (READ)
    public void tampilData(DefaultTableModel modelTabel) {
        // Kosongkan tabel dulu sebelum diisi data baru
        modelTabel.setRowCount(0);
        
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "SELECT * FROM kamar";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()) {
                Object[] data = {
                    rs.getInt("id_kamar"),
                    rs.getString("nomor_kamar"),
                    rs.getDouble("harga_kost"),
                    rs.getInt("kapasitas"),
                    rs.getString("status")
                };
                modelTabel.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data kamar: " + e.getMessage());
        }
    }
    
    // 2. FUNGSI UNTUK SIMPAN DATA KAMAR (CREATE)
    public void simpanKamar(ModelKamar mk) {
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "INSERT INTO kamar (nomor_kamar, harga_kost, kapasitas, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, mk.getNomorKamar());
            ps.setDouble(2, mk.getHargaKost());
            ps.setInt(3, mk.getKapasitas());
            ps.setString(4, mk.getStatus());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Kamar Berhasil Disimpan!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data: " + e.getMessage());
        }
    }
    
public void ubahKamar(ModelKamar mk) {
    try {
        Connection con = Koneksi.getKoneksi();
        String query = "UPDATE kamar SET harga_kost=?, kapasitas=?, status=? WHERE nomor_kamar=?";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setDouble(1, mk.getHargaKost());
        ps.setInt(2, mk.getKapasitas());
        ps.setString(3, mk.getStatus());
        ps.setString(4, mk.getNomorKamar()); // Mengubah berdasarkan nomor kamar
        
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Kamar Berhasil Diubah!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal Mengubah Data: " + e.getMessage());
    }
}

public void hapusKamar(String nomorKamar) {
    try {
        Connection con = Koneksi.getKoneksi();
        String query = "DELETE FROM kamar WHERE nomor_kamar=?";
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setString(1, nomorKamar);
        
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Kamar Berhasil Dihapus!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal Menghapus Data: " + e.getMessage());
    }
}
}