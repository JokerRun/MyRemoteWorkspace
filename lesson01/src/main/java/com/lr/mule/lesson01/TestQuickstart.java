package com.lr.mule.lesson01;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class TestQuickstart extends FunctionalTestCase{

	@Override
	protected String getConfigFile() {
		// TODO Auto-generated method stub
		return "./src/main/app/lesson01.xml";
	}
	@Test
	public void TestQuickStart() throws Exception {
		String msg="Mule Quickstart";
		MuleClient client = muleContext.getClient();
		MuleMessage received = client.send("vm://qs",getTestMuleMessage(msg));
		MuleMessage receivedFromJMS = client.request("jms://quickstart", RECEIVE_TIMEOUT);
		assertNotNull(receivedFromJMS);
		assertNull(receivedFromJMS.getExceptionPayload());
		assertEquals(msg, receivedFromJMS.getPayloadAsString());
	}

}
