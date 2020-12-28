public class Bank {
    private String namaBank;
    private int jumlahNasabah;
    private Nasabah nasabah[];

    public Bank (String inputNamaBank) {
        this.namaBank = inputNamaBank;
        nasabah = new Nasabah[200];
    }
    public void tambahNasabah (Nasabah nasabahBaru) {
        nasabah[jumlahNasabah] = nasabahBaru;
        jumlahNasabah++;
    }
    public int getJumlahNasabah() {
        return this.jumlahNasabah;
    }
    public Nasabah getNasabah(int index) {
        return this.nasabah[index];
    }
    public String getNamaBank() {
        return this.namaBank;
    }
}