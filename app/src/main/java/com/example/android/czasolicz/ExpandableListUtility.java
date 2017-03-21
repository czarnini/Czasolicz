package com.example.android.czasolicz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 15.03.2017.
 */

public class ExpandableListUtility
{
    public static List<String> categories = new ArrayList<>();
    public static HashMap<String, List<String>> childElements = new HashMap<>();
    static
    {
        categories.add("Produktywność");
        categories.add("Relaks");
        categories.add("Sport");

        List<String> cat1 = new ArrayList<>();
        cat1.add("Praca");
        cat1.add("Nauka");

        List<String> cat2 = new ArrayList<>();
        cat2.add("Czytanie");
        cat2.add("Oglądanie telewizji");
        cat2.add("Internet");

        List<String> cat3 = new ArrayList<>();
        cat3.add("Bieganie");
        cat3.add("Jazda na rowerze");
        cat3.add("Siłownia");

        childElements.put(categories.get(0), cat1);
        childElements.put(categories.get(1), cat2);
        childElements.put(categories.get(2), cat3);
    }

}
