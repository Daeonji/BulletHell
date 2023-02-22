package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter{
	SpriteBatch batch;
	Texture planeSprite;
	Texture plane2Sprite;
	Texture bulletSprite;
	Texture bullet2Sprite;
	Texture enemySprite;
	private OrthographicCamera camera;
	private Rectangle plane;
	private List<Bullet> bullets;
	Boolean backwards = false;
	private List<Enemy> enemies;

	@Override
	public void create () {
		batch = new SpriteBatch();
		planeSprite = new Texture("Witch_NBG.png");
		plane2Sprite = new Texture("WitchB_NBG.png");
		bulletSprite = new Texture("SwordF.png");
		bullet2Sprite = new Texture("Sword.png");
		enemySprite = new Texture("enemysprite.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,800);
		plane = new Rectangle();
		plane.x = 64;
		plane.y = 305;
		plane.width = 64;
		plane.height = 64;
		bullets = new ArrayList<>();
		enemies = new ArrayList<>();
		enemies.add(new Enemy(500,200));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			backwards = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E))
		{
			backwards = false;
		}
		batch.begin();
		if(backwards)
		{
			batch.draw(plane2Sprite, plane.x, plane.y);
		}
		else {
			batch.draw(planeSprite, plane.x, plane.y);
			backwards = false;
		}

		Iterator<Bullet> itr = bullets.iterator();

		while(itr.hasNext()){
			Bullet current = itr.next();
				current.bullet.x += current.deltaX;
				if(current.bullet.x < 0 || current.bullet.x > 1000) {
					itr.remove();
				}
				else
					if(backwards)
						batch.draw(bullet2Sprite, current.bullet.x, current.bullet.y);
					else
						batch.draw(bulletSprite, current.bullet.x, current.bullet.y);
		}
		Iterator<Enemy> eitr = enemies.iterator();

		while(eitr.hasNext()){
			Enemy current = eitr.next();
			current.update(Gdx.graphics.getDeltaTime());
			if(current.enemy.x < 0 || current.enemy.x > 1000) {
				eitr.remove();
			}
			else {
				batch.draw(enemySprite, current.position.x, current.position.y);
			}
		}


		batch.end();
		if(Gdx.input.isKeyPressed(Input.Keys.A)) plane.x -= 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.D)) plane.x += 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.W)) plane.y += 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.S)) plane.y -= 300 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			bullets.add(new Bullet(plane.x, plane.y, backwards ? -20 : 20));
		}


		if(plane.x < 0) plane.x = 0;
		if(plane.x > 800 - 64) plane.x = 800 -64;
		if(plane.y < 0) plane.y = 0;
		if(plane.y > 800 - 64) plane.y = 800 - 64;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		planeSprite.dispose();
		plane2Sprite.dispose();
		bullet2Sprite.dispose();
		bulletSprite.dispose();
	}
}

