package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MikeFrameHelper {
    private final String IMAGE_PATH = "mike-walkiing.png";
    private final int ASSET_WIDTH = 1068;
    private final int ASSET_HEIGHT = 2100;
    private final int UP = 0;
    private final int RIGHT = 1;
    private final int LEFT = 2;
    private final int DOWN = 3;
    private final int TOTAL_FRAMES = 4;

    private Texture texture;
    private SpriteBatch batch;
    private int currentFrame = 0;
    private int currentDirection = UP;

    public void create() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(IMAGE_PATH));
    }

    public void standing(int x, int y) {
        currentFrame = 0;
        walk(x, y);
    }

    public void walkLeft(int x, int y) {
        // return an iterator that returns next frame.
        // for now, can botch it with a current frame variable that preserves the state.
        currentDirection = LEFT;
        walk(x, y);
        incrementFrame();
    }

    public void walkRight(int x, int y) {
        currentDirection = RIGHT;
        walk(x, y);
        incrementFrame();
    }

    public void walkUp(int x, int y) {
        currentDirection = UP;
        walk(x, y);
        incrementFrame();
    }

    public void walkDown(int x, int y) {
        currentDirection = DOWN;
        walk(x, y);
        incrementFrame();
    }

    private void walk(int x, int y) {
        batch.begin();
        batch.draw(texture, x, y, currentFrame * ASSET_WIDTH, currentDirection * ASSET_HEIGHT, ASSET_WIDTH, ASSET_HEIGHT);
        batch.end();
    }

    private void incrementFrame() {
        currentFrame = (currentFrame + 1) % TOTAL_FRAMES;
    }
}
