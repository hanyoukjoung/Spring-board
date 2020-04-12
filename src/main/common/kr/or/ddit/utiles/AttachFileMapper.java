package kr.or.ddit.utiles;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kr.or.ddit.global.GlobalConstant;
import kr.or.ddit.vo.FileItemVO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// @Component 자동으로 빈 등록
@Component
public class AttachFileMapper {
   public List<FileItemVO> mapper(MultipartFile[] items, String bo_no){
      List<FileItemVO> fileItemList = new ArrayList<FileItemVO>();
      
      if (items != null) {
         // 업로드한 파일 정보를 저장하는 곳 : FileItemVO
         FileItemVO fileItemInfo = null;
         for(MultipartFile item : items){
        	 if(item.getOriginalFilename()!=""){
            fileItemInfo = new FileItemVO();
            
            fileItemInfo.setFile_bo_no(bo_no);
            
            // a.png -> a.png
            // D:\temp\a.png -> a.png (getName())  --> 다운로드 창에서 활용
            // item.getOriginalFilename() : 파일의 이름을 취득
            String fileName = FilenameUtils.getName(item.getOriginalFilename());
            // 취득한 이름을 세터를 통해서 저장
            fileItemInfo.setFile_name(fileName);
            
            // a.png    a + UUID랜덤값 + png --> 실제저장소에 파일을 저장할때 쓰임
            String baseName = FilenameUtils.getBaseName(fileName);
            String extension = FilenameUtils.getExtension(fileName);
            
            String genID = UUID.randomUUID().toString().replace("-", "");
            
            String saveFileName = baseName + genID + "." + extension; //실제저장파일명
            fileItemInfo.setFile_save_name(saveFileName);
            
            fileItemInfo.setFile_content_type(item.getContentType());
            fileItemInfo.setFile_size(String.valueOf(item.getSize()));
            
            fileItemList.add(fileItemInfo);
            
            saveFile(saveFileName, item);
        	 }
         }
      }
      return fileItemList;
   }

   private void saveFile(String saveFileName, MultipartFile item) {
      File saveFile = new File(GlobalConstant.FILE_PATH, saveFileName);
      try {
         // transferTo : 파일에다가 저장처리
         item.transferTo(saveFile);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}