
public class Print {

    public static void printPopulation(Population population) {
        for (Permutation permutation : population.getPopulation()) {
            printPermutation(permutation);
        }
        System.out.println();
    }

    public static void printPermutation(Permutation permutation) {

        System.out.print("[ ");
        for (Task task : permutation.getPermutation()) {
            System.out.print(task.getNumber() + " ");
        }
        System.out.print("] | ");
        System.out.println(permutation.calculateMakespan());
    }
}
