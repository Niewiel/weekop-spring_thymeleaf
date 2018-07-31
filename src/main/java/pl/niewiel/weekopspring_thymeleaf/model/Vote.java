package pl.niewiel.weekopspring_thymeleaf.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long vote_id;


    @ManyToOne(targetEntity = Discovery.class)
    private Discovery discovery;


    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column
    private Timestamp date;

    @Column
    private VoteType voteType;

    public Vote() {
    }

    public Vote(Discovery discovery, User user, VoteType voteType) {
        this.discovery = discovery;
        this.user = user;
        this.date = new Timestamp(new Date().getTime());
        this.voteType = voteType;
    }

    public long getVote_id() {
        return vote_id;
    }

    public void setVote_id(long vote_id) {
        this.vote_id = vote_id;
    }

    public Discovery getDiscovery() {
        return discovery;
    }

    public void setDiscovery(Discovery discovery) {
        this.discovery = discovery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }
}
