package dfs;

public class PGMS_250606_비밀코드해독 {
    int answer = 0;
    boolean[] number;

    public void dfs(int idx, int cnt,int n, int[][] q, int[] ans){
        if(cnt == 5){
            for(int i=0; i<q.length; i++){
                int[] cur = q[i];

                int same = 0;
                for(int su : cur) if(number[su]) same++;
                if(same != ans[i]){
                    return;
                }
            }

            answer++;

            return;
        }

        for(int i = idx; i <= n; i++){
            number[i] = true;
            dfs(i+1, cnt+1, n, q, ans);
            number[i] = false;
        }
    }

    public int solution(int n, int[][] q, int[] ans) {
        number = new boolean[n+1];
        dfs(1,0,n,q,ans);

        return answer;
    }
}