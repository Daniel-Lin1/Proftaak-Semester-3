package com.company;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Point> arrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            arrayList.add(new Point(i, i+5));
        }

        int count = 0;
        for (Point point: arrayList) {
            System.out.println(count + " - X: " + point.getX() + " Y: " + point.getY());
            count ++;
        }

        arrayList.set(0, null);

        System.out.println("======================================================================================================================");

        int count1 = 0;
        for (Point point: arrayList) {
            System.out.println(count1 + " - X: " + point.getX() + " Y: " + point.getY());
            count1++;
        }
    }
}
