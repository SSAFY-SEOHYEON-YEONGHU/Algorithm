package twentytwentyfour.october.week2;

public class PGMS_241013_바탕화면정리 {

    public int[] solution(String[] wallpaper) {
        int[] answer = findMinWindow(wallpaper);

        return answer;
    }

    public int[] findMinWindow(String[] wallpaper) {
        int lux = wallpaper.length, luy = wallpaper[0].length(), rdx = 0, rdy = 0; // 드래그 직사각형의 왼쪽 위(lux, luy), 오른쪽 아래(rdx, rdy) => lux: 가장 높은 윗쪽 값, rdx: 가장 낮은 아랫쪽 값, luy: 가장 낮은 왼쪽 값, rdy: 가장 높은 오른쪽 값

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length() ;j++) {
                if (wallpaper[i].charAt(j) == '#') {    // 파일인 경우
                    lux = Math.min(lux, i);
                    luy = Math.min(luy, j);
                    rdx = Math.max(rdx, i+1);
                    rdy = Math.max(rdy, j+1);
                }
            }
        }

        int[] minWindow = new int[] {lux, luy, rdx, rdy};
        return minWindow;
    }

}
