package com.tank.actor.vehicles;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tank.interfaces.Collidable;
import com.tank.interfaces.Destructible;
import com.tank.interfaces.Teamable;
import com.tank.stats.Stats;

public abstract class AbstractVehicle extends Actor implements Collidable, Destructible, Teamable{
	
	protected int health;
	protected int maxHealth;
	protected Stats stats;
	protected Vector2 velocity;

	public AbstractVehicle(float x, float y) {
		setX(x);
		setY(y);
	}

	public void act(float delta) {
		
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	public void updateVelocityAndMove() {
		setX(getX() + velocity.x);
		setY(getY() + velocity.y);
	}
	public void applyForce(Vector2 acceleration) {
		velocity = velocity.add(acceleration);
	}
	public void applyForce(float mag, float dir) {
		
	}
	public int getStat(String stat) {
		return stats.getStatValue(stat);
	}

	public abstract ArrayList<Polygon> getHitbox();
	public abstract void checkCollision(Collidable other);

	public void damage(Actor source, int damage) {
		health -= damage;
		if (health <= 0 && !isDestroyed()) destroy();
	}
	public void heal(Actor source, int heal) {
		health += heal;
		if (health > maxHealth) health = maxHealth;
	}
	public void destroy() {
		super.remove();
	}
	public boolean isDestroyed() {
		return (getStage() == null);
	}

	public String getTeam() {
		return null;
	}
}
