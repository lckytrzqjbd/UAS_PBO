package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi; // Memakai K-kapital sesuai class kamu
import model.ModelPenghuni;

public class ControllerPenghuni {
    
    // FUNGSI A: ISI COMBOBOX KAMAR OTOMATIS
    public void isiComboKamar(JComboBox cbKamar) {
        cbKamar.removeAllItems();
        cbKamar.addItem("-- Pilih Kamar --");
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "SELECT nomor_kamar FROM kamar WHERE status = 'Kosong'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                cbKamar.addItem(rs.getString("nomor_kamar"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data kamar: " + e.getMessage());
        }
    }
    
    // FUNGSI B: TAMPIL DATA PENGHUNI KE JTABLE
    public void tampilData(DefaultTableModel modelTabel) {
        modelTabel.setRowCount(0);
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "SELECT * FROM penghuni";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Object[] data = {
                    rs.getInt("id_penghuni"),
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("no_hp"),
                    rs.getString("alamat"),
                    rs.getString("tgl_masuk"),
                    rs.getString("kamar")
                };
                modelTabel.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data penghuni: " + e.getMessage());
        }
    }
    
    // FUNGSI C: SIMPAN PENGHUNI
    public void simpanPenghuni(ModelPenghuni mp) {
        try {
            Connection con = Koneksi.getKoneksi();
            String queryInsert = "INSERT INTO penghuni (nama, jenis_kelamin, no_hp, alamat, tgl_masuk, kamar) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = con.prepareStatement(queryInsert);
            psInsert.setString(1, mp.getNama());
            psInsert.setString(2, mp.getJenisKelamin());
            psInsert.setString(3, mp.getNoHp());
            psInsert.setString(4, mp.getAlamat());
            psInsert.setString(5, mp.getTglMasuk());
            psInsert.setString(6, mp.getKamar());
            psInsert.executeUpdate();
            
            String queryUpdateKamar = "UPDATE kamar SET status = 'Terisi' WHERE nomor_kamar = ?";
            PreparedStatement psUpdate = con.prepareStatement(queryUpdateKamar);
            psUpdate.setString(1, mp.getKamar());
            psUpdate.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Penghuni Berhasil Disimpan & Status Kamar Diperbarui!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data: " + e.getMessage());
        }
    }
    
    // FUNGSI D: UBAH DATA PENGHUNI
    public void ubahPenghuni(ModelPenghuni mp) {
        try {
            Connection con = Koneksi.getKoneksi();
            String query = "UPDATE penghuni SET nama=?, jenis_kelamin=?, no_hp=?, alamat=?, tgl_masuk=? WHERE id_penghuni=?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, mp.getNama());
            ps.setString(2, mp.getJenisKelamin());
            ps.setString(3, mp.getNoHp());
            ps.setString(4, mp.getAlamat());
            ps.setString(5, mp.getTglMasuk());
            ps.setInt(6, mp.getIdPenghuni());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Penghuni Berhasil Diubah!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Mengubah Data: " + e.getMessage());
        }
    }

    // FUNGSI E: HAPUS PENGHUNI
    public void hapusPenghuni(int idPenghuni, String nomorKamar) {
        try {
            Connection con = Koneksi.getKoneksi();
            String queryDelete = "DELETE FROM penghuni WHERE id_penghuni=?";
            PreparedStatement psDelete = con.prepareStatement(queryDelete);
            psDelete.setInt(1, idPenghuni);
            psDelete.executeUpdate();
            
            String queryUpdateKamar = "UPDATE kamar SET status = 'Kosong' WHERE nomor_kamar = ?";
            PreparedStatement psUpdate = con.prepareStatement(queryUpdateKamar);
            psUpdate.setString(1, nomorKamar);
            psUpdate.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Data Penghuni Dihapus & Kamar Kembali Kosong!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Data: " + e.getMessage());
        }
    }
}