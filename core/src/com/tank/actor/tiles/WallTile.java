package com.tank.actor.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.tank.interfaces.Collidable;

public class WallTile extends AbstractMapTile implements Collidable{

	public WallTile(Texture t, float x, float y) {
		super(t, x, y);
	}
	
	public Polygon getHitbox(){
		return null;
	}
	public void checkCollision(Collidable other) {
		
	}

}
