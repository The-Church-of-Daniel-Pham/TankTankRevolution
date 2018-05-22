package com.tank.controls;

public abstract class TankController {
	public abstract boolean upPressed();
	public abstract boolean downPressed();
	public abstract boolean rightPressed();
	public abstract boolean leftPressed();
	public abstract boolean firePressed();
	public abstract boolean subPressed();
	public abstract boolean subRightPressed();
	public abstract boolean subLeftPressed();
	public abstract float[] moveCursor(float x, float y);
}
