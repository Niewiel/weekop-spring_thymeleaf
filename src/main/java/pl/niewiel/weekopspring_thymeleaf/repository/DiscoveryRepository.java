package pl.niewiel.weekopspring_thymeleaf.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.model.User;

import java.util.List;

@Repository
public interface DiscoveryRepository extends JpaRepository<Discovery, Long> {

    List<Discovery> findAllByUser(User user);

    @Override
    <S extends Discovery> S saveAndFlush(S s);

    @Override
    List<Discovery> findAll();


    @Override
    void delete(Discovery discovery);

    @Override
    Discovery getOne(Long aLong);

}
