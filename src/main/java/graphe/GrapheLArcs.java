package main.java.graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs extends Graphe {
 private Arc[] a;
 private  List<String> som;
 public GrapheLArcs(){
    a = new Arc[0];
    som = new ArrayList<>();
 }


 @Override
 public void ajouterSommet(String noeud) {
     if(!contientSommet(noeud)){
         System.out.print("Ce noeud existe deja !");
         return;
         //faire peut etre un throw

    }
    som.add(noeud);

 }

 @Override
 public void ajouterArc(String source, String destination, Integer valeur) {
    Arc [] new_a = new Arc[a.length + 1];
    for(int i=0 ; i < a.length ; i++)
        new_a[i] = a[i];
    if(contientSommet(source) && contientSommet(destination)){
        new_a[a.length+1] = new Arc(source , destination , valeur);
    }
    a = new_a;

 }

 @Override
 public void oterSommet(String noeud) {
     assert(contientSommet(noeud));
     //faire throw au lieu d'assert
     som.remove(noeud);
     for(Arc ax : a)
         if(ax.getSource().contentEquals(noeud))
             ax = new Arc("0" , "0", 0);
         else if(ax.getSource().contentEquals(noeud) || ax.getDestination().contentEquals(noeud))
             ax = new Arc("0" , "0" , 0);

 }

 @Override
 public void oterArc(String source, String destination) {
    assert(contientSommet(source) & contientSommet(destination));
    //faire throw au lieu d'assert
    for(Arc ax : a)
         if(contientArc(source, destination))
             ax = new Arc("0" , "0", 0);

 }

 @Override
 public List<String> getSommets() {
     return som;
 }

 @Override
 public List<String> getSucc(String sommet) {
     List<String> succ = new ArrayList<>();
      for(Arc ax : a )
          if(ax.getSource().contentEquals(sommet))
              succ.add(ax.getDestination());
      return succ;
 }

 @Override
 public int getValuation(String src, String dest) {
     int val = -1;
     for(Arc ax : a)
         if(contientArc(src, dest))
            val = ax.getValuation();

     return val;
 }

 @Override
 public boolean contientSommet(String sommet) {
     return som.contains(sommet);
 }

 @Override
 public boolean contientArc(String src, String dest) {
     for(Arc ax : a)
         if(ax.getSource().contentEquals(src) && ax.getDestination().contentEquals(dest))
             return true;
     return false;
 }
}