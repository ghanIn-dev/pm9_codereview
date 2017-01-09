package gu.admin.test;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gu.common.FileUtil;
import gu.common.FileVO;
import gu.common.LocaleMessage;
import gu.common.SearchVO;
import gu.common.Unzip;

@Controller 
public class TestCtr {

    @Autowired
    private TestSvc testSvc;
    
    static final Logger LOGGER = LoggerFactory.getLogger(TestCtr.class);
    /**
     * 공통 코드 리스트.
     */
    @RequestMapping(value = "/adTestList")
    public String testList(HttpServletRequest request, SearchVO searchVO, ModelMap modelMap) {
        searchVO.pageCalculate( testSvc.selectTestCount(searchVO) ); // startRow, endRow
        List<?> listview  = testSvc.selectTestList(searchVO);
        
        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);
        
        return "admin/test/TestList";
        
    }
    
    /** 
     * 공통 코드 쓰기. 
     */
    @RequestMapping(value = "/adTestForm")
    public String testForm(HttpServletRequest request, TestVO testInfo, ModelMap modelMap) {
        if (testInfo.getClassno() != null) {
            testInfo = testSvc.selectTestOne(testInfo);
        
            modelMap.addAttribute("testInfo", testInfo);
            modelMap.addAttribute("testFormType", "U");
        }
        
        return "admin/test/TestForm";
    }
    
    /**
     * 공통 코드 저장.
     */
    @RequestMapping(value = "/adTestSave")
    public String testSave(HttpServletRequest request, TestVO testInfo, ModelMap modelMap) {
    	String testFormType = request.getParameter("testFormType");
    	String filePath = LocaleMessage.getMessage("info.filePath"); // 파일 업로드 위치
    	  	
        FileUtil fs = new FileUtil(); // 파일저장
        List<FileVO> filelist = fs.saveAllFiles(testInfo.getUploadfile());
        Unzip unzip = new Unzip();	    
        
        	   
        for (int i=0; i<filelist.size(); i ++) {
        	FileVO fvo = (FileVO)filelist.get(i);
	        String realpath = fs.getRealPath(filePath, fvo.getRealname());  // 파일 업로드 위치 + 현재 년도
	        String filenamepath = realpath+fvo.getRealname(); // 파일 업로드 위치 + 현재 년도 + realname
	        String realname = fvo.getRealname();
	        File zipFile = new File(filenamepath);
	        File targetDir = new File(realpath);	        
	        boolean fileNameToLowerCase=false;
	        
	        try {
				unzip.unzipTarget(zipFile, targetDir, fileNameToLowerCase, realname);
				unzip.subDirList(filenamepath+"_folder");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
        
  /*      unzip.subDirList(filenamepath+"_folder");
        
        for (int i=0; i<aaaalist.size(); i ++) {
        	insert dfds
        }
*/
        
    	 if (!"U".equals(testFormType)) { // insert 
    		 TestVO cvo = testSvc.selectTestOne(testInfo);
    		 if (cvo!=null) {
    			 modelMap.addAttribute("msg", "이미 사용중인 코드입니다.");
    			 return "common/message";
    		 }
    	 }
        testSvc.insertTest(testFormType,testInfo ,filelist); 

        return "redirect:/adTestList";
    }

    /**
     * 공통 코드 읽기.
     */
    @RequestMapping(value = "/adTestRead")
    public String testRead(HttpServletRequest request, TestVO testVO, ModelMap modelMap) {
        
    	String classno = testVO.getClassno();
    	
        TestVO testInfo = testSvc.selectTestOne(testVO);
        List<?> listview = testSvc.selectTestFileList(classno);

        modelMap.addAttribute("testInfo", testInfo);
        modelMap.addAttribute("listview", listview);
        
        return "admin/test/TestRead";
    }
    
    /**
     * 공통 코드 삭제.
     */
    @RequestMapping(value = "/adTestDelete")
    public String testDelete(HttpServletRequest request, TestVO testVO) {

        testSvc.deleteTestOne(testVO);
        
        return "redirect:/adTestList";
    }
   
}
