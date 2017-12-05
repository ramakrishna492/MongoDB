package com.mongo.exception;

public class DatabaseConnectivityFailedExcepion extends Exception{
	
	public DatabaseConnectivityFailedExcepion(String exception)
	{
		super(exception);
		System.out.println("Database not ound");
	}

}
