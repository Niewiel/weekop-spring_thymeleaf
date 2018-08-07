package pl.niewiel.weekopspring_thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User getOne(Long aLong);

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S s);

    User findByUserName(String userName);
    User findByEmail(String email);
    List<User> findAllByAuthorities(Authority authority);

}
