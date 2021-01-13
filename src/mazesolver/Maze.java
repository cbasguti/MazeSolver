package mazesolver;
import java.util.Stack;

public class Maze {
    private Stack<Path> paths;
    private final Stack<String> mazeFile;
    
    public Maze(Stack<String> mazeLines){
        paths = new Stack<>();
        mazeFile = mazeLines;
        findAllPaths();
        cleanPaths();
    }

    public String[][] createMaze(Stack<String> mazeLines){
        int x,y;
        x = mazeLines.elementAt(0).length();
        y = mazeLines.size();
        String[][] maze = new String[y][x];
        Stack<String> chars = new Stack<>();
        Stack<String> temp = new Stack<>();
        for(int i = 0; i < mazeLines.size(); i++){
            for(int j = 0; j < mazeLines.elementAt(i).length(); j++){
                String temp2 = mazeLines.elementAt(i).substring(j, j + 1);
                chars.push(temp2);
            }
        }
        while(!chars.empty()){
            temp.push(chars.pop());
        }
        for(int i = 0; i < y;i++){
            for(int j = 0; j < x;j++){
                String a = temp.peek();
                switch(a){
                    case "0":
                        maze[i][j] = "0";
                        break;
                    case "1":
                        maze[i][j] = "1";
                        break;
                    case "i":
                        maze[i][j] = "i";
                        break;
                    case "f":
                        maze[i][j] = "f";
                        break;
                    default:
                        System.out.println("a");
                        break;         
                }
                temp.pop();    
            }
        }
        return maze;
    }
    
    public void cleanPaths(){
        Stack<Path> totalPath;
        totalPath = new Stack<>();
        totalPath.push(paths.pop());
        for (Path pathN : paths) {
            Path a = pathN;
            if(!containPath(a, totalPath)){
                totalPath.push(a);
            }
        }
        paths = totalPath;
    }

    public boolean containPath(Path b, Stack<Path> a){
        for (Path pathN : a) {
            if(pathN.isEqualTo(b)){
                return true;
            }
        }
        return false;
    }

    public Stack<Path> getPaths(){
        return paths;
    }

    public void findAllPaths(){
        for(int i = 0; i < 200; i++){
            String[][] maze = createMaze(mazeFile);
            Mice stuart = new Mice(maze);
            stuart.findPaths();
            paths.push(stuart.getPath());
        }
    }

    public void showPaths(){
        for (int i = 0 ; i < paths.size(); i++) {
            printPath(paths.elementAt(i),i);
        }
    }
    
    public void showPaths(int a){
        printPath(paths.elementAt(a), a);
    }

    public void printMazeColor(String[][] a){
        final String CYAN = "\u001B[36m";
        final String MORADO = "\u001B[35m";
        final String RESET = "\u001B[0m";
        final String BLANCO = "\u001B[37m";
        final String AMARILLO = "\u001B[33m";
        for (int i = 0; i < a.length; i++) { 
            for (int j = 0; j < a[i].length; j++) {
                if(a[i][j]=="0"){
                    System.out.print(BLANCO + a[i][j] + RESET + " ");
                }else if(a[i][j]=="1"){
                    System.out.print(MORADO + a[i][j] + RESET + " ");
                }else if(a[i][j]=="2"){
                    System.out.print(CYAN + a[i][j] + RESET + " ");
                }else if(a[i][j]=="i" || a[i][j]=="f"){
                    System.out.print(AMARILLO + a[i][j] + RESET + " ");
                }else{
                    System.out.print(a[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public void printMaze(String[][] a){
        for (int i = 0; i < a.length; i++) { 
            for (int j = 0; j < a[i].length; j++) {
            	System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void shortestPaths(){
        for (int i = 0 ; i < paths.size(); i++) {
            if(paths.elementAt(i).isShortest(paths)){
                printPath(paths.elementAt(i),i);
            }
        }
    }
    
    public void printPath(Path path, int a){
        int x, y;
        System.out.println();
        System.out.println("Camino "+ (a+1));
        System.out.println();
        String[][] maze = createMaze(mazeFile);
        for(int i = 0; i < path.getSteps().size(); i++){
            x = path.getSteps().elementAt(i).getX();
            y = path.getSteps().elementAt(i).getY();
            if(maze[y][x] != "f"){
                maze[y][x] = "2";
            } 
        }
        printMazeColor(maze);
    }
}
