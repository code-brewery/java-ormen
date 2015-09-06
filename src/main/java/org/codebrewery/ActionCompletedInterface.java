package org.codebrewery;

/**
 * Created by jepp3 on 2015-09-05.
 */
public interface ActionCompletedInterface {


    void onDone(RESTModel model);

    void onError(Throwable e);


}
