package gu.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
	
	private static final int BUFFER_SIZE = 1024 * 2;

    public void unzipTarget(File zipFile, File targetDir, boolean fileNameToLowerCase, String realname) throws Exception {
        FileInputStream fis = null;
        ZipInputStream zis = null;
        ZipEntry zentry = null;

        try {
            fis = new FileInputStream(zipFile); // FileInputStream
            zis = new ZipInputStream(fis); // ZipInputStream
            File targetFolder = new File(targetDir, realname+"_folder") ;
            new File(targetDir, realname+"_folder").mkdir();
 

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
    
    
	public void subDirList(String source){
		File dir = new File(source); 
		File[] fileList = dir.listFiles(); 
		try{
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile()){
    // 파일이 있다면 파일 이름 출력
					System.out.println("\t 파일 이름 = " + file.getName());
				}else if(file.isDirectory()){
					System.out.println("디렉토리 이름 = " + file.getName());
    // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString()); 
				}
			}
		}catch(IOException e){
			
		}
	}
		

}
