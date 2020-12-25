public class test {
    public static void main(String[] args) {
        Bank bank1 = new Bank();

        Nasabah nasabah1 = new Nasabah("Wahyu", "Jl. Bogen No 28", "0881026740531", 60288 , new Tabungan(100));
        Nasabah nasabah2 = new Nasabah("Puji", "Jl. Bogen No 29", "0881026740532", 60289 , new Tabungan(100));
        Nasabah nasabah3 = new Nasabah("Ramadhan", "Jl. Bogen No 30", "0881026740533", 60290 , new Tabungan(100));

        bank1.tambahNasabah(nasabah1);
        bank1.tambahNasabah(nasabah2);
        bank1.tambahNasabah(nasabah3);

        for(int i =0; i < bank1.getJumlahNasabah(); i++){
            if ( bank1.getNasabah(i).getPin() == 60289 ) {
                continue;
            } else {
                System.out.println("\nNasabah ke : " + (i+1));
                System.out.println("Nama : " + bank1.getNasabah(i).getNamaNasabah());
                System.out.println("Saldo : " + bank1.getNasabah(i).getTabungan().getSaldo());
                System.out.println("No telp : " + bank1.getNasabah(i).getnoTelp());                
            }
        } 
           
    }
}
