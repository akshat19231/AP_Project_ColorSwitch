package sample;

public class smallBalls extends Ball{
    int vx;
    public smallBalls(int v, double x, double y, int vx) {
        super(v, x, y);
        this.vx=vx;

    }
    public void setVx(int x){
        this.vx=x;
    }
    public int getVx(){
        return this.vx;
    }
}
