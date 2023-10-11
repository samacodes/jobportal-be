package sama.company.jobportalbe.dto;

import sama.company.jobportalbe.models.Job;

import java.util.List;

public class JobSearchResponseDTO {
    private Job job;

    private List<Job> jobs;

    public JobSearchResponseDTO() {
        super();
    }

    public JobSearchResponseDTO(Job job) {
        super();
        this.job = job;
    }

    public JobSearchResponseDTO(List<Job> jobs) {
        super();
        this.jobs = jobs;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
