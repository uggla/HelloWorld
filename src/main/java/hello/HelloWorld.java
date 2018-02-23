package hello;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public class HelloWorld {
    private static final String INPUT_GZIP_FILE = "ascii.txt.gz";

	public static void main(String[] args) {
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
        GZIPInputStream gzis;
        byte[] buffer = new byte[1024];
		String result = "";
		int len;
		
        try {
			gzis = new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE));
			
			while ((len = gzis.read(buffer)) > 0) {
				System.out.println("reading..." + len + "bytes");
				String str = new String(Arrays.copyOf(buffer, len), "UTF-8");
				result += str;
			}
			
			
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
        System.out.println(result);
    }
}
