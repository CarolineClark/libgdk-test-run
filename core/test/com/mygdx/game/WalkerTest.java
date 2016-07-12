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
    public void onInitialisation_startAtOrigin() {
        assertEquals(0, walker.x, 0.01);
        assertEquals(0, walker.y, 0.01);
    }

    @Test
    public void coordinatesAfterSteppingRight_givesCorrectCoordinates() {
        walker.stepRight();
        assertEquals(Walker.STEP_SIZE, walker.x, 0.01);
        assertEquals(0, walker.y, 0.01);
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
    public void walkerWalksUpWhenInstructedToDoSo(int sourceX, int sourceY) {
        // Given
        walker.setCoordinates(sourceX, sourceY);

        //When
        walker.stepUp();

        //Then
        assertEquals(sourceX, walker.x, 0.01);
        assertEquals(sourceY + Walker.STEP_SIZE, walker.y, 0.01);
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
    public void notStartingAtOrigin_afterSpecifyingPointWithEqualXAndYToWalkTo_walkerMovesFromOriginTowardsPoint() {
        //Given
        walker.setCoordinates(80, 80);

        // When
        walker.stepTowards(200, 200);

        // Then
        assertEquals(walker.x, walker.y, 0.01);
    }

    @Test
    public void afterSpecifyingPointWithXGreaterThanYToWalkTo_walkerMovesFromOriginTowardsPoint() {
        //Given
        walker.setCoordinates(0, 0);

        walker.stepTowards(300, 200);
        assertThat(walker.x, Matchers.greaterThan(walker.y));
    }

    @Test
    public void afterSpecifyingPointWithYGreaterThanXToWalkTo_walkerMovesFromOriginTowardsPoint() {
        // Given
        walker.setCoordinates(0, 0);

        walker.stepTowards(200, 300);
        assertThat(walker.x, Matchers.lessThan(walker.y));
    }

    @Test
    public void afterSpecifyingPointWithNegativeXPositiveYToWalkTo_walkerMovesFromOriginTowardsPoint() {
        // Given
        walker.setCoordinates(0, 0);

        // When
        walker.stepTowards(-300, 200);

        // Then
        assertThat(walker.x, Matchers.lessThan(0.0f));
        assertThat(walker.y, Matchers.greaterThan(0.0f));
    }

    @Test
    @Parameters({"300, 200",
                 "50, 200",
                 "20, 15"})
    public void afterSpecifyingDestInUpperQuadrant_walkerMovesToDest(int destX, int destY) {
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
    public void afterSpecifyingDestAboveLeftOfWalker_walkerMovesToDest(int destX, int destY) {
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
    public void afterSpecifyingDestBelowRightOfWalker_walkerMovesToDest(int destX, int destY) {
        // Given
        walker.setCoordinates(300, 250);

        // When
        walker.stepTowards(destX, destY);

        // Then
        assertThat(walker.x, Matchers.greaterThan(300.0f));
        assertThat(walker.y, Matchers.lessThan(250.0f));
    }

}