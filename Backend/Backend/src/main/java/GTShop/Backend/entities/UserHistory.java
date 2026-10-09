package GTShop.Backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_history")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @Column(name = "reward_name", nullable = false)
    private String rewardName;

    @Column(name = "points_spent", nullable = false)
    private Integer pointsSpent;

    @Column(name = "status", nullable = false)
    private String status;

    public UserHistory() {}

    public UserHistory(String rewardName, Integer pointsSpent, String status) {
        this.rewardName = rewardName;
        this.pointsSpent = pointsSpent;
        this.status = status;
    }

    public void setUserProfile(UserProfile userProfile) { this.userProfile = userProfile; }
    public String getRewardName() { return rewardName; }
    public Integer getPointsSpent() { return pointsSpent; }
    public String getStatus() { return status; }
}