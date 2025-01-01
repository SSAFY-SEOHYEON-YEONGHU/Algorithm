package dfs;

import java.io.*;
import java.util.*;
public class BOJ_250101_소트게임 {
    static int N,K;
    static String input = "";
    static HashMap<String, Integer> hmap = new HashMap<>();
    static int answer = Integer.MAX_VALUE;
    static boolean isAscending(String numbers){
        boolean isTrue = true;
        for (int i = 0; i < N-1; i++) {
            if(numbers.charAt(i) > numbers.charAt(i+1)){
                isTrue = false;
                break;
            }
        }

        return isTrue;
    }

    static String change(String num, int idx){
        int left = idx;
        int right = idx + K-1;

        char[] tmp = num.toCharArray();
        while(left < right){
            char a = tmp[left];
            char b = tmp[right];

            tmp[right] = a;
            tmp[left] = b;

            left++;
            right--;
        }

        String result = "";
        for(char c : tmp) result += c;

        return result;
    }

    static void bfs(){
        Queue<String> q = new ArrayDeque<>();
        HashMap<String, Integer> visited = new HashMap<>();

        q.add(input);
        visited.put(input, 0);

        int depth = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for (int s = 0; s < size; s++) {
                String cur = q.poll();
                if(isAscending(cur)){
                    System.out.println(depth-1);
                    return;
                }

                for (int i = 0; i <= N-K; i++) {
                    String newNum = change(cur, i);
                    if(hmap.containsKey(newNum)) continue;

                    q.add(newNum);
                    hmap.put(newNum, depth);
                }
            }
            depth++;
        }

        System.out.println(-1);
    }
    static void dfs(int cnt, String numbers ){
        //System.out.println(numbers);
        String tmp = numbers;

        if(hmap.containsKey(numbers)){
            if(isAscending(numbers)){
                answer = Math.min(answer, cnt);
                return;
            }
            if(hmap.get(numbers) <= cnt) return;
        }
        else hmap.put(numbers, cnt);

        for (int i = 0; i <= N-K; i++) {
            String changeNum = change(numbers, i);
            if(hmap.containsKey(changeNum)){
                if(hmap.get(changeNum) > cnt+1) dfs(cnt + 1, changeNum);
            }
            else dfs(cnt+1, changeNum);


        }


    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        String numbers = "";
        for (int i = 0; i < N; i++) {
            numbers += st.nextToken();
        }

        input = numbers;

        //dfs(0,numbers);
        bfs();
//        System.out.println(answer);
    }
}
