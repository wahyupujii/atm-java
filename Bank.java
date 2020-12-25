public class Bank {
    // private String namaBank;
    private int jumlahNasabah;
    private Nasabah nasabah[];

    public Bank () {
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
}