package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton btn_start;
    private AppCompatButton btn_stop;
    private AppCompatButton btn_up;
    private AppCompatButton btn_down;
    private AppCompatButton btn_left;
    private AppCompatButton btn_right;
    private TextView txt_score;

    private GameEngine gameEngine;
    private GameView gameView;

    private final Handler handler = new Handler();
    private final long update_delay = 500;
    private Runnable gameTask;
    private boolean isRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txt_score = findViewById(R.id.text_score);
        btn_start = findViewById(R.id.btn_startGame);
        btn_stop = findViewById(R.id.btn_stopGame);
        btn_down = findViewById(R.id.btn_down);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_up = findViewById(R.id.btn_up);

        gameEngine = new GameEngine();
        gameView = findViewById(R.id.gameView);
        gameEngine.clear_board();
        gameView.setGameMap(gameEngine.get_map());

        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btn_start){
            isRunning = true;
            gameEngine.init_game();
            gameView.setGameMap(gameEngine.get_map());
            startHandler();
        }
        else if(isRunning == false)return;
        else if(view == btn_down)gameEngine.setDown();
        else if(view == btn_up)gameEngine.setUp();
        else if(view == btn_left)gameEngine.setLeft();
        else if(view == btn_right)gameEngine.setRight();
        else if(view == btn_stop){
            isRunning = false;
            gameEngine.clear_board();
            gameView.setGameMap(gameEngine.get_map());
        }
    }

    private void startHandler(){
        gameTask = new Runnable() {
            @Override
            public void run() {
                if(isRunning){
                    if(gameEngine.update_game() == -1){
                        isRunning = false;
                        ToastMsg("gg!");
                    }
                    gameView.setGameMap(gameEngine.get_map());
                    handler.postDelayed(gameTask, update_delay);
                }
                gameView.invalidate();
                txt_score.setText("Score : " + Integer.toString(gameEngine.get_score()));
            }
        };
        gameTask.run();
    }

    public void ToastMsg(String string){
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }
}
