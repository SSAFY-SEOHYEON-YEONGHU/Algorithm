package math;

import java.io.*;
import java.util.*;
public class BOJ_241118_다이어트 {

    public static void getAnswer(int n){
        int end = (int)Math.sqrt(n);
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 1; i <= end ; i++) {
            if(n%i != 0 || i == (n/i)) continue;
            double su = (i+(double)(n/i))/2.0;
            if(su % 1 == 0.0) ans.add((int)su);
        }

        if(ans.size() == 0) System.out.println(-1);
        else{
            Collections.sort(ans);
            for(int su : ans) System.out.println(su);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        getAnswer(N);
    }
}
