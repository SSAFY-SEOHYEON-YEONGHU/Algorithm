

public class PGMS_241007_스티커모으기 {
    int answer = 0;

    public void simulation(int start, int end, int[] dp, int[] sticker){
        for(int i=start; i<end; i++){
            if(dp[i] == 0) dp[i] = sticker[i];

            if(i+2 < end){
                if(dp[i+2] < (dp[i] + sticker[i+2])){
                    dp[i+2] = dp[i] + sticker[i+2];
                }
            }

            if(i+3 < end){
                if(dp[i+3] < (dp[i] + sticker[i+3])){
                    dp[i+3] = dp[i] + sticker[i+3];
                }
            }

            if(answer < dp[i]) answer = dp[i];
        }
    }

    public int solution(int sticker[]) {
        if(sticker.length == 1) return sticker[0];

        int[] dp = new int[sticker.length];
        int[] dp2 = new int[sticker.length];

        dp[0] = sticker[0];
        dp2[1] = sticker[1];

        simulation(0, sticker.length-1, dp, sticker);
        simulation(1, sticker.length, dp2, sticker);

        return answer;
    }
}