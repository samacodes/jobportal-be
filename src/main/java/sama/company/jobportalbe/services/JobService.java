package sama.company.jobportalbe.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sama.company.jobportalbe.dto.JobSearchResponseDTO;
import sama.company.jobportalbe.models.Job;
import sama.company.jobportalbe.repository.JobRepository;

import java.util.List;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public JobSearchResponseDTO searchJobs(String job, String location) {
        List<Job> jobs;
        if (job == null || job.isEmpty() || location == null || location.isEmpty()){
            jobs = jobRepository.findAllJobs();
        } else {
            // use the jobRepository to search for jobs
            jobs = jobRepository.searchJobsByTitleAndLocation(job, location);
        }
        // return the jobs
        return new JobSearchResponseDTO(jobs);
    }

}
