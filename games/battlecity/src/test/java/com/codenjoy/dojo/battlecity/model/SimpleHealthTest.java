package com.codenjoy.dojo.battlecity.model;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SimpleHealthTest {

    SimpleHealth underTests;

    @Test
    public void livesShouldNotExceedMaxLivesValue() {
        underTests = givenLives(1, 3);

        underTests.addLives(1);
        underTests.addLives(1);
        underTests.addLives(1);

        assertThat(underTests.getLives(), equalTo(3));
    }

    private SimpleHealth givenLives(int startLives, int maxLives) {
        return new SimpleHealth(startLives, maxLives);
    }
}
