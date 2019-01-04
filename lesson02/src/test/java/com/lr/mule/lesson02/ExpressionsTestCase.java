package com.lr.mule.lesson02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.xml.sax.InputSource;

public class ExpressionsTestCase extends FunctionalTestCase{

	@Override
	protected String getConfigResources() {
		// TODO Auto-generated method stub
		return "09_expressions.xml";
	}
	@Test
	public void string() throws Exception {
		MuleClient muleClient = new MuleClient(muleContext);
		MuleMessage result = muleClient.send("vm://string-expression.in","<payment />", null);		
		//将返回消息中的payload转换成xml文档格式，并获取根节点下的tid属性值（预期为UUID）
		String idValue = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder()
				.parse(new InputSource(new StringReader(result.getPayloadAsString()))).getDocumentElement().getAttribute("tid");

		assertThat("Got: " + result.getPayloadAsString(),idValue.split("-").length, is(5));

	}
	@Test
	public void logger() throws Exception {
		MuleClient muleClient = new MuleClient(muleContext);
		MuleMessage result = muleClient.send("vm://logger.in","<invoice id='123' />", null);	
		assertThat(result.getPayloadAsString(), is("123"));
	}

	@Test
	public void ensureAttached() throws Exception {
		MuleClient muleClient = new MuleClient(muleContext);
		MuleMessage messageWithoutAttachment = new DefaultMuleMessage("a", muleContext);
		MuleMessage messageWithAttachment = new DefaultMuleMessage("a", muleContext);
		messageWithAttachment.addOutboundAttachment("data", "bar", "text/plain");
		assertThat(muleClient.send("vm://ensure.attached", messageWithoutAttachment).getPayloadAsString(), is("ERROR: no attachment!"));
		assertThat(muleClient.send("vm://ensure.attached", messageWithAttachment).getPayloadAsString(), is("OK"));
		
		
	}


}
