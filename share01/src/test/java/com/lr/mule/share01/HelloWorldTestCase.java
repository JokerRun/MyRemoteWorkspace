package com.lr.mule.share01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class HelloWorldTestCase extends FunctionalTestCase{

	@Override
	protected String getConfigFile() {
		return "1.helloworld.xml";
	}
	@Test
	public void hello() throws Exception {
		MuleClient muleClient = new MuleClient(muleContext);
		HashMap<String,Object> properties = new HashMap<String,Object>();
		MuleMessage message = muleClient.send("http://localhost:8081/",TEST_PAYLOAD,properties);
		assertNull(message.getExceptionPayload());
		assertEquals(message.getPayloadAsString(), "Hello Mule");
		
	}

}
