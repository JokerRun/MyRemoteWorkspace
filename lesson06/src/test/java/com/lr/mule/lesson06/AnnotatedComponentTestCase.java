package com.lr.mule.lesson06;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class AnnotatedComponentTestCase extends FunctionalTestCase
{

    @Override
    protected String getConfigResources()
    {
        return "4.annotated-component.xml";
    }

    @Test
    public void testSimpleValidator() throws Exception
    {
        MuleClient muleClient = new MuleClient(muleContext);

        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("state", BigDecimal.ZERO);
        
        MuleMessage result = muleClient.send("vm://tax-calculator-service.in", BigDecimal.ZERO, headers);

        assertThat(result.getPayload(), is(instanceOf(BigDecimal.class)));
    }
    
}
