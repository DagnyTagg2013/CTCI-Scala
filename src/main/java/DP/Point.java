/**
 * Created by daphneeng on 11/8/16.
 */

package DP;

// TODO: refactor as INDEPENDENT Point class!
class Point {

    private int x = 0;
    private int y = 0;

    //constructor
    public Point(int a, int b) {
        x = a;
        y = b;
    }

    //ATTN:  EQUALS override on DEFAULT OBJECT!!!
    public boolean equals(Object rhs) {

        // TEST instance type FIRST!
        if (!(rhs instanceof Point)) {
            return false;
        }

        // CAST RHS OBJECT to POINT, which maybe upcasting for inheritance!
        Point rhp = (Point)rhs;

        // DEEP COMPARISON
        // ATTN: could ALSO put super.equals()
        boolean isEquals = ((this.x == rhp.x) && (this.y == rhp.y));

        return isEquals;

    }

    //hashcode necessary!

    public String toString() {
        return String.format("[%d, %d]", x, y);
    }

}
