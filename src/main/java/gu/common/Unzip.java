package gu.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {

	public void unzip(String source) throws IOException {
		File file = new File(source);
		ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null){
			if (entry.isDirectory()) {
				System.out.println("디렉토리 :" + entry.getName());
			} else {
				System.out.println("\t파일 :" + entry.getName());
			}
			
			System.out.println("================================================");
		}
		zis.close();
	}
}
