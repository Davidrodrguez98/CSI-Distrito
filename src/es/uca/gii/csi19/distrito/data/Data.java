package es.uca.gii.csi19.distrito.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import es.uca.gii.csi19.distrito.util.Config;


public class Data {

public static String getPropertiesUrl() { return "./db.properties"; }
	
	public static Connection Connection() throws Exception{
		try {
			
			Properties properties = Config.Properties(getPropertiesUrl());
			return DriverManager.getConnection(
					properties.getProperty("jdbc.url"),
					properties.getProperty("jdbc.username"),
					properties.getProperty("jdbc.password"));
		}catch(Exception ee) {
			throw ee;
		}
	}
	
	public static void LoadDriver() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException {
		Class.forName(Config.Properties(Data.getPropertiesUrl())
				.getProperty("jdbc.driverClassName")).newInstance();
	}
	
	/**
	 * Conversor de Booleano a formato Sql, que en este caso es un entero
	 * @param b
	 * @return int, si b es true devuelve 1 y si es false, 0
	 */
	public static int Boolean2Sql(boolean b) {
		if(b) 
			return 1;
		
		return 0;
	}
	
	/**
	 * Convierte un String a formato SQL
	 * @param s
	 * @param bAddQuotes
	 * @param bAddWildcards
	 * @return String
	 */
	public static String String2Sql(String s, boolean bAddQuotes, boolean bAddWildcards) {
		String sResult = s;

		sResult = sResult.replace("'", "''");
		
		if(bAddWildcards) {
			sResult = "%" + sResult;
			sResult = sResult + "%";
		}
		
		if(bAddQuotes) {
			sResult = "'" + sResult;
			sResult = sResult + "'";
		}
		
		return sResult;
	}
}
