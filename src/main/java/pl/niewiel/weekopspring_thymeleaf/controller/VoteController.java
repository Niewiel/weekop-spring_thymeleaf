package pl.niewiel.weekopspring_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.niewiel.weekopspring_thymeleaf.model.Discovery;
import pl.niewiel.weekopspring_thymeleaf.model.Vote;
import pl.niewiel.weekopspring_thymeleaf.model.VoteType;
import pl.niewiel.weekopspring_thymeleaf.service.DiscoveryService;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;
import pl.niewiel.weekopspring_thymeleaf.service.VoteService;

@Controller
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    private final UserService userService;
    private final DiscoveryService discoveryService;

    @Autowired
    public VoteController(VoteService voteService, UserService userService, DiscoveryService discoveryService) {
        this.voteService = voteService;
        this.userService = userService;
        this.discoveryService = discoveryService;
    }

    @RequestMapping("/up")
    public String voteUp(long id, Authentication authentication) {
        Discovery discovery = discoveryService.getOne(id);
        if (discovery != null && authentication != null) {
            Vote vote = voteService.getVoteByDiscoveryAndUser(discovery, userService.getByUsername(authentication.getName()));
            if (vote == null) {
                vote = new Vote(discovery, userService.getByUsername(authentication.getName()), VoteType.VOTE_UP);
                discovery.setUpVote(discovery.getUpVote() + 1);
                discoveryService.addOrUpdate(discovery);
                voteService.add(vote);
            } else {
                if (voteService.update(vote, new Vote(discovery, userService.getByUsername(authentication.getName()), VoteType.VOTE_UP))) {
                    discovery.setDownVote(discovery.getDownVote() - 1);
                    discovery.setUpVote(discovery.getUpVote() + 1);
                    discoveryService.addOrUpdate(discovery);
                }
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/down")
    public String voteDown(long id, Authentication authentication) {
        Discovery discovery = discoveryService.getOne(id);
        if (discovery != null && authentication != null) {
            Vote vote = voteService.getVoteByDiscoveryAndUser(discovery, userService.getByUsername(authentication.getName()));
            if (vote == null) {
                vote = new Vote(discovery, userService.getByUsername(authentication.getName()), VoteType.VOTE_DOWN);
                discovery.setDownVote(discovery.getDownVote() + 1);
                discoveryService.addOrUpdate(discovery);
                voteService.add(vote);
            } else {
                if (voteService.update(vote, new Vote(discovery, userService.getByUsername(authentication.getName()), VoteType.VOTE_DOWN))) {
                    discovery.setDownVote(discovery.getDownVote() + 1);
                    discovery.setUpVote(discovery.getUpVote() - 1);
                    discoveryService.addOrUpdate(discovery);
                }
            }
        }
        return "redirect:/";
    }
}
