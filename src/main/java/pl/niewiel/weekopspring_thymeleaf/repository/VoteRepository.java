package pl.niewiel.weekopspring_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.model.Vote;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findVoteByUserAndDiscovery(User user, Discovery discovery);

    @Override
    List<Vote> findAll();

    @Override
    Vote getOne(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Vote> S saveAndFlush(S s);

    Vote findVoteByDiscoveryAndUser(Discovery discovery, User user);
}
