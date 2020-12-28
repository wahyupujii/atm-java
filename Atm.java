import java.util.*;

public class Atm {
    public static void main(String[] args) {
        Bank mandiri = new Bank("Mandiri");
        Bank bri = new Bank("BRI");

        Scanner userInput = new Scanner(System.in);

        Nasabah wahyu = new Nasabah("Wahyu", 11111, 11111111 , new Tabungan(100));
        Nasabah agus = new Nasabah("Agus", 22222, 22222222 , new Tabungan(200));

        Nasabah puji = new Nasabah("Puji", 33333, 33333333 , new Tabungan(300));
        Nasabah tuti = new Nasabah("Tuti", 44444, 44444444 ,new Tabungan(400));

        mandiri.tambahNasabah(wahyu);
        mandiri.tambahNasabah(agus);
        bri.tambahNasabah(puji);
        bri.tambahNasabah(tuti);

        System.out.println("Selamat datang di ATM Bersama");
        System.out.println("Silahkan pilih bank : ");
        int pilihanBank = pilihBank();
        
        System.out.print("Masukkan no pin anda : ");
        int pinAccountUser = userInput.nextInt();
    
        int pilihan;
        if ( pilihanBank == 1 ) {
            for (int i = 0; i < mandiri.getJumlahNasabah(); i++) {
                if (mandiri.getNasabah(i).getPin() == pinAccountUser) {
                    pilihan = pilihanMenu();
                    if (pilihan == 5) {
                        pilihanBank = pilihBank();
                        if (pilihanBank == 1) {
                            System.out.println("Maaf jika anda ingin transfer bank sama , silahkan pilih menu ke - 4");
                        } else {
                            transferBedaBank(bri, mandiri, pinAccountUser , mandiri.getNasabah(i));
                        }
                    } else {
                        aksiMenu(pilihan, pinAccountUser, mandiri, i);
                    }
                } else if (i == mandiri.getJumlahNasabah()-1) {
                    System.out.println("Maaf akun di anda di bank mandiri tidak ada");
                } else {
                    continue;
                }
            }
        } else if (pilihanBank == 2) {
            for (int i = 0; i < bri.getJumlahNasabah(); i++) {
                if (bri.getNasabah(i).getPin() == pinAccountUser) {
                    pilihan = pilihanMenu();
                    if (pilihan == 5) {
                        pilihanBank = pilihBank();
                        if (pilihanBank == 1) {
                            transferBedaBank(mandiri, bri, pinAccountUser , bri.getNasabah(i));
                        } else if (pilihanBank == 2) {
                            System.out.println("Maaf jika anda ingin transfer bank sama , silahkan pilih menu ke - 4");
                        }
                    } else {
                        aksiMenu(pilihan, pinAccountUser, bri, i);
                    }
                } else if (i == bri.getJumlahNasabah()-1) {
                    System.out.println("Maaf akun di anda di bank bri tidak ada");
                } else {
                    continue;
                }
            }
        }
    }

    static int pilihBank() {
        int pilihan;
        Scanner userInput = new Scanner(System.in);
        System.out.println("1. Mandiri");
        System.out.println("2. BRI");
        System.out.print("Pilihan anda : ");
        pilihan = userInput.nextInt();
        return pilihan;
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
                        System.out.println("Saldo awal anda : " + namaBank.getNasabah(i).getTabungan().getSaldo());
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
                            System.out.println("Anda transfer : " + jumlah);
                            System.out.println("Saldo anda sekarang : " + saldoAkunAktif);
                            return;
                        } else if (j == namaBank.getJumlahNasabah()-1){
                            System.out.println("Akun yang ingin anda transfer tidak ada di bank ini");
                        } else {
                            continue;
                        }
                    }
                }
                break;
        }
    }

    static void transferBedaBank(Bank namaBankTujuan , Bank namaBankAkunAktif , int pinAccountUser , Nasabah nasabahAktif) {
        Scanner userInput = new Scanner(System.in);
        int jumlah;
        int noRekTujuan;
        int saldoNasabahAktif = nasabahAktif.getTabungan().getSaldo();
        System.out.print("Masukkan jumlah uang : ");
        jumlah = userInput.nextInt();
        System.out.print("Masukkan no rek tujuan : ");
        noRekTujuan = userInput.nextInt();
        System.out.println(nasabahAktif.getNamaNasabah());
        System.out.println("Saldo awal anda : " + saldoNasabahAktif);
        saldoNasabahAktif -= jumlah;
        System.out.println("Anda mentransfer " + jumlah);
        for (int i = 0; i < namaBankTujuan.getJumlahNasabah(); i++) {
            if (namaBankTujuan.getNasabah(i).getNoRek() == noRekTujuan) {
                namaBankTujuan.getNasabah(i).getTabungan().tambahSaldo(jumlah);
                nasabahAktif.getTabungan().setSaldo(saldoNasabahAktif);
                System.out.println("Saldo anda sekarang : " + saldoNasabahAktif);
                return; 
            } else if (i == namaBankTujuan.getJumlahNasabah()-1 ) {
                System.out.println("Maaf no rek yang anda tujukan tidak ada");
                return;
            } else {
                continue;
            }
        }
    }
}
