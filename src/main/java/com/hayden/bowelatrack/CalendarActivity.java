package com.hayden.bowelatrack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        dbHandler = new DBHandler(this, null, null, 1);

        Button homeButton = findViewById(R.id.homeButton);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Cooper.ttf");
        homeButton.setTypeface(custom_font);
        Context context = CalendarActivity.this;

        CalendarView calendarView = findViewById(R.id.calendarView);

        View view = findViewById(R.id.calendarLayout);

        view.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            calendarView.setEvents(getEvents());
        } else {
            LocalDateTime currentDate = LocalDateTime.now();
            Database date = new Database(currentDate);
            dbHandler.addDate(date);
            calendarView.setEvents(getEvents());
        }

    }

    private List<EventDay> getEvents() {
        List<EventDay> events = new ArrayList<>();

        String datesText = dbHandler.ReturnAsString();
        Log.d("Hayden", "getEvents String returned: " + datesText);
        if (datesText.equals("")) {
            return events;
        } else {
            String dates[] = datesText.split("\n");

            for (int i = 0; i < dates.length; i++) {
                Log.d("Hayden", "Date to split: " + dates[i]);
                String dateParts[] = dates[i].split("-");
                Log.d("Hayden", "Date Split: " + dateParts[0] + "/" + dateParts[1] + "/" + dateParts[2]);
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]) - 1; //calendar month start at 0 (January)
                int day = Integer.parseInt(dateParts[2]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                events.add(new EventDay(calendar, R.drawable.event));
            }
        }
        return events;
    }

    public void GoHome(View view) {
        Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
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
