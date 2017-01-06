
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.Test;

public class test {

	private static final int BUFFER_SIZE = 1024 * 2;

	
	@Test
	public void test() throws Exception {

		// String source = "C:/Users/간석현/workspace/pms9/src/main/webapp/css/codefolio-master.zip";
		// String source = "C:/Users/간석현/Desktop/dirstructure.zip";
		 String sourcePath = "C:/Users/간석현/Desktop";
		 String source = "C:/Users/간석현/Desktop/codefolio-master";
		// String source = "C:/Users/간석현/Desktop/codefolio-master.zip";
		subDirList(source);
		// unzip(source);
		
		//File zipFile =  new File(source);
		//boolean fileNameToLowerCase = false;		
		//File targetDir = new File(sourcePath);
		
		//unzipTarget(zipFile, targetDir, fileNameToLowerCase);
	}

	public void subDirList(String source) throws FileNotFoundException {
		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];

				if (file.isFile()) {
					// 파일이 있다면 파일 이름 출력
					System.out.println("\t 파일 이름 = " + file.getName());
				} else if (file.isDirectory()) {
					System.out.println("디렉토리 이름 = " + file.getName());
					subDirList(file.getCanonicalPath().toString());
					}
				}
			
		} catch (IOException e) {

		}
	}

	public void unzip(String source) throws IOException {
		File file = new File(source);
		ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {

/*			StringTokenizer st = new StringTokenizer(entry.getName(), "/");
			String[] arr = new String[st.countTokens()];
			int i = 0;
			while(st.hasMoreTokens()){
				arr[i] = st.nextToken();			
				i++;
				System.out.println(arr[st.countTokens()]);	*/
			
			if (entry.isDirectory()) {
				System.out.println("디렉토리 :" + entry.getName());
			}
			else{
				System.out.println("\t 파일 :" + entry.getName());
			}
		
		
		}		
			
			
		zis.close();
		}
	
	


		public void unzip2(String source) throws IOException {
			
			File file = new File(source);
			ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
			ZipEntry entry = null;
			List<String> dirName = new ArrayList<String>();
			List<Integer> dirno = new ArrayList<Integer>();
			int e = 1;
			while ((entry = zis.getNextEntry()) != null){
				if (entry.isDirectory()) {
					dirName.add(new File(entry.getName()).getName());
					dirno.add(e);
					e++;
				} else {
					System.out.println("\t파일 :" + entry.getName());
				}
			}
			zis.close();	
		}

		
	    public static void unzipTarget(File zipFile, File targetDir, boolean fileNameToLowerCase) throws Exception {
	        FileInputStream fis = null;
	        ZipInputStream zis = null;
	        ZipEntry zentry = null;

	        try {
	            fis = new FileInputStream(zipFile); // FileInputStream
	            zis = new ZipInputStream(fis); // ZipInputStream

	            while ((zentry = zis.getNextEntry()) != null) {
	                String fileNameToUnzip = zentry.getName();
	                if (fileNameToLowerCase) { // fileName toLowerCase
	                    fileNameToUnzip = fileNameToUnzip.toLowerCase();
	                }

	                File targetFile = new File(targetDir, fileNameToUnzip);

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

}