package uz.pdp.appproblemsolver.repository;

import jdk.jfr.Percentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appproblemsolver.entity.Problem;

import java.util.UUID;
@Repository
public interface ProblemRepository extends JpaRepository<Problem, UUID> {
}
