import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sheff
 */
public class Collatz {
    
    private static Map<Long, Integer> storage = new HashMap<>();

    /**
     * 
     * @param n must be between 1 and 1 million
     * @return cycles
     */
    public static int cycleLength(long n) {
        if (storage.containsKey(n)) {
            return storage.get(n);
        }
        
        if (n == 1) {
            storage.put(n, 1);
            return 1;
        }
        
        long i = 0;
        
        if ((n & 1) == 0){ i = n/2;}
        else { i = 3 * n + 1;}
        
        storage.put(n, cycleLength(i)+1);
        return storage.get(n);
    }

    public static int maximumCycle(int i, int j) {
        int maxCycle = 0;
        if (i < j) {
            while (i <= j) {
                int temp = cycleLength(i);
                if (maxCycle < temp) maxCycle = temp;
                i++;
            }
        } else {
            while (j <= i || j == i) {
                int temp = cycleLength(j);
                if (maxCycle < temp) maxCycle = temp;
                j++;
            }
        }
        return maxCycle;
    }
    
}
