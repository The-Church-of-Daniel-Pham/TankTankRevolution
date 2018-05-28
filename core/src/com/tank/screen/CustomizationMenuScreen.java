package com.tank.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.tank.game.TankInfinity;
import com.tank.stage.CustomizationMenu;;

public class CustomizationMenuScreen implements Screen{
	public CustomizationMenu customizationMenu;
	private TankInfinity game;
	
	public CustomizationMenuScreen (TankInfinity game) {
		this.game = game;
		customizationMenu = new CustomizationMenu(this.game);
	}
	
	@Override
	public void show() {
		game.addInput(customizationMenu);
	}
	
	@Override
	public void hide() {
		game.removeInput(customizationMenu);
	}
	
	@Override
	public void resize (int width, int height) {
		customizationMenu.getViewport().update(width, height, true);
	}
	
	@Override
	public void render (float delta) {
		exitButton();

		//Clear the screen
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
		customizationMenu.act(delta);
		customizationMenu.draw();
	}

	public void dispose() {
		customizationMenu.dispose();
	}
	
	public void exitButton() {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}