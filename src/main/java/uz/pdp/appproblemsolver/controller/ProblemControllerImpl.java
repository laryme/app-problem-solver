package uz.pdp.appproblemsolver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appproblemsolver.controller.interfaces.ProblemController;
import uz.pdp.appproblemsolver.payload.ProblemDTO;
import uz.pdp.appproblemsolver.payload.ResultMessage;
import uz.pdp.appproblemsolver.entity.Problem;
import uz.pdp.appproblemsolver.service.interfaces.ProblemService;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProblemControllerImpl implements ProblemController {
    private final ProblemService problemService;

    @Override
    public ResponseEntity<?> getAllProblems(Integer page, int size, String[] sort) {
        if (sort[1].equals("asc"))
            return ResponseEntity
                    .ok()
                    .body(problemService.getAllProblems(
                            PageRequest.of(page, size, Sort.by(sort[0])
                                    .ascending())));

        return ResponseEntity
                .ok()
                .body(problemService.getAllProblems(
                        PageRequest.of(page, size, Sort.by(sort[0])
                                .descending())));
    }

    @Override
    public ResponseEntity<?> getProblemById(UUID id) {
        Optional<Problem> problem = problemService.getProblemById(id);
        if(problem.isPresent())
            return ResponseEntity
                    .ok()
                    .body(problem);

        return ResponseEntity
                .badRequest()
                .body(new ResultMessage(
                        false,
                        "Problem not found with this id"
                ));
    }

    @Override
    public ResponseEntity<ResultMessage> createNewProblem(@Valid ProblemDTO problemDTO, BindingResult result) {
        if(result.hasErrors())
            return ResponseEntity
                    .badRequest()
                    .body(new ResultMessage(
                            false,
                            result.getFieldErrors()
                                    .stream()
                                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                    .collect(Collectors.toList())
                    ));

        ResultMessage resultMessage = problemService.createNewProblem(problemDTO);

        if(resultMessage.isStatus())
            return ResponseEntity
                    .ok()
                    .body(resultMessage);

        return ResponseEntity
                .badRequest()
                .body(resultMessage);
    }
}
