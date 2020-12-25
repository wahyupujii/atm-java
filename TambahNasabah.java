import java.util.*;

public class TambahNasabah {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Program untuk menambah nasabah baru");
        System.out.println("===================================");
        System.out.println("Pilih bank : ");
        System.out.println("1. Mandiri");
        System.out.println("2. BRI");
        System.out.println("3. BCA");
        System.out.print("Pilihan anda : ");
        int pilihan = userInput.nextInt();

        System.out.println("\nSilahkan isi data diri");
        System.out.println("===================================");
        System.out.print("Masukkan nama : ");
        String nama = userInput.nextLine();
        System.out.print("Masukkan alamat : ");
        String alamat = userInput.nextLine();
        System.out.print("Masukkan no telp : ");
        String noTelp = userInput.nextLine();

        Random rand = new Random();
        int pin = rand.nextInt(100000);

        Bank mandiri = new Bank();
        Bank bri = new Bank();
        Bank bca = new Bank();

        Nasabah nasabahBaru = new Nasabah(nama, alamat, noTelp, pin, new Tabungan(0));

        switch(pilihan) {
            case 1: 
                mandiri.tambahNasabah(nasabahBaru);
                break;
            case 2: 
                bri.tambahNasabah(nasabahBaru);
                break;
            case 3: 
                bca.tambahNasabah(nasabahBaru);
                break;
        }
    }
}
