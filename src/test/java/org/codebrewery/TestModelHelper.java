package org.codebrewery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ejeserl on 9/19/15.
 */
public class TestModelHelper {

    @ResourceUrl(url="location")
    class M extends  Model {
        @Override
        public String getIdentifierValue() {
            return "id";
        }
    };

    class MW extends  Model {
        @Override
        public String getIdentifierValue() {
            return "id";
        }
    };

    @Test
    public void test_that_an_model_with_annotation_gets_that_model() {
        Model model = new M();
        String url = ModelHelper.getCollectionUrlLocation(model.getClass());
        assertEquals("location",url);
    }
    @Test
    public void test_that_an_model_without_anotions_gets_an_url_containing_the_class_name() {

        Model model = new MW();
        String url = ModelHelper.getCollectionUrlLocation(model.getClass());
        assertEquals("MW",url);

    }
}
