package com.tank.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.tank.utils.Assets;

public class DeathExplosion extends Actor {
    private static Animation<TextureRegion> explosionAnimation;
    private static Texture death_explosion = Assets.manager.get(Assets.death_explosion);
    private static final int FRAMES_ROWS = 10;
    private static final int FRAMES_COLS = 9;
    private static final int FPS = 30;
    private static final int FRAME_WIDTH = death_explosion.getWidth() / FRAMES_COLS;
    private static final int FRAME_HEIGHT = death_explosion.getHeight() / FRAMES_ROWS;
	
	static {
		TextureRegion[][] textureRegions = TextureRegion.split(death_explosion,
				death_explosion.getWidth() / FRAMES_COLS,
				death_explosion.getHeight() / FRAMES_ROWS);

        TextureRegion[] explosionFrames = new TextureRegion[FRAMES_COLS * FRAMES_ROWS];
        int index = 0;
        for (int i = 0; i < FRAMES_ROWS; i++) {
            for (int j = 0; j < FRAMES_COLS; j++) {
                explosionFrames[index++] = textureRegions[i][j];
            }
        }

        explosionAnimation = new Animation<TextureRegion>(1.0f / FPS, explosionFrames);
	}

    private float stateTime;

    public DeathExplosion(float x, float y) {
        stateTime = 0f;
        setX(x);
        setY(y);
        setOriginX(FRAME_WIDTH / 2);
		setOriginY(FRAME_HEIGHT / 2);
    }

    @Override
    public void act(float delta) {
    	stateTime += delta;
    	if (stateTime >= (float)(FRAMES_ROWS * FRAMES_COLS + 1) / FPS) {
    		remove();
    	}
    }

    public void draw(Batch batch, float a) {
    	TextureRegion currentFrame = explosionAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, super.getX() - super.getOriginX(), super.getY() - super.getOriginY(), super.getOriginX(),
				super.getOriginY(), FRAME_WIDTH, FRAME_HEIGHT, super.getScaleX(), super.getScaleY(), getRotation());
    }
}