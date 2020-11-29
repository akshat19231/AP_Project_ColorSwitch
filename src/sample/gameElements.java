package sample;

public abstract class gameElements {
    double posx;
    double posy;
    public gameElements(double x, double y){
        this.posx=x;
        this.posy=y;
    }
    public double getX(){
        return posx;
    }
    public double getY(){
        return posy;
    }
    public void collisonCheck(){

    }
    public void rotationSettings(){

    }
}
