package com.keneitec.flipyybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	//Texturas
	private SpriteBatch batch;
	private Texture passaros[],fundo;
	private Texture canoBaixo, canoTopo;


	//Atributos de configuracao
	private float larguraDispositivo, alturaDispositivo;
	private float variacao = 0;
	private float movimentoX = 0;
	private float movimentoY = 0;
	private float gravidade = 0;
	private float posicaoInicialY = 0;
	private float posicaoCanoHorizontal;
	private float espacoEntreCanos;

	@Override
	public void create () {
		inicializarTexturas();
		inicializarObjetos();

	}

	@Override
	public void render () {
		verificaEstadoJogo();
		desenharTexturas();
	}
	private void verificaEstadoJogo(){
		//Aplica evento de clique na tela
		boolean toqueTela = Gdx.input.justTouched();
		if (toqueTela){
			gravidade= -20;
		}

		//Aplicar gravidade no passaro
		if(posicaoInicialY >0 || toqueTela) posicaoInicialY = posicaoInicialY - gravidade;

		variacao += Gdx.graphics.getDeltaTime() * 12;
		//Animaçao de bater asas do passaro
		if (variacao >4) variacao = 0;
		gravidade += 1.4;

	}

	private void desenharTexturas(){
		batch.begin();

		batch.draw(fundo,0,0, larguraDispositivo ,alturaDispositivo);
		batch.draw(passaros[(int) variacao],movimentoX,posicaoInicialY);

		batch.draw(canoBaixo,posicaoCanoHorizontal-100,alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos/2);
		batch.draw(canoTopo,posicaoCanoHorizontal-100,alturaDispositivo/2 + espacoEntreCanos/2);

		batch.end();
	}

	private void inicializarTexturas(){
		passaros = new Texture[4];
		passaros[0] = new Texture("passaro1.png");
		passaros[1] = new Texture("passaro2.png");
		passaros[2] = new Texture("passaro3.png");
		passaros[3] = new Texture("passaro2.png");
		fundo = new Texture("fundo.png");

		canoBaixo = new Texture("cano_baixo_maior.png");
		canoTopo = new Texture("cano_topo_maior.png");
	}

	private void inicializarObjetos(){
		batch = new SpriteBatch();
		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();
		posicaoInicialY = alturaDispositivo/2;
		posicaoCanoHorizontal = larguraDispositivo;
		espacoEntreCanos = 200;
	}
	
	@Override
	public void dispose () {

	}
}
