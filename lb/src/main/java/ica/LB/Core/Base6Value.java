package ica.LB.Core;

import java.util.*;

/**
 * Created by jcapuano on 5/14/2014.
 */
public class Base6Value {
    private int value;
    private static ArrayList<Integer> table;

    public Base6Value(int v) {
        setValue(v);
    }

    public void setValue(int v) {
        if 		(v < 11) value = 11;
        else if (v > 66) value = 66;
        else 		     value = v;
    }
    public int getValue() {
        return value;
    }

    public int add(int v) {
        value = op(value, v, true);
        return value;
    }

    public int subtract(int v) {
        value = op(value, v, false);
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    private int op(int lhs, int rhs, Boolean add)
    {
        ArrayList<Integer> t = getTable();

        int index=-1;
        for (int i=0; i<t.size() && index==-1; i++)
        {
            if (lhs == t.get(i))
                index = i;
        }

        if (index >= 0)
        {
            if (add)
                index += rhs;
            else
                index -= rhs;
            if 		(index < 0) 			index = 0;
            else if (index >= t.size()) index = t.size() - 1;

            return t.get(index);
        }
        else
        {
            return lhs;
        }
    }

    private ArrayList<Integer> getTable() {
        if (table == null) {
            table = new ArrayList<Integer>();
            for (int i=1; i<=6; i++) {
                for (int j = 1; j <= 6; j++) {
                    table.add(i * 10 + j);
                }
            }
        }
        return table;
    }
}
