package animals;

import animals.herbivore.Herbivore;
import animals.herbivore.herbivores.Mouse;
import animals.herbivore.herbivores.Rabbit;
import animals.predator.Predator;
import animals.predator.predators.Eagle;
import animals.predator.predators.Fox;
import animals.predator.predators.Wolf;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        //почему я могу в травоядных добавить хищников??
//        List<Predator> predators = new ArrayList<>();
//        predators.add(new Wolf());
//        predators.add(new Fox());
//        predators.add(new Eagle());
//        List<Herbivore> herbivores = new ArrayList<>();
//        herbivores.add(new Mouse());
//        herbivores.add(new Rabbit());
//
//        for (int i = 0; i <= predators.size(); i++){
//            for (Herbivore herbivore : herbivores) {
//                predators.get(i).eat(herbivore);
//            }
//        }

        ArrayList<Wolf> wolves = Wolf.createWolf(1);

        ArrayList<Fox> foxes = Fox.createFox(1);

        ArrayList<Eagle> eagles = Eagle.createEagle(1);

        ArrayList<ArrayList<? extends Predator>> predators = new ArrayList<>();
        predators.add(wolves);
        predators.add(foxes);
        predators.add(eagles);
        for (int i = 0; i < predators.size(); i++) {
            for (int j = 0; j < predators.get(i).size(); j++) {
                predators.get(i).get(j).eat(new Rabbit());
                predators.get(i).get(j).eat(new Rabbit());
                predators.get(i).get(j).eat(new Rabbit());
                predators.get(i).get(j).eat(new Rabbit());
                predators.get(i).get(j).eat(new Rabbit());
                predators.get(i).get(j).eat(new Rabbit());

                            }

        }


    }

}
