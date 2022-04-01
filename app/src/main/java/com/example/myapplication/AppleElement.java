package com.example.myapplication;

public class AppleElement extends BoardComponent {
	 
    public AppleElement(char symbol) {
        setIcon(symbol);
    }
     
    public void addRandomApple(Board screen, AppleElement apple, SnakeElement snake, int appleCount) {
         
        int x;
        int y;

        do{
            x = (int) (((Math.random()) * (screen.getBoardWidth() - 1)));
            y = (int) (((Math.random()) * (screen.getBoardHeight() - 1)));
        }while(snake.isAppleConflicts(x, y, appleCount));

        if(x==0)
        {
            x = 2;
        }
         
        if(y==0)
        {
            y = 2;
        }
        screen.setObjectOnLocation(apple,x,y);
        apple.setX(x);
        apple.setY(y);
    }
}