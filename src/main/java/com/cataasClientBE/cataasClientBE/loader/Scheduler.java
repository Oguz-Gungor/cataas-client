package com.cataasClientBE.cataasClientBE.loader;

import com.cataasClientBE.cataasClientBE.repositories.CatImageRepository;
import com.cataasClientBE.cataasClientBE.services.util.FileService;
import jakarta.annotation.PostConstruct;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

    Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private FileService fileService;
    @Autowired
    private CatImageRepository repo;


    @Value("${prunePeriodInSeconds}")
    private Long prunePeriodInSeconds;

    @PostConstruct
    public void init() {
        logger.info("Cron Job {} will be executed each {} seconds", "prune", prunePeriodInSeconds);
        BackgroundJob.scheduleRecurrently(cronStringGeneratorInSeconds(prunePeriodInSeconds), this::pruneJob);
    }

    private String cronStringGeneratorInSeconds(long seconds) {
        return "*/" + seconds + " * * * * *";
    }

    @Job(name = "Prune Job")
    public void pruneJob() {
        logger.info("Cron Job {} is being executed", "prune");
        repo.deleteFiles();
        fileService.prune();
    }
}
