import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    private static ArrayList<Rule> reguly = new ArrayList<>();
    private static String kroki="X";

    public static void main(String[] args) {

        ArrayList<String> reg = new ArrayList<>();
        reg.add("F->FF");
        reg.add("X->F+[[X]-X]-F[-FX]+X");

        for (int i = 0; i < reg.size(); i++) {
            String[] pom = reg.get(i).split("->");
            reguly.add(new Rule(pom[0], pom[1]));
        }
        start();
    }

    public static void start(){
        double x=0;
        double y=0;
        double a= 25 * 3.14159265 / 180;

        double zmianaA= 25 * 3.14159265 / 180;

        Stack<Double> xpom = new Stack<>();
        Stack<Double> ypom = new Stack<>();
        Stack<Double> apom = new Stack<>();


            int liczbaZamian = 5;
            for (int i = 0; i < liczbaZamian; i++) {
                for (int j = 0; j < reguly.size(); j++) {
                    String z = reguly.get(j).getZ();
                    String na = reguly.get(j).getNa();
                    kroki = kroki.replace(z, na);
                }
            }

        System.out.println(kroki);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("wyjscie.txt"));
            for (int i = 0; i < kroki.length(); i++) {
                switch (kroki.charAt(i)) {
                    case 'F':
                        x += Math.cos(a);
                        y += Math.sin(a);
                        writer.write(x + "," + y + "\n");
                        break;
                    case '-':
                        a-=zmianaA;
                        break;
                    case '+':
                        a+=zmianaA;
                        break;
                    case '[':
                        xpom.push(x);
                        ypom.push(y);
                        apom.push(a);
                        break;
                    case ']':
                        if (!xpom.empty()) {
                            x = xpom.peek();
                            y = ypom.peek();
                            a = apom.peek();
                            xpom.pop();
                            ypom.pop();
                            apom.pop();
                        }
                        writer.write(",\n");
                        writer.write(x + "," + y + "\n");
                        break;
                }
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
