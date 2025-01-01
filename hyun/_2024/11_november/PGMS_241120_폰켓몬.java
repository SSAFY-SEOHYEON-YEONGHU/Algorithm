package implementation;

import java.util.*;

public class PGMS_241120_폰켓몬 {
    public int solution(int[] nums) {

        Set<Integer> s = new HashSet<>();
        for(int su : nums) s.add(su);

        return s.size() < nums.length / 2 ? s.size() : nums.length / 2;
    }
}