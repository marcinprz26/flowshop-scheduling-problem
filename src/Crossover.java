import java.util.ArrayList;

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
        ArrayList<Task> child1 = parent1.getPermutation();
        ArrayList<Task> child2 = parent2.getPermutation();

        for (int i=0; i<2; i++) {
            Task t2 = child2.get(i);
            int index = child1.indexOf(t2);
            Task t1 = child1.get(i);
            child1.set(index, t1);
            child1.set(i, t2);
        }

        parent1.setPermutation(child1);

        return parent1;
    }

    private static Permutation orderedCrossover(Permutation parent1, Permutation parent2) {
        ArrayList<Task> child1 = parent1.getPermutation();
        ArrayList<Task> child2 = parent2.getPermutation();

        parent1.setPermutation(child1);

        return parent1;
    }

    private static Permutation ownCrossover(Permutation parent1, Permutation parent2) {
        ArrayList<Task> child1 = parent1.getPermutation();
        ArrayList<Task> child2 = parent2.getPermutation();

        parent1.setPermutation(child1);

        return parent1;
    }

}
