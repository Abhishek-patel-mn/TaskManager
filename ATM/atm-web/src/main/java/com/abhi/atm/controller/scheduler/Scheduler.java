package com.abhi.atm.controller.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Abhishek Patel M N Jan 17, 2018
 */
@Component
public class Scheduler {

	final int scheduleTime = 10000;
	@Scheduled(fixedDelay = scheduleTime)
	private void scheduler() {
		System.out.println("************ THIS IS SPRING BOOT SCHEDULER FOR " + scheduleTime + " MILLISECINDS ************");
	}
}
