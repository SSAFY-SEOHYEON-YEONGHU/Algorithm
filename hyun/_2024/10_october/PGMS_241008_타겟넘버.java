package dfs;

public class PGMS_241008_타겟넘버 {
    int answer = 0;
    int[] input;
    int tgt;

    public void dfs(int cnt, int num){
        if(cnt == input.length){
            if(num == tgt) answer++;
            return;
        }

        dfs(cnt + 1, num - input[cnt]);

        dfs(cnt + 1, num + input[cnt]);

    }

    public int solution(int[] numbers, int target) {
        input = numbers;
        tgt = target;

        dfs(0,0);

        return answer;
    }
}