package com.example.myapplication;

public class SnakeElement extends BoardComponent {

	private char last_move = 'w';
	 
    public SnakeElement(char symbol, int xStartingLocation, int yStartingLocation) {
        setIcon(symbol);
        setArrX(xStartingLocation);
        setArrY(yStartingLocation);
    }
     
    public void moveLeft(Board screen, SnakeElement snake, int appleCount) {
    	snake.setArrY(getArrY());
        snake.setArrX(getArrX() - 1);
        if(appleCount == 0)screen.ClearScreenLocation(snake.getArrX() + 1, snake.getArrY());
        else {
        	screen.printObjectOnLocation('t', snake.getArrX() + 1, snake.getArrY());
        	screen.ClearScreenLocation(snake.getLastX(appleCount), snake.getLastY(appleCount));
        }
        screen.setObjectOnLocation(snake, snake.getArrX(), snake.getArrY());
    }
     
    public void moveRight(Board screen, SnakeElement snake, int appleCount) {
    	snake.setArrY(getArrY());
    	snake.setArrX(getArrX() + 1);
    	if(appleCount == 0)screen.ClearScreenLocation(snake.getArrX() - 1, snake.getArrY());
    	else {
        	screen.printObjectOnLocation('t', snake.getArrX() - 1, snake.getArrY());
        	screen.ClearScreenLocation(snake.getLastX(appleCount), snake.getLastY(appleCount));
        }
    	screen.setObjectOnLocation(snake, snake.getArrX(), snake.getArrY());
    }
     
    public void moveUp(Board screen, SnakeElement snake, int appleCount) {
    	snake.setArrX(getArrX());
    	snake.setArrY(getArrY() - 1);
    	if(appleCount == 0)screen.ClearScreenLocation(snake.getArrX(), snake.getArrY() + 1);
    	else {
        	screen.printObjectOnLocation('t', snake.getArrX(), snake.getArrY() + 1);
        	screen.ClearScreenLocation(snake.getLastX(appleCount), snake.getLastY(appleCount));
        }
    	screen.setObjectOnLocation(snake, snake.getArrX(), snake.getArrY());
    }
     
    public void moveDown(Board screen, SnakeElement snake, int appleCount) {
    	snake.setArrX(getArrX());
    	snake.setArrY(getArrY() + 1);
    	if(appleCount == 0)screen.ClearScreenLocation(snake.getArrX(), snake.getArrY() - 1);
    	else {
        	screen.printObjectOnLocation('t', snake.getArrX(), snake.getArrY() - 1);
        	screen.ClearScreenLocation(snake.getLastX(appleCount), snake.getLastY(appleCount));
        }
    	screen.setObjectOnLocation(snake, snake.getArrX(), snake.getArrY());
    }

    public void moveLast(Board screen, SnakeElement snake, int appleCount){
    	if(snake.last_move == 'w')moveUp(screen, snake, appleCount);
    	else if(snake.last_move == 'a')moveLeft(screen, snake, appleCount);
		else if(snake.last_move == 's')moveDown(screen, snake, appleCount);
		else if(snake.last_move == 'd')moveRight(screen, snake, appleCount);
	}

	public void setLast_move(char move){
    	last_move = move;
	}

	public char getLast_move(){return last_move;}

	public boolean isAppleConflicts(int x, int y, int appleCount){
		if(getArrX() == x && getArrY() == y)return true;
    	while(appleCount > 0) {
			if (getLastX(appleCount) == x && getLastY(appleCount) == y) return true;
			appleCount = appleCount - 1;
		}
    	return false;
	}
}