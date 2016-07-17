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

    public void stepUp() {
        step(0);
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
            float ratio = differenceY / differenceX;
            float stepX = (float)Math.sqrt(STEP_SIZE*STEP_SIZE / (1 + ratio*ratio));
            float stepY = Math.abs(stepX * ratio);

            if (Double.isNaN(stepX) || stepX > Math.abs(differenceX)) {
                // y is too small
                int signX = getSignOfNumber(differenceX);
                x = x + signX * STEP_SIZE;
            }
            else if (Double.isNaN(stepY) || stepY > Math.abs(differenceY)) {
                // x is too small
                int signY = getSignOfNumber(differenceY);
                y = y + signY * STEP_SIZE;
            }
            else {
                int signX = getSignOfNumber(differenceX);
                int signY = getSignOfNumber(differenceY);

                x = x + signX * stepX;
                y = y + signY * stepY;
            }
        }
    }

    private int getSignOfNumber(float number) {
        return (int)(number / Math.abs(number));
    }

    public boolean distanceGreaterThanStepSize(float differenceX, float differenceY) {
        return differenceX * differenceX + differenceY * differenceY > STEP_SIZE * STEP_SIZE;
    }
}
