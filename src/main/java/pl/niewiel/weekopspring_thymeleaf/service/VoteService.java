package pl.niewiel.weekopspring_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.model.Vote;
import pl.niewiel.weekopspring_thymeleaf.model.VoteType;
import pl.niewiel.weekopspring_thymeleaf.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }


    public Vote getVoteByDiscoveryAndUser(Discovery discovery, User user) {
        return voteRepository.findVoteByDiscoveryAndUser(discovery, user);
    }

    @Transactional
    public void add(Vote vote) {
        voteRepository.saveAndFlush(vote);
    }

    public boolean update(Vote vote, Vote newVote) {
        if (!vote.equals(newVote)) {
            if (vote.getVoteType() == VoteType.VOTE_DOWN)
                vote.setVoteType(VoteType.VOTE_UP);
            else
                vote.setVoteType(VoteType.VOTE_DOWN);
            voteRepository.saveAndFlush(vote);
            return true;
        } else return false;
    }

}
