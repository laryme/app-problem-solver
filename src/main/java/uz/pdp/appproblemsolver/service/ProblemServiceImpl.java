package uz.pdp.appproblemsolver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appproblemsolver.entity.Category;
import uz.pdp.appproblemsolver.entity.Problem;
import uz.pdp.appproblemsolver.entity.enums.ProblemLevel;
import uz.pdp.appproblemsolver.exception.DataNotFoundException;
import uz.pdp.appproblemsolver.payload.ApiResult;
import uz.pdp.appproblemsolver.payload.ProblemDTO;
import uz.pdp.appproblemsolver.repository.CategoryRepository;
import uz.pdp.appproblemsolver.repository.ProblemRepository;
import uz.pdp.appproblemsolver.service.interfaces.ProblemService;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ApiResult<Page<Problem>> getAllProblems(Pageable pageable) {
        return ApiResult
                .successResponse(problemRepository.findByIsDeletedFalse(pageable));
    }

    @Override
    public ApiResult<Problem> getProblemById(UUID id) {
        return ApiResult
                .successResponse(
                        problemRepository.findById(id)
                                .orElseThrow(() -> new DataNotFoundException("Problem not fount with given id"))
                );
    }

    @Override
    public ApiResult<?> createNewProblem(ProblemDTO problemDTO) {
        Category category = categoryRepository.findById(problemDTO.categoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found"));

        Problem problem = Problem.builder()
                .title(problemDTO.title())
                .description(problemDTO.description())
                .problemLevel(ProblemLevel.values()[problemDTO.problemLevel()])
                .category(category)
                .build();

        problemRepository.save(problem);

        return ApiResult
                .successResponse("Problem successfully created");
    }

    @Override
    public ApiResult<Page<Problem>> getAllProblemsForAdmin(Pageable pageable) {
        return ApiResult
                .successResponse(problemRepository.findAll(pageable));
    }

    @Override
    public ApiResult<?> editProblem(UUID id, ProblemDTO problemDTO) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Problem not found with given id"));

        Category category = categoryRepository.findById(problemDTO.categoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found with given id"));

        problem.setTitle(problem.getTitle());
        problem.setDescription(problem.getDescription());
        problem.setProblemLevel(ProblemLevel.values()[problemDTO.problemLevel()]);
        problem.setCategory(category);

        problemRepository.save(problem);

        return ApiResult
                .successResponse("Problem successfully updated");
    }

    @Override
    public ApiResult<?> deleteProblem(UUID id) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Problem not fount with given id"));

        problem.setDeleted(!problem.isDeleted());
        problemRepository.save(problem);

        return ApiResult
                .successResponse("Problem successfully deleted");
    }

    @Override
    public ApiResult<Map<Integer, String>> getAllProblemLevel() {
        return ApiResult
                .successResponse(
                        IntStream.range(0, ProblemLevel.values().length)
                                .boxed()
                                .collect(Collectors.toMap(i -> i, i -> ProblemLevel.values()[i].name())));
    }
}
