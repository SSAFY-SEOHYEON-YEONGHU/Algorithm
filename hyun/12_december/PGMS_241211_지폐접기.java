package implementation;

public class PGMS_241211_지폐접기 {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        int minW, maxW;
        int minB, maxB;

        while(true){
            minW = Math.min(wallet[0], wallet[1]);
            maxW = Math.max(wallet[0], wallet[1]);

            minB = Math.min(bill[0], bill[1]);
            maxB = Math.max(bill[0], bill[1]);

            if(minW >= minB && maxW >= maxB) break;

            if(bill[0] > bill[1]) bill[0] /= 2;
            else bill[1] /= 2;

            answer++;
        }

        return answer;
    }
}