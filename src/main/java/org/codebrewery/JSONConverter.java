package org.codebrewery;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

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
     * @param restModel the model that should be marshalled
     * @return
     * @throws IOException
     */
    public static String marshall(Model restModel) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(restModel);
    }

    /**
     *  Converts an json String into a object of the specific class type.
     * @param json json string
     * @param klazz the AbstractRestModel class to be used
     * @return
     * @throws IOException
     */
    public static Model unMarshall(String json, Class<? extends Model> klazz) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, klazz);
    }

    public static List<Model> unMarshallList(String json, Class<? extends Model> klazz) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, klazz));
    }



}
