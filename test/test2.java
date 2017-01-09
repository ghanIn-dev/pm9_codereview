import java.io.File;

import org.junit.Test;

public class test2 {

	@Test
	public void test() {
		String filePath ="C:/Users/간석현/Desktop";
	

        File targetDir = new File(filePath);
        
        
        new File(targetDir, "test").mkdir();
        
	}

}
