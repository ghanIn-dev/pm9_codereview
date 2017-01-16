import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class fileView {

	@Test
	public void test() {


		    try {
		      ////////////////////////////////////////////////////////////////
		    	String source = "C:/Users/간석현/Desktop/codefolio-master/codefolio-master/README.md";
		    	File file = new File(source);
		    	BufferedReader in = new BufferedReader(new FileReader(file));
		    	String s;
		    	int i = 0;
		    	String[] arrayTest = new String[100];
		    	
		      while ((s = in.readLine()) != null) {
		    	  arrayTest[i]=s;
		    	  i++;
		      }
		      in.close();
		      
		      for (String string : arrayTest) {
				System.out.println(string);
			}
		      ////////////////////////////////////////////////////////////////
		    } catch (IOException e) {
		        System.err.println(e); // 에러가 있다면 메시지 출력
		        System.exit(1);
		    }

		  }
}

