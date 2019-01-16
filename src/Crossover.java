import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Crossover {

    public static final int PMXCROSSOVER = 0;
    public static final int ORDEREDCROSSOVER = 1;
    public static final int OWNCROSSOVER = 2;

    public static ArrayList<Permutation> Crossover(Permutation parent1, Permutation parent2, int crossoverType) {
        ArrayList<Permutation> children = new ArrayList<>();
        switch (crossoverType) {
            case PMXCROSSOVER:
                children.add(PMXCrossover(parent1, parent2));
                children.add(PMXCrossover(parent2, parent1));
                break;
            case ORDEREDCROSSOVER:
                children.add(orderedCrossover(parent1, parent2));
                children.add(orderedCrossover(parent2, parent1));
                break;
            case OWNCROSSOVER:
                children.add(ownCrossover(parent1, parent2));
                children.add(ownCrossover(parent2, parent1));
                break;
            default:
                children.add(PMXCrossover(parent1, parent2));
                children.add(PMXCrossover(parent2, parent1));
        }

        return children;
    }

    private static Permutation PMXCrossover(Permutation parent1, Permutation parent2) {
        ArrayList<Task> child1 = new ArrayList<>(parent1.getPermutation());
        ArrayList<Task> child2 = new ArrayList<>(parent2.getPermutation());

        for (int i=0; i<2; i++) {
            Task t2 = child2.get(i);
            int index = child1.indexOf(t2);
            Task t1 = child1.get(i);
            child1.set(index, t1);
            child1.set(i, t2);
        }

        return new Permutation(child1);
    }

    private static Permutation orderedCrossover(Permutation parent1, Permutation parent2) {
        ArrayList<Task> child1 = new ArrayList<>();
        ArrayList<Task> child2 = new ArrayList<>();

        int cutStart = ThreadLocalRandom.current().nextInt(0, (parent1.getPermutation().size() - 1));
        int cutEnd = ThreadLocalRandom.current().nextInt(cutStart, (parent1.getPermutation().size() - 1));

        return new Permutation(child1);
    }

    private static Permutation ownCrossover(Permutation parent1, Permutation parent2) {
        ArrayList<Task> child = new ArrayList<>();
        ArrayList<Task> parent1Perm = parent1.getPermutation();
        ArrayList<Task> parent2Perm = parent2.getPermutation();

        int position = ThreadLocalRandom.current().nextInt(1, (parent1.getPermutation().size() / 2) + 1);
        for(int i=0; i<position; i++) {
            child.add(parent1Perm.get(i));
        }
        while(child.size() != parent1Perm.size()) {
            Task task = parent2Perm.get(position);
            if(!child.stream().filter(t -> t.equals(task)).findFirst().isPresent()) {
                child.add(task);
            }
            position = (position + 1) >= parent1Perm.size() ? 0 : position + 1;
        }

        return new Permutation(child);
    }

}
