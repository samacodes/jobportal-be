package sama.company.jobportalbe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sama.company.jobportalbe.dto.JobApplyDTO;
import sama.company.jobportalbe.dto.JobSearchResponseDTO;
import sama.company.jobportalbe.services.JobService;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public List<JobSearchResponseDTO> getJobs(@RequestParam(name = "job", required = false) String job, @RequestParam(name = "location", required = false) String location) {
        if (job == null) {
            job = "";
        }
        if (location == null) {
            location = "";
        }
        return jobService.searchJobs(job, location);
    }

    // get all the applied jobs for the user
    @GetMapping("/applied")
    public List<JobSearchResponseDTO> getAppliedJobs() {
        return jobService.getAppliedJobs();
    }

    // apply for a job
    @PostMapping("/apply")
    public void applyForJob(@RequestBody JobApplyDTO body) {
        jobService.applyForJob(body.getJobId());
    }

}
