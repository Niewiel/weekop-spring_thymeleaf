package pl.niewiel.weekopspring_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.niewiel.weekopspring_thymeleaf.model.Authority;
import pl.niewiel.weekopspring_thymeleaf.repository.AuthorityRepository;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public void add(Authority authority){
        authorityRepository.save(authority);
    }

    @Transactional
    public Authority getByAuthority(String authority){
        return authorityRepository.findByAuthority(authority);
    }
}
