import java.util.Scanner;

public class NotSistemi {
    public static void main(String[] args) {
        Scanner imput = new Scanner(System.in);

        System.out.println("Vize Notunu Girin: ");
        int vizenotu = imput.nextInt();
        System.out.println("Final Notu Girin: ");
        int finalnotu = imput.nextInt();
        System.out.println("Odev Notu Girin: ");
        int odevnotu = imput.nextInt();

        System.out.println("========Ogrenci Not Sistemi========");
        System.out.println("Vize Notu = " + vizenotu);
        System.out.println("Final Notu = " + finalnotu);
        System.out.println("Odev Notu = " + odevnotu);

        double ort = calculateAverage(vizenotu, finalnotu, odevnotu);
        System.out.println("=============");
        System.out.println("Ortalama : " + ort);

        System.out.println("Harf Notu : " + getLetterGrade(ort));
        System.out.println("Durum : " + (isPassingGrade(ort) ? "GEÇTİ" :  "KALDI" ) );
        System.out.println("Onur Listesi : " + isHonnorList(ort, vizenotu, finalnotu, odevnotu) + "EVET" + "HAYIR");
        System.out.println("Bütünleme :  " + hasRetakeRigh(ort) + "EVET" + "HAYIR");
    }

    public static double calculateAverage(double vizenot, double finalnot, double odevnot) {
        return vizenot * 0.3 + finalnot * 0.4 + odevnot * 0.6;
    }

    public static boolean isPassingGrade(double ort) {
        if (ort >= 50) {
            return true;
        } else {
            return false;
        }
    }

    public static String   getLetterGrade(double ort) {
        String harfnot = "";
        if (ort >= 90 && ort <= 100) {
            harfnot = "A";
        } else if (ort >= 80 && ort <= 90) {
            harfnot = "B";
        } else if (ort >= 70 && ort <= 80) {
            harfnot = "C";
        } else if (ort >= 60 && ort <= 70) {
            harfnot = "D";
        } else {
            harfnot = "F";
        }
     return harfnot;
    }

    public static boolean isHonnorList(double ort, int vizenotu, int finalnotu, int odevnotu) {
        if (ort >= 85 && vizenotu >= 70 && finalnotu >= 70 && odevnotu >= 70) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasRetakeRigh(double ort) {
        return (ort >= 40 && ort <= 50) ? true : false;
    }
}



