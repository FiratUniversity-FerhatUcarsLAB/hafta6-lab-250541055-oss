public class SinemaBileti {

    // 1) Hafta sonu kontrolü
    public static boolean isWeekend(int gun) {
        // 6 = Cumartesi, 7 = Pazar
        return gun == 6 || gun == 7;
    }

    // 2) Matine kontrolü (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {

        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        // Hafta içi (1-5) fiyatları
        if (!weekend) {
            if (matinee)
                return 45;  // Hafta içi matine
            else
                return 65;  // Hafta içi normal
        }

        // Hafta sonu (6-7) fiyatları
        else {
            if (matinee)
                return 55;  // Hafta sonu matine
            else
                return 85;  // Hafta sonu normal
        }
    }

    // 4) İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {

        // 65 yaş üstü → %30
        if (yas >= 65) {
            return 0.30;
        }

        // 12 yaş altı → %25
        if (yas < 12) {
            return 0.25;
        }

        // Meslek seçimi (1=Öğrenci, 2=Öğretmen, 3=Diğer)
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) {
                    return 0.20; // Pazartesi–Perşembe
                } else {
                    return 0.15; // Cuma–Pazar
                }

            case 2: // Öğretmen
                if (gun == 3) { // Çarşamba
                    return 0.35;
                }
                break;

            default:
                return 0.0;
        }

        return 0.0;
    }

    // 5) Film türü ekstra ücret
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;     // 2D
            case 2: return 25;    // 3D
            case 3: return 35;    // IMAX
            case 4: return 50;    // 4DX
            default: return 0;
        }
    }

    // 6) Toplam fiyat hesaplama
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {

        double base = calculateBasePrice(gun, saat);  // Temel fiyat
        double discount = calculateDiscount(yas, meslek, gun); // İndirim oranı
        double formatExtra = getFormatExtra(filmTuru); // Format ekstra ücreti

        double priceAfterDiscount = base - (base * discount);

        return priceAfterDiscount + formatExtra;
    }

    // 7) Bilet bilgisi üretme
    public static String generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {

        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double extra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        return "----- BİLET BİLGİSİ -----\n" +
                "Temel Fiyat: " + base + " TL\n" +
                "İndirim Oranı: %" + (discountRate * 100) + "\n" +
                "Format Ekstra: " + extra + " TL\n" +
                "Toplam Fiyat: " + finalPrice + " TL\n";
    }

    // ÖRNEK KULLANIM (main)
    public static void main(String[] args) {

        // ÖRNEK SENARYO:
        // Gün = 4 (Perşembe)
        // Saat = 10 (Matine)
        // Yaş = 22
        // Meslek = 1 (Öğrenci)
        // Film türü = 2 (3D)

        String ticket = generateTicketInfo(4, 10, 22, 1, 2);
        System.out.println(ticket);
    }
}

