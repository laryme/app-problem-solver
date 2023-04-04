package uz.pdp.appproblemsolver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String input;
    private String output;
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;
}
