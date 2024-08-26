package binary_serarch;

import java.io.*;
import java.util.*;

public class PGMS_43236_징검다리 {
    int[] nrocks;
    int ndis;
    int rock;
    public int func(int len){
        ArrayList<Integer> arr = new ArrayList<>();
        int before = 0;
        for(int i=1; i<nrocks.length; i++){
            if(nrocks[i] - before < len) continue;
            arr.add(nrocks[i]);
            before = nrocks[i];
        }

        int result = 0;
        if(arr.get(arr.size() - 1) != ndis) result =  arr.size() - 2;
        result = arr.size() - 1;

        System.out.println(result + " " + len);

        return rock - result;
    }


    public int solution(int distance, int[] rocks, int n) {
        rock = rocks.length;
        ndis = distance;

        nrocks = new int[rocks.length + 2];
        nrocks[0] = 0;
        nrocks[nrocks.length - 1] = distance;
        for(int i=1; i<1+rocks.length; i++){
            nrocks[i] = rocks[i-1];
            // System.out.print(nrocks[i]);
        }

        Arrays.sort(nrocks);

        int lo = 0;
        int hi = distance + 1;

        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            if(func(mid) <= n) lo = mid;
            else hi = mid;
        }

        return lo;
    }
}
