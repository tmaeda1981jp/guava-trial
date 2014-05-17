package jp.smilecode;

import java.util.*;

import com.google.common.base.*;

public class SplitterExample {

    public static void main(String[] args) {
        
        String target = ",a,,b,";
        
        String[] splited = target.split(",");
        System.out.println(splited.length);
        System.out.println(Arrays.toString(splited));
        
        Iterable<String> iterable = Splitter.on(',').split(target);
        System.out.println(iterable.toString());
        
        Iterable<String> iterable2 = Splitter.on(',').omitEmptyStrings().split(target);
        System.out.println(iterable2.toString());

    }
}
