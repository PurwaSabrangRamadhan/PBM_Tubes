package id.nyrat.tugas.adapter;

public class Data {
    private String id, nama, kerusakan;

    public Data() {
    }

    public Data(String id, String nama, String alamat){
        this.id = id;
        this.nama = nama;
        this.kerusakan = alamat;
    }

    public String getId() { return id; }
    public void setId(String id){ this.id = id; }
    public String getNama() { return nama; }
    public void setNama (String nama) { this.nama = nama; }
    public String getKerusakan() { return kerusakan; }
    public void setKerusakan(String kerusakan) { this.kerusakan = kerusakan; }

}
