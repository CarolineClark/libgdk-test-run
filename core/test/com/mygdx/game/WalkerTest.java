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

    // TODO Specify a series of point for the walker to start from.
    @Test
    public void walkerWalksDownWhenInstructedToDoSo() {
        // Given
        walker.setCoordinates(80, 50);

        //When
        walker.stepDown();

        //Then
        assertEquals(80, walker.x, 0.01);
        assertEquals(50 - Walker.STEP_SIZE, walker.y, 0.01);
    }

    @Test
    //@Parameters({"17, false", "22, true" })
    public void walkerWalksLeftWhenInstructedToDoSo() {
        // Given
        walker.setCoordinates(80, 50);

        //When
        walker.stepLeft();

        //Then
        assertEquals(80 - Walker.STEP_SIZE, walker.x, 0.01);
        assertEquals(50, walker.y, 0.01);
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

}