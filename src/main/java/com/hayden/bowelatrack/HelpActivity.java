package com.hayden.bowelatrack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;

public class HelpActivity extends AppCompatActivity {

    Button homeButton;
    Button runnyPoo;
    Button painfulPoo;
    Button cantPoo;
    TextView helpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        homeButton = findViewById(R.id.homeButton);
        runnyPoo = findViewById(R.id.runnyPoo);
        painfulPoo = findViewById(R.id.painfulPoo);
        cantPoo = findViewById(R.id.cantPoo);
        helpText = findViewById(R.id.helpText);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Cooper.ttf");
        homeButton.setTypeface(custom_font);
        runnyPoo.setTypeface(custom_font);
        painfulPoo.setTypeface(custom_font);
        cantPoo.setTypeface(custom_font);
        helpText.setTypeface(custom_font);

        Context context = HelpActivity.this;

        View view = findViewById(R.id.helpLayout);

        view.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }

    public void GoHome(View view) {
        Intent intent = new Intent(HelpActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void RunnyPoo(View view) {
        String help = "Eat more fibre! \nTry bananas, rice, applesauce, and toast. \n\nStay hydrated! \n\nIf problems persist see a Doctor.";
        helpText.setText(help);
    }

    public void PainfulPoo(View view) {
        String help = "You may have eaten lots of spicy foods \n\nYou may also have hemorrhoids\n\nGo to the doctors!";
        helpText.setText(help);
    }

    public void CantPoo(View view) {
        String help = "You may be constipated! \nDrink more water, and eat Dried fruits\n\n You may also need to see a Doctor.";
        helpText.setText(help);
    }

    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new OnSwipeTouchListener.GestureListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }
}
