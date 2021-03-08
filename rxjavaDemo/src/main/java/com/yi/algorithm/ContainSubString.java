package com.yi.algorithm;

/**
 * https://mp.weixin.qq.com/s/67uf7pRxXh7Iwm7MMpqJoA
 * 主字符串是否包含子字符串，输入匹配的开始位置
 * a b b c e f g h
 * b c e
 * 2
 */
public class ContainSubString {

    public static void main(String[] args) {

        String mainStr = "abbcefgh";
        String subStr = "bce";

        int mainStrLength = mainStr.length();
        int subStrLength = subStr.length();

        int subStrHash = hash(subStr);
        System.out.println("subStrHash= " + subStrHash);

        int strCode = hash(mainStr.substring(0, subStrLength));
        System.out.println("1 strCode= " + strCode);

        for (int i = 0; i < mainStrLength - subStrLength - 1; i++) {
//            int tempHash = hash(mainStr.substring(i, i + subStrLength));
//            System.out.println("tempHash= " + tempHash);
            if (strCode == subStrHash) {
                System.out.println("postion= " + i);
            }
            strCode = nextHash(mainStr, strCode, i, subStrLength);
            System.out.println("2 strCode= " + strCode + " i= " + i);
        }
    }

    private static int nextHash(String str, int hash, int index, int n) {
        hash -= str.charAt(index) - 'a';
        hash += str.charAt(index + n) - 'a';
        return hash;
    }

    /**
     * 计算hash
     */
    private static int hash(String str) {
        int hashcode = 0;
        for (int i = 0; i < str.length(); i++) {
            hashcode += str.charAt(i) - 'a';
        }
        return hashcode;
    }


}
