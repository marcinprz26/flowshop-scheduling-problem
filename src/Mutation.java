import java.util.ArrayList;
import java.util.Random;

public class Mutation {

    public static Permutation mutation(Permutation permutation) {
        Random random = new Random();
        ArrayList<Task> perm = permutation.getPermutation();
        int pos1 = random.nextInt(perm.size()),
                pos2 = random.nextInt(perm.size());
        Task task1 = perm.get(pos1),
                task2 = perm.get(pos2);
        perm.set(pos1, task2);
        perm.set(pos2, task1);
        permutation.setPermutation(perm);

        return permutation;
    }
}
