package share01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.glassfish.grizzly.utils.BufferInputStream;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
public class BufferStreamTransformer extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		String string = null ;
		if(src instanceof BufferInputStream) {
			BufferedReader r = new BufferedReader(
			        new InputStreamReader((BufferInputStream) src, StandardCharsets.UTF_8));

			try {
				string = r.readLine();
				System.out.println("#######################"+string);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		return string;
	}
	
	
}
