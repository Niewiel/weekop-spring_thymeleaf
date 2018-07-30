package pl.niewiel.weekopspring_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Discovery> getAll(){
        return discoveryRepository.findAll();
    }

    public void add(Discovery discovery){
        discoveryRepository.save(discovery);
    }

    public List<Discovery> findDiscoveryByUser(User user){
        return discoveryRepository.findAllByUser(user);
    }
}
