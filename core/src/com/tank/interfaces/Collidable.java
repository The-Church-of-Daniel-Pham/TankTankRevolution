package com.tank.interfaces;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;

public interface Collidable {
	public ArrayList<Polygon> getHitbox();
	public void checkCollision(Collidable other);
}
