import java.lang.Math;
public class Main {
    public static void taylorSin(double value) {
        double rad=value;

        //Sprowadzenie do przedzialu [0;2*pi]
        rad = value % (2 * Math.PI);
        value = rad;

        //Zmiana znaku przy dolnej cwiartce
        boolean czyDolneCwiartki;
        if(rad>Math.PI)
            czyDolneCwiartki=true;
        else
            czyDolneCwiartki=false;

        //Sprawdzanie cwiartki i zmiana wartosci
        if (rad > 1.5 * Math.PI)
            rad = 2 * Math.PI - rad;
        else if (rad > Math.PI)
            rad = rad - Math.PI;
        else if (rad > Math.PI / 2)
            rad = Math.PI - rad;

        //Wzor Taylora na Sinus
        double res = 0;
        boolean change = true;
        for (int i = 1; i <= 10; i++) {
            //Zmiana znaku odejmowania/dodawania
            if(i%2==0) {
                System.out.println(i + ". wyraz szeregu: " + res);
            } else {
                if (change) {
                    res = res + Math.pow(rad, i) / fact(i);
                    change = false;
                } else {
                    res = res - Math.pow(rad, i) / fact(i);
                    change = true;
                }
                System.out.println(i + ". wyraz szeregu: " + res);
            }
        }

        //Zmiana znaku jesli cwiartka 3/4
        if(czyDolneCwiartki)
            res *= -1;

        //Wypisywanie wartosci i roznicy miedzy nimi
        double roznica = Math.abs(res-Math.sin(value));
        System.out.println("---------------");
        System.out.println("Wartosc funkcji sin: " + Math.sin(value));
        System.out.println("Finalna wartosc twojej funkcji: " + res);
        System.out.println("Roznica miedzy sin, a nasza funkcja: " + roznica);
    }

    //liczenie silnii
    public static int fact(int value){
        int res=1;
        for(int i=1; i<=value; i++){
            res=res*i;
        }
        return res;
    }

    public static void main(String[] args) {
        taylorSin(10*Math.PI);
    }
}
