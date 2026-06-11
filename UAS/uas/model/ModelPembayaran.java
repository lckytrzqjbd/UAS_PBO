package model;

public class ModelPembayaran {
    private int idPembayaran;
    private String namaPenghuni;
    private double totalBayar;
    private String tglBayar;
    private String statusBayar;

    public int getIdPembayaran() { return idPembayaran; }
    public void setIdPembayaran(int idPembayaran) { this.idPembayaran = idPembayaran; }

    public String getNamaPenghuni() { return namaPenghuni; }
    public void setNamaPenghuni(String namaPenghuni) { this.namaPenghuni = namaPenghuni; }

    public double getTotalBayar() { return totalBayar; }
    public void setTotalBayar(double totalBayar) { this.totalBayar = totalBayar; }

    public String getTglBayar() { return tglBayar; }
    public void setTglBayar(String tglBayar) { this.tglBayar = tglBayar; }

    public String getStatusBayar() { return statusBayar; }
    public void setStatusBayar(String statusBayar) { this.statusBayar = statusBayar; }
}