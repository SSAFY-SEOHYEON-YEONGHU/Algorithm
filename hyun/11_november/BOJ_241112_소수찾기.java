package dfs;

import java.util.*;

public class BOJ_241112_소수찾기 {
    int N;
    String input;
    int answer = 0;
    Set<Integer> prime = new HashSet<>();

    public void checkPrime(String made){

        if(made == "") return;
        int num = Integer.parseInt(made);

        if(prime.contains(num)) return;

        if(num <= 1) return;
        if(num == 2) {
            answer++;
            prime.add(num);
            return;
        }
        if(num % 2 == 0) return;

        int end = (int)Math.sqrt(num);
        for(int i=3; i<= end ; i++){
            if(num % i == 0) return;
        }
        answer++;
        prime.add(num);
    }

    public void comb(boolean[] visited, String made, int cnt){
        // 소수 확인
        checkPrime(made);

        if(cnt == N) return;

        for(int i=0; i<N; i++){
            if(visited[i]) continue;

            visited[i] = true;
            comb(visited, made + input.charAt(i) , cnt + 1);
            visited[i] = false;
        }
    }
    public int solution(String numbers) {
        N = numbers.length();
        input = numbers;

        comb(new boolean[N], "", 0);

        return answer;
    }
}