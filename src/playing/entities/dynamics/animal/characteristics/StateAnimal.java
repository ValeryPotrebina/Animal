package playing.entities.dynamics.animal.characteristics;

public class StateAnimal {
    private final float weight;
    private final float saturationKilos;
    private final int speed;
    private final int maxCountOnTheSameCell;
    private final int range;

    public StateAnimal(float weight, float saturationKilos, int speed, int maxCountOnTheSameCell, int range) {
        this.weight = weight;
        this.saturationKilos = saturationKilos;
        this.speed = speed;
        this.maxCountOnTheSameCell = maxCountOnTheSameCell;
        this.range = range;
    }

    public float getWeight() {
        return weight;
    }

    public float getSaturationKilos() {
        return saturationKilos;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxCountOnTheSameCell() {
        return maxCountOnTheSameCell;
    }

    public int getRange() {
        return range;
    }

//    @Override
//    public String toString() {
//        return "StateAnimal{" +
//                "weight=" + weight +
//                ", saturationKilos=" + saturationKilos +
//                ", speed=" + speed +
//                ", maxCountOnTheSameCell=" + maxCountOnTheSameCell +
//                '}';
//    }
}
