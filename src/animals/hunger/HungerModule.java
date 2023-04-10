package animals.hunger;

import animals.Animal;

public abstract class HungerModule {
    private float priorityOfHunger;
    private float countKiloNeedToEat;
    private final float saturation;

    public HungerModule(float saturation, Animal animal) {
        this.saturation = saturation;
        countKiloNeedToEat = saturation;
        priorityOfHunger = 1.0f;
        System.out.println("Инициализация конструктора HungerModule");
    }

    public abstract void gettingLessHunger(Animal animal);

    public float getPriorityOfHunger() {
        return priorityOfHunger;
    }

    public float getCountKiloNeedToEat() {
        return countKiloNeedToEat;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setPriorityOfHunger(float priorityOfHunger) {
        this.priorityOfHunger = priorityOfHunger;
    }

    public void setCountKiloNeedToEat(float countKiloNeedToEat) {
        this.countKiloNeedToEat = countKiloNeedToEat;
    }
}
