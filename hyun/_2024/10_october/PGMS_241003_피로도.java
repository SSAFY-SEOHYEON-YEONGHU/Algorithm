package implementation;

public class PGMS_240930_줄서는방법 {
    int K;
    int[][] input;
    int answer = 0;

    public void perm(boolean[] selected, int cnt, int tired){
        answer = Math.max(answer, cnt);

        for(int i=0; i<input.length; i++){
            if(selected[i]) continue;

            if(tired >= input[i][0]){
                selected[i] = true;
                perm(selected, cnt + 1, tired - input[i][1]);
                selected[i] = false;
            }

        }
    }

    public int solution(int k, int[][] dungeons) {
        K = k;
        input = dungeons;

        perm(new boolean[dungeons.length], 0,k);

        return answer;
    }
}