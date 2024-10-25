package be.kdg.prog6.grandparents.domain;

import java.util.List;

public class GrandKids {

    public static final List<String> allKids = List.of("Bobby","Betty");

    public static boolean isGrandKid(String name){
        return allKids.contains(name);
    }

}
