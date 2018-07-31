package pl.niewiel.weekopspring_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.model.Vote;
import pl.niewiel.weekopspring_thymeleaf.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }


    public Vote getVoteByDiscoveryAndUser(Discovery discovery, User user){
        return voteRepository.findVoteByDiscoveryAndUser(discovery,user);
    }

    public void add(Vote vote){
        voteRepository.saveAndFlush(vote);
    }


}
