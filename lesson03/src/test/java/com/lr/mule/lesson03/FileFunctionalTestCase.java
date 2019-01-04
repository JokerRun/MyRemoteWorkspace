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

public class FileFunctionalTestCase extends FunctionalTestCase {

    CountDownLatch inLatch;
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
        new File("./data/expenses/1/in").mkdirs();
        new File("./data/expenses/out").mkdirs();
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
        inLatch = new CountDownLatch(1);
        outLatch = new CountDownLatch(1);
        muleContext.registerListener(new EndpointMessageNotificationListener() {
            public void onNotification(final ServerNotification notification) {
            	//当出现 01_file-configFlow 流事件提醒，且时间类型为接受数据时，执行countDown，从而把inLatch的锁打开
                if ("1.copyExpenseReports".equals(notification.getResourceIdentifier())
                        && "receive".equals(notification.getActionName())) {
                    inLatch.countDown();
                }
                //当出现 01_file-configFlow 流事件提醒，且时间类型为结束数据分发时，执行countDown，从而把outLatch的锁打开
                if ("1.copyExpenseReports".equals(notification.getResourceIdentifier())
                		&& "end dispatch".equals(notification.getActionName())) {
                	outLatch.countDown();
                }
            }
        });
    }    
    @Override
    protected String getConfigResources() {
        return "01_file-config.xml";
    }    
    
    @Test
    public void testCanCopyExpenseReports() throws Exception {
        FileUtils.writeStringToFile(new File("./data/expenses/1/in", "expenses.xls"), "a crazy bar tab");
        Collection files = FileUtils.listFiles(new File("./data/expenses"), new String[]{"xls"}, true);
      //监听等待，当伪服务器1.copyExpenseReports收发数据后，会执行通知事件，执行latch的countDown方法，释放锁，返回true
        assertTrue(inLatch.await(15000, TimeUnit.SECONDS)&&outLatch.await(15000, TimeUnit.SECONDS));
//        如果不用锁的话，可以利用
//        Thread.sleep(2000);
        assertEquals(1, files.size());
    }    
    
    
    
}
