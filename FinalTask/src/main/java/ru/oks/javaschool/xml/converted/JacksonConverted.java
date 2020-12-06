package ru.oks.javaschool.xml.converted;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * Класс сериализации и десериализации объектов xml.
 */
public class JacksonConverted {
    /**
     * Десериализация объекта из xml в объект нужного типа.
     *
     * @param xml строка xml
     * @param type тип, в который надо конвертировать
     * @param <T> класс типа конвертации
     * @return десериализованный объект
     * @throws IOException исключение
     */
    public <T> T fromXml(String xml, Class<T> type) throws IOException {
        XmlMapper mapper = createXmlMapper();
        return mapper.readValue(xml, type);
    }

    /**
     * Сериализация объекта в xml-строку.
     *
     * @param obj объект, который необходимо сериализовать
     * @return xml строку
     * @throws JsonProcessingException исключение
     */
    public String toXml(Object obj) throws JsonProcessingException {
        XmlMapper mapper = createXmlMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * Создание XmlMapper.
     *
     * @return XmlMapper
     */
    private XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
