import java.util.*;

public class Atm {
    public static void main(String[] args) {
        Bank mandiri = new Bank();
        Bank bri = new Bank();
        Bank bca = new Bank();

        Scanner userInput = new Scanner(System.in);

        int pilihanBank = pilihBank();
        int pinAccountUser = userInput.nextInt();
        boolean condition;

        if (pilihanBank == 1) {
            condition = cekPin(mandiri, pinAccountUser);
        } else if (pilihanBank == 2){
            condition = cekPin(bri, pinAccountUser);
        } else if (pilihanBank == 3) {
            condition = cekPin(bca, pinAccountUser);
        }
    }

    static int pilihBank() {
        int pilihan;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Selamat datang di ATM Bersama");
        System.out.println("Silahkan pilih bank : ");
        System.out.println("1. Mandiri");
        System.out.println("2. BRI");
        System.out.println("3. BCA");
        System.out.print("Pilihan anda : ");
        pilihan = userInput.nextInt();
        return pilihan;
    }

    static boolean cekPin(Bank namaBank , int pinAccount) {
        for (int i = 0; i < namaBank.getJumlahNasabah(); i++) {
            if (pinAccount == namaBank.getNasabah(i).getPin()) {
                return true;
            } else {
                continue;
            }
        }
		return false;
    }
    
    static int pilihanMenu() {
        Scanner userInput = new Scanner(System.in);
        int pilihan;
        System.out.println("Apa yang ingin anda lakukan : ");
        System.out.println("1. Melihat saldo tabungan");
        System.out.println("2. Menambah saldo tabungan");
        System.out.println("3. Mengambil uang dari saldo tabungan");
        System.out.println("4. Melihat identitas");
        System.out.print("Pilihan anda : ");
        pilihan = userInput.nextInt();
        return pilihan;
    }
}
