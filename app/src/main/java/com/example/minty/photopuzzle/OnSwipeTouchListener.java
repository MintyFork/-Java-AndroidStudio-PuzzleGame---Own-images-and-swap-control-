package com.example.minty.photopuzzle;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector det;

    public OnSwipeTouchListener(Context context) {
        det = new GestureDetector(context, new GestureListener());
    }

    public void swipeL() {
    }

    public void swipeR() {
    }

    public void swipeT() {
    }

    public void swipeB() {
    }

    public boolean onTouch(View v, MotionEvent event) {
        return det.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int swipe_dist = 100;
        private static final int swipe_vel = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velX, float velY) {
            float distX = e2.getX() - e1.getX();
            float distY = e2.getY() - e1.getY();
            if (Math.abs(distX) > Math.abs(distY) && Math.abs(distX) > swipe_dist && Math.abs(velX) > swipe_vel) {
                if (distX > 0)
                    swipeR();
                else
                    swipeL();
                return true;
            }else if (Math.abs(distY) > swipe_dist && Math.abs(velY) > swipe_vel) {
                if (distY > 0) {
                    swipeB();
                } else {
                    swipeT();
                }
                return true;
            }
            return false;
        }
    }
}
