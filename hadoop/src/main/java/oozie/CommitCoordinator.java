package oozie;

import org.apache.oozie.client.CoordinatorJob;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;

import java.util.Properties;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: CommitCoordinator
 * @time: 2018/12/6 13:53
 * @desc:
 */
public class CommitCoordinator {

    public static void main(String[] args) {
        //get a OozieClient for local Oozie
        OozieClient wc = new OozieClient("http://192.168.86.128:11000/oozie");

        //create workflow job configuration
        Properties conf = wc.createConfiguration();
        conf.setProperty(OozieClient.COORDINATOR_APP_PATH, "hdfs://namenode:9000/user/hue/oozie/workspaces/coordinator.xml");

        //set a workflow parameters
        conf.setProperty("nameNode", "hdfs://namenode:9000");
        conf.setProperty("workflowAppUri","hdfs://namenode:9000/user/hue/oozie/workspaces/workflow.xml");
        conf.setProperty("frequency","*/1 * * * *");
//       conf.setProperty("outputDir", "hdfs://192.168.121.35:8020/user/cdhfive/examples/output-data");
        conf.setProperty("user.name", "root");
        conf.setProperty("start","2018-12-06T06:44Z");
        conf.setProperty("end","2018-12-06T18:50Z");
        conf.setProperty("EXEC","ls /");
        conf.setProperty("jobTracker","namenode:9000");
        conf.setProperty("queueName","default");

        //submit and start the workflow job
        try{
            String jobId = wc.run(conf);
            System.out.println("coordinator job submitted");

            //wait until the workflow job finishes
            while(wc.getCoordJobInfo(jobId).getStatus() == CoordinatorJob.Status.RUNNING){
                System.out.println("coordinator job running...");
                try{
                    Thread.sleep(10*1000);
                }catch(InterruptedException e){
                    e.printStackTrace();}
            }
            if(wc.getCoordJobInfo(jobId).getStatus() == CoordinatorJob.Status.FAILED) {
                System.out.println("coordinator job FAILED!");
            }
            if(wc.getCoordJobInfo(jobId).getStatus() == CoordinatorJob.Status.KILLED) {
                System.out.println("coordinator job KILLED!");
            }
            if(wc.getCoordJobInfo(jobId).getStatus() == CoordinatorJob.Status.SUCCEEDED) {
                System.out.println("coordinator job SUCCEEDED!");
            }
            System.out.println(wc.getJobId(jobId));
        }catch(OozieClientException e){e.printStackTrace();}
    }
}