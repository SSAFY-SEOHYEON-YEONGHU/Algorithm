package september.week3;

public class PGMS_240917_마법의엘리베이터 {

    public int solution(int storey) {
        int answer = calcMinCount(storey);

        return answer;
    }

    int calcMinCount(int storey) {
        int answer = 0;
        int n;
        while (storey != 0) {
            n = storey % 10;

            if (n >= 6) {
                storey += 10 - n;
                answer += 10 - n;
            } else if (n == 5 && (storey / 10) % 10 >= 5) {
                storey += 10 - n;
                answer += 10 - n;
            } else {
                answer += n;
                storey = storey / 10;
            }

        }

        return answer;
    }

}
