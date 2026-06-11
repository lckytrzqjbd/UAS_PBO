package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;
import model.ModelPembayaran;

public class ControllerPembayaran {
    
    public void isiComboPenghuni(JComboBox cbPenghuni) {
        cbPenghuni.removeAllItems();
        cbPenghuni.addItem("-- Pilih Penghuni --");
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "SELECT nama FROM penghuni";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                cbPenghuni.addItem(rs.getString("nama"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat penghuni: " + e.getMessage());
        }
    }
    
    // SEKARANG CUMA PANGGIL HARGA UTK TXTTOTAL
    public void panggilHargaKost(String namaPenghuni, JTextField txtTotal) {
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "SELECT k.harga_kost FROM penghuni p " +
                           "JOIN kamar k ON p.kamar = k.nomor_kamar WHERE p.nama = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, namaPenghuni);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                txtTotal.setText(rs.getString("harga_kost"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat harga: " + e.getMessage());
        }
    }
    
    public void tampilData(DefaultTableModel modelTabel) {
        modelTabel.setRowCount(0);
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "SELECT * FROM pembayaran";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Object[] data = {
                    rs.getInt("id_pembayaran"),
                    rs.getString("nama_penghuni"),
                    rs.getDouble("total_bayar"),
                    rs.getString("tgl_bayar"),
                    rs.getString("status_bayar")
                };
                modelTabel.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data pembayaran: " + e.getMessage());
        }
    }
    
    public void simpanPembayaran(ModelPembayaran mpay) {
        try {
            Connection con = Koneksi.getKoneksi();
            // Query dikurangi, gak pake nomor_kamar lagi
            String query = "INSERT INTO pembayaran (nama_penghuni, total_bayar, tgl_bayar, status_bayar) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, mpay.getNamaPenghuni());
            ps.setDouble(2, mpay.getTotalBayar());
            ps.setString(3, mpay.getTglBayar());
            ps.setString(4, mpay.getStatusBayar());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pembayaran Berhasil Dicatat!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Mencatat Pembayaran: " + e.getMessage());
        }
    }
}