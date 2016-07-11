package com.mygdx.game;


public class Walker {
    public float x;
    public float y;

    public static final int STEP_SIZE = 20;

    public Walker() {
        x = 0;
        y = 0;
    }

    public void setCoordinates(float newX, float newY) {
        x = newX;
        y = newY;
    }

    public void stepRight() {
        step(90);
    }

    public void stepDown() {
        step(180);
    }

    public void stepLeft() {
        step(270);
    }

    public void step(double angle) {
        double doubleX = STEP_SIZE * Math.sin(Math.toRadians(angle));
        x = x + (float)doubleX;
        double doubleY = STEP_SIZE * Math.cos(Math.toRadians(angle));
        y = y + (float)doubleY;
    }

    public void stepTowards(float destX, float destY) {
        float differenceX = destX - x;
        float differenceY = destY - y;
        if (distanceGreaterThanStepSize(differenceX, differenceY)) {
            double theta = Math.toDegrees(Math.atan(differenceX/differenceY));
            step(theta);
        }
    }

    public boolean distanceGreaterThanStepSize(float differenceX, float differenceY) {
        return differenceX * differenceX + differenceY * differenceY > STEP_SIZE * STEP_SIZE;
    }
}
