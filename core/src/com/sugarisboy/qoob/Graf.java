package com.sugarisboy.qoob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Graf {

    private List<GrafVertex> vertexs;
    private float alphaX = 0;
    private float alphaY = 0;

    public Graf(String file) {


        vertexs = new ArrayList();

        readVertexs(Gdx.files.internal(String.format("%s_v.txt", file)).file());
        readAdjacent(Gdx.files.internal(String.format("%s_a.txt", file)).file());

    }

    private void readVertexs(File file) {
        try{
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line, data[];
            float x, y, z;
            while ((line = br.readLine()) != null){
                data = line.split(" ");
                x = Float.valueOf(data[0]);
                y = Float.valueOf(data[1]);
                z = Float.valueOf(data[2]);
                vertexs.add(new GrafVertex(new Vector3(x, y, z)));
            }
        } catch (Exception e){
           e.printStackTrace();
        }
    }

    private void readAdjacent(File file) {
        try{
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line, data[];
            int a, b;
            while ((line = br.readLine()) != null){
                data = line.split(" ");
                a = Integer.valueOf(data[0]);
                b = Integer.valueOf(data[1]);
                vertexs.get(a).join(vertexs.get(b));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void rotate(float x, float y) {
        alphaX += x;
        alphaY += y;
        for (GrafVertex vertex : vertexs)
            vertex.rotate(alphaX, alphaY);
    }

    void draw(ShapeRenderer renderer, int x, int y) {
        for (GrafVertex vertex : vertexs) {
            for (GrafVertex smej : vertex.getAdjacents()) {
                renderer.line(x + vertex.getX() * 100, y + vertex.getY() * 100, x + smej.getX() * 100, y + smej.getY() * 100);
            }
        }
    }
}
