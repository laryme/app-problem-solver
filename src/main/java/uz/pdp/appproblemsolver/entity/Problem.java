package uz.pdp.appproblemsolver.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.appproblemsolver.entity.enums.ProblemLevel;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "problem")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProblemLevel problemLevel;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "problem", fetch = FetchType.EAGER)
    private List<Example> example;
    boolean isDeleted;
}
