package september.week2;

public class PGMS_240909_최고의집합 {

    public int[] solution(int n, int s) {
        int[] answer = findBestSet(n, s);


        return answer;
    }

    int[] findBestSet(int n, int s) {
        int div = s / n;    // 몫
        if (div == 0) return new int[] {-1};    // 몫이 0이면 자연수 n개를 이용해서 s를 만들 수 없음.

        int mod = s % n;    // 나머지
        int[] answer = new int[n];
        for (int i = n-1; i >= 0; i--) {   // 기본적으로는 n개를 몫만큼 뽑고, 거기에다가 필요한 나머지만큼을 골고루 분포시키면 됨.
            if (i >= n - mod) answer[i] = div+1; // n에서 나머지를 뺀 것보다 크거나 같은 수까지는 몫에 1을 더해서 넣어줌
            else answer[i] = div; // n에서 나머지를 뺀 것보다 작다면 몫을 넣어줌(예를 들어, n=4이고 s가 10이라면 2, 2, 3, 3이 됨)
        }

        return answer;
    }

}
