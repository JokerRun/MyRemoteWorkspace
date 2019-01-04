package com.lr.mule.lesson03;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mule.api.MuleEventContext;
import org.mule.tck.functional.EventCallback;
import org.mule.tck.junit4.FunctionalTestCase;

public class HTTPFunctionalTestCase_FileToHTTP extends FunctionalTestCase {
    @Override
    protected String getConfigResources() {
        return "02_http-config.xml,globle.xml";
    }    
    
    @Test
    public void testCanPostExpenseReportsFromADirectory() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        EventCallback callback = new EventCallback()
        {
            public void eventReceived(MuleEventContext context, Object component)
                    throws Exception
            {
                latch.countDown();
            }
        };

        getFunctionalTestComponent("2.dummyHttpServer").setEventCallback(callback);
        //写入数据
        FileUtils.writeStringToFile(new File("./data/expenses/2/in/foo.xls"),"Foo");
        //监听等待，当伪服务器dummyServer收到数据后，会执行以上代码中定义的回调事件，执行latch的countDown方法，释放锁，返回true
        assertTrue(latch.await(15000,TimeUnit.SECONDS));
    }
 
    
}
