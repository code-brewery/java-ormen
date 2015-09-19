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
    public void save() throws JavaOrmenException {
        api().save(this);
    }


    /**
     * Update this model.
     *
     * @see ApiInterface#update(Model)
     */
    public void update() throws JavaOrmenException{
        api().update(this);
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
     * @see ApiInterface#refresh(Model)
     */
    public void fetch() throws JavaOrmenException {
        api().refresh(this);
    }

    /**
     * A concrete implementation of Find.
     * <p>.
     * </p>
     * @param <I> type of the Id property
     * @param <T> type of the model model
     */
    public static class Finder<I, T> extends Find<I, T> {

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
        public Finder(Class<T> type) {
            super(null, type);
        }

        /**
         * Create with the type of the model model and specific server name.
         */
        public Finder(String serverName, Class<T> type) {
            super(serverName, type);
        }

    }

    /**
     * <p>
     * Typically a Find instance is defined as a public static field on an Model class to provide a
     * nice way to write queries.
     *
    **/
    public static abstract class Find<I, T> {

        /**
         * The Model model type.
         */
        private final Class<T> type;

        /**
         * The name of the ApiInterface, null for the default server.
         */
        private final String serverName;

        /**
         * Creates a finder for model of type <code>T</code> with ID of type <code>I</code>.
         * <p/>
         * Typically you create Find as a public static field on each Model as the example below.
         *
         * <p/>
         * Note that Find is an abstract class and hence <code>{}</code> is required. This is done so
         * that the type (class literal) of the Model can be derived from the generics parameter.
         *
         * <pre>{@code
         *
         *
         * public class Customer extends BaseModel {
         *
         *   // Note the trailing {} as Find is an abstract class.
         *   // We do this so that we can derive the type literal Customer.class
         *   // via reflection
         *   public static final Find<Long,Customer> find = new Find<Long,Customer>(){};
         *   ...
         *
         * }</pre>
         * <p/>
         * This enables you to write code like:
         * <pre>{@code
         *
         * Customer customer = Customer.find.byId(42L);
         *
         * List<Customer> customers =
         *     Customer.find
         *        .select("name, email, dateOfBirth")
         *        .findList();
         *
         * }</pre>
         *
         *
         */

        /**
         * Construct passing the class literal type of the model type.
         */
        protected Find(String serverName, Class<T> type) {
            this.serverName = serverName;
            this.type = type;
        }

        /**
         * Return the underlying 'default' ApiInterface.
         *
         * <p>
         * This provides full access to the API such as explicit transaction demarcation etc.
         *
         */
        public ApiInterface api() {

            return ApiFactory.getDefaultImplementation();
        }


        /**
         * Retrieves all entities of the given type.
         *
         */
        public List<T> all() {

            return null;
        }



    }
}