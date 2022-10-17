import java.util.ArrayList;

public class Main {
    private static int x = 10;
    private static int y = 10;
    private static int[][] plansza = new int[y][x];
    private static int[][] planszaCykl = new int[y+2][x+2];
    private static ArrayList<Integer> wygrane = new ArrayList<>();
    private static ArrayList<Integer> przegrane = new ArrayList<>();
    private static boolean changed = true;

    public static void main(String[] args) {

        String reg = args[0];
        String[] reguly = reg.split("/");
        for(String r : reguly[0].split(""))
            wygrane.add(Integer.parseInt(r));
        for(String r : reguly[1].split(""))
            przegrane.add(Integer.parseInt(r));

        //-----------------------------------------

        setAlive(2,1);
        setAlive(3,2);
        setAlive(4,2);
        setAlive(4,1);
        setAlive(4,0);

        start();
    }

    public static void start(){
        setPlanszaCykl();
        printPlansza();
        while(changed) {
            changed = false;
            int[][] planszaPom = plansza.clone();

            for (int i = 0; i < plansza.length; i++) {
                for (int j = 0; j < plansza[i].length; j++) {
                    if(planszaPom[i][j]==0) {
                        if (przegrane.contains(countNeighbours(j, i))) {
                            setAlive(i, j);
                            changed = true;
                        }
                    }
                    else {
                        if (!wygrane.contains(countNeighbours(j, i))) {
                            setDead(i, j);
                            changed = true;
                        }
                    }
                }
            }
            setPlanszaCykl();
            printPlansza();
            try {
                Thread.sleep(500);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void setDead(int x, int y){
        plansza[x][y]=0;
    }

    public static void setAlive(int x, int y){
        plansza[x][y]=1;
    }

    public static void printPlansza(){
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                System.out.print(plansza[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }

    public static int countNeighbours(int x, int y){
        int count = 0;
        x++;
        y++;
        count+=planszaCykl[y-1][x-1];
        count+=planszaCykl[y-1][x];
        count+=planszaCykl[y-1][x+1];

        count+=planszaCykl[y][x-1];
        count+=planszaCykl[y][x+1];

        count+=planszaCykl[y+1][x-1];
        count+=planszaCykl[y+1][x];
        count+=planszaCykl[y+1][x+1];

        return count;
    }

    public static void setPlanszaCykl(){
        for (int i = 1; i < x + 1; i++) {
            planszaCykl[0][i]=plansza[y-1][i-1];
            planszaCykl[i][0]=plansza[i-1][y-1];
            planszaCykl[y+1][i]=plansza[0][i-1];
            planszaCykl[i][y+1]=plansza[i-1][0];
        }
        planszaCykl[0][0]=plansza[plansza.length-1][plansza.length-1];
        planszaCykl[planszaCykl.length-1][planszaCykl.length-1]=plansza[0][0];
        planszaCykl[planszaCykl.length-1][0]=plansza[0][plansza.length-1];
        planszaCykl[0][planszaCykl.length-1]=plansza[plansza.length-1][0];
        for (int i = 1; i < plansza.length+1; i++) {
            for (int j = 1; j < plansza.length+1; j++) {
                planszaCykl[i][j]= plansza[i-1][j-1];
            }
        }
    }
}
