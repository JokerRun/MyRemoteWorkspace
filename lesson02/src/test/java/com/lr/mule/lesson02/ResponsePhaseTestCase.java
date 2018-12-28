package com.lr.mule.lesson02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.drools.command.assertion.AssertEquals;
import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class ResponsePhaseTestCase extends FunctionalTestCase
{
    private static final String TEST_PAYLOAD = "ignored";

    @Override
    protected String getConfigResources()
    {
        return "02_response-phase.xml,globleconfig.xml";
    }
    @Test
    public void testImplicitResponse() throws Exception {
		String payload = "implicit";
		assertEquals(payload,new MuleClient(muleContext).send("http://localhost:8081/accounts", payload,null).getPayloadAsString());
	}
    
    @Test
    public void explicitResponse() throws Exception
    {
        assertThat(
            new MuleClient(muleContext).send("vm://input", "hello", null)
                .getPayloadAsString(), is("hello world wide web"));
    }
    
    
    
    
    
    
    
    
    

    @Test
    public void responseHeader() throws Exception
    {
        MuleClient muleClient = new MuleClient(muleContext);
        //<start id="lis_12_mule_client-2"/>
        MuleMessage result = muleClient.send("http://localhost:8080/json/data",
            TEST_PAYLOAD, null);
        assertThat((String) result.getInboundProperty("Content-Type"),
            is("application/json"));
        //<end id="lis_12_mule_client-2"/>
    }

    @Test
    public void flowVarPersistence() throws Exception {
        MuleClient muleClient = new MuleClient(muleContext);
        final MuleMessage result = muleClient.send("vm://user.fetch", "<user id='123' />", null);
        assertThat((String) result.getInboundProperty("X-User-ID"), is("123"));
        assertThat(result.getPayloadAsString(), is("some_user@/api/users/123"));
    }
}
