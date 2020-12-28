import java.util.*;

public class Atm {
    public static void main(String[] args) {
        Bank mandiri = new Bank();
        Bank bri = new Bank();
        Bank bca = new Bank();

        Scanner userInput = new Scanner(System.in);

        Nasabah wahyu = new Nasabah("Wahyu", 11111, 11111111 , new Tabungan(100));
        Nasabah agus = new Nasabah("Agus", 22222, 22222222 , new Tabungan(200));
        Nasabah puji = new Nasabah("Puji", 33333, 33333333 , new Tabungan(300));
        Nasabah tuti = new Nasabah("Tuti", 44444, 44444444 ,new Tabungan(400));
        Nasabah ramadhan = new Nasabah("Ramadhan", 55555 , 55555555 , new Tabungan(500));
        Nasabah bambang = new Nasabah("Bambang", 66666 , 66666666 , new Tabungan(600));

        mandiri.tambahNasabah(wahyu);
        mandiri.tambahNasabah(agus);
        bri.tambahNasabah(puji);
        bri.tambahNasabah(tuti);
        bca.tambahNasabah(ramadhan);
        bca.tambahNasabah(bambang);

        int pilihanBank = pilihBank();
        
        System.out.print("Masukkan no pin anda : ");
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
        Scanner userInput = new Scanner(System.in);
        int jumlah;
        int pilihan;
        int noRekTujuan;
        boolean status = false;
        for (int i = 0; i < namaBank.getJumlahNasabah(); i++) {
            if (pinAccount == namaBank.getNasabah(i).getPin()) {
                status = true;
                pilihan = pilihanMenu();
                aksiMenu(pilihan , pinAccount , namaBank , i);
            } else if (i == namaBank.getJumlahNasabah()-1 && status == false) {
                System.out.println("Maaf , akun dengan pin tersebut tidak ditemukan");
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
        System.out.println("4. Transfer bank sama");
        System.out.println("5. Transfer bank beda");
        System.out.print("Pilihan anda : ");
        pilihan = userInput.nextInt();
        return pilihan;
    }

    static void aksiMenu(int pilihan , int pinAccount , Bank namaBank , int i) {
        boolean status = false;
        Scanner userInput = new Scanner(System.in);
        int input;
        switch(pilihan) {
            case 1:
                for (int j = 0; j < namaBank.getJumlahNasabah(); j++) {
                    if (namaBank.getNasabah(j).getPin() == pinAccount) {
                        System.out.println("Saldo tabungan anda adalah : " + namaBank.getNasabah(j).getTabungan().getSaldo());
                        status = true;
                        break;
                    } else if (j == namaBank.getJumlahNasabah()-1 && status == false) {
                        System.out.println("Maaf , akun atas nama anda tidak ditemukan");
                    } else {
                        continue;
                    }
                };
                break;
            case 2: 
                status = false;
                for (int j = 0; j < namaBank.getJumlahNasabah(); j++) {
                    if (namaBank.getNasabah(j).getPin() == pinAccount) {
                        System.out.print("Masukkan uang yang ingin ditabung : ");
                        input = userInput.nextInt();
                        namaBank.getNasabah(j).getTabungan().tambahSaldo(input);
                        System.out.println("Saldo anda manjadi : " + namaBank.getNasabah(j).getTabungan().getSaldo());
                        status = true;
                        break;
                    } else if (j == namaBank.getJumlahNasabah()-1 && status == false) {
                        System.out.println("Maaf , akun atas nama anda tidak ditemukan");
                    } else {
                        continue;
                    }
                };
                break;
            case 3:
                status = false;
                for (int j = 0; j < namaBank.getJumlahNasabah(); j++) {
                    if (namaBank.getNasabah(j).getPin() == pinAccount) {
                        System.out.print("Masukkan jumlah yang ingin diambil : ");
                        input = userInput.nextInt();
                        status = namaBank.getNasabah(j).getTabungan().ambilUang(input);
                        if (status) {
                            System.out.println("Anda mengambil sebanyak " + input);
                            System.out.println("Saldo anda sekarang : " + namaBank.getNasabah(j).getTabungan().getSaldo());
                            break;
                        } else {
                            System.out.println("Pengambilan uang gagal , mungkin saldo anda tidak cukup");
                            break;
                        }
                    } else if (j == namaBank.getJumlahNasabah()-1 && status == false) {
                        System.out.println("Maaf , akun atas nama anda tidak ditemukan");
                    } else {
                        continue;
                    }
                };
                break;
            case 4:
                System.out.print("Masukkan no rekening tujuan : ");
                int noRekTujuan = userInput.nextInt();
                System.out.print("Masukkan jumlah uang : ");
                int jumlah = userInput.nextInt();
                int saldoAkunAktif = namaBank.getNasabah(i).getTabungan().getSaldo();
                System.out.println("Saldo awal anda : " + saldoAkunAktif);
                if ((saldoAkunAktif - jumlah) < 0) {
                    System.out.println("Saldo anda tidak cukup untuk transfer");
                    return;
                } else {
                    saldoAkunAktif -= jumlah;
                    namaBank.getNasabah(i).getTabungan().setSaldo(saldoAkunAktif);
                    for (int j = 0; j < namaBank.getJumlahNasabah(); j++) {
                        if (namaBank.getNasabah(j).getNoRek() == noRekTujuan) {
                            System.out.println("Saldo anda sekarang : " + saldoAkunAktif);
                            return;
                        } else {
                            System.out.println("Transfer gagal");
                            continue;
                        }
                    }
                }
                break;
            // case 5:
        }
    }
}
