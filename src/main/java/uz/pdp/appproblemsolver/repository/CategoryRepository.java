package uz.pdp.appproblemsolver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appproblemsolver.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
