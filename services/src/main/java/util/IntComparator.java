package util;

import java.util.Comparator;

/**
 * Created by hirs akeaksandr on 25.04.15.
 */

public class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return (o1>o2 ? 1 : (o1==o2 ? 0 : -1));
    }
}