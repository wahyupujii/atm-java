public class Nasabah extends Bank {
    private String nama;
    private String alamat;
    private String noTelp;
    private int pin;
    private Tabungan tabungan;
    
    public Nasabah (String inputnama , String inputalamat , String inputnotelp , int inputpin , Tabungan saldoAwal) {
        this.nama = inputnama;
        this.alamat = inputalamat;
        this.noTelp = inputnotelp;
        this.pin = inputpin;
        this.tabungan = saldoAwal;
    }

    public String getNamaNasabah() {
        return this.nama;
    }

    public String getnoTelp() {
        return this.noTelp;
    }

    public int getPin() {
        return this.pin;
    }

    public Tabungan getTabungan() {
        return this.tabungan;
    }
}
