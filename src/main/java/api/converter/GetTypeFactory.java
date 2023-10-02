package api.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class GetTypeFactory {
    public static JavaType typeOf(Class clazz){
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
