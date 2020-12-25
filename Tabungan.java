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
}
