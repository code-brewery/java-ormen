package org.codebrewery;

import java.util.List;

/**
 * Created by ejeserl on 9/19/15.
 */
public interface ApiInterface<T> {

    Model save(Model model) throws JavaOrmenException;

    Model update(Model model) throws JavaOrmenException;

    Model insert(Model model) throws JavaOrmenException;

    void delete(Model model) throws JavaOrmenException;

    Model fetch(Model model) throws JavaOrmenException;

    List<Model> getAll(Class<? extends Model> aClass) throws JavaOrmenException;

}
