package uz.pdp.appproblemsolver.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appproblemsolver.dto.ProblemDTO;
import uz.pdp.appproblemsolver.dto.ResultMessage;
import uz.pdp.appproblemsolver.utils.Constants;

import java.util.UUID;

@RequestMapping(ProblemController.BASE_PATH)
public interface ProblemController {
    String BASE_PATH = Constants.BASE_PATH + "/problem";

    @GetMapping("/all")
    ResponseEntity<?> getAllProblems(
            @RequestParam(required = false,defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id,asc") String[] sort);

    @GetMapping("/{id}")
    ResponseEntity<?> getProblemById(@PathVariable UUID id);

    @PreAuthorize(value = "hasAuthority('PROBLEM_CREATE')")
    @PostMapping
    ResponseEntity<ResultMessage> createNewProblem(@RequestBody ProblemDTO problemDTO, BindingResult result);

}
