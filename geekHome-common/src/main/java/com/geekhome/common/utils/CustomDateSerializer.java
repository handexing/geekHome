package com.geekhome.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Customized Date Serializer Convert Date Object to Date String with format
 * yyyy-MM-dd HH:mm:ss
 * To use this , fields should add annotation :
 * 
 * @JsonSerialize(using = CustomDateSerializer.class)
 * 
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jsonGenerator.writeString(sdf.format(value));
	}
}