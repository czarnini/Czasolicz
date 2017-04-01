package com.example.android.czasolicz;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Michał on 15.03.2017.
 */

public class ExpandableListUtility
{


    private List<String> categories;
    private HashMap<String, List<String>> childElements;

    ExpandableListUtility(Context context)
    {
        categories = new ArrayList<>();
        childElements = new HashMap<>();

        Model.populateExpandableList(context, categories, childElements);

    }

    public List<String> Categories() {
        return categories;
    }

    public HashMap ChildElements() {
        return childElements;
    }
}
