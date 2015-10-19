package org.codebrewery;

import java.lang.annotation.Annotation;

/**
 * Created by ejeserl on 9/19/15.
 */
public class  ModelHelper {

    public static String getCollectionUrlLocation(Class<? extends Model> klazz) {

        Annotation annotation = klazz.getAnnotation(ResourceUrl.class);

        if(annotation != null){
            ResourceUrl myAnnotation = (ResourceUrl) annotation;
            return myAnnotation.url();
        }

        // if the user hasn't set any annotation, then use the class name
        return klazz.getSimpleName();
    }
}
