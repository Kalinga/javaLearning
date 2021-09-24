package de.ray.java.basics.utils;

import java.util.HashMap;

public class Utilities {

    public static int firstUniqueCharacter(String s) {
        System.out.println(s);
        //Kalinga: 0
        //Arizona:1
        //HashMap parameter can not be primitive type?
        // type-erasure
        // HashMap<Object, Object>
        var characterMap = new HashMap<Character, Integer>();

        for(int i= 0; i < s.length(); i++) {
            if(null == characterMap.get(s.charAt(i))) {
                // Put the character and the corresponding Index
                characterMap.put(s.charAt(i), i);
            } else
            {
                // Indicate the Character that we already seen
                characterMap.put(s.charAt(i), -1);
            }
        }

        int minIndex = Integer.MAX_VALUE;
        for (Integer index : characterMap.values()) {
            if(-1 != index && minIndex > index ) {
                minIndex = index;
            }
        }

        return minIndex==Integer.MAX_VALUE? -1:minIndex ;
    }

    public static int smallestCharacter(String s) {
        if(null == s || s.isEmpty())
            return -1;

        int min = Integer.MAX_VALUE;

        for (char c : s.toCharArray()) {
            //System.out.println(c);
            if (min > c)
                min = c;
        }
        return min;
    }

    public static void main(String[] args) {
        String str = "arizona";
        System.out.println(firstUniqueCharacter(str));
        System.out.printf("%c",smallestCharacter(str));

    }
}
