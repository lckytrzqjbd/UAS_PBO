package model;

public class ModelKamar {
    private int idKamar;
    private String nomorKamar;
    private double hargaKost;
    private int kapasitas;
    private String status;

    // Bikin Getter dan Setter-nya (Di NetBeans tinggal tekan Alt + Insert -> Getter and Setter -> Centang Semua)
    public int getIdKamar() { return idKamar; }
    public void setIdKamar(int idKamar) { this.idKamar = idKamar; }
    
    public String getNomorKamar() { return nomorKamar; }
    public void setNomorKamar(String nomorKamar) { this.nomorKamar = nomorKamar; }
    
    public double getHargaKost() { return hargaKost; }
    public void setHargaKost(double hargaKost) { this.hargaKost = hargaKost; }
    
    public int getKapasitas() { return kapasitas; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}