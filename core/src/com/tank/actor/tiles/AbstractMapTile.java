package com.tank.actor.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tank.utils.Assets;

public abstract class AbstractMapTile extends Actor {
	protected Texture tex;
	public static final int SIZE = Assets.manager.get(Assets.grass0).getWidth();
	
	public AbstractMapTile(Texture t, float x, float y) {
		tex = t;
		setX(x);
		setY(y);
	}

	public void act(float delta) {
		
	}
	public void draw(Batch batch, float a) {
		
	}
}
