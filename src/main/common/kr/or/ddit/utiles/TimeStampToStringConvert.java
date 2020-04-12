package kr.or.ddit.utiles;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

// TimeStamp : 년,월,일,시,분,초,플랫(밀리세컨즈)
//             플랫 : default 6자리(최대 9자리 표현 가능)
public class TimeStampToStringConvert {
	public static String convert(Object target){
		Date date = new Date(((Timestamp)target).getTime());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
}







