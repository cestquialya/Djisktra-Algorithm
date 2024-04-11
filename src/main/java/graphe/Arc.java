package main.java.graphe;

public class Arc {

    private String source;
    private String destination;
    private int valuation;
    public Arc(String s,String d,int v){
        s=this.source;
        d=this.destination;
        v=this.valuation;

    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getValuation() {
        return valuation;
    }

    @Override
    public String toString() {
        return  source +
                "-" + destination +
                "(" + valuation +")"
               ;
    }
}
