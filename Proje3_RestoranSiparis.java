import java.util.Scanner;

public class RestoranSistemi {

    // 1) Ana yemek fiyatlarını döndürür
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;   // Alınmadı
        }
    }

    // 2) Başlangıç fiyatları
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;  // Çorba
            case 2: return 45;  // Humus
            case 3: return 55;  // Sigara Böreği
            default: return 0;
        }
    }

    // 3) İçecek fiyatları
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15; // Kola
            case 2: return 12; // Ayran
            case 3: return 35; // Meyve Suyu
            case 4: return 25; // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı fiyatları
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65; // Künefe
            case 2: return 55; // Baklava
            case 3: return 35; // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo Menü kontrolü → Ana Yemek + İçecek + Tatlı alındı mı?
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli; // Üçü de true ise combo menü
    }

    // 6) Happy hour kontrolü (14-17)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) Toplam indirim hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {

        double indirim = 0;

        // COMBO: %15
        if (combo) {
            indirim += tutar * 0.15;
        }

        // HAPPY HOUR: Sadece içecek indirimli
        if (isHappyHour(saat)) {
            // İçecek indirimi menü toplamı üzerinden yapılmaz,
            // İçecek fiyatı üzerinden %20 alınır
            // Bu nedenle bunu ayrı işlemek gerekiyor
        }

        // Öğrenci indirimi (Hafta içi)
        if (ogrenci) {
            indirim += tutar * 0.10;
        }

        // 200 TL üstü ekstra %10
        if (tutar > 200) {
            indirim += tutar * 0.10;
        }

        return indirim;
    }

    // 8) Garson bahşiş önerisi (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }



    // -------------------------------------------------
    //  ÖRNEK KULLANIM (main)
    // -------------------------------------------------
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Ana Yemek (1-4) (0 = Alma): ");
        int ana = input.nextInt();

        System.out.println("Başlangıç (1-3) (0 = Alma): ");
        int bas = input.nextInt();

        System.out.println("İçecek (1-4) (0 = Alma): ");
        int icec = input.nextInt();

        System.out.println("Tatlı (1-3) (0 = Alma): ");
        int tat = input.nextInt();

        System.out.println("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.println("Öğrenci misiniz? (E/H): ");
        boolean ogrenci = input.next().equalsIgnoreCase("E");


        // Fiyatları hesapla
        double anaFiyat = getMainDishPrice(ana);
        double basFiyat = getAppetizerPrice(bas);
        double icecFiyat = getDrinkPrice(icec);
        double tatFiyat = getDessertPrice(tat);

        double araToplam = anaFiyat + basFiyat + icecFiyat + tatFiyat;

        // Combo kontrolü
        boolean combo = isComboOrder(anaFiyat > 0, icecFiyat > 0, tatFiyat > 0);

        // HAPPY HOUR içecek indirimi
        double icecekIndirimi = 0;
        if (isHappyHour(saat) && icecFiyat > 0) {
            icecekIndirimi = icecFiyat * 0.20;
        }

        // Temel indirim hesaplama
        double digerIndirimler = calculateDiscount(araToplam, combo, ogrenci, saat);

        double toplamIndirim = icecekIndirimi + digerIndirimler;

        double toplam = araToplam - toplamIndirim;

        double bahsis = calculateServiceTip(toplam);


        // ---------------------------
        //     ÇIKTI
        // ---------------------------
        System.out.println("\n----- SİPARİŞ ÖZETİ -----");
        System.out.println("Ara toplam: " + araToplam + " TL");
        System.out.println("Toplam İndirim: -" + toplamIndirim + " TL");
        System.out.println("Ödenecek Tutar: " + toplam + " TL");
        System.out.println("Bahşiş Önerisi (10%): " + bahsis + " TL");
    }
}

