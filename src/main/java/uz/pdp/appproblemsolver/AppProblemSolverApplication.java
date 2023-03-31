package uz.pdp.appproblemsolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.appproblemsolver.entity.enums.Permission;

@SpringBootApplication
public class AppProblemSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppProblemSolverApplication.class, args);
        //todo idea : make a feature like stackoverflow
    }

}
