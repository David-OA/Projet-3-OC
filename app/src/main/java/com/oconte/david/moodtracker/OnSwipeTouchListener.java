package com.oconte.david.moodtracker;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    ImageView smiley_swipe;

    public OnSwipeTouchListener(Context ctx) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 200;
        int smiley_swipe = 0;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {

                    smiley_swipe++;

                    if (smiley_swipe == 5) {
                        smiley_swipe = 4;
                        onSwipeBottom();
                    } else {

                        smiley_swipe--;
                        if (smiley_swipe == -1)
                            smiley_swipe = 0;
                        onSwipeTop();
                    }

                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }

    public void onSwipeTop() {


        switch (smiley_swipe) {
            case 0:
                smiley_swipe.setImageResource(R.drawable.smiley_happy);
                smiley_swipe.setBackgroundResource(R.color.light_sage);

                break;

            case 1:
                smiley_swipe.setImageResource(R.drawable.smiley_super_happy);
                smiley_swipe.setBackgroundResource(R.color.banana_yellow);
                break;
        }
    }

    public void onSwipeBottom() {
        switch (smiley_swipe) {
            case 2:
                smiley_swipe.setImageResource(R.drawable.smiley_normal);
                smiley_swipe.setBackgroundResource(R.color.cornflower_blue_65);
                break;

            case 3:
                smiley_swipe.setImageResource(R.drawable.smiley_disappointed);
                smiley_swipe.setBackgroundResource(R.color.warm_grey);
                break;

            case 4:
                smiley_swipe.setImageResource(R.drawable.smiley_sad);
                smiley_swipe.setBackgroundResource(R.color.faded_red);
                break;
        }
    }
}
