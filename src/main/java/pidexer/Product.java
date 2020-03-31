package pidexer;

import java.util.HashMap;

public class Product {
    String SN;
    String name;
    double fett;
    double energy;
    double kolhydrat;
    double protein;
    double fiber;
    HashMap<String,Reviewer> reviewers;

    public Product(){
        SN = "";
        name = "";
        fett = 0;
        energy = 0;
        kolhydrat = 0;
        protein = 0;
        fiber = 0;
        reviewers = new HashMap<>();
    }

    public Product(String SN,
                   String name,
                   double fett,
                   double energy,
                   double protein,
                   double kolhydrat,
                   double fiber,
                   HashMap<String,Reviewer> reviewers){
        this.SN = SN;
        this.name = name;
        this.fett = fett;
        this.energy = energy;
        this.kolhydrat = kolhydrat;
        this.protein = protein;
        this.fiber = fiber;
        this.reviewers = reviewers;
    }

    public String toString(){
        return "SN: " + SN
                + ",Name: " + name
                + ",fett: " + fett
                + ", energy:" + energy
                + ", protein:" + protein
                + ", kolhydrat:" + kolhydrat
                + ", fiber:" + fiber + "\n"
                +",Reviewers:"
                + reviewers.entrySet().stream()
                .map(entry -> entry.getValue()
                        .toString()).reduce("",(result,entry)->{ return result +"\n"+ entry; });
    }
}
