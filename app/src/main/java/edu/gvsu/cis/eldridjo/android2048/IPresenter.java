package edu.gvsu.cis.eldridjo.android2048;

/**
 * Created by Hans Dulimarta on Feb 23, 2016.
 */
public interface IPresenter {
    void onSlide(SlideDirection dir);
    void onRandomizeTiles();
    void onAttachView(IView v);
}
