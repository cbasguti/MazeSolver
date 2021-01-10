package mazesolver;

public class Mice {
    private Point position;
    private String[][] maze;
    private boolean arrive;
    private String[] around;
    private Path path;

    public Mice(String[][] maze){
        this.maze = maze;
        position = startPoint();
        around = new String[4];
        arrive = false;
        fillAround();
    }

    public Path getPath(){
        return path;
    }

    public void findPaths(){
        Path n = new Path();
        searchPath(n);
        path = n;
    }

    public void searchPath(Path n){
        while(!arrive){
            while(canMove()){
                if(arrive){
                    break;
                }
                goForward(n);
            }
            while(!canMove()){
                n.popPoint();
                goBackward(n);
            }
        }
        arrive = false;
        position = startPoint();
    }

    public boolean move(int x, int y, Path n){
        Point newPos;
        if(validPoints(x, y)){
            if(maze[y][x] != "i"){
                if(maze[y][x] == "0"){
                    maze[y][x] = "S";
                    newPos = new Point(x, y);
                    n.pushPoint(newPos);
                    position = newPos;
                    fillAround();
                    return true;
                }else if(maze[y][x] == "f"){
                    newPos = new Point(x, y);
                    n.pushPoint(newPos);
                    position = newPos;
                    fillAround();
                    arrive = true;
                    return true;
                }
            }
        }
        return false;
    }

    public void goBackward(Path n){
        Point newPos;
        int x, y;
        x = position.getX();
        y = position.getY();
        maze[y][x] = "X";
        x = n.peekPoint().getX();
        y = n.peekPoint().getY();
        newPos = new Point(x, y);
        position = newPos;
        fillAround();
    }

    public boolean goForward(Path n){
        int x, y, z;
        boolean moved = false;
        while(!moved){
            z = (int)(Math.random()*(4)+1);
            switch(z){
                case 1:
                    x = position.getX();
                    y = (position.getY() + 1);
                    if(move(x, y, n)){
                        moved = true;
                        return moved;
                    }
                    break;
                case 2:
                    x = (position.getX() - 1);
                    y = position.getY();
                    if(move(x, y, n)){
                        moved = true;
                        return moved;
                    }
                    break;
                case 3:
                    x = position.getX();
                    y = (position.getY() - 1);
                    if(move(x, y, n)){
                        moved = true;
                        return moved;
                    }
                    break;
                case 4:
                    x = (position.getX() + 1);
                    y = position.getY();
                    if(move(x, y, n)){
                        return true;
                    }
                    break;
                default:                        
                    break;

            }
        }
        return false;
    }

    public boolean canMove(){
        for(int i = 0; i < around.length; i++){
            if(around[i] != null && around[i] != "i")
            {
                if(around[i] == "0"){
                    return true;
                }else if(around[i] == "f"){
                    return true;
                }
            }
        }
        return false;
    }

    public void fillAround(){
        int x, y;
        x = position.getX();
        y = (position.getY() + 1);
        if(validPoints(x, y)){
            around[0] = maze[y][x];
        }
        x = (position.getX() - 1);
        y = position.getY();
        if(validPoints(x, y)){
            around[1] = maze[y][x];
        } 
        x = position.getX();
        y = (position.getY() - 1);
        if(validPoints(x, y)){
            around[2] = maze[y][x];
        } 
        x = (position.getX() + 1);
        y = position.getY();
        if(validPoints(x, y)){
            around[3] = maze[y][x];
        } 
    }

    public boolean validPoints(int x, int y){
        if((x >= 0) && (y >= 0) && (y < maze.length) && (x < maze[0].length)){
            return true;
        } 
        return false;
    }

    public Point startPoint(){
        for (int i = 0; i < maze.length; i++) { 
            for (int j = 0; j < maze[i].length; j++) {
                if(maze[i][j] == "i"){
                    Point inicio = new Point(j,i);
                    return inicio;
                }
            }
        }
        return new Point(1,0);
    }

    public Point getPosition(){
        return position;
    }
}
