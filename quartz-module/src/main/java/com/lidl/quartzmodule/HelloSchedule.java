package com.lidl.quartzmodule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:
 *
 * @author lidongliang
 * @create 2018-01-16 16:36
 */
public class HelloSchedule {

    public static void main(String[] args) throws SchedulerException {
        // 创建一个JobDetail实例，将该实例与HelloJob Class绑定
//        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
//                .withIdentity("myJob", "group1").build();

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob").usingJobData("message", "hello my job1")
                .usingJobData("FloatJobValue", 3.14F)
                .build();

        System.out.println("jobDetail's name:" + jobDetail.getKey().getName());
        System.out.println("jobDetail's group:" + jobDetail.getKey().getGroup());
        System.out.println("jobDetail's jobClass:" + jobDetail.getClass().getName());

        // 创建一个Trigger实例，定义该Job立即执行，并且每隔两秒钟重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
                .usingJobData("message", "hello my trigger")
                .usingJobData("DoubleTriggerValue", 2.0D)
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        // 创建Schedule实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();
        // 打印当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time Is: " + simpleDateFormat.format(date));
        scheduler.scheduleJob(jobDetail, trigger);

    }
}
