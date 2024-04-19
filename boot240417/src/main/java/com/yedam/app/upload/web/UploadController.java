package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@Value("${file.upload.path}") //내부 코드가 아니라, 외부(프로퍼티즈 등등을 읽어들일 때 사용....) 자신에게 있는 필드, 변수에 담는다.
	private String uploadPath;
	
	@GetMapping("upload")
	//아래의 void : 컨트롤러로 요청하는 이름 그대로를 리턴하겠다는 의미
	//classpath:/templates/upload.html
	public void getUploadPage() {}
	
	@PostMapping("formUpload")
	public String formUploadFile(@RequestPart MultipartFile[] images) {
		for(MultipartFile image : images) {
			if(image.getContentType().startsWith("image") == false) {
				System.out.println("No Image");
				return null;
			}
			String originalName = image.getOriginalFilename();
			System.out.println("original : " + originalName);
			String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
			System.out.println("fileName : " + fileName);
			
			String uploadPath = "C:\\upload"; //자바에서는 \\를 인식하지 못함.
			String saveName = uploadPath + File.separator + fileName; //File.separator : 자바가 인식하는 경로 구분자
			System.out.println("saveName : " + saveName);
			
			Path savePath = Paths.get(saveName); //경로를 바꾸는 작업
			
			//사용자가 업로드할 때의 이름(경로)과 DB에서 저장되는 이름(경로)는 달라야 한다(중복 방지)
			try {
				image.transferTo(savePath);	//transferTo : 실제 파일을 경로에 저장하는 메서드
				// 이걸 쓰지 않는다면 수많은 작업이 요구됨 (닫는 등.. ) 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		return "upload";
	}
	

	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String originalName = uploadFile.getOriginalFilename();
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성 <<= 현업에서 사용하는 방식(서버의 물리적 한계를 고려, 5년뒤 폴더 지운다거나 하는 식..)
	        String folderPath = makeFolder();
	        //UUID
	        String uuid = UUID.randomUUID().toString();
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	        // 위 코드는 미리보기 -> 서버에 ajax로 정보를 보내서 경로를 만들고, 리턴되어 보는 방식
	     }
	    
	    return imageList;
	}
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator); //날짜 포맷에 포함된 /를 자바 경로로 인식할 수 있도록 처리하는 것
		
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		
		return folderPath;
	}
	 
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}

}
