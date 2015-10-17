package org.codebrewery;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by jepp3 on 2015-09-06.
 *
 * This json converter converts from AbstractRestModels to json and the other way around.
 *
 * Its designed to only be used by the AbstractRestModel.
 *
 */
public class JSONConverter {

    /**
     * Converts a AbstractRestModel to a json string. It will look at the anotations on the elements.
     *
     * @param restModel
     *            the model that should be marshalled
     * @return
     * @throws IOException
     */
    public static String marshall(Model restModel) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(restModel);
    }

    /**
     * Converts an json String into a object of the specific class type.
     * 
     * @param json
     *            json string
     * @param klazz
     *            the AbstractRestModel class to be used
     * @return
     * @throws IOException
     */
    public static <T extends Model> T unMarshall(String json, Class<T> klazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, klazz);
    }

    public static <T extends Model> List<T> unMarshallList(String json, Class<T> klazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, klazz));
    }

}
