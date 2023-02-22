package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
public class Bullet {
    Rectangle bullet;
    int deltaX;
    public Bullet(float x, float y, int deltaX){
        bullet = new Rectangle();
        bullet.x = x;
        bullet.y = y;
        bullet.width = 64;
        bullet.height = 64;
        this.deltaX = deltaX;
    }
}
