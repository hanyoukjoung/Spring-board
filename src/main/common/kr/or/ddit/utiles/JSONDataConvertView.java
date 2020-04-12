package kr.or.ddit.utiles;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

public class JSONDataConvertView {
	public void object2JSONConvert(Object target,
			                       HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(target);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(jsonData);
	}
}


