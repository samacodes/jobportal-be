package sama.company.jobportalbe.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sama.company.jobportalbe.dto.JobMapper;
import sama.company.jobportalbe.dto.JobSearchResponseDTO;
import sama.company.jobportalbe.models.ApplicationUser;
import sama.company.jobportalbe.models.Job;
import sama.company.jobportalbe.repository.JobRepository;
import sama.company.jobportalbe.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private UserRepository userRepository;

    public List<JobSearchResponseDTO> searchJobs(String job, String location) {
        List<Job> jobs;
        if (job.isEmpty() && location.isEmpty()){
            jobs = jobRepository.findAllJobs();
        } else if (job.isEmpty()) {
            jobs = jobRepository.searchJobsByLocation(location);
        } else if (location.isEmpty()) {
            jobs = jobRepository.searchJobsByTitle(job);
        } else {
            // use the jobRepository to search for jobs
            jobs = jobRepository.searchJobsByTitleAndLocation(job, location);
        }
        System.out.println("Jobs: " + jobs);
        List<Integer> appliedJobIds = getJobsForUser();
        System.out.println("Applied Jobs: " + appliedJobIds);
        // return the jobs
        return jobMapper.mapJobsToDTOList(jobs, appliedJobIds);
    }

    private List<Integer> getJobsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Integer> appliedJobIds;
        if (authentication != null) {
            String currentPrincipalName = authentication.getName();
            System.out.println("Current Principal Name: " + currentPrincipalName);
            ApplicationUser applicationUser = userRepository.findByUsername(currentPrincipalName).isPresent() ? userRepository.findByUsername(currentPrincipalName).get() : new ApplicationUser();
            // get the list of applied job ids
            if (applicationUser.equals(new ApplicationUser())) {
                return List.of();
            }
            System.out.println("User: " + applicationUser.getUserId());
            System.out.println("Applied Jobs: " + applicationUser.getAppliedJobs());
            appliedJobIds = applicationUser.getAppliedJobs().stream().map(Job::getJobId).toList();
        } else {
            appliedJobIds = List.of();
        }
        return appliedJobIds;
    }

    // get all the applied jobs for the user
    public List<JobSearchResponseDTO> getAppliedJobs() {
        List<Integer> appliedJobIds = getJobsForUser();
        // get the list of jobs
        List<Job> jobs = jobRepository.findAllJobs();
        // return the jobs
        return jobMapper.mapJobsToDTOList(jobs, appliedJobIds);
    }

    // apply for a job
    public void applyForJob(Integer jobId) {
        // get the job from the database
        Job job = jobRepository.findByJobId(jobId).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String currentPrincipalName = authentication.getName();
            ApplicationUser applicationUser = userRepository.findByUsername(currentPrincipalName).get();
            applicationUser.getAppliedJobs().add(job);
        }
    }

}
