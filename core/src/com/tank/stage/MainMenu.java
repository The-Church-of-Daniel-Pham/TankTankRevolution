package com.tank.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.tank.actor.ui.Background;
import com.tank.game.TankInfinity;
import com.tank.utils.Assets;
import com.tank.utils.Constants;

public class MainMenu extends Stage implements InputProcessor{
	protected TankInfinity game;
	protected Table uiTable;
	private Texture title = Assets.manager.get(Assets.title);
	private Skin skin = Assets.manager.get(Assets.skin);
	private Background titleBackground;
	
	public MainMenu(TankInfinity game) {
		//super(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		super(new ExtendViewport(Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT));
		this.game = game;
		titleBackground = new Background(title);
		titleBackground.setFill(true);
		super.addActor(titleBackground);
		uiTable = new Table();
		buildTable();
		super.addActor(uiTable);
	}
	
	private void buildTable() {
		uiTable.setFillParent(false);
		uiTable.setDebug(false); // This is optional, but enables debug lines for tables.
		uiTable.defaults().width(250).height(100).space(25).left();
		uiTable.bottom().padBottom(50).left().padLeft(150);

		// Add widgets to the table here.
		TextButton playButton = new TextButton("Play", skin);
		playButton.getLabel().setAlignment(Align.left);
		TextButton tutorialButton = new TextButton("Tutorial", skin);
		tutorialButton.getLabel().setAlignment(Align.left);
		TextButton creditsButton = new TextButton("Credits", skin);
		creditsButton.getLabel().setAlignment(Align.left);
		TextButton settingsButton = new TextButton("Settings", skin);
		settingsButton.getLabel().setAlignment(Align.left);
		TextButton quitButton = new TextButton("Quit", skin);
		quitButton.getLabel().setAlignment(Align.left);
		
		playButton.addListener(new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 game.setScreen(game.screens.get("Customization Menu"));	//go here first
	        	 event.stop();
	         }
	      });
		
		tutorialButton.addListener(new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 game.setScreen(game.screens.get("Tutorial"));
	        	 event.stop();
	         }
	      });
		
		creditsButton.addListener(new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 game.setScreen(game.screens.get("Credits"));
	        	 event.stop();
	         }
	      });
		
		settingsButton.addListener(new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 game.setScreen(game.screens.get("Settings Menu"));
	        	 event.stop();
	         }
	      });
		
		quitButton.addListener(new ClickListener() {
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	        	 Gdx.app.exit();
	        	 event.stop();
	         }
	      });
		
		uiTable.add(playButton);
		uiTable.row();
		uiTable.add(tutorialButton);
		uiTable.row();
		uiTable.add(creditsButton);
		uiTable.row();
		uiTable.add(settingsButton);
		uiTable.row(); 
		uiTable.add(quitButton);
	}
}