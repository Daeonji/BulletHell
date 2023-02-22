package com.mygdx.game;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Enemy {
    Rectangle enemy;
    int deltaX;
    int deltaY;
    Interpolation easAlpha = Interpolation.circle;
    int lifeTime = 1;
    float elapsed = 0f;
    Vector2 position;
    List<Vector2> pattern;
    int currentDestination;
    public Enemy(float x, float y){
        enemy = new Rectangle();
        position = new Vector2();
        pattern = new ArrayList<>();
        enemy.x = x;
        enemy.y = y;
        enemy.width = 16;
        enemy.height = 16;
        this.deltaX = 0;
        this.deltaY = 0;
        position.x = x;
        position.y = y;
        pattern.add(new Vector2(400,100));
        pattern.add(new Vector2(300,500));
        pattern.add(new Vector2(0,0));
        currentDestination = 0;
    }
    public void update(float deltaT)
    {
        elapsed += deltaT;
        float progress = Math.min(1f, elapsed/lifeTime);
        float alpha = easAlpha.apply(progress);
        position.lerp(pattern.get(currentDestination),alpha);
        if(alpha == 1f && currentDestination < pattern.size())
        {
            currentDestination++;
            elapsed = 0f;
        }
    }
}
