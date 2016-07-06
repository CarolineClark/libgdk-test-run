package com.mygdx.game;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


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
        //Given
        walker.setCoordinates(0, 0);

        walker.stepTowards(200, 300);
        assertThat(walker.x, Matchers.lessThan(walker.y));
    }

    @Test
    public void afterSpecifyingPointWithNegativeXPositiveYToWalkTo_walkerMovesFromOriginTowardsPoint() {
        //Given
        walker.setCoordinates(0, 0);

        walker.stepTowards(-300, 200);
        assertThat(walker.x, Matchers.lessThan(0.0f));
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