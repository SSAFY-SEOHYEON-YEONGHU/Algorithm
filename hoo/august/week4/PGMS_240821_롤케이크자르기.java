package august.week4;

public class PGMS_240821_롤케이크자르기 {

    boolean[][] isUsed;
    int[][] toppingCounts;    // 인덱스마다 사용된 토핑 종류 카운트하고 저장할 배열

    public int solution(int[] topping) {
        init(topping);

        return countCase(topping);
    }

    void init(int[] topping) {
        int l = topping.length;

        isUsed = new boolean[2][10_001];   // 최대 10,000개의 토핑에 대해 사용여부 체크
        toppingCounts = new int[2][l]; // 0: 앞에서 카운트한 경우, 1: 뒤에서 카운트한 경우
        toppingCounts[0][0] = 1;
        isUsed[0][topping[0]] = true;
        toppingCounts[1][l-1] = 1;
        isUsed[1][topping[l-1]] = true;

        for (int i = 1; i < l; i++) {
            checkTopping(topping, topping[i], i, 0);
            checkTopping(topping, topping[l-i-1], l-i-1, 1);
        }
    }

    void checkTopping(int[] topping, int nowTopping, int index, int mode) { // mode => 0: 정방향, 1: 역방향
        int beforeIndex;
        if (mode == 0) beforeIndex = index - 1;
        else beforeIndex = index + 1;

        if (isUsed[mode][nowTopping]) toppingCounts[mode][index] = toppingCounts[mode][beforeIndex];
        else {
            isUsed[mode][nowTopping] = true;
            toppingCounts[mode][index] = toppingCounts[mode][beforeIndex] + 1;
        }
    }

    int countCase(int[] topping) {
        int answer = 0;
        for (int i = 0; i < topping.length-1; i++) {
            if (toppingCounts[0][i] == toppingCounts[1][i+1]) answer++;
        }

        return answer;
    }

}
