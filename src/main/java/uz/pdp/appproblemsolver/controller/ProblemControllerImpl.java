package uz.pdp.appproblemsolver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appproblemsolver.controller.interfaces.ProblemController;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.ProblemDTO;
import uz.pdp.appproblemsolver.service.interfaces.ProblemService;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProblemControllerImpl implements ProblemController {
    private final ProblemService problemService;

    @Override
    public ApiResult<?> createNewProblem(@Valid ProblemDTO problemDTO) {
        return problemService.createNewProblem(problemDTO);
    }

    @Override
    public ApiResult<?> getProblemById(UUID id) {
        return problemService.getProblemById(id);
    }

    @Override
    public ApiResult<?> getAllProblems(Integer page, int size, String[] sort) {
        return problemService.getAllProblems(
                PageRequest.of(
                        page,
                        size,
                        Objects.equals(sort[1], "desc") ?
                                Sort.by(sort[0])
                                        .descending() :
                                Sort.by(sort[0])
                                        .ascending()));
    }


    @Override
    public ApiResult<?> getAllProblemsForAdmin(Integer page, int size, String[] sort) {
        return problemService.getAllProblemsForAdmin(
                PageRequest.of(
                        page,
                        size,
                        Objects.equals(sort[1], "desc") ?
                                Sort.by(sort[0])
                                        .descending() :
                                Sort.by(sort[0])
                                        .ascending()));
    }

    @Override
    public ApiResult<?> editProblem(UUID id, ProblemDTO problemDTO) {
        return problemService.editProblem(id, problemDTO);
    }

    @Override
    public ApiResult<?> deleteProblem(UUID id) {
        return problemService.deleteProblem(id);
    }

    @Override
    public ApiResult<Map<Integer, String>> getAllProblemLevel() {
        return problemService.getAllProblemLevel();
    }

}
