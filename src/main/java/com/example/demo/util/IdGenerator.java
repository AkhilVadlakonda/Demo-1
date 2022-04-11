package com.example.demo.util;

import org.bson.types.ObjectId;

public class IdGenerator {

	public static String generateId() {
		return String.valueOf(Math.abs(ObjectId.get().hashCode()));
	}

}
