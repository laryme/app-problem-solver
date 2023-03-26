package uz.pdp.appproblemsolver.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appproblemsolver.entity.Category;
import uz.pdp.appproblemsolver.entity.Problem;
import uz.pdp.appproblemsolver.entity.Role;
import uz.pdp.appproblemsolver.entity.User;
import uz.pdp.appproblemsolver.entity.enums.Permission;
import uz.pdp.appproblemsolver.entity.enums.ProblemLevel;
import uz.pdp.appproblemsolver.entity.enums.RoleType;
import uz.pdp.appproblemsolver.repository.CategoryRepository;
import uz.pdp.appproblemsolver.repository.ProblemRepository;
import uz.pdp.appproblemsolver.repository.RoleRepository;
import uz.pdp.appproblemsolver.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProblemRepository problemRepository;
    private final CategoryRepository categoryRepository;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String flag;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(flag, "create") || Objects.equals(flag, "create-drop")) {
            Role admin = roleRepository.save(new Role(
                    "ADMIN",
                    RoleType.ADMIN,
                    Set.of(Permission.values())
            ));

            roleRepository.save(new Role(
                    "USER",
                    RoleType.USER,
                    Set.of(
                            Permission.CATEGORY_ONE,
                            Permission.CATEGORY_LIST)
            ));

            User superUser = new User("lary",
                    passwordEncoder.encode("lary2002#"),
                    "lochinbek433@gmail.com",
                    admin);
            superUser.setActive(true);

            Category cat1 = new Category("Algorithm");
            Category cat2 = new Category("Database");

            categoryRepository.save(cat1);
            categoryRepository.save(cat2);

            Problem pro1 = Problem.builder()
                    .title("Problem 1")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro2 = Problem.builder()
                    .title("Problem 2")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.MEDIUM)
                    .category(cat1)
                    .build();

            Problem pro3 = Problem.builder()
                    .title("Problem 3")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro4 = Problem.builder()
                    .title("Problem 4")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro5 = Problem.builder()
                    .title("Problem 5")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro6 = Problem.builder()
                    .title("Problem 6")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro7 = Problem.builder()
                    .title("Problem 7")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro8 = Problem.builder()
                    .title("Problem 8")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro9 = Problem.builder()
                    .title("Problem 9")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro10 = Problem.builder()
                    .title("Problem 10")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro11 = Problem.builder()
                    .title("Problem 11")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            Problem pro12 = Problem.builder()
                    .title("Problem 12")
                    .description("Lorem Ipsum is simply dummy text of the " +
                            "printing and typesetting industry. Lorem Ipsum has been " +
                            "the industry's standard dummy text ever since the 1500s, when an unknown" +
                            " printer took a galley of type and scrambled it to make a type specimen book.")
                    .problemLevel(ProblemLevel.EASY)
                    .category(cat1)
                    .build();

            problemRepository.saveAll(List.of(pro1, pro2, pro3, pro4, pro5, pro6, pro7, pro8, pro9, pro10, pro11, pro12));

            userRepository.save(superUser);
        }
    }
}
