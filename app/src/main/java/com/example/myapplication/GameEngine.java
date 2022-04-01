package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

public class GameEngine {
    final int BOARD_WIDTH = 20;
    final int BOARD_HEIGHT = 10;
    final int START_X = BOARD_WIDTH / 2;
    final int START_Y = BOARD_HEIGHT / 2;
    int appleCount;

    Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
    RoomWall wall;
    SnakeElement snake;
    AppleElement apple;
    GameActivity gameActivity = new GameActivity();
 
	public void init_game() {
        //뷰 배열 초기화
        board.initBoard();
        appleCount = 0;

        //뷰 배열에 벽 생성
        wall = new RoomWall('w');
        wall.addRoomWallRow(board, wall, 0); 
        wall.addRoomWallRow(board, wall, board.getBoardHeight() - 1); 
        wall.addRoomWallColumn(board, wall, 0); 
        wall.addRoomWallColumn(board, wall, board.getBoardWidth() - 1); 
 
        snake = new SnakeElement('s', START_X, START_Y);
        board.setObjectOnLocation(snake, snake.getArrX(), snake.getArrY());
 
        apple = new AppleElement('a');
        apple.addRandomApple(board, apple, snake, appleCount);
    }

    public int get_score(){
	    return appleCount * 10;
    }
    
    public char[][] get_map(){
        char[][] boardMatrix = board.getBoardMatrix();
        return boardMatrix;
    }

    public int update_game(){
	    snake.moveLast(board, snake, appleCount);
	    if(check_apple()){
	        apple.addRandomApple(board, apple, snake, appleCount);
            appleCount = appleCount + 1;
        }else if(check_wall_conflicts() || check_body_conflicts()){
	        return -1;
        }
	    return 0;
    }

    public void setLeft(){
	    if(snake.getLast_move() != 'd')
	        snake.setLast_move('a');
    }

    public void setUp(){
        if(snake.getLast_move() != 's')
            snake.setLast_move('w');
    }

    public void setRight(){
        if(snake.getLast_move() != 'a')
            snake.setLast_move('d');
    }

    public void setDown(){
        if(snake.getLast_move() != 'w')
            snake.setLast_move('s');
    }

    public void clear_board(){
	    board.initBoard();
    }

    public boolean check_apple(){
	    return (snake.getArrX() == apple.getX() && snake.getArrY() == apple.getY());
    }

    public boolean check_wall_conflicts(){
	    return (snake.getArrX() == 0 || snake.getArrX() == board.getBoardWidth() - 1 ||
                snake.getArrY() == 0 || snake.getArrY() == board.getBoardHeight() - 1);
    }

    public boolean check_body_conflicts(){
	    int count = appleCount;
	    while(count > 0){
	        if(snake.getArrX() == snake.getLastX(count) && snake.getArrY() == snake.getLastY(count))
                return true;
	        count = count - 1;
        }
	    return false;
    }
}