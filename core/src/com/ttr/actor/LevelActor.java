package com.ttr.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ttr.level.Level;

public abstract class LevelActor extends Actor{
	protected Texture texture;
	protected Level level;
	
	public void setTexture(Texture tex) {
		texture = tex;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setLevel(Level lev) {
		level = lev;
	}
	
	public Level getLevel() {
		return level;
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(texture, super.getX()-super.getOriginX(), super.getY()-super.getOriginY(),super.getOriginX(),super.getOriginY(), texture.getWidth(),
				texture.getHeight(), super.getScaleX(), super.getScaleY(), super.getRotation(), 0, 0, texture.getWidth(),
				texture.getHeight(), false, false);
	}
	
	public String toString() {
		return ("(" + super.getX() + ", " + super.getY() + ") with " + Math.toRadians(super.getRotation())
				+ " orientation");
	}
}
