package com.sugarisboy.qoob;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import java.util.HashSet;
import java.util.Set;

public class GrafVertex {

    private Vector3 vector;
    private Set<GrafVertex> adjacents;
    private float x;
    private float y;
    private float k;

    public GrafVertex(Vector3 vector) {
        this.vector = vector;
        adjacents = new HashSet();
        calculate(vector.x, vector.y, vector.z);
    }

    void rotate(float x, float y) {
        calculate(
                vector.x * MathUtils.cos(y) + (vector.y * -MathUtils.sin(x) + vector.z * MathUtils.cos(x)) * -MathUtils.sin(y),
                vector.y * MathUtils.cos(x) + vector.z * MathUtils.sin(x),
                vector.x * MathUtils.sin(y) + (vector.y * -MathUtils.sin(x) + vector.z * MathUtils.cos(x)) * -MathUtils.cos(y)
        );
    }

    public void calculate(float x, float y, float z) {
        k = 1 + z * 0.01F;
        this.x = x * 1 / k;
        this.y = y * 1 / k;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Set<GrafVertex> getAdjacents() {
        return adjacents;
    }

    public void join(GrafVertex... vertex) {
        for (GrafVertex vertex1 : vertex) {
            adjacents.add(vertex1);
        }
    }
}
