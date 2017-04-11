package com.example.holmesk.cehua.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：holmes k
 * 时间：2017.04.10 19:43
 */


public class UriType {
    public static final String URI = "http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573";
    private static String uritype1 = "tt";
    private static String uritype2 = "gj";
    private static String uritype3 = "ss";
    private static String uritype4 = "cj";
    private static String uritype5 = "kj";
    private static String uritype6 = "js";
    private static String uritype7 = "ty";
    private static String uritype8 = "yl";
    private static String uritype9 = "gn";
    private static String uritype10 = "shehui";

    public static List<String> getTypeList() {
        List<String> typelist = new ArrayList<>();
        typelist.add(uritype1);
        typelist.add(uritype2);
        typelist.add(uritype3);
        typelist.add(uritype4);
        typelist.add(uritype5);
        typelist.add(uritype6);
        typelist.add(uritype7);
        typelist.add(uritype8);
        typelist.add(uritype9);
        typelist.add(uritype10);

        return typelist;
    }
}
