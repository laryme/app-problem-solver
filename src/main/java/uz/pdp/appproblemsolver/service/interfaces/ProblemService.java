package uz.pdp.appproblemsolver.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uz.pdp.appproblemsolver.payload.ProblemDTO;
import uz.pdp.appproblemsolver.payload.ResultMessage;
import uz.pdp.appproblemsolver.entity.Problem;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProblemService {
    Page<Problem> getAllProblems(Pageable pageable);

    Optional<Problem> getProblemById(UUID id);

    ResultMessage createNewProblem(ProblemDTO problemDTO);
}
