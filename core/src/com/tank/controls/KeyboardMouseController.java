package com.tank.controls;

import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;

public class KeyboardMouseController extends TankController {
	private LinkedHashMap<String, Integer> keyMap;

	public boolean upPressed() {
		return Gdx.input.isButtonPressed(keyMap.get("UP"));
	}
	public boolean downPressed() {
		return Gdx.input.isButtonPressed(keyMap.get("DOWN"));
	}
	public boolean rightPressed() {
		return Gdx.input.isButtonPressed(keyMap.get("RIGHT"));
	}
	public boolean leftPressed() {
		return Gdx.input.isButtonPressed(keyMap.get("LEFT"));
	}
	public boolean firePressed() {
		return Gdx.input.isButtonPressed(keyMap.get("SHOOT"));
	}
	public boolean subPressed() {
		return Gdx.input.isButtonPressed(keyMap.get("SUB"));
	}
	public boolean subRightPressed() {
		return Gdx.input.isButtonPressed(keyMap.get("RSWITCH"));
	}
	public boolean subLeftPressed() {
		return Gdx.input.isButtonPressed(keyMap.get("LSWITCH"));
	}
	public float[] moveCursor(float x, float y) {
		return null;
	}
}
