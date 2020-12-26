public class Nasabah extends Bank {
    private String nama;
    private int pin;
    private int noRek;
    private Tabungan tabungan;
    
    public Nasabah (String inputnama , int inputpin , int inputnorek , Tabungan saldoAwal) {
        this.nama = inputnama;
        this.pin = inputpin;
        this.noRek = inputnorek;
        this.tabungan = saldoAwal;
    }

    public String getNamaNasabah() {
        return this.nama;
    }

    public int getPin() {
        return this.pin;
    }

    public Tabungan getTabungan() {
        return this.tabungan;
    }
    public int getNoRek() {
        return this.noRek;
    }
}
