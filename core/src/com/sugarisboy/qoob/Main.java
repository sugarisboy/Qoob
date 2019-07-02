package com.sugarisboy.qoob;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {

    SpriteBatch batch;
    ShapeRenderer renderer;
    Graf graf;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        graf = new Graf("cube");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLUE);
        graf.draw(renderer,300, 300);
        renderer.end();

        update(Gdx.graphics.getDeltaTime());
    }

    private void update(float deltaTime) {


        if (Gdx.input.isTouched()) {
            System.out.printf("\n%d %d", Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            graf.rotate(Gdx.input.getDeltaY() * 0.07F, Gdx.input.getDeltaX() * 0.07F);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            graf.rotate(deltaTime * 5F, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            graf.rotate(0, deltaTime * 5F);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            graf.rotate(deltaTime * -5F, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            graf.rotate(0, deltaTime * -5F);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
