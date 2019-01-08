package com.lr.mule.lesson06;



import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transport.NullPayload;

public class ExternalScriptingComponentTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "7.external-scripting-component.xml";
    }

    @Test
    public void testExternalScript() throws Exception
    {
        MuleClient muleClient = new MuleClient(muleContext);

        MuleMessage result = muleClient.send("vm://external-rhino-message-enritchment-service.in", "", null);

        assertThat(result.getPayload(), is(not(instanceOf(NullPayload.class))));
    }
    
}
