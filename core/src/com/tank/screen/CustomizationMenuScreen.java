/**
 * @author The Church of Daniel Pham
 * Description:
 * Screens are one of the highest levels of code we
 * implement using Libgdx, the highest being Game/DesktopLauncher.
 * The Libgdx framework calls the render(...) method,
 * and we write the Stages that get called in this method.
 * Screens essentially implement their non-screen,
 * Stage counterparts, calling their act(...) and draw()
 * methods.
 * 
 * The Customization Menu Screen class is used to invoke the logic
 * and draw the textures of the player customization that occurs
 * before the start of each game.
 */
package com.tank.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.tank.game.TankInfinity;
import com.tank.stage.CustomizationMenu;
import com.tank.utils.Constants;;

public class CustomizationMenuScreen implements Screen{
	public CustomizationMenu customizationMenu;
	private TankInfinity game;
	
	public CustomizationMenuScreen (TankInfinity game) {
		this.game = game;
		customizationMenu = new CustomizationMenu(this.game);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		//Clear the screen
		Gdx.gl.glClearColor(Constants.CLEAR_COLOR, Constants.CLEAR_COLOR, Constants.CLEAR_COLOR, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
		customizationMenu.act(delta);
		customizationMenu.draw();
	}

	public void dispose() {
		customizationMenu.dispose();
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
