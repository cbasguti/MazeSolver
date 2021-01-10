package mazesolver;
import java.io.*;
import java.util.*;

public class Program{
    
    public static Stack<String> mazeLines = new Stack<>();
    
    public static void main (String[] args){
        start();  
    };

    public static void print(Object x){
        System.out.println(x);
    }
    
    public static void start(){
        readFile("laberinto.txt");
        Maze a = new Maze(mazeLines);
        boolean continuar;
        continuar = false;
        print("= = = = = = = = = = = = = = = = =");
        print("             MAZE                ");
        print("            SOLVER               ");
        print("= = = = = = = = = = = = = = = = =");
        print("");
        print("Se han encontrado " + a.getPaths().size() + " soluciones");
        print("para el laberinto: ");
        while(!continuar){
            print("");
            print("= = M E N U  P R I N C I P A L = =");
            print("Escoja como desea visualizarlas: ");
            print("> 1. Ver todas.");
            print("> 2. Ver una solucion.");
            print("> 3. Ver la mas corta.");
            print("> 4. Salir.");
            switch(leerInt("Ingrese el numero de la accion: ")){
                case 1:
                    print("");
                    a.showPaths();
                    break;
                case 2:
                    print("");
                    a.showPaths(leerInt("Digite el numero de la solucion que desea ver:")-1);
                    break;
                case 3:
                    print("");
                    print("El camino mas corto es: ");
                    a.shortestPaths();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    print("Opcion invalida. Intente de nuevo");
                    break;
            }
        }
    }
    
    public static void readFile(String filepath){
        try{
            BufferedReader bf = new BufferedReader(new FileReader(filepath));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                temp = bfRead.replaceAll(" ","");
                mazeLines.push(temp);
            }
        }catch(Exception e){
            print("File not found");
        }
    }
    
    public static int leerInt(String mensaje){
        System.out.print(mensaje);
        Scanner leer = new Scanner (System.in);
        int x;     
        x = leer.nextInt();
        return x;
    }
}