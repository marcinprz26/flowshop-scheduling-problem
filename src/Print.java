import java.util.ArrayList;

public class Print {

    public static void printPopulation(Population population) {
        for (Permutation permutation : population.getPopulation()) {
            printPermutation(permutation);
        }
        System.out.println();
    }

    public static void printPermutation(Permutation permutation) {

//        System.out.print("[ ");
//        for (Task task : permutation.getPermutation()) {
//            System.out.print(task.getNumber() + 1 + " ");
//        }
//        System.out.print("] | ");
//        System.out.println(permutation.calculateMakespan());
        permutation.calculateMakespan();
    }

    public static void printPermutationWithOperations(Permutation permutation) {
        ArrayList<Task> perm = permutation.getPermutation();
        System.out.print("[ ");
        for (Task task : perm) {
            System.out.print(task.getNumber() + 1 + " ");
        }
        System.out.print("] | ");
        System.out.println(permutation.calculateMakespan());

        for(int i=0; i<perm.get(0).getOperations().size(); i++) {
            System.out.print("  ");
            for (Task task : perm) {
                System.out.print(task.getOperations().get(i) + " ");
            }
            System.out.println();
        }

    }

    public static void printGeneration(int generation) {
        System.out.println("-=-=-=-=-=-=-=-");
        System.out.println("\tStep " + (generation + 1));
        System.out.println("-=-=-=-=-=-=-=-\n");
    }

    public static void printMakespanWithMessage(Double average, String message) {
        System.out.println(message + average);
        System.out.println();
    }
}
