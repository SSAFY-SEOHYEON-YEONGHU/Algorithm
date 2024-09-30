package implementation;

public class PGMS_240930_줄서는방법 {
    int[] answer;
    int N;
    long K;
    long[] fac;

    public boolean perm(boolean[] selected, int digit){
        if(K==1 && digit == N){
            for(int i=1; i<=N; i++){
                if(!selected[i]){
                    answer[digit-1] = i;
                    break;
                }
            }

            return true;
        }

        for(int i=1; i<=N; i++){
            if(selected[i]) continue;
            else{
                if((K - fac[(N-digit)]) > 0){
                    K -= fac[(N-digit)];
                }
                else{
                    selected[i] = true;
                    answer[digit-1] = i;
                    if(perm(selected, digit + 1)) return true;
                }
            }

        }

        return true;
    }


    public int[] solution(int n, long k) {
        answer = new int[n];
        N = n;
        K = k;
        fac = new long[n+1];
        fac[1] = 1;
        for(int i=2; i<= n; i++) fac[i] = fac[i-1] * i;

        perm(new boolean[n+1], 1);

        return answer;
    }
}