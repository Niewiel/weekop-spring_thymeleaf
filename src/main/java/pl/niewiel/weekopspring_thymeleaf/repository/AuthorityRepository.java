package pl.niewiel.weekopspring_thymeleaf.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,String> {
    @Override
    List<Authority> findAll();

    @Override
    <S extends Authority> S save(S s);

    Authority findByAuthority(String authority);

    @Override
    <S extends Authority> List<S> findAll(Example<S> example);
}
