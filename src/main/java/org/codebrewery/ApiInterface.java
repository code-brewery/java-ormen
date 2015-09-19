package org.codebrewery;

/**
 * Created by ejeserl on 9/19/15.
 */
public interface ApiInterface {
    void markAsDirty(Model model);

    Model save(Model model) throws JavaOrmenException;

    Model update(Model model) throws JavaOrmenException;

    Model insert(Model model) throws JavaOrmenException;

    void delete(Model model) throws JavaOrmenException;

    Model refresh(Model model) throws JavaOrmenException;
}
