package com.mygdx.game;


import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MyGdxGame implements ApplicationListener, InputProcessor {
	private SpriteBatch batch;
	private BitmapFont font;
	private int w,h;
	private GlyphLayout layout;
    private ShapeRenderer shapeRenderer;
	private ShapeRenderer walkerRenderer;
	private Walker walker;

	class TouchInfo {
		public float touchX = 0;
		public float touchY = 0;
		public boolean touched = false;
	}

	private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();

	@Override
	public void create() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setColor(Color.BLACK);
		walkerRenderer = new ShapeRenderer();
		walkerRenderer.setAutoShapeType(true);
		walkerRenderer.setColor(Color.RED);
		walker = new Walker();
		walker.setCoordinates(80, 80);

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		layout = new GlyphLayout();
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		Gdx.input.setInputProcessor(this);
		for(int i = 0; i < 5; i++){
			touches.put(i, new TouchInfo());
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		String message = "";
        float touchX = 0.0f;
        float touchY = 0.0f;
        for (int i = 0; i < 5; i++) {
			if (touches.get(i).touched) {
                touchX = touches.get(i).touchX;
                touchY = touches.get(i).touchY;
				// TODO why is this "h - " needed?
                shapeRenderer.circle(touchX, h - touchY, 80);

                message += "Finger:" + Integer.toString(i) + "touch at:" +
                        Float.toString(touchX) +
                        "," +
                        Float.toString(touchY) +
                        "\n";
            }

		}
        shapeRenderer.end();

		walkerRenderer.begin(ShapeRenderer.ShapeType.Filled);
		// get last touches
		if (touchX != 0.0f && touchY != 0.0f) {
			walker.stepTowards(touchX, touchY);
		}
		walkerRenderer.circle(walker.x, h - walker.y, 80);
		walkerRenderer.end();

//      batch.begin();
//		layout = new GlyphLayout();
//		layout.setText(font, message);
//		float x = w/2 - layout.width/2;
//		float y = h/2 + layout.height/2;
//		font.draw(batch, message, x, y);

//		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pointer < 5){
			touches.get(pointer).touchX = screenX;
			touches.get(pointer).touchY = screenY;
			touches.get(pointer).touched = true;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(pointer < 5){
			touches.get(pointer).touchX = 0;
			touches.get(pointer).touchY = 0;
			touches.get(pointer).touched = false;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(pointer < 5){
            touches.get(pointer).touchX = screenX;
            touches.get(pointer).touchY = screenY;
        }
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}