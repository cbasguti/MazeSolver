package mazesolver;
import java.util.Stack;

public class Path {
    private Stack<Point> steps;

    public Path(){
        steps = new Stack<>();
    }

    public void pushPoint(Point n){
        steps.push(n);
    }

    public void popPoint(){
        steps.pop();
    }

    public Point peekPoint(){
        return steps.peek();
    }

    public boolean isEqualTo(Path a){
        int x1, x2, y1, y2;
        if(a.getSteps().size() == steps.size()){
            for(int i = 0; i < steps.size(); i++){
                x1 = a.getSteps().elementAt(i).getX();
                x2 = steps.elementAt(i).getX();
                if(x1 != x2){
                    return false;
                }
                y1 = a.getSteps().elementAt(i).getY();
                y2 = steps.elementAt(i).getY();
                if(y1 != y2){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    public boolean isShortest(Stack<Path> paths){
        for(Path pathN : paths){
            if(steps.size() > pathN.getSteps().size()){
                return false;
            }
        }
        return true;
    }
        
    public Stack<Point> getSteps(){
        return steps;
    }
}
