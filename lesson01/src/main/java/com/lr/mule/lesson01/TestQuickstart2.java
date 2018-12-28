package com.lr.mule.lesson01;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class TestQuickstart2 extends FunctionalTestCase{

	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "./src/main/app/lesson01.xml";
	}
	@Test
	public void TestQuickStart() throws Exception {
		String msg="Mule Quickstart";
		MuleClient client = muleContext.getClient();
		MuleMessage send = client.send("http://localhost:8081",msg,null);
		MuleMessage message = client.request("jms://quickstart", RECEIVE_TIMEOUT);
		assertNotNull(message);
		assertNull(message.getExceptionPayload());
		assertEquals(msg, message.getPayloadAsString());
	}

}
