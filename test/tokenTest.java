import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;

public class tokenTest {

	@Test
	public void test() {
		
		String path ="D:\workspace\fileupload\2017\201701120320089005_folder\codefolio-master\codefolio";
		
		StringTokenizer st = new StringTokenizer(path, "\\");
		
		String array[] = new String[st.countTokens()];
		int i = 0;
		
		while(st.hasMoreTokens()){
			array[i]=st.nextToken();
			System.out.println(array[i]);
			i++;
		}
		
		System.out.println(i);
		String parent = array[i-2];
		System.out.println(parent);
		
		assertEquals(i,7);
		assertEquals(parent,"codefolio-master");
		

	}

}
