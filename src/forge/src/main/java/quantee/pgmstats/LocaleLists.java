package quantee.pgmstats;

import java.util.ArrayList;

public class LocaleLists {
    // Not sure how to approach this, but I'm sure this one is not the best
    // This file, for now will store "from" translated to every PGM language
    public static ArrayList<String> fromLocale = new ArrayList<String>();

    public static void init () {
        fromLocale.add("From ");
        fromLocale.add("De ");
        fromLocale.add("Van ");
        fromLocale.add("Von ");
        fromLocale.add("Da ");
        fromLocale.add("から ");
        fromLocale.add("Frawm ");
        fromLocale.add("Fra ");
        fromLocale.add("De la ");
        fromLocale.add("От ");
        fromLocale.add("Från ");
    }
}
