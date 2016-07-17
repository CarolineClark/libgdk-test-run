package com.mygdx.game;

import junitparams.Parameters;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class WalkerTest {

    private Walker walker;

    @Before
    public void setup() {
        walker = new Walker();
    }

    @Test
    @Parameters({"17, 17",
                 "22, 22",
                 "-100, -10",
                 "-20, 30",
                 "40, -80"})
    public void walkerWalksDownWhenInstructedToDoSo(int sourceX, int sourceY) {
        // Given
        walker.setCoordinates(sourceX, sourceY);

        //When
        walker.stepDown();

        //Then
        assertEquals(sourceX, walker.x, 0.01);
        assertEquals(sourceY - Walker.STEP_SIZE, walker.y, 0.01);
    }

    @Test
    @Parameters({"17, 17",
                "22, 22",
                "-100, -10",
                "-20, 30",
                "40, -80"})
    public void walkerWalksLeftWhenInstructedToDoSo(int sourceX, int sourceY) {
        // Given
        walker.setCoordinates(sourceX, sourceY);

        //When
        walker.stepLeft();

        //Then
        assertEquals(sourceX - Walker.STEP_SIZE, walker.x, 0.01);
        assertEquals(sourceY, walker.y, 0.01);
    }

    @Test
    public void coordinatesAfterStepping45Degrees_changesBothXAndY() {
        walker.step(45);
        assertEquals(walker.x, walker.y, 0.01);
        assertEquals(walker.x * walker.x + walker.y * walker.y, Walker.STEP_SIZE * Walker.STEP_SIZE, 0.01);
    }

    @Test
    public void afterSpecifyingPointWithEqualXAndYToWalkTo_walkerMovesFromOriginTowardsPoint() {
        // When
        walker.stepTowards(200, 200);

        // Then
        assertEquals(walker.x, walker.y, 0.01);
        assertEquals(walker.x * walker.x + walker.y * walker.y, Walker.STEP_SIZE * Walker.STEP_SIZE, 0.01);
    }

    @Test
    @Parameters({"300, 200",
                 "50, 200",
                 "20, 15"})
    public void afterSpecifyingDestinationInUpperQuadrant_walkerMovesToDestination(int destX, int destY) {
        // Given
        walker.setCoordinates(0, 0);

        // When
        walker.stepTowards(destX, destY);

        // Then
        assertThat(walker.x, Matchers.greaterThan(0.0f));
        assertThat(walker.y, Matchers.greaterThan(0.0f));
    }

    @Test
    public void whenWalkerInstructedToWalkToCurrentPosition_walkerDoesNotMove() {
        //Given
        walker.setCoordinates(300, 200);

        //When
        walker.stepTowards(300, 200);

        //Then
        assertEquals(300, walker.x, 0.01);
        assertEquals(200, walker.y, 0.01);
    }

    @Test
    @Parameters({"10, 400",
                 "50, 500",
                 "-20, 310"})
    public void afterSpecifyingDestinationAboveLeftOfWalker_walkerMovesToDestination(int destX, int destY) {
        // Given
        walker.setCoordinates(200, 300);

        // When
        walker.stepTowards(destX, destY);

        // Then
        assertThat(walker.x, Matchers.lessThan(200.0f));
        assertThat(walker.y, Matchers.greaterThan(300.0f));
    }

    @Test
    @Parameters({"350, 200",
                "400, 100",
                "450, 0"})
    public void afterSpecifyingDestinationBelowRightOfWalker_walkerMovesToDestination(int destX, int destY) {
        // Given
        walker.setCoordinates(300, 250);

        // When
        walker.stepTowards(destX, destY);

        // Then
        assertThat(walker.x, Matchers.greaterThan(300.0f));
        assertThat(walker.y, Matchers.lessThan(250.0f));
    }

    @Test
    @Parameters({"150, 100",
                "50, 200",
                "230, 240"})
    public void afterSpecifyingDestinationBelowLeftOfWalker_walkerMovesToDestination(int destX, int destY) {
        // Given
        walker.setCoordinates(300, 250);

        // When
        walker.stepTowards(destX, destY);

        // Then
        assertThat(walker.x, Matchers.lessThan(300.0f));
        assertThat(walker.y, Matchers.lessThan(250.0f));
    }

    @Test
    @Parameters({"100",
                 "200",
                 "240"})
    public void afterSpecifyingDestinationExactlyLeftOfWalker_walkerMovesToDestination(int destX) {
        // Given
        int startX = 300;
        int startY = 250;
        walker.setCoordinates(startX, startY);

        // When
        walker.stepTowards(destX, startY);
        assertThat(walker.x, Matchers.lessThan((float)startX));
        assertThat(walker.x, Matchers.greaterThan((float)destX));
        assertEquals(walker.y, startY, 0.01);

        walker.stepTowards(destX, startY);
        assertThat(walker.x, Matchers.lessThan((float)startX));
        assertThat(walker.x, Matchers.greaterThan((float)destX));
        assertEquals(walker.y, startY, 0.01);

        walker.stepTowards(destX, startY);
        assertThat(walker.x, Matchers.lessThan((float)startX));
        assertThat(walker.x, Matchers.greaterThan((float)destX));
        assertEquals(walker.y, startY, 0.01);
    }

    @Test
    @Parameters({"150",
                 "200",
                 "240"})
    public void afterSpecifyingDestinationExactlyRightOfWalker_walkerMovesToDestination(int destX) {
        // Given
        int startY = 250;
        int startX = 100;
        walker.setCoordinates(startX, startY);

        // When
        walker.stepTowards(destX, startY);
        assertThat(walker.x, Matchers.greaterThan((float)startX));
        assertThat(walker.x, Matchers.lessThan((float)destX));
        assertEquals(walker.y, startY, 0.01);

        walker.stepTowards(destX, startY);
        assertThat(walker.x, Matchers.greaterThan((float)startX));
        assertThat(walker.x, Matchers.lessThan((float)destX));
        assertEquals(walker.y, startY, 0.01);

        walker.stepTowards(destX, startY);
        assertThat(walker.x, Matchers.greaterThan((float)startX));
        assertThat(walker.x, Matchers.lessThan((float)destX));
        assertEquals(walker.y, startY, 0.01);
    }

    @Test
    @Parameters({"150",
                 "200",
                 "240"})
    public void afterSpecifyingDestinationExactlyBelowOfWalker_walkerMovesToDestination(int destY) {
        // Given
        int startX = 90;
        int startY = 100;
        walker.setCoordinates(startX, startY);

        // When
        walker.stepTowards(startX, destY);
        assertThat(walker.y, Matchers.greaterThan((float)startY));
        assertThat(walker.y, Matchers.lessThan((float)destY));

        walker.stepTowards(startX, destY);
        assertThat(walker.y, Matchers.greaterThan((float)startY));
        assertThat(walker.y, Matchers.lessThan((float)destY));

        walker.stepTowards(startX, destY);
        assertThat(walker.y, Matchers.greaterThan((float)startY));
        assertThat(walker.y, Matchers.lessThan((float)destY));
    }

    @Test
    @Parameters({"100",
            "200",
            "240"})
    public void afterSpecifyingDestinationNearlyLeftOfWalker_walkerMovesToDestination(int destX) {
        // Given
        int startX = 300;
        int startY = 250;
        walker.setCoordinates(startX, startY);

        // When
        float destY = startY + 0.01f;

        walker.stepTowards(destX, destY);
        assertThat(walker.x, Matchers.lessThan((float)startX));
        assertThat(walker.x, Matchers.greaterThan((float)destX));
        assertEquals(walker.y, startY, 0.01);

        walker.stepTowards(destX, destY);
        assertThat(walker.x, Matchers.lessThan((float)startX));
        assertThat(walker.x, Matchers.greaterThan((float)destX));
        assertEquals(walker.y, startY, 0.01);

        walker.stepTowards(destX, destY);
        assertThat(walker.x, Matchers.lessThan((float)startX));
        assertThat(walker.x, Matchers.greaterThan((float)destX));
        assertEquals(walker.y, startY, 0.01);
    }

    //TODO write tests for number of steps it should take to reach a destination
    @Test
    public void afterSpecifyingDestinationDirectlyBelow_walkerTakesTwoSteps() {
        int startX = 200;
        int startY = 100;
        walker.setCoordinates(startX, startY);

        // hypotenuse needs to be 40
        float destX = startX + 0.01f;
        int destY = startY - 2 * Walker.STEP_SIZE;

        // Then
        walker.stepTowards(destX, destY);
        assertEquals(startX, walker.x, 0.01);
        assertEquals(startY - Walker.STEP_SIZE, walker.y, 0.01);

        walker.stepTowards(destX, destY);
        assertEquals(startX, walker.x, 0.01);
        assertEquals(startY - 2 * Walker.STEP_SIZE, walker.y, 0.01);
    }
}