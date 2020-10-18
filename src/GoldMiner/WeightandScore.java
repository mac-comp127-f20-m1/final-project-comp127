package GoldMiner;

public class WeightandScore {
    public double weight;
    public double score;
    // The weight and the score of each gold 

    public double radius;
      /**
     * Creates a new mineral
     * @param weight     The weight of the mineral
     * @param score   The score of the mineral
     * @param rating    The radius of the mineral
     */
    public WeightandScore(double weight,double score,double radius){
             this.weight=weight;
             this.score=score;
             this.radius=radius;
    }

    public double getWeight(){
        return weight;
    }
    
    public double getScore(){
        return score;
    }

    public double getRadius(){
        return radius;
    }
    
}
