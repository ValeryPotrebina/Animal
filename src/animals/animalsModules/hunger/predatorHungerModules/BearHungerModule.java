package animals.animalsModules.hunger.predatorHungerModules;

import animals.Animal;
import animals.animalsModules.hunger.HungerModule;
import animals.predator.predators.Bear;

public class BearHungerModule extends HungerModule {

    Bear bear;
    public BearHungerModule(float saturation, Bear bear) {
        super(saturation, bear);
        System.out.println("Инициализация конструктора BearHungerModule");
        this.bear = bear;
    }

    @Override
    public void gettingLessHunger(Animal animal) {
        float weight = animal.getStateAnimal().getWeight();
        if (weight >= getSaturation() || getCountKiloNeedToEat() <= 0.0) {
            setPriorityOfHunger(0);
            setCountKiloNeedToEat(0);
            System.out.println("медведь полностью наелся ");
            bear.setHungry(false);
            System.out.println("медведь голоден? " + bear.isHungry());

        } else {
            setCountKiloNeedToEat(getCountKiloNeedToEat() - weight);
            setPriorityOfHunger(getPriorityOfHunger() - (weight/getSaturation()));
            System.out.println("медведь не наелся и все еще голоден. Нужно еще сьесть - " + getCountKiloNeedToEat() + " приоритет голода - " + getPriorityOfHunger() * 100 + " %");
            bear.setHungry(true);
            System.out.println("медведь голоден? " + bear.isHungry());
        }
    }
}
