package com.lr.mule.lesson04;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

import com.lr.mule.lesson04.statistics.ActivityReport;

public class AutoTransformerTestCase extends FunctionalTestCase
{

    private static final String ACTIVITY_REPORT_XML = "<ActivityReport><value1>a</value1>" +
    		"<value2>b<value2></ActivityReport>";

	@Override
    protected String getConfigResources()
    {
        return "9.auto-transformer.xml";
    }

    @Test
    public void testAutoTransformer() throws Exception
    {
        MuleClient muleClient = new MuleClient(muleContext);

        String payload = ACTIVITY_REPORT_XML;

        MuleMessage result = muleClient.send("vm://自动转换器.in", payload, null);
        assertThat(result, is(notNullValue()));
        assertThat(result.getPayload(), is(notNullValue()));
        assertThat(result.getPayload(), is(instanceOf(ActivityReport.class)));
    }

}
