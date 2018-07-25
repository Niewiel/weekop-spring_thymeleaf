package pl.niewiel.weekopspring_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.niewiel.weekopspring_thymeleaf.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
}
