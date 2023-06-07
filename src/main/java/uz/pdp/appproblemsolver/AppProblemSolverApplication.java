package uz.pdp.appproblemsolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.appproblemsolver.entity.User;

@SpringBootApplication
public class AppProblemSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppProblemSolverApplication.class, args);
        //todo idea : make a feature like stackoverflow
        //todo idea : add a connecting with admin sent users message to telegram also web platform and add status message read or unread
        //todo write special dto

        User.builder()
                .build();

    }

}
