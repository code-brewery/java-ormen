package org.codebrewery;

import java.util.List;

/**
 * Created by ejeserl on 9/19/15.
 */
public interface ApiInterface {

    <M extends Model> M save(M model) throws JavaOrmenException;

    <M extends Model> M update(M model) throws JavaOrmenException;

    <M extends Model> M insert(M model) throws JavaOrmenException;

    <M extends Model> void delete(M model) throws JavaOrmenException;

    <M extends Model> M fetch(M model) throws JavaOrmenException;

    <M extends Model> List<M> getAll(Class<M> aClass) throws JavaOrmenException;

}
