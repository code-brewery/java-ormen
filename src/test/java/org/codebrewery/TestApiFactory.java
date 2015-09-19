package org.codebrewery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ejeserl on 9/19/15.
 */
public class TestApiFactory {

    @Test
    public void test_that_ApiFactory_returns_an_default_implementation() {

        assertEquals(DefaultApiImplementation.class,ApiFactory.getDefaultImplementation().getClass());
    }
}
