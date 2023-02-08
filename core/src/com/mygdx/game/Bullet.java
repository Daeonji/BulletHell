package com.mygdx.game;

import java.awt.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;
public class Bullet {
    Rectangle bullet;
    int deltaX;
    public Bullet(float x, float y, int deltaX){
        bullet = new Rectangle();
        bullet.x = x;
        bullet.y = y;
        bullet.width = 16;
        bullet.height = 16;
        this.deltaX = deltaX;
    }
}
