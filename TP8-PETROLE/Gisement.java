public class Gisement{
    public int x1;
    public int x2;
    public int y;
    public int qt;

    public Gisement(int x1, int x2, int y){
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
        qt = x1 - x2;
        if(qt < 0)qt = -qt;
    }
}