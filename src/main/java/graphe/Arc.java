package main.java.graphe;

public class Arc {

    private String source;
    private String destination;
    private int valuation;
    public Arc(String s,String d,int v){
        if(v<0){
            throw new IllegalArgumentException();
        }
        this.source=s;
        this.destination=d;
        this.valuation=v;

    }
    //commentaire
    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getValuation() {
        return valuation;
    }




}
