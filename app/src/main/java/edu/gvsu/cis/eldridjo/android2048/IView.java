package edu.gvsu.cis.eldridjo.android2048;

/**
 * Created by Hans Dulimarta on Feb 23, 2016.
 */
public interface IView {
    void swapTiles(int r1, int c1, int r2, int c2);
    void redrawTiles(int[][] arr);
    void showMessage(String msg);
}
