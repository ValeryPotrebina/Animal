package animals.hunger.animalHungerModules;

import animals.Animal;
import animals.hunger.HungerModule;
import animals.predator.predators.Wolf;

public class WolfHungerModule extends HungerModule {
    Wolf wolf;
    public WolfHungerModule(float saturation, Wolf wolf) {
        super(saturation, wolf);
        System.out.println("Инициализация конструктора WolfHungerModule");
        this.wolf = wolf;
    }

    @Override
    public void gettingLessHunger(Animal animal) {
        float weight = animal.getStateAnimal().getWeight();
        if (weight >= getSaturation() || getCountKiloNeedToEat() <= 0.0) {
            setPriorityOfHunger(0);
            setCountKiloNeedToEat(0);
            System.out.println("волк полностью наелся ");
            wolf.setHungry(false);
            System.out.println("волк голоден? " + wolf.isHungry());

        } else {
            setCountKiloNeedToEat(getCountKiloNeedToEat() - weight);
            setPriorityOfHunger(getPriorityOfHunger() - (weight/getSaturation()));
            System.out.println("волк не наелся и все еще голоден. Нужно еще сьесть - " + getCountKiloNeedToEat() + " приоритет голода - " + getPriorityOfHunger() * 100 + " %");
            wolf.setHungry(true);
            System.out.println("волк голоден? " + wolf.isHungry());
        }
    }

    public boolean isHungry(){
        return getPriorityOfHunger() != 0;
    }
}
