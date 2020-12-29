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
                    if (pilihan == 1) {
                        tampilkanSaldo(mandiri.getNasabah(i));
                    } else if (pilihan == 2) {
                        tambahSaldo(mandiri.getNasabah(i));
                    } else if (pilihan == 3) {
                        ambilUang(mandiri.getNasabah(i));
                    } else if (pilihan == 4) {
                        transferBankSama(mandiri , mandiri.getNasabah(i));
                    } else if (pilihan == 5) {
                        pilihanBank = pilihBank();
                        if (pilihanBank == 1) {
                            System.out.println("Maaf jika anda ingin transfer bank sama , silahkan pilih menu ke - 4");
                        } else {
                            transferBedaBank(bri, mandiri, mandiri.getNasabah(i));
                        }
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
                    if (pilihan == 1) {
                        tampilkanSaldo(bri.getNasabah(i));
                    } else if (pilihan == 2) {
                        tambahSaldo(bri.getNasabah(i));
                    } else if (pilihan == 3) {
                        ambilUang(bri.getNasabah(i));
                    } else if (pilihan == 4) {
                        transferBankSama(bri , bri.getNasabah(i));
                    } else if (pilihan == 5) {
                        pilihanBank = pilihBank();
                        if (pilihanBank == 2) {
                            System.out.println("Maaf jika anda ingin transfer bank sama , silahkan pilih menu ke - 4");
                        } else {
                            transferBedaBank(mandiri, bri, bri.getNasabah(i));
                        }
                    }
                } else if (i == bri.getJumlahNasabah()-1) {
                    System.out.println("Maaf akun di anda di bank bri tidak ada");
                } else {
                    continue;
                }
            }
        }
    }

    static void tampilkanSaldo(Nasabah nasabahAktif) {
        System.out.println("Saldo tabungan anda adalah : " + nasabahAktif.getTabungan().getSaldo());
    }

    static void tambahSaldo(Nasabah nasabahAktif) {
        Scanner userInput = new Scanner(System.in);
        int jumlah; 
        System.out.print("Masukkan jumlah uang : ");
        jumlah = userInput.nextInt();
        System.out.println("Saldo awal anda : " + nasabahAktif.getTabungan().getSaldo());
        nasabahAktif.getTabungan().tambahSaldo(jumlah);
        System.out.println("Saldo setelah ditambah : " + nasabahAktif.getTabungan().getSaldo());
    }

    static void ambilUang(Nasabah nasabahAktif) {
        Scanner userInput = new Scanner(System.in);
        int jumlah; 
        boolean status;
        System.out.print("Masukkan jumlah uang : ");
        jumlah = userInput.nextInt();
        System.out.println("Saldo awal anda : " + nasabahAktif.getTabungan().getSaldo());
        status = nasabahAktif.getTabungan().ambilUang(jumlah);
        if (status) {
            System.out.println("Pengambilan berhasil , saldo anda sekarang " + nasabahAktif.getTabungan().getSaldo());
        } else {
            System.out.println("Pengambilan uang gagal");
        }
    }

    static void transferBankSama (Bank namaBank , Nasabah nasabahAktif) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Masukkan no rekening tujuan : ");
        int noRekTujuan = userInput.nextInt();
        System.out.print("Masukkan jumlah uang : ");
        int jumlah = userInput.nextInt();
        int saldoAkunAktif = nasabahAktif.getTabungan().getSaldo();
        System.out.println("Saldo awal anda : " + saldoAkunAktif);
        if ((saldoAkunAktif - jumlah) < 0){
            System.out.println("Saldo anda tidak cukup untuk transfer");
            return;
        } else {
            saldoAkunAktif -= jumlah;
            nasabahAktif.getTabungan().setSaldo(saldoAkunAktif);
            for (int i = 0; i < namaBank.getJumlahNasabah(); i++) {
                if (namaBank.getNasabah(i).getNoRek() == noRekTujuan) {
                    System.out.println("Anda transfer : " + jumlah);
                    System.out.println("Saldo anda sekarang : " + nasabahAktif.getTabungan().getSaldo());
                    return;
                } else if (i == namaBank.getJumlahNasabah()-1) {
                    System.out.println("Akun yang ingin anda transfer tidak ada di bank ini");
                } else {
                    continue;
                }
            }
        }
    }

    static void transferBedaBank(Bank namaBankTujuan , Bank namaBankAkunAktif , Nasabah nasabahAktif) {
        Scanner userInput = new Scanner(System.in);
        int jumlah;
        int noRekTujuan;
        int saldoNasabahAktif = nasabahAktif.getTabungan().getSaldo();
        System.out.print("Masukkan jumlah uang : ");
        jumlah = userInput.nextInt();
        System.out.print("Masukkan no rek tujuan : ");
        noRekTujuan = userInput.nextInt();
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
}
