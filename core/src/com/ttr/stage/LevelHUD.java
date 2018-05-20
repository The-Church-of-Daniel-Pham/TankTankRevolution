package com.ttr.stage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ttr.TankTankRevolution;
import com.ttr.actor.tank.Tank;
import com.ttr.utils.Assets;

public class LevelHUD extends Stage implements InputProcessor {
	protected Game game;
	private Label fpsLabel;
	private static float sinceChange;
	private ProgressBar reloadBar;

	private Skin skin = Assets.manager.get(Assets.skin);

	public LevelHUD(Game game) {
		super(new ScreenViewport());
		this.game = game;
		super.addActor(buildTable());
	}

	@Override
	public void act(float delta) {
		//update fps counter
		sinceChange += delta;	//add time since last act() to counter
        if(sinceChange >= 1.0f) {	//after 1 second or more
            sinceChange = 0;	//reset counter
    		fpsLabel.setText(Gdx.graphics.getFramesPerSecond() + " FPS");	//update fps label
        }
        
        //update reload bar
		float completion = Tank.reloadTime / (1 / Tank.RATE_OF_FIRE); // reload time out of max reload time (inverse of
																	// rate of fire)
		reloadBar.setValue(completion);
	}

	private Table buildTable() {
		Table uiTable = new Table();
		uiTable.setFillParent(true);
		uiTable.setDebug(true); // This is optional, but enables debug lines for tables.

		// Add widgets to the table here.
		fpsLabel = new Label("0 FPS", skin);
		TextButton returnButton = new TextButton("Return to Main Menu", skin);
		reloadBar = new ProgressBar(0.0f, 1.0f, 0.01f, false, skin);

		returnButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(TankTankRevolution.screens.get("Main Menu"));
				event.stop();
			}
		});

		uiTable.defaults().width(200).height(75).space(25).center();
		uiTable.add(fpsLabel).width(100).expand().top().left();
		uiTable.add(returnButton).width(300).expand().top().right();
		uiTable.row();
		uiTable.add(reloadBar).width(400).colspan(2).expand().bottom().right();

		return uiTable;
	}
}
