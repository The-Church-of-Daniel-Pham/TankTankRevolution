package com.tank.interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface Destructible {
	public void damage(Actor source, int damage);
	public void heal(Actor source, int heal);
	public void destroy();
	public boolean isDestroyed();
}
