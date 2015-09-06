package org.codebrewery;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

/**
 * Created by jepp3 on 2015-09-04.
 *
 *
 *
 * Test that your async process is submitted properly. You can mock the object that accepts your async requests
 * and make sure that the submitted job has correct properties, etc.
 *
 * Test that your async callbacks are doing the right things. Here you can mock out the originally submitted job
 * and assume it's initialized properly and verify that your callbacks are correct.
 *
 *
 */
public class TestDefaultConfiguration {


    @Test
    public void testToGenerateFullHttpUrl() throws ExecutionException, InterruptedException {

        RestModelConfiguration config = new RestModelConfiguration();

        config.getRequestBaseUrl();

        assertEquals("http://localhost:8081/api",config.getRequestBaseUrl());

    }


}
