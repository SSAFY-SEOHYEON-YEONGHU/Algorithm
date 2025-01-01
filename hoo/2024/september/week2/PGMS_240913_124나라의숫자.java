package september.week2;

public class PGMS_240913_124나라의숫자 {

    public String solution(int n) {
        StringBuilder answer = new StringBuilder();

        while (n > 0) {
            int num = n%3;
            if (num == 0) {
                answer.insert(0, "4");
                n = n/3 - 1;
            }
            else {
                answer.insert(0, Integer.toString(num));
                n = n/3;
            }

        }

        return answer.toString();
    }

}
