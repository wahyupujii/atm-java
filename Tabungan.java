public class Tabungan {
    private int saldo;

    public Tabungan (int saldo) {
        this.saldo = saldo;
    }
    public void tambahSaldo (int uang) {
        this.saldo += uang;
    }
    public int getSaldo() {
        return this.saldo;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public boolean ambilUang(int jumlah) {
        if ((this.saldo - jumlah) < 0) {
            return false;
        } else {
            this.saldo -= jumlah;
            return true;
        }
    }
}
