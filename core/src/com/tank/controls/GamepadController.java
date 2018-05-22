package com.tank.controls;

import java.util.LinkedHashMap;

import com.badlogic.gdx.controllers.Controller;

public class GamepadController extends TankController {
	public Controller game;
	private LinkedHashMap<String, Integer> keyMap;
	
	public boolean upPressed() {
		return game.getButton(keyMap.get("UP"));
	}
	public boolean downPressed() {
		return game.getButton(keyMap.get("DOWN"));
	}
	public boolean rightPressed() {
		return game.getButton(keyMap.get("RIGHT"));
	}
	public boolean leftPressed() {
		return game.getButton(keyMap.get("LEFT"));
	}
	public boolean firePressed() {
		return game.getButton(keyMap.get("SHOOT"));
	}
	public boolean subPressed() {
		return game.getButton(keyMap.get("SUB"));
	}
	public boolean subRightPressed() {
		return game.getButton(keyMap.get("RSWITCH"));
	}
	public boolean subLeftPressed() {
		return game.getButton(keyMap.get("LSWITCH"));
	}
	public float[] moveCursor(float x, float y) {
		return null;
	}
}
