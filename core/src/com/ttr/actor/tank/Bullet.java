package com.ttr.actor.tank;
/**
 * @author Samuel
import java.util.ArrayList;


import com.badlogic.gdx.graphics.Texture;


 * @version April 13th 2018
 * 
 * Description: Simple projectile, with position and orientation. Continues along straight-line path while within Map bounds.
 */

import com.badlogic.gdx.math.Vector2;
import com.ttr.actor.DynamicCollider;
import com.ttr.level.Level;
import com.ttr.utils.Assets;

public class Bullet extends DynamicCollider {
	public float tempX, tempY; // test values to determine if move is free from collision

	public static final int SIZE = Assets.manager.get(Assets.bullet).getWidth();
	public static final float VELOCITY = 800;
	Vector2 v;

	public Bullet(float x, float y, float orientation, Level level) {
		super.setX(x);
		super.setY(y);
		super.setRotation((float) Math.toDegrees(orientation));
		super.setOrigin(SIZE / 2f, SIZE / 2f); // set origin to center of texture-sized square
		super.setTexture(Assets.manager.get(Assets.bullet));
		super.setLevel(level);
		v = new Vector2(26,7);
		collidesAt(0, 0, 0); // set-up vertex arrays
	}
	
	private void move(float delta) {
		tempX = (super.getX() + (float) Math.cos(Math.toRadians(super.getRotation())) * Bullet.VELOCITY * delta);
		tempY = (super.getY() + (float) Math.sin(Math.toRadians(super.getRotation())) * Bullet.VELOCITY * delta);

		if (super.getLevel().map.inMap(tempX, tempY)
				&& !collidesAt(tempX, tempY, (float) Math.toRadians(super.getRotation()))) {
			super.setY(tempY);
			super.setX(tempX);
		} else {
			onCollision();
		}
	}

	@Override
	public void act(float delta) {
		move(delta);
	}
	
	@Override
	public float[] getVertices(float x, float y, float orientation) {
		float[] vertices = new float[8];
		v.setAngleRad(orientation+0.263f);
		vertices[0] = super.getX() + v.x; //* (float)Math.cos(orientation);
		vertices[1] = super.getY() + v.y;//* (float)Math.sin(orientation);
		v.rotateRad(2.6156f);
		vertices[2] = super.getX() + v.x;// * (float)Math.cos(orientation);
		vertices[3] = super.getY() + v.y;//* (float)Math.sin(orientation);
		v.rotateRad(0.526f);
		vertices[4] = super.getX() + v.x;// * (float)Math.cos(orientation);
		vertices[5] = super.getY() + v.y; //* (float)Math.sin(orientation);
		v.rotateRad(2.6156f);
		vertices[6] = super.getX() + v.x;// * (float)Math.cos(orientation);
		vertices[7] = super.getY() + v.y; //* (float)Math.sin(orientation);
		return vertices;
	}

	@Override
	public void onCollision() {
		super.remove();
	}
}