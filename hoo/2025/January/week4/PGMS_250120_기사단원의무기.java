package twentytwentyfive.january.week4;

public class PGMS_250120_기사단원의무기 {

    public int solution(int number, int limit, int power) {
        int answer = 0;
        int[] divisorCount = makeDivisorCount(number);
        for (int i = 1; i <= number; i++) answer += (divisorCount[i] <= limit)? divisorCount[i]:power;

        return answer;
    }

    int[] makeDivisorCount(int number) {  // 약수 개수를 저장한 배열을 만드는 함수
        int[] divisorCount = new int[number+1];
        divisorCount[1] = 1;
        for (int i = 2; i <= number; i++) {
            int count = 0;
            for (int j = 1; j <= Math.sqrt(i); j++) if (i % j == 0) count += (j==Math.sqrt(i))? 1:2;
            divisorCount[i] = count;
        }
        // System.out.println(Arrays.toString(divisorCount));

        return divisorCount;
    }

}
