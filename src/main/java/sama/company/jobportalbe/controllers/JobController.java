package sama.company.jobportalbe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sama.company.jobportalbe.dto.JobSearchResponseDTO;
import sama.company.jobportalbe.services.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public JobSearchResponseDTO getJobs(@RequestParam(name = "job", required = false) String job, @RequestParam(name = "location", required = false) String location) {
        return jobService.searchJobs(job, location);
    }

}
