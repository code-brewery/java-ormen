package org.codebrewery;

/**
 * Created by jepp3 on 2015-09-05.
 */
public interface ActionCompletedInterface {


    void onDone(AbstractRESTModel model);

    void onError(Throwable e);


}
