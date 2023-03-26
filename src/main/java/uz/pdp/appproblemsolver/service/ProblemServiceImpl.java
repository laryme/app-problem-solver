package uz.pdp.appproblemsolver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appproblemsolver.dto.ProblemDTO;
import uz.pdp.appproblemsolver.dto.ResultMessage;
import uz.pdp.appproblemsolver.entity.Category;
import uz.pdp.appproblemsolver.entity.Problem;
import uz.pdp.appproblemsolver.repository.CategoryRepository;
import uz.pdp.appproblemsolver.repository.ProblemRepository;
import uz.pdp.appproblemsolver.service.interfaces.ProblemService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Page<Problem> getAllProblems(Pageable pageable) {
        return problemRepository.findAll(pageable);
    }

    @Override
    public Optional<Problem> getProblemById(UUID id) {
        return problemRepository.findById(id);
    }

    @Override
    public ResultMessage createNewProblem(ProblemDTO problemDTO) {
        Optional<Category> category = categoryRepository.findById(problemDTO.categoryId());
        if(category.isPresent()){
            Problem problem = Problem.builder()
                    .title(problemDTO.title())
                    .description(problemDTO.description())
                    .problemLevel(problemDTO.problemLevel())
                    .category(category.get())
                    .build();
            problemRepository.save(problem);
            return new ResultMessage(
                    true,
                    "Problem successfully created"
            );
        }


        return new ResultMessage(false, "Category not found");
    }
}
