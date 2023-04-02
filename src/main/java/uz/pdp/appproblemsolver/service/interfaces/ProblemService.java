package uz.pdp.appproblemsolver.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.ProblemDTO;
import uz.pdp.appproblemsolver.entity.Problem;

import java.util.Map;
import java.util.UUID;

@Repository
public interface ProblemService {
    ApiResult<Page<Problem>> getAllProblems(Pageable pageable);

    ApiResult<Problem> getProblemById(UUID id);

    ApiResult<?> createNewProblem(ProblemDTO problemDTO);

    ApiResult<Page<Problem>> getAllProblemsForAdmin(Pageable pageable);

    ApiResult<?> editProblem(UUID id, ProblemDTO problemDTO);

    ApiResult<?> deleteProblem(UUID id);

    ApiResult<Map<Integer, String>> getAllProblemLevel();
}
