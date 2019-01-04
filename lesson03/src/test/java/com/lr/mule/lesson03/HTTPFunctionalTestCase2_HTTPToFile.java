package com.lr.mule.lesson03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.api.context.notification.EndpointMessageNotificationListener;
import org.mule.api.context.notification.ServerNotification;
import org.mule.tck.junit4.FunctionalTestCase;
import org.python.modules.thread.thread;

public class HTTPFunctionalTestCase2_HTTPToFile extends FunctionalTestCase {

    CountDownLatch outLatch;
    /**
     * 每次启动都初始化文件夹，保证文件数量跟assert数量一致
     */
    @BeforeClass
    public static void setupDirectories() throws Exception {
        File dataDirectory = new File("./data");
        if (dataDirectory.exists()) {
            FileUtils.deleteDirectory(dataDirectory);
        }
        dataDirectory.mkdirs();
        new File("./data/expenses/status").mkdirs();
    }
    /**
     * 启动过程注册监听器notification，该监听器会反复执行onNotification方法，对CountDownLanch进行解锁操作。
     * （因为文件连接器是定时去pull的，所以一旦被监听的文件夹下有文件写入，则会立即执行pull操作，所以需要通过锁来进行监控操作。）
     */
    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        //CountDownLatch参数：${count} the number of times countDown must be invokedbefore threads can pass through await
        //此处使用锁来保证数据是否进入flow 的inboundEndpoint以及outboundEndpoint;当某个流中的inbound获取到数据，则会给notification监听器(EndpointMessageNotificationListener)发送消息提醒
        outLatch = new CountDownLatch(1);
        muleContext.registerListener(new EndpointMessageNotificationListener() {
            public void onNotification(final ServerNotification notification) {
                //当出现3.expenseReportCallback 流事件提醒，且事件类型为结束数据分发时，执行countDown，从而把outLatch的锁打开
                if ("3.expenseReportCallback".equals(notification.getResourceIdentifier())
                		&& "end dispatch".equals(notification.getActionName())) {
                	outLatch.countDown();
                }
            }
        });
    }    
    @Override
    protected String getConfigResources() {
        return "02_http-config.xml,globle.xml";
    }    
    
    @SuppressWarnings("deprecation")
	@Test
    public void testCanPerformCallback() throws Exception {
        assertEquals(0,FileUtils.listFiles(new File("./data/expenses/status"), new String[]{"xml"}, false).size());
        muleContext.getClient().dispatch("http://localhost:8081/expenseReportCallback","FOO",null);
        assertTrue(outLatch.await(15000,TimeUnit.MILLISECONDS));
        assertEquals(1,FileUtils.listFiles(new File("./data/expenses/status"), new String[]{"xml"}, false).size());

    } 
    
}
