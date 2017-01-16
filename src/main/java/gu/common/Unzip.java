package gu.common;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
	
	private static final int BUFFER_SIZE = 1024 * 2;
	List<FileDirStrVO> listVO = new ArrayList<FileDirStrVO>();

    public void unzipTarget(File zipFile, File targetDir, boolean fileNameToLowerCase, String realname) throws Exception {
        FileInputStream fis = null;
        ZipInputStream zis = null;
        ZipEntry zentry = null;

        try {
            fis = new FileInputStream(zipFile); // FileInputStream
            zis = new ZipInputStream(fis); // ZipInputStream
            File targetFolder = new File(targetDir, realname+"_folder"); // 파일이름과 같은 폴더를 만들 수 없어 _folder를 붙임
            new File(targetDir, realname+"_folder").mkdir(); // realname으로 dir만듬
 

            while ((zentry = zis.getNextEntry()) != null) {
                String fileNameToUnzip = zentry.getName();
                if (fileNameToLowerCase) { // fileName toLowerCase
                    fileNameToUnzip = fileNameToUnzip.toLowerCase();
                }
                
      
                File targetFile = new File(targetFolder, fileNameToUnzip);

                if (zentry.isDirectory()) {// Directory 인 경우
                   new File(targetFile.getAbsolutePath()).mkdir();
                } else { // File 인 경우
                    // parent Directory 생성
                	new File(targetFile.getParent()).mkdir();
                    unzipEntry(zis, targetFile);
                }
            }
        } finally {
            if (zis != null) {
                zis.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
    
    protected static File unzipEntry(ZipInputStream zis, File targetFile) throws Exception {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);

            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = zis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        return targetFile;
    }
    
    
	public List<FileDirStrVO> subDirList(String source, String classno){
		File dir = new File(source); 
		File[] fileList = dir.listFiles();
			
		try{
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i];						
				FileDirStrVO strDo = setPara(file.getPath());
				strDo.setClassno(classno);
				
				if(file.isFile()){ // 파일이 있다면 파일 이름 출력 System.out.println("\t 파일 이름 = " + file.getName());
					strDo.setFileflag(0);
					strDo.setFilePath(file.getPath());
					
					 
				}else if(file.isDirectory()){ // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색 System.out.println("디렉토리 이름 = " + file.getName()); 
					strDo.setFileflag(1);
					
					
					subDirList(file.getCanonicalPath().toString(), classno); 
				}
				
				listVO.add(strDo);
			}
		}catch(IOException e){
			
		}
		return listVO;
	}
		

	public FileDirStrVO setPara(String path){
		
		StringTokenizer st = new StringTokenizer(path,"\\");
		
		String array[] = new String[st.countTokens()];
		int i = 0;
		
		while(st.hasMoreTokens()){
			array[i]=st.nextToken();
			i++;
		}
		
		String name = array[i-1];
		String parent = array[i-2];
		
		FileDirStrVO f = new FileDirStrVO();
		
		f.setName(name);
		f.setDepth(i-6);
		f.setParentName(parent);
	
		return f;
		
	}
	
	
}
