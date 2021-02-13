package site.grec.fabricclientsimulation.utils.hash;

import java.util.Arrays;

public class HashFunction {
    public static int getHash(Object... objs) {
        return Arrays.hashCode(objs);
    }
}
