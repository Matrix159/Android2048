package edu.gvsu.cis.eldridjo.android2048;

import java.util.ArrayList;

/**
 * Created by eldridjo on 2/23/16.
 */
public class Presenter implements IPresenter {
    private IView view;
    private NumberPuzzle numberPuzzle;
    int rows = 4, cols = 4;

    public Presenter()
    {
        numberPuzzle = new NumberGame();
        numberPuzzle.resizeBoard(rows, cols);
        numberPuzzle.scramble();
    }


    @Override
    public void onSlide(SlideDirection dir) {
        Cell[] cells = numberPuzzle.moveIntoEmptySpot(dir);
        if(cells != null)
        view.swapTiles(cells[0].row, cells[0].column, cells[1].row, cells[1].column);
        if(numberPuzzle.getStatus() == GameStatus.USER_WON)
        {
            view.showMessage("You won!");
        }
    }

    @Override
    public void onRandomizeTiles() {
        numberPuzzle.scramble();
        ArrayList<Cell> cellList = numberPuzzle.getAllTiles();
        int[][] arr = new int[rows][cols];
        for(Cell x : cellList)
        {
            arr[x.row][x.column] = x.value;
        }
        view.redrawTiles(arr);
    }

    @Override
    public void onAttachView(IView v) {
        view = v;
        // Again, where do i get the param?
        ArrayList<Cell> cellList = numberPuzzle.getAllTiles();
        int[][] arr = new int[rows][cols];
        for(Cell x : cellList)
        {
            arr[x.row][x.column] = x.value;
        }
        view.redrawTiles(arr);
    }
}
