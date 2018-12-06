package oozie;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowJob;

import java.util.Properties;

/**
 * @author: tongly
 * @contact:wuxin@yscredit.com
 * @file: CommitJob
 * @time: 2018/12/5 16:08
 * @desc:
 */
public class CommitJob {

    public static void main(String[] args) {
        //get a OozieClient for local Oozie
        OozieClient wc = new OozieClient("http://192.168.86.128:11000/oozie");

        //create workflow job configuration
        Properties conf = wc.createConfiguration();
        conf.setProperty(OozieClient.APP_PATH, "hdfs://namenode:9000/user/hue/oozie/workspaces/workflow.xml");

        //set a workflow parameters
        conf.setProperty("nameNode", "hdfs://namenode:9000");
        conf.setProperty("EXEC","ls /");
        conf.setProperty("EXEC","mkdir /tly");
//       conf.setProperty("outputDir", "hdfs://192.168.121.35:8020/user/cdhfive/examples/output-data");
        conf.setProperty("user.name", "root");

        //submit and start the workflow job
        try{
            String jobId = wc.run(conf);
            System.out.println(wc.getJobId(jobId));
            System.out.println("Workflow job submitted");

            //wait until the workflow job finishes
            while(wc.getJobInfo(jobId).getStatus() == WorkflowJob.Status.RUNNING){
                System.out.println("Workflow job running...");
                try{
                    Thread.sleep(10*1000);
                }catch(InterruptedException e){
                    e.printStackTrace();}
            }
            if(wc.getJobInfo(jobId).getStatus() == WorkflowJob.Status.FAILED) {
                System.out.println("Workflow job FAILED!");
            }
            if(wc.getJobInfo(jobId).getStatus() == WorkflowJob.Status.KILLED) {
                System.out.println("Workflow job KILLED!");
            }
            if(wc.getJobInfo(jobId).getStatus() == WorkflowJob.Status.SUCCEEDED) {
                System.out.println("Workflow job SUCCEEDED!");
            }
        }catch(OozieClientException e){e.printStackTrace();}
    }
}