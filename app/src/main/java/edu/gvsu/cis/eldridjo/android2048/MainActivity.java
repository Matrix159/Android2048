package edu.gvsu.cis.eldridjo.android2048;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, IView {

    private GridLayout gridLayout;
    private GestureDetectorCompat gestureDetect;
    private IPresenter presenter;
    private TextView[][] gridViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        gestureDetect = new GestureDetectorCompat(this, this);
        gridLayout = (GridLayout) (findViewById(R.id.gridLayout));
        Drawable border = getResources().getDrawable(R.drawable.somedrawable);
        gridViews = new TextView[4][4];
        for (int k = 0; k < 16; k++) {
            int ri = k / 4;    /* determine row and column index */
            int ci = k % 4;
            TextView mytext = new TextView(this);
            mytext.setBackground(border);
            mytext.setGravity(Gravity.CENTER_HORIZONTAL);
            mytext.setWidth(300);
            mytext.setHeight(500);
            mytext.setText (String.valueOf(k));
            mytext.setTextSize (50);   /* or use a number that works better for your device */
  /* place the TextView at the desired row and column */
            GridLayout.Spec r_spec = GridLayout.spec (ri, GridLayout.CENTER);
            GridLayout.Spec c_spec = GridLayout.spec (ci, GridLayout.CENTER);
            GridLayout.LayoutParams par = new GridLayout.LayoutParams (r_spec, c_spec);
            par.setGravity (Gravity.FILL_HORIZONTAL);
            gridLayout.addView(mytext, par);
            gridViews[ri][ci] = mytext;
        }
        presenter = new Presenter();
        presenter.onAttachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("Velocity x: ", "" + velocityX);
        Log.d("Velocity y: ", "" + velocityY);
        if(velocityX > 3000)
        {
            presenter.onSlide(SlideDirection.RIGHT);
            return true;
        }
        else if(velocityX < - 3000)
        {
            presenter.onSlide(SlideDirection.LEFT);
            return true;
        }
        else if(velocityY > 3000)
        {
            presenter.onSlide(SlideDirection.DOWN);
            return true;
        }
        else if(velocityY < -3000)
        {
            presenter.onSlide(SlideDirection.UP);
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        return gestureDetect.onTouchEvent(e);
    }

    @Override
    public void swapTiles(int r1, int c1, int r2, int c2) {
        String lab1 = gridViews[r1][c1].getText().toString();
        String lab2 = gridViews[r2][c2].getText().toString();
        gridViews[r1][c1].setText(lab2);
        gridViews[r2][c2].setText(lab1);
    }

    @Override
    public void redrawTiles(int[][] arr) {
        for (int k = 0; k < arr.length; k++)
            for (int m = 0; m < arr[k].length; m++)
                if (arr[k][m] != 0)
                    gridViews[k][m].setText(String.valueOf(arr[k][m]));
                else
                    gridViews[k][m].setText("");
    }

    @Override
    public void showMessage(String msg) {

    }
}
