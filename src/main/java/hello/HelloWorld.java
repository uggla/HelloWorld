package hello;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.apache.log4j.Logger;
import java.util.zip.GZIPInputStream;

public class HelloWorld {
    private static final String INPUT_GZIP_FILE = "ascii.txt.gz";
    private static Logger logger = Logger.getLogger(HelloWorld.class);

	public static void main(String[] args) throws IOException {
		logger.info("Starting application");
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
        GZIPInputStream gzis = null;
        byte[] buffer = new byte[1024];
        StringBuilder result = new StringBuilder();
		int len;
		
        try {
			gzis = new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));
			
			while ((len = gzis.read(buffer)) > 0) {
				System.out.println("reading..." + len + "bytes");
				String str = new String(Arrays.copyOf(buffer, len), "UTF-8");
				result.append(str);
			}
			gzis.close();
			
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e.getMessage());
			System.exit(1);
		} finally {
			if(gzis != null) {
				gzis.close();
			}
			
		}
        
        System.out.println(result);
    }
}
