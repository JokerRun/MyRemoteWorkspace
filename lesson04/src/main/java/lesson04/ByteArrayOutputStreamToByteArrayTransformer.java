package lesson04;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class ByteArrayOutputStreamToByteArrayTransformer extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc) throws TransformerException {
		ByteArrayOutputStream out=(ByteArrayOutputStream) src;
		return out.toByteArray();
//		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
//		try {
//			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//			return objectInputStream.readObject();
//		} catch (IOException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}

}
