package animals.animalsModules.hunger.predatorHungerModules;

import animals.Animal;
import animals.animalsModules.hunger.HungerModule;
import animals.predator.predators.Eagle;

public class EagleHungerModule extends HungerModule {
    Eagle eagle;
    public EagleHungerModule(float saturation, Eagle eagle) {
        super(saturation, eagle);
        System.out.println("Инициализация конструктора EagleHungerModule");
        this.eagle = eagle;
    }

    @Override
    public void gettingLessHunger(Animal animal) {
        float weight = animal.getStateAnimal().getWeight();
        if (weight >= getSaturation() || getCountKiloNeedToEat() <= 0.0) {
            setPriorityOfHunger(0);
            setCountKiloNeedToEat(0);
            System.out.println("ястреб полностью наелся ");
            eagle.setHungry(false);
            System.out.println("ястреб голоден? " + eagle.isHungry());

        } else {
            setCountKiloNeedToEat(getCountKiloNeedToEat() - weight);
            setPriorityOfHunger(getPriorityOfHunger() - (weight/getSaturation()));
            System.out.println("ястреб не наелся и все еще голоден. Нужно еще сьесть - " + getCountKiloNeedToEat() + " приоритет голода - " + getPriorityOfHunger() * 100 + " %");
            eagle.setHungry(true);
            System.out.println("ястреб голоден? " + eagle.isHungry());
        }
    }
}
