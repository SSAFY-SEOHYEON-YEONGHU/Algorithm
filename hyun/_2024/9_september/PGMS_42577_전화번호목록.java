package hash;

import java.io.*;
import java.util.*;

public class PGMS_42577_전화번호목록 {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> hmap = new HashMap<>();
        for(int i=0; i<phone_book.length; i++){
            hmap.put(phone_book[i],1);
        }

        for(int i=0; i<phone_book.length; i++){
            String p = phone_book[i];
            String tmp = "";
            for(int j=0; j<p.length()-1; j++){
                tmp += Character.toString(p.charAt(j));
                if(hmap.containsKey(tmp)) return false;
            }
        }


        return true;
    }
}
