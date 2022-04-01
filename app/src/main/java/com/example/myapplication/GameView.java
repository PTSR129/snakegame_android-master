package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private Paint paint = new Paint();
    private float size;

    private char[][] viewMap;
    public void setGameMap(char[][] inputMap){viewMap = inputMap;}

    //맵 그리기
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int inc_x = canvas.getWidth() / viewMap[0].length;
        size = (float)(inc_x * (0.5));
        int x = inc_x / 2;
        int inc_y = canvas.getHeight() / viewMap.length;
        int y = inc_y / 2;

        for(int i = 0;i < viewMap.length;i++) {
            for (int j = 0; j < viewMap[0].length; j++) {
                
                //색깔 바꾸기. w(벽) : 검정, a(사과) : 빨강, s(머리) : 파랑, t(꼬리) : 회색, 그외 : 하양
                if(viewMap[i][j] == 'w')paint.setColor(Color.BLACK);
                else if (viewMap[i][j] == 'a')paint.setColor(Color.RED);
                else if (viewMap[i][j] == 's')paint.setColor(Color.BLUE);
                else if (viewMap[i][j] == 't')paint.setColor(Color.GRAY);
                else paint.setColor(Color.WHITE);
                
                canvas.drawCircle(x, y, size, paint);
                x += inc_x;
            }
            x = inc_x / 2;
            y += inc_y;
        }
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
