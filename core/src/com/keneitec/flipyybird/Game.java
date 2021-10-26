package com.keneitec.flipyybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture passaro,fundo;
	private float larguraDispositivo, alturaDispositivo;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		passaro = new Texture("passaro1.png");
		fundo = new Texture("fundo.png");

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();

	}

	@Override
	public void render () {
		batch.begin();

		batch.draw(fundo,0,0, larguraDispositivo ,alturaDispositivo);
		batch.draw(passaro,300,0);


		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
