package oozie;

import org.apache.oozie.client.CoordinatorJob;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: getJobStatus
 * @time: 2018/12/6 15:01
 * @desc:
 */
public class getJobStatus {
    public static void main(String[] args) {
        OozieClient wc = new OozieClient("http://192.168.86.128:11000/oozie");
        try {
            CoordinatorJob.Status status = wc.getCoordJobInfo("0000029-181206141411972-oozie-root-W").getStatus();
            System.out.println(status);
        } catch (OozieClientException e) {
            e.printStackTrace();
        }

    }
}