package animals.hunger.predatorHungerModules;

import animals.Animal;
import animals.hunger.HungerModule;
import animals.predator.predators.Fox;

public class FoxHungerModule extends HungerModule {
    Fox fox;
    public FoxHungerModule(float saturation, Fox fox) {
        super(saturation, fox);
        System.out.println("Инициализация конструктора FoxHungerModule");
        this.fox = fox;
    }

    @Override
    public void gettingLessHunger(Animal animal) {
        float weight = animal.getStateAnimal().getWeight();
        if (weight >= getSaturation() || getCountKiloNeedToEat() <= 0.0) {
            setPriorityOfHunger(0);
            setCountKiloNeedToEat(0);
            System.out.println("лис полностью наелся ");
            fox.setHungry(false);
            System.out.println("лис голоден? " + fox.isHungry());

        } else {
            setCountKiloNeedToEat(getCountKiloNeedToEat() - weight);
            setPriorityOfHunger(getPriorityOfHunger() - (weight/getSaturation()));
            System.out.println("лис не наелся и все еще голоден. Нужно еще сьесть - " + getCountKiloNeedToEat() + " приоритет голода - " + getPriorityOfHunger() * 100 + " %");
            fox.setHungry(true);
            System.out.println("лис голоден? " + fox.isHungry());
        }
    }
}
