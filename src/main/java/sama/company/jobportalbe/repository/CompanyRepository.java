package sama.company.jobportalbe.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sama.company.jobportalbe.models.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    // add methods to get companies
    Optional<Company> findByCompanyId(Integer companyId);

    // add method to find lots of all the companies
    @Query("SELECT c FROM Company c ORDER BY c.companyId DESC")
    List<Company> findAllCompanies();

}
