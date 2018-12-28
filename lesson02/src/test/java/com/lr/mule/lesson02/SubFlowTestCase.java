package com.lr.mule.lesson02;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.client.LocalMuleClient;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class SubFlowTestCase extends FunctionalTestCase {

	@Override
	protected String[] getConfigFiles() {
		return new String[] {"./src/test/resources/globleconfig.xml","./src/test/resources/03_1sub-flow.xml","./src/test/resources/03_2choice-dispatcher-stubs.xml"};
	}
	@Test
	public void requestDispatcherSubFlowWithClassClient() throws Exception {
		MuleClient muleClient = new MuleClient(muleContext);
		assertThat(
	            	muleClient.send("vm://test-request-dispatcher.in", "foo",
	                Collections.singletonMap("valid", true))
	                .getPayloadAsString(), is("foo:valid"));
        assertThat(
        		muleClient.send("vm://test-request-dispatcher.in", "foo",
                Collections.singletonMap("valid", false))
                .getPayloadAsString(), is("foo:invalid"));
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void requestDispatcherSubFlowWithInterfaceClient() throws Exception {
		LocalMuleClient muleClient = muleContext.getClient();
		assertThat(
	            	muleClient.send("vm://test-request-dispatcher.in", "foo",
	                Collections.singletonMap("valid", true))
	                .getPayloadAsString(), is("foo:valid"));
        assertThat(
        		muleClient.send("vm://test-request-dispatcher.in", "foo",
                Collections.singletonMap("valid", false))
                .getPayloadAsString(), is("foo:invalid"));
	
	}
}
