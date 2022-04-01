package com.example.myapplication;

public class BoardComponent {
    
    private int x, y;
    private int[] queueX = new int[100], queueY = new int[100];
    private int queueXi = 0, queueYi = 0;
    private char icon;
 
    public int getX() {
        return this.x;
    }
 
    public int getY() {
        return this.y;
    }
    
    public int getArrX() {
    	if(queueXi == 0)return this.queueX[99];
    	else return this.queueX[queueXi - 1];
    }
 
    public int getArrY() {
    	if(queueYi == 0)return this.queueY[99];
    	else return this.queueY[queueYi - 1];
    }
    
    public int getLastX(int appleCount) {
    	if(queueXi - appleCount - 2 < 0)return this.queueX[queueXi - appleCount - 2 + 100];
    	else return this.queueX[queueXi - appleCount - 2];
    }
    
    public int getLastY(int appleCount) {
    	if(queueYi - appleCount - 2 < 0)return this.queueY[queueYi - appleCount - 2 + 100];
    	else return this.queueY[queueYi - appleCount - 2];
    }
 
    public char getIcon() {
        return icon;
    }
 
    public void setX(int newLocation) {
        this.x = newLocation;
    }
     
    public void setY(int newLocation) {
        this.y = newLocation;
    }
    
    public void setArrX(int newLocation) {
        this.queueX[queueXi] = newLocation;
        queueXi = (queueXi + 1)%100;
    }
     
    public void setArrY(int newLocation) {
    	this.queueY[queueYi] = newLocation;
        queueYi = (queueYi + 1)%100;
    }
     
    public void setIcon(char newSymbol) {
        this.icon = newSymbol;
    }
}