package pl.niewiel.weekopspring_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.repository.DiscoveryRepository;

import java.util.List;

@Service
public class DiscoveryService {

    private final DiscoveryRepository discoveryRepository;

    @Autowired
    public DiscoveryService(DiscoveryRepository discoveryRepository) {
        this.discoveryRepository = discoveryRepository;
    }

    @Transactional
    public List<Discovery> getAll() {
        return discoveryRepository.findAll(Sort.by(Sort.Direction.DESC, "upVote"));
    }

    @Transactional
    public Discovery getOne(Long id) {
        return discoveryRepository.getOne(id);
    }

    @Transactional
    public void addOrUpdate(Discovery discovery) {
        discoveryRepository.saveAndFlush(discovery);
    }

    @Transactional
    public List<Discovery> findDiscoveryByUser(User user) {
        return discoveryRepository.findAllByUser(user);
    }

    @Transactional
    public void flush() {
        discoveryRepository.flush();
    }
}
