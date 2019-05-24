package br.com.springbatchexemplo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process-batch")
public class LoadController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job; 
	
	@GetMapping("/user")
	public BatchStatus load() {
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(maps );
		JobExecution jobExecution = null;
		try {
			jobExecution = this.jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			logger.error("Erro ao processar job - {}", e.getCause());
			e.printStackTrace();
		}		
		logger.info("JobExecution -> {}", jobExecution.getStatus());
		isJobRunnig(jobExecution);
		return jobExecution.getStatus();
	}

	private void isJobRunnig(JobExecution jobExecution) {
		logger.info("Batch is Running...");
		while (jobExecution.isRunning()) {
			logger.info("...");
		}
		
	}
}
