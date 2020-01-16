package me.codeminions.attackaircraftproject.tool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ToolsTest {

    @Test
    public void randomLoc() {
        int  i = 0;
        Location a;
        int b;
        while(i < 30){
            a = Tools.RandomLocation();
            b = Tools.RandomLDirection();
            System.out.println(a.x + "  " + a.y + "         " + b);

            i++;
        }
    }
}