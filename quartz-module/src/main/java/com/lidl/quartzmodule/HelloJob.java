package com.lidl.quartzmodule;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:
 *
 * @author lidongliang
 * @create 2018-01-16 16:33
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is: " + simpleDateFormat.format(date));
        System.out.println("Hello World!");


        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("My JobName and group are" + key.getName() + ":" + key.getGroup());
        TriggerKey key1 = jobExecutionContext.getTrigger().getKey();
        System.out.println("My TriggerName and group are" + key1.getName() + ":" + key1.getGroup());

//        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//        JobDataMap jobDataMap1 = jobExecutionContext.getTrigger().getJobDataMap();
//        String message = jobDataMap.getString("message");
//        Float floatJobValue = jobDataMap.getFloat("FloatJobValue");
//        String message1 = jobDataMap1.getString("message");
//        Double doubleTriggerValue = jobDataMap1.getDouble("DoubleTriggerValue");
//        System.out.println("JobMsg FloatMessage is:" + message);
//        System.out.println("JobMsg FloatJobValue is:" + floatJobValue);
//        System.out.println("JobMsg DoubleMessage is:" + message1);
//        System.out.println("JobMsg DoubleJobValue is:" + doubleTriggerValue);
    }
}
