package com.csus.csc133.models;

public abstract class GameObject {
    int x;
    int y;

    
    // default constructor
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void move();
    abstract void handleCollide(Student s);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
