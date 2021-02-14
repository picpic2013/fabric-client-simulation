package site.grec.fabricclientsimulation.utils.hash;

import java.util.Arrays;

public class InnerHashFunction implements HashFunction {
    public int getHash(Object... objs) {
        return Arrays.hashCode(objs);
    }
}
