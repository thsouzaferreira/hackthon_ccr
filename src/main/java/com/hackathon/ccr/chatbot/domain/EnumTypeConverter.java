/**
 * 
 */
package com.hackathon.ccr.chatbot.domain;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

/**
 * @author Thiago Souza Ferreira
 */
public abstract class EnumTypeConverter<E extends Enum<E> & EnumType> implements AttributeConverter<E, Integer> {

    private Class<E> enumClass;

    public EnumTypeConverter(Class<E> class1) {
        enumClass = class1;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getId();
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return EnumSet.allOf(enumClass).stream().filter(t -> t.getId() == dbData).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Invalid id %d for enum %s", dbData, enumClass.getName())));
    }

}
