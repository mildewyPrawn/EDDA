import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static String lector (String file) throws IOException{
        FileReader fr = null;
        BufferedReader br = null;
        String finale = "";
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String cadena;
            while((cadena = br.readLine()) != null)
                finale += cadena;
        }catch(IOException ioe){
            System.out.println("Error en la lectura del archivo");
            System.exit(1);
        }
        return finale;
    }
    
    public static void main(String []args) {
        try {
            SplayTree st = new SplayTree();
            String s = lector(args[0]);
            String[] e = s.split(",");
            ArrayList<Integer> elems = new ArrayList<>();
            for (String tni : e) {
                elems.add(Integer.parseInt(tni));
                st.insert(Integer.parseInt(tni));
            }
            // splay inicial
            st.prettyPrint();
            Collections.sort(elems);
            // primer paso, cadena derecha
            for (int i : elems) {
                st.searchTree(i);
                st.prettyPrint();
            }

            System.out.println(elems);
            int n = (elems.size())/2; // fórmula n/2
            int ini = n; 
            System.out.println(">>>>" + elems.size());
            System.out.println(">>>>N" + n);
            int less = 0; // los que vamos a ir restando
            int aux = 3; // el más grande entre cada 4 elementos
            int i = 0; // le sacamos el módulo para saber en qué caso cae
            int count = 0; // banderita que nos dice si podemos avanzar al siguiente mayor
            // mientras n/2 sea distinto del mayor+1
            while (ini != 1) {
                // System.out.println(elems.get(ini));
                // buscamos ese elemento
                ini = n; // n/2
                int dis = 0; // la constante que le vamos a sumar
                if (i%4 == 0) { // caso en el que restamos, inicia con 0
                    dis = less;
                    less--; // vamos a ir restando uno siempre que lo contamos
                } else if (i%4 == 1 || i%4 == 3) { // caso de los dos extremos
                    dis = aux - 1;  // le restamos al más grande de los 4 elementos
                    if (count%2 == 1) { // bandera para saber si es último chico
                        aux++;
                    }
                    count++;
                } else {
                    dis = aux; // el más grande
                }
                i++;
                ini += dis; // actualizar ini
                st.searchTree(elems.get(ini-1));
            }
            // tomamos el último
            st.searchTree(elems.get(elems.size()-1));
            st.prettyPrint();
        } catch (IOException ioe) {}
    }
}
