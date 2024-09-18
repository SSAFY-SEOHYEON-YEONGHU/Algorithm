package implementation;

public class PGMS_240918_기치국설치 {
    int answer = 0;
    public void addAnswer(int len, int width){
        //System.out.println(len + " " + width);
        if(len <= width) {
            answer++;
            return;
        }

        int remainder = len % width;
        if(remainder == 0) answer += (len / width);
        else answer += ((len / width) + 1);
    }

    public int solution(int n, int[] stations, int w) {

        if(stations[0] - w > 1) addAnswer(stations[0] - w - 1 , (w*2+1));

        for(int i=0; i<stations.length-1; i++){
            if(stations[i] + w + 1 < stations[i+1] - w)
                addAnswer((stations[i+1] - w) - (stations[i] + w) - 1,  (w*2+1));
        }

        if(stations[stations.length-1] + w < n)
            addAnswer((n - (stations[stations.length-1]+w)), (w*2+1));

        return answer;
    }
}
