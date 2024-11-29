package math;

public class PGMS_241129_콜라문제 {
    public int solution(int a, int b, int n) {
        int answer = 0;

        while(n >= a){
            System.out.println(n);
            answer += (n/a) * b;
            n = (n/a) * b + (n%a);
        }

        return answer;
    }
}