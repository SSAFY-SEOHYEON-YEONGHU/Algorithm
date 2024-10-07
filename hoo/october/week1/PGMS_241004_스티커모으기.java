package october.week1;

public class PGMS_241004_스티커모으기 {

    public int solution(int sticker[]) {
        int answer = calcMaxSticker(sticker);

        return answer;
    }

    int calcMaxSticker(int sticker[]) {
        if (sticker.length == 1) return sticker[0]; // 내 일반식은 길이가 3인 것부터 유효하므로 길이가 1, 2일 때는 조건문으로 처리해주기
        else if (sticker.length == 2) return Math.max(sticker[0], sticker[1]);

        int[] firstSelectedSticker = new int[sticker.length-1]; // 첫 번째 스티커를 고른 경우의 배열
        int[] lastSelectedSticker = new int[sticker.length-1];  // 마지막 스티커를 고른 경우의 배열
        for (int i = 0; i < sticker.length-1; i++) {    // 각 배열 초기화
            firstSelectedSticker[i] = sticker[i];
            lastSelectedSticker[i] = sticker[i+1];
        }
        int firstMax = dynamicProgramming(firstSelectedSticker);    // 첫 번째 스티커를 고른 경우의 최댓값 계산
        int lastMax = dynamicProgramming(lastSelectedSticker);    // 마지막 스티커를 고른 경우의 최댓값 계산

        return Math.max(firstMax, lastMax);
    }

    int dynamicProgramming(int sticker[]) { // 첫 번째, 마지막 스티커를 사용한 경우에 따라 다른 배열을 가지고 dp를 수행
        int[] dpArr = new int[sticker.length];
        dpArr[0] = sticker[0];
        dpArr[1] = Math.max(dpArr[0], sticker[1]);
        for (int i = 2; i < sticker.length; i++) {
            dpArr[i] = Math.max(dpArr[i-2] + sticker[i], dpArr[i-1]);
        }

        return dpArr[sticker.length-1];
    }

}
