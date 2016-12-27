import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.Test;

public class test {

	@Test
	public void test() throws IOException {

		 String source = "C:/Users/간석현/workspace/pms9/src/main/webapp/css/codefolio-master.zip";
		// String source = "C:/Users/간석현/Desktop/dirstructure";
		// String source = "C:/Users/간석현/Desktop/computer";
		// String source = "C:/Users/간석현/Desktop/computer.zip";
		//subDirList(source);
		 unzip(source);
	}

	public void subDirList(String source) throws FileNotFoundException {
		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];

				if (file.isFile()) {
					// 파일이 있다면 파일 이름 출력
					System.out.println("파일 이름 = " + file.getName());
				} else if (file.isDirectory()) {
					System.out.println("디렉토리 이름 = " + file.getName());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					if (file.exists()) {
						System.out.println("내 자식!");
						subDirList(file.getCanonicalPath().toString());
					}
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

			

			StringTokenizer st = new StringTokenizer(entry.getName(), "/");
			String[] arr = new String[st.countTokens()];
			int i = 0;
			while(st.hasMoreTokens()){
				arr[i] = st.nextToken();			
				i++;
				System.out.println(arr[st.countTokens()]);
			}		
			
		
			/*while(st.hasMoreTokens()){
				System.out.println(st.nextToken());
				System.out.println(st.countTokens());
			}
*/
			
/*			if (entry.isDirectory()) {

				System.out.println("디렉토리 :" + entry.getName());

			} else {
				System.out.println("파일 : " + entry.getName() );
			}*/
		}
		zis.close();
	}
}