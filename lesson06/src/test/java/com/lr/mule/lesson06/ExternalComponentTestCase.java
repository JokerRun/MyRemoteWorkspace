package com.lr.mule.lesson06;



import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transport.NullPayload;


public class ExternalComponentTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "9.external-expression-component.xml";
    }

    @Test
    public void testExpressionComponent() throws Exception
    {
        MuleClient muleClient = new MuleClient(muleContext);
        
        Brew payload = new Brew();

        MuleMessage result = muleClient.send("vm://external-process-stamp.in", payload, null);

        assertThat(result.getPayload(), is(not(instanceOf(NullPayload.class))));
        assertThat(((Brew)result.getPayload()).getProcessedTime(), is(notNullValue()));
    }
    
}
