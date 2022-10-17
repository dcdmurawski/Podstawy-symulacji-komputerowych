import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[][] tab = new int[42][42];
        int tempGora = 200;
        int tempLewo = 100;
        int tempDol = 150;
        int tempPrawo = 50;
        for(int i=0; i<42; i++){
            tab[0][i] = tempGora;
            tab[i][0] = tempLewo;
            tab[41][i] = tempDol;
            tab[i][41] = tempPrawo;
        }
        int[][] macierz = new int[1600][1601];
        int w=0; //do przechodzenia po wierszach macierzy
        int k=0; //do przechodzenia po kolumnach macierzy
        //Wzor T(i+1,j) - 4*T(i,j) + T(i-1,j) + T(i,j+1) + T(i,j-1) = 0
        ArrayList<Integer> niewiadome = new ArrayList<>();
        int[][] pom = new int[42][42];
        int suma = 0;
        for(int i=1; i < 41; i++){
            for (int j = 1; j < 41; j++) {
                niewiadome.add(tab[i+1][j]);
                niewiadome.add(tab[i][j]);
                niewiadome.add(tab[i-1][j]);
                niewiadome.add(tab[i][j+1]);
                niewiadome.add(tab[i][j-1]);
                //Przechodzenie po wartosciach ze wzoru i przypisywanie im odpowiednich liczb do macierzy
                for(int o=0; o<niewiadome.size(); o++){
                    switch(o){
                        case 0:
                            if(niewiadome.get(o)==0)
                                pom[i+1][j]=1;
                            else
                                suma+=niewiadome.get(o);
                            break;
                        case 1:
                            if(niewiadome.get(o)==0)
                                pom[i][j]=-4;
                            else
                                suma+=niewiadome.get(o);
                            break;
                        case 2:
                            if(niewiadome.get(o)==0)
                                pom[i-1][j]=1;
                            else
                                suma+=niewiadome.get(o);
                            break;
                        case 3:
                            if(niewiadome.get(o)==0)
                                pom[i][j+1]=1;
                            else
                                suma+=niewiadome.get(o);
                            break;
                        case 4:
                            if(niewiadome.get(o)==0)
                                pom[i][j-1]=1;
                            else
                                suma+=niewiadome.get(o);
                            break;
                    }
                }
                //robie z tablicy 2D -> 1-wymiarowa i aplikuje do wiersza macierzy
                for(int p=1; p<41; p++)
                    for (int l = 1; l < 41; l++)
                        macierz[w][k++] = pom[p][l];
                macierz[w][k]=-1*suma;
                w++;
                k=0;
                suma=0;
                pom=new int[42][42];
                niewiadome.clear();
            }
        }
        //Zapisywanie wynikow do pliku
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("lab7.txt"));
            for(int i=0; i<macierz.length; i++){
                for (int j = 0; j < macierz[i].length; j++) {
                    writer.write(macierz[i][j]+";");
                }
                System.out.println();
                writer.write("\n");
            }
           writer.close();
        } catch (Exception e){}
    }
}
