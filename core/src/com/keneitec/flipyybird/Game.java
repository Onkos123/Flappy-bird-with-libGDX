package com.keneitec.flipyybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Game extends ApplicationAdapter {
	//Texturas
	private SpriteBatch batch;
	private Texture passaros[],fundo;
	private Texture canoBaixo, canoTopo;

	//Formas para colisoes
	private ShapeRenderer shapeRenderer;
	private Circle circuloPassaro;
	private Rectangle retanguloCanoCima;
	private Rectangle retanguloCanoBaixo;


	//Atributos de configuracao
	private float larguraDispositivo, alturaDispositivo;
	private float variacao = 0;
	private float movimentoX = 0;
	private float movimentoY = 0;
	private float gravidade = 0;
	private float posicaoInicialY = 0;
	private float posicaoCanoHorizontal;
	private float posicaoCanoVertical;
	private float espacoEntreCanos;
	private Random random;
	private int pontos;
	private boolean passouCano;

	//Exibição de textos
	BitmapFont textoPontuacao;

	@Override
	public void create () {
		inicializarTexturas();
		inicializarObjetos();

	}

	@Override
	public void render () {
		verificaEstadoJogo();
		validarPontos();
		desenharTexturas();
		detectarColisoes();
	}

	private void detectarColisoes() {
		circuloPassaro.set(
				50+passaros[0].getWidth()/2,posicaoCanoVertical+passaros[0].getHeight()/2,passaros[0].getWidth()/2
		);
		retanguloCanoBaixo.set(
				posicaoCanoHorizontal-100,alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos/2 + posicaoCanoVertical,
				canoBaixo.getWidth(),canoBaixo.getHeight()
		);
		retanguloCanoCima.set(
				posicaoCanoHorizontal-100,alturaDispositivo/2 + espacoEntreCanos/2 + posicaoCanoVertical,
				canoTopo.getWidth(),canoTopo.getHeight()
		);

		boolean colidiuCanoCima = Intersector.overlaps(circuloPassaro,retanguloCanoCima);
		boolean colidiuCanoBaixo = Intersector.overlaps(circuloPassaro,retanguloCanoBaixo);

		if (colidiuCanoCima || colidiuCanoBaixo){

		}

		/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		shapeRenderer.circle(50+passaros[0].getWidth()/2,posicaoCanoVertical+passaros[0].getHeight()/2,passaros[0].getWidth()/2);

		shapeRenderer.end();*/

	}

	private void verificaEstadoJogo(){

		//Movimentar o cano
		posicaoCanoHorizontal -= Gdx.graphics.getDeltaTime() * 350;
		if (posicaoCanoHorizontal < - canoTopo.getWidth()){
			posicaoCanoHorizontal = larguraDispositivo + canoTopo.getWidth();
			posicaoCanoVertical = random.nextInt(400) - 200;
			passouCano = false;
		}

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
		batch.draw(passaros[(int) variacao],50,posicaoInicialY);

		batch.draw(canoBaixo,posicaoCanoHorizontal-100,alturaDispositivo/2 - canoBaixo.getHeight() - espacoEntreCanos/2 + posicaoCanoVertical);
		batch.draw(canoTopo,posicaoCanoHorizontal-100,alturaDispositivo/2 + espacoEntreCanos/2 + posicaoCanoVertical);

		textoPontuacao.draw(batch,String.valueOf(pontos),larguraDispositivo/2,alturaDispositivo -100);

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

	private void validarPontos() {
		if (posicaoCanoHorizontal < 50 - passaros[0].getWidth()){//Passou da posição do passaro
			if (!passouCano){
				pontos++;
				passouCano = true;
			}
		}
	}

	private void inicializarObjetos(){
		batch = new SpriteBatch();
		random = new Random();

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();
		posicaoInicialY = alturaDispositivo/2;
		posicaoCanoHorizontal = larguraDispositivo;
		espacoEntreCanos = 230;

		//Configuração dos textos
		textoPontuacao = new BitmapFont();
		textoPontuacao.setColor(Color.WHITE);
		textoPontuacao.getData().setScale(10);

		//Formas geometricas para colisoes
		shapeRenderer = new ShapeRenderer();
		circuloPassaro = new Circle();
		retanguloCanoCima = new Rectangle();
		retanguloCanoBaixo = new Rectangle();
	}
	
	@Override
	public void dispose () {

	}
}
