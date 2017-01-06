package gu.admin.test;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class TestVO {

    private String   classno		// 대분류
    				, testcd		// 코드
    				, testnm;		// 코드명

    private List<MultipartFile> uploadfile;
    
	public List<MultipartFile> getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(List<MultipartFile> uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getClassno() {
		return classno;
	}

	public void setClassno(String classno) {
		this.classno = classno;
	}

	public String getTestcd() {
		return testcd;
	}

	public void setTestcd(String testcd) {
		this.testcd = testcd;
	}

	public String getTestnm() {
		return testnm;
	}

	public void setTestnm(String testnm) {
		this.testnm = testnm;
	}
    
}
