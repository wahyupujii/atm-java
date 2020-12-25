import java.util.*;
public class Atm {
    public static void main(String[] args) {
        Bank mandiri = new Bank();
        Bank bri = new Bank();
        Bank bca = new Bank();

        Scanner userInput = new Scanner(System.in);

        int pilihanBank = pilihBank();
        int pinAccountUser = userInput.nextInt();
        
        if (pilihanBank == 1) {
            cekPin(mandiri, pinAccountUser);
        } else if (pilihanBank == 2){
            cekPin(bri, pinAccountUser);
        } else if (pilihanBank == 3) {
            cekPin(bca, pinAccountUser);
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

    static void cekPin(Bank namaBank , int pinAccount) {
        boolean status = false;
        int pilihan;
        for (int i = 0; i < namaBank.getJumlahNasabah(); i++) {
            if (pinAccount == namaBank.getNasabah(i).getPin()) {
                status = true;
                pilihan = pilihanMenu();
                aksiMenu(pilihan , pinAccount , namaBank);
            } else if ((i == namaBank.getJumlahNasabah()) && (status == true)) {
                System.out.println("Maaf , pin yang anda masukkan salah");
            } else {
                continue;
            }
        }
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

    static void aksiMenu(int pilihan , int pinAccount , Bank namaBank) {
        boolean status = false;
        Scanner userInput = new Scanner(System.in);
        int input;
        switch(pilihan) {
            case 1:
                for (int i = 0; i < namaBank.getJumlahNasabah(); i++) {
                    if (namaBank.getNasabah(i).getPin() == pinAccount) {
                        System.out.println("Saldo tabungan anda adalah : " + namaBank.getNasabah(i).getTabungan());
                        status = true;
                        break;
                    } else if ((i == namaBank.getJumlahNasabah()) && (status == false)) {
                        System.out.println("Maaf , saldo atas nama anda tidak ditemukan");
                    } else {
                        continue;
                    }
                };
                break;
            case 2: 
                status = false;
                for (int i = 0; i < namaBank.getJumlahNasabah(); i++) {
                    if (namaBank.getNasabah(i).getPin() == pinAccount) {
                        System.out.print("Masukkan uang yang ingin ditabung : ");
                        input = userInput.nextInt();
                        namaBank.getNasabah(i).getTabungan().tambahSaldo(input);
                        System.out.println("Saldo anda manjadi : " + namaBank.getNasabah(i).getTabungan().getSaldo());
                        status = true;
                        break;
                    } else if ((i == namaBank.getJumlahNasabah()) && (status == false)) {
                        System.out.println("Maaf , saldo atas nama anda tidak ditemukan");
                    } else {
                        continue;
                    }
                };
                break;
        }
    }
}
