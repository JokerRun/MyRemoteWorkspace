package com.lr.mule.lesson04;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class TestB extends FunctionalTestCase {

	@Override
	protected String getConfigResources() {
		// TODO Auto-generated method stub
		return "testbyte.xml";
	}

	@Test
	public void name() throws Exception {
		MuleClient muleClient = new MuleClient(muleContext);
//		List<String> send=new ArrayList<>();
//		send.add("1");
		Map<String,String> send=new HashMap<String,String>();
		
		send.put("1", "1");
		MuleMessage response = muleClient.send("vm://test.in", send, null);
		System.out.println("返回的消息为：：：：：：：：：：：：："+response);
		System.out.println("返回的消息为：：：：：：：：：：：：："+response.getPayload());
		System.out.println("返回的消息为：：：：：：：：：：：：："+response.getPayloadAsString());
		assertEquals(send, response.getPayload());
	}
	
	@Test
	public void ser() throws Exception {
		MuleClient muleClient = new MuleClient(muleContext);
//		List<String> send=new ArrayList<>();
//		send.add("1");
		Map<String,String> send=new HashMap<String,String>();
		send.put("1", "1");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(send);
		byte[] serialize=byteArrayOutputStream.toByteArray();
//		byte[] serialize = SerializationUtils.serialize((Serializable) send);
		
		MuleMessage response = muleClient.send("vm://test.in",byteArrayOutputStream , null);
		System.out.println("返回的消息为：：：：：：：：：：：：："+response);
		System.out.println("返回的消息为：：：：：：：：：：：：："+response.getPayload());
		System.out.println("返回的消息为：：：：：：：：：：：：："+response.getPayloadAsString());
		assertEquals(send, response.getPayload());
	}
}
