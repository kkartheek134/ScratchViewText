
package com.example.kkavalireddy.scratchviewtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PaintView(this));
    }

}


class PaintView extends View implements View.OnTouchListener {

    private static final String TAG = "PaintView";
    private Bitmap bitmap;
    private Canvas canvas;
    Path p;
    Rect r;
    Region re;
    int[] xArray = new int[100];
    int[] yArray = new int[100];

    LinearLayout layout;
    TextView textView;


    Bitmap Bitmap1, Bitmap2;
    Bitmap Transparent;
    int X = -100;
    int Y = -100;
    int count = 0;
    Canvas c2;
    private boolean isTouched = false;

    Paint paint = new Paint();
    //Paint paint1 = new Paint();

    Path drawPath = new Path();

    public PaintView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        Transparent = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //Bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
        Bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.test);

        c2 = new Canvas();
        c2.setBitmap(Transparent);
        c2.drawBitmap(Bitmap2, 0, 0, paint);

        paint.setAlpha(0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        //paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setAntiAlias(true);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("onDraw");
       /* String text = "Hello Buddy";
         r = new Rect();
        *//*rect = new Rect();
        getHitRect(rect);*//*

        paint1.getTextBounds(text, 0, text.length(), r);
        float mt = paint1.measureText(text);
        int bw = r.width();

        Log.i("LCG", String.format(
                "measureText %f, getTextBounds %d (%s)",
                mt,
                bw, r.toShortString())
        );
        r.offset(0, -r.top);
        paint1.setStyle(Paint.Style.STROKE);
        canvas.drawRect(r, paint1);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
       // paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;*/
       //Toast.makeText(getContext(), " "+cWidth+" \n"+cHeight, Toast.LENGTH_SHORT).show();

     /*   p = new Path();
        //p.lineTo(x, y);
       *//* p.close();*//*
        p.moveTo(0,0);
        p.lineTo(392, 318);*/
       /* p.lineTo(x, y);*/
        /*p.lineTo(100, 100);
        p.lineTo(80, 100);*/
        //p.close();

       /* canvas.drawPath(p, paint);

        RectF rectF = new RectF();
        p.computeBounds(rectF, true);
        re = new Region();
        re.setPath(p, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));*/

        //Toast.makeText(getContext(), ""+rectF.left+"\n"+rectF.top+"\n"+rectF.right+"\n"+rectF.bottom, Toast.LENGTH_SHORT).show();


        if(isTouched)
        {
            /*int xPos = (canvas.getWidth() / 2);
            int yPos = (int) ((canvas.getHeight() / 2) - ((paint1.descent() + paint1.ascent()) / 2));*/
         /*   paint1.setTextSize(30);
            *//*float X =  cWidth / 2f - r.width();
            float Y = cHeight / 2f + r.height();*//*
            //Toast.makeText(getContext(), " "+x+"\n"+y, Toast.LENGTH_SHORT).show();
//            paint1.setColor(Color.WHITE);

            canvas.drawText(text, x,y,paint1);
            getHitRect(r);*/
            layout = new LinearLayout(getContext());

             textView = new TextView(getContext());
            textView.setVisibility(View.VISIBLE);
            textView.setText("Hello world");
            textView.setTextSize(30);
            layout.addView(textView);

            layout.measure(canvas.getWidth(), canvas.getHeight());
            layout.layout(0, 0, canvas.getWidth(), canvas.getHeight());
            layout.setGravity(Gravity.CENTER_VERTICAL);

// To place the text view somewhere specific:
//canvas.translate(0, 0);

            layout.draw(canvas);



        }
        canvas.drawBitmap(Transparent, 0, 0, null);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (inViewInBounds(textView, (int) event.getX(), (int) event.getY())) {
                // User moved outside bounds
                c2.drawColor(0, PorterDuff.Mode.CLEAR);
                Log.e("dispatchTouchEvent", "you touched inside button");
                Log.e("dispatch",""+event.getX()+" "+event.getY());


            } else {
                Log.e("dispatchTouchEvent", "you touched outside button");
            }

        }
        return super.dispatchTouchEvent(event);
    }

    Rect outRect = new Rect();
    int[] location = new int[2];


    private boolean inViewInBounds(View view, int x, int y) {
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isTouched = true;
        X = (int) event.getX();
        Y = (int) event.getY();
        /*Rect rect = new Rect();
        getHitRect(rect);*/
        /*if (r.contains((int)event.getX(), (int) event.getY())) {

            Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
        }*/

        /*Point point = new Point();
        point.x = event.getX();
        point.y = event.getY();
//        points.add(point);
        invalidate();
        Log.d(TAG, "point: " + point);

        if(re.contains((int)point.x,(int) point.y))
        {
            Log.d(TAG, "Buddy : Touch IN");
            Toast.makeText(getContext(), "Yes You got it ", Toast.LENGTH_SHORT).show();
        }*/


        //Toast.makeText(getContext(), " "+X+"\n"+Y, Toast.LENGTH_SHORT).show();
       /* float startX = 0;
        float startY = 0;
        float movedX = 0;
        float movedY = 0;
*/

        paint.setStrokeWidth(60);



        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(X, Y);
                c2.drawPath(drawPath, paint);

                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(X, Y);
                c2.drawPath(drawPath, paint);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(X, Y);
                c2.drawPath(drawPath, paint);
                drawPath.reset();
                count=0;
                break;
            default:
                return false;
        }
       /* if (event.getAction() == MotionEvent.ACTION_DOWN) {

            startX = event.getRawX();
            startY = event.getRawY();
            Log.e("resize", "action down");

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Log.e("resize", "action move");


            movedX = event.getRawX() - startX;
            movedY = event.getRawY() - startY;

            Log.e("", " movement " + movedX + " " + movedY + "");
        }
*/

        invalidate();
        return true;}}class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}




