package animals;

public class StateAnimal {
    private final int weight;
    private final float saturationKilos;
    private final int speed;
    private final int maxCountOnTheSameCell;

    public StateAnimal(int weight, float saturationKilos, int speed, int maxCountOnTheSameCell) {
        this.weight = weight;
        this.saturationKilos = saturationKilos;
        this.speed = speed;
        this.maxCountOnTheSameCell = maxCountOnTheSameCell;
    }

    public int getWeight() {
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

    @Override
    public String toString() {
        return "StateAnimal{" +
                "weight=" + weight +
                ", saturationKilos=" + saturationKilos +
                ", speed=" + speed +
                ", maxCountOnTheSameCell=" + maxCountOnTheSameCell +
                '}';
    }
}
