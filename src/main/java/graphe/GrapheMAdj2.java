package main.java.graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheMAdj2 extends Graphe{
    int [][] matrice ;
    List<String> som ;

    public GrapheMAdj2(){
        matrice = new int[0][0];
        som = new ArrayList<>();
    }

    public GrapheMAdj2(String str){
        this();
        this.peupler(str);
    }
    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            som.add(noeud);
            int[][] nvmatrice = new int[som.size()][som.size()];


            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice[i].length; j++) {
                    nvmatrice[i][j] = matrice[i][j];
                }
            }

            for (int i = 0; i < nvmatrice.length; i++) {
                nvmatrice[i][som.size() - 1] = 0;
                nvmatrice[som.size() -1][i] = 0;
            }

            matrice = nvmatrice;
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if(contientArc(source , destination)){
            throw new IllegalArgumentException("il existe déjà un arc entre " + source + "et" + destination);
        }
        if(valeur < 0 ){
            throw new IllegalArgumentException("La valuation ne doit pas être negative !");
        }
        if (!contientSommet(source)) {
            ajouterSommet(source);
        }
        if (!contientSommet(destination)) {
            ajouterSommet(destination);
        }
        for(int i = 0 ; i < matrice.length ; i++){
            for(int y=0 ; y < matrice.length ; y++){
                if(som.get(i).equals(source) && som.get(y).equals(destination)) {
                    matrice[i][y]= valeur;
                }

            }
        }

    }

    @Override
    public void oterSommet(String noeud) {

            if (contientSommet(noeud)) {
                int index = som.indexOf(noeud);


                for (int i = 0; i < matrice.length; i++) {
                    matrice[index][i] = 0;
                    matrice[i][index] = 0;
                }

                som.remove(noeud);
            }

    }

    @Override
    public void oterArc(String source, String destination) {
        if(!contientArc(source, destination)){
            throw new IllegalArgumentException();
        }
        for(int i = 0 ; i < matrice.length ; i++){
            for(int y = 0 ; y < matrice.length ; y++ ){
                if(som.get(i).equals(source) && som.get(y).equals(destination))  {
                    matrice[i][y] = 0;
                }

            }
        }
    }

    @Override
    public List<String> getSommets() {
        return som;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        int index = som.indexOf(sommet);
        for(int i =0 ; i < matrice.length ; i++){
            if(matrice[index][i] > 0){
                succ.add(som.get(i));


            }
        }
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        int indice_src = som.indexOf(src);
        int indice_dest = som.indexOf(dest);
        if(matrice[indice_src][indice_dest] > 0){
            return matrice[indice_src][indice_dest];
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return som.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for(int i =0 ; i < matrice.length ; i++){
            for(int y = 0 ; y < matrice.length ; y++){
                if(som.get(i).equals(src) && som.get(y).equals(dest))  {
                    if(matrice[i][y] > 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
