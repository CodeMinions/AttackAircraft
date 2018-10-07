package me.codeminions.attackaircraftproject.until;

import java.io.Serializable;


/**
 * 创建时间：2018/10/4 0:00
 * 描述：TODO
 */
public class Location  implements Serializable {
    public int x;
    public int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getLocation(){
        return "(" + x + "," + y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (obj == null)
            return false;
//        if (getClass() != obj.getClass())
//            return false;
        Location other = (Location) obj;
        if (x == other.x && y == other.y)
            return true;
        else
            return false;
    }
}
