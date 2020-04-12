package kr.or.ddit.utiles;

import java.io.BufferedReader;
import java.sql.Clob;

public class CLOBToStringConvert {
	public static String convert(Object target) throws Exception{
		StringBuffer buffer = new StringBuffer();
		
		BufferedReader reader = new BufferedReader(((Clob)target).getCharacterStream());
		
		String dummy = "";
		while((dummy = reader.readLine()) != null){
			buffer.append(dummy);
		}
		reader.close();
		
		return buffer.toString();
	}
}







