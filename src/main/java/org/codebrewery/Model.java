package org.codebrewery;

import java.util.List;



public abstract class Model {

    public static ApiInterface api() {
        return ApiFactory.getDefaultImplementation();
    }


    public abstract String getIdentifierValue();


    /**
     * Return a named ApiInterface that is typically different to the default apiImpl.
     *
     * <p>
     * If you are using multiple restApis then each api has a name and maps to a single
     * ApiInterface. You can use this method to get an ApiInterface for another restApi.
     *
     * @param apiImpl
     *          The name of the ApiInterface. If this is null then the default ApiInterface is returned.
     */
    public static ApiInterface api(String apiImpl) {
        return null;
    }

    /**
     * Insert or update this model depending on its state.
     *
     * <p>
     * ApiFactory will detect if this is a new model or a previously fetched model and perform either an
     * insert or an update based on that.
     *
     * You may see this as an smart method for update/insert.
     * @see ApiInterface#save(Model)
     */
    public Model save() throws JavaOrmenException {
        return api().save(this);
    }


    /**
     * Update this model.
     *
     * @see ApiInterface#update(Model)
     */
    public Model update() throws JavaOrmenException{
        return api().update(this);
    }

    /**
     * Insert this model.
     *
     * @see ApiInterface#insert(Model)
     */
    public Model insert() throws JavaOrmenException {
        return api().insert(this);
    }

    /**
     * Delete this model.
     *
     * @see ApiInterface#delete(Model)
     */
    public void delete() throws JavaOrmenException {
        api().delete(this);
    }


    /**
     * Refreshes this model from the API.
     *
     * @see ApiInterface#fetch(Model)
     */
    public Model fetch() throws JavaOrmenException {
        return api().fetch(this);
    }

    /**
     * A concrete implementation of Find.
     * <p>.
     * </p>
     */
    public static class Finder  {

        private final Class<? extends Model> type;


        /**
         * Create with the type of the model model.
         *
         * <pre>{@code
         *
         * public class Customer extends BaseModel {
         *
         *   public static final Finder<Long,Customer> find = new Finder<Long,Customer>(Customer.class);
         *   ...
         *
         * }</pre>
         *
         * <p/>
         * The preferred approach is to instead use <code>Find</code> as below. This approach is more DRY in that it does
         * not require the class literal Customer.class to be passed into the constructor.
         *
         * <pre>{@code
         *
         * public class Customer extends BaseModel {
         *
         *   public static final Find<Long,Customer> find = new Find<Long,Customer>(){};
         *   ...
         *
         * }</pre>
         */

        public Finder( Class<? extends Model> type) {

            this.type = type;
        }

        public List<Model> all() throws JavaOrmenException {
            return api().getAll(this.type);
        }
    }


}