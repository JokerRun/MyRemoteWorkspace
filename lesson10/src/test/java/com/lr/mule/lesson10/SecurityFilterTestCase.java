package com.lr.mule.lesson10;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mule.api.ExceptionPayload;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.transport.NullPayload;

public class SecurityFilterTestCase extends AbstractConfigurationLoaderTestCase 
{

	@Override
	protected String getConfigResources() 
	{
		return "3.security-filter.xml";
	}

	@Test
	public void testEndpointAuthenticated() throws Exception 
	{
		MuleClient client = new MuleClient(muleContext);

		MuleMessage result = client.send("http://admin:admin@localhost:8081/secure", "", null);
		assertNull(result.getExceptionPayload());
		assertThat(result.getPayload(),is(not(instanceOf(ExceptionPayload.class))));
		assertThat(result.getPayload(),is(not(instanceOf(NullPayload.class))));
	}
}