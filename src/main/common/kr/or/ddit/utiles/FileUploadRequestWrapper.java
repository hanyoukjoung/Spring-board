package kr.or.ddit.utiles;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

// FileUploadRequestWrapper is a HttpServletRequestWrapper is a HttpServletRequest
public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
	// 파일업로드 시 폼필드의 값 취득 용이.
	//      "       파일의 정보 취득 용이.
	private boolean multipartFlag = false;
	// 폼필드 값을 저장
	private Map<String, String[]> parameterMap;
	// 파일 정보 저장
	private Map<String, FileItem[]> fileitemMap;
	
	public FileUploadRequestWrapper(HttpServletRequest request) {
		this(request, -1, -1);
	}
	
	public FileUploadRequestWrapper(HttpServletRequest request,
			                        int thresold,
			                        int sizeMax){
		super(request);
		
		parsing(request, thresold, sizeMax);
	}

	private void parsing(HttpServletRequest request, int thresold, int sizeMax) {
		// 파일업로드 요청 여부 : true or false
		this.multipartFlag = ServletFileUpload.isMultipartContent(request);
		
		if(this.multipartFlag){
			this.parameterMap = new HashMap<String, String[]>();
			this.fileitemMap = new HashMap<String, FileItem[]>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// -1 : 무제한
			factory.setSizeThreshold(thresold);
			
			ServletFileUpload fileUploadServlet = new ServletFileUpload(factory);
			// -1 : 무제한
			fileUploadServlet.setSizeMax(sizeMax);
			
			// 클라이언트 : input name=mem_id, mem_pass, mem_name..... => 폼필드
			//             input name=files type=file 파일
			//             폼필드와 파일이 서버 단에서 FileItem 타입으로 변환
			try {
				List<FileItem> items = fileUploadServlet.parseRequest(request);
				
				for(FileItem item : items){
					if(item.getSize() > 0){
						// mem_id,mem_pass,mem_name,files
						// bo_title,bo_nickname....
						String fieldName = item.getFieldName();
						if(item.isFormField()){
							// 폼필드
							String value = item.getString("UTF-8");
							// mem_id=a001
							// mem_id=a001&mem_id=b001
							String[] values = this.parameterMap.get(fieldName);
							if(values == null){
								values = new String[]{ value };
							}else{
								String[] temp = new String[values.length + 1];
								
								// 스왈로우 카피 : 원본 배열의 각각의 인덱스에서 레퍼런스하는 요소값의 주소가
								//                복사 대상 배열에 제공.(원본 배열의 요소값 주소가 복사대상 배열의 요소값 주소와 동일)
								//                원본배열[0] ==> (0x34f5) memberInfo(MemberVO)
								//                복사대상배열[0] ==> (0x34f5)
								// 딥 카피 : 원본 배열의 각각의 인덱스에서 레퍼런스하는 요소값의 주소와
								//          복사 대상 배열의 인데스에서 레퍼런스하는 요소값의 주소가 상이
								//                원본배열[0] ==> (0x23f5) memberInfo(MemberVO)
								//                복사대상배열[0] ==> (0x153) memberInfo(MemberVO)			
								// 1. 원본배열
								// 2. 원본배열의 복사 시작 인덱스
								// 3. 복사대상 배열
								// 4. 복사대상 배열의 복사 시작 인덱스
								// 5. 원본배열의 사이즈
								System.arraycopy(values, 0, temp, 0, values.length);
								
								temp[temp.length - 1] = value;
								
								values = temp;
							}
							this.parameterMap.put(fieldName, values);
						}else{
							// 파일
							FileItem[] values = this.fileitemMap.get(fieldName);
							if(values == null){
								values = new FileItem[]{ item };
							}else{
								FileItem[] temp = new FileItem[values.length + 1];
								System.arraycopy(values, 0, temp, 0, values.length);
								
								temp[temp.length - 1] = item;
								
								values = temp;
							}
							this.fileitemMap.put(fieldName, values);
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getParameter(String name) {
		if(this.multipartFlag){
			String[] values = this.parameterMap.get(name);
			if(values == null){
				return null;
			}else{
				return values[0];
			}
		}else{
			// super == HttpServletRequest
			return super.getParameter(name);
		}
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		if(this.multipartFlag){
			return this.parameterMap;
		}else{
			return super.getParameterMap();
		}
	}

	@Override
	public Enumeration<String> getParameterNames() {
		if(this.multipartFlag){
			return new Enumeration<String>() {
				Iterator<String> keys = parameterMap.keySet().iterator();
				
				@Override
				public boolean hasMoreElements() {
					return keys.hasNext();
				}
				@Override
				public String nextElement() {
					return keys.next();
				}
			};
		}else{
			return super.getParameterNames();
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		if(this.multipartFlag){
			return this.parameterMap.get(name);
		}else{
			return super.getParameterValues(name);
		}
	}
	
	public FileItem getFileItem(String name){
		if(this.multipartFlag){
			FileItem[] items = this.fileitemMap.get(name);
			if(items == null){
				return null;
			}else{
				return items[0];
			}
		}else{
			return null;
		}
	}
	
	public FileItem[] getFileItemValues(String name){
		if(this.multipartFlag){
			return this.fileitemMap.get(name);
		}else{
			return null;
		}
	}
	
	public Map<String, FileItem[]> getFileItemMap(){
		if(this.multipartFlag){
			return this.fileitemMap;
		}else{
			return null;
		}
	}
}









