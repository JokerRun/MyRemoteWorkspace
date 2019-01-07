package com.lr.mule.lesson04;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.dom4j.Document;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.module.xml.util.XMLUtils;
import org.mule.tck.junit4.FunctionalTestCase;

public class TransRef extends FunctionalTestCase
{

    String sampleXml = "<sample/>";

    @Override
    protected String getConfigResources()
    {
        return "globle.xml,2.trans-refs.xml";
    }

    @Test
    public void testRefs() throws Exception
    {
        final MuleClient muleClient = new MuleClient(muleContext);

        final MuleMessage result = muleClient.send("vm://transformerRefsFlow.in", sampleXml, null);
        assertThat(result, is(notNullValue()));
        System.out.println("result为啊啊啊啊啊啊啊： "+result);
        String resultString = result.getPayloadAsString();
        System.out.println("resultString为啊啊啊啊啊啊啊： "+resultString);
        assertThat(result.getPayload(), is(notNullValue()));
    }

    @Test
    public void testMPs() throws Exception
    {
        final MuleClient muleClient = new MuleClient(muleContext);

        final MuleMessage result = muleClient.send("vm://transformerFlow.in", sampleXml, null);
        assertThat(result, is(notNullValue()));
        assertThat(result.getPayload(), is(notNullValue()));
        System.out.println("result为啊啊啊啊啊啊啊： "+result);
        String resultString = result.getPayloadAsString();
        System.out.println("resultString为啊啊啊啊啊啊啊： "+resultString);
        Document resultDocument = XMLUtils.toDocument(resultString, muleContext);
        System.out.println("resultDocument为啊啊啊啊啊啊啊： "+resultDocument);
        assertThat(resultDocument, is(notNullValue()));
    }
}
