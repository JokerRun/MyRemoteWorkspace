package com.lr.mule.lesson03;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.Diff;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class HTTPFunctionalTestCase3_WebService2  {
	/**
	 * 请先启动服务后再执行此测试（解除@Test注释）
	 * @throws Exception
	 */
    @SuppressWarnings("deprecation")
//	@Test
    public void testCanConsumeSOAPService() throws Exception {
    	 MuleClient client = new MuleClient(true);
        String request = FileUtils.readFileToString(new File("src/test/resources/brew.soap.request.xml"));
        //注：此版本中的集成客户端无法向HTTP发送数据，导致本处代码运行错误。 如果要测试，需要通过独立客户端去访问。
        MuleMessage response = client.send("http://localhost:8081/soap", request, null);
        assertNotNull(response);
        Diff diff = new Diff(FileUtils.readFileToString(new File("src/test/resources/brew.soap.response.xml")),
                response.getPayloadAsString());
        assertTrue( diff.toString(),diff.similar());
    }
}