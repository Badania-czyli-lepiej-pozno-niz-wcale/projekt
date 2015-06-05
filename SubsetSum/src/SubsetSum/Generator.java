package SubsetSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Generator {
    private final ArrayList<Integer> tmp = new ArrayList<>();
    private final int size;
    private final int reach;

    public Generator(int size, int reach)    {
        this.size = size;
        this.reach = reach;
    }

    public ArrayList<Integer> generateNumbers() {
        Random randomGenerator = new Random();
        for (int i = 0; i < size; i++) {
            tmp.add(randomGenerator.nextInt(reach));
        }
        for (int j = 0; j < size/2; j++) {
            tmp.set(j, 0 - tmp.get(j));
        }
        Collections.sort(tmp);
        return tmp;
    }
}
