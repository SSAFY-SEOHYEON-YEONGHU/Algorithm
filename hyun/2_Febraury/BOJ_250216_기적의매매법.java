package implementation;

import java.io.*;
import java.util.*;
public class BOJ_250216_기적의매매법 {
    static int cash;
    static int[] input = new int[14];

    static int junhyun(){
        int junCash = cash;
        int junSu = 0;

        for(int i=0; i<14; i++){
            int cur = input[i]; // 현재 주가
            if(junCash / cur > 0){
                junSu += (junCash / cur);
                junCash = (junCash % cur);
            }
        }

        return junCash + input[13] * junSu;
    }

    static int sungmin(){
        int sungCash = cash;
        int sungSu = 0;

        for (int i = 0; i < 13; i++) {
            int cnt = 0;
            boolean isUp = input[i] < input[i+1] ? true : false;

            if(isUp) { // 팔기
                for (int j = i + 1; j <= i+3 && j < 14; j++) {
                    if (input[j - 1] < input[j]) cnt++;
                    else break;
                }
                if(cnt < 3) continue;
                if(sungSu > 0){

                    sungCash += (sungSu * input[i+3]);
                    sungSu = 0;
                }

            }
            else{ // 사기
                for (int j = i + 1; j <= i+3 && j < 14; j++) {
                    if (input[j - 1] > input[j]) cnt++;
                    else break;
                }
                if(cnt < 3) continue;
                if(sungCash / input[i+3] > 0){
                    sungSu += sungCash / input[i+3];
                    sungCash = sungCash % input[i+3];
                }
            }
        }

        return sungCash + input[13] * sungSu;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cash = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 14; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //

        int j = junhyun();
        int s = sungmin();

        if(j > s) System.out.println("BNP");
        else if(j < s) System.out.println("TIMING");
        else System.out.println("SAMESAME");
    }
}
