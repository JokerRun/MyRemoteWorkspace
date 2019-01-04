package com.lr.mule.lesson03;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.Diff;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.module.xml.util.XMLUtils;
import org.mule.tck.junit4.FunctionalTestCase;
import org.w3c.dom.Document;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.*;

public class JMSFunctionalTestCase extends FunctionalTestCase {
    @Override
    protected String getConfigResources() {
        return "globle.xml,03_jms-config.xml";
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testCanDispatchExpenseReports() throws Exception {
        muleContext.getClient().dispatch("vm://localhost:dispatchExpenseReports.in", "foo", null);
        MuleMessage response = muleContext.getClient().request("jms://topic:expenses.status", 1500);
        assertNotNull(response);
        assertNotNull(response.getPayload());
        assertEquals("foo", response.getPayloadAsString());
    }

    @Test
    public void testCanListBrews() throws Exception {
        MuleMessage response = muleContext.getClient().send("jms://brews.list", null, null, 15000);
        assertNotNull(response);

        Diff diff = new Diff(FileUtils.readFileToString(new File("src/test/resources/brews.xml")),
                response.getPayloadAsString());
        assertTrue( diff.toString(),diff.similar());
    }

    @Ignore
    public void testAjax() {
        // ToDo figure out how to implement this
    }
}
