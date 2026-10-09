package GTShop.Backend.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id", unique = true, nullable = false)
    private String sessionId;

    @Column(name = "activity_points", nullable = false)
    private Integer activityPoints;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserHistory> history = new ArrayList<>();

    public UserProfile() {}

    public UserProfile(String sessionId) {
        this.sessionId = sessionId;
        this.activityPoints = 4971;
    }

    public void addHistory(UserHistory userHistory) {
        history.add(userHistory);
        userHistory.setUserProfile(this);
    }

    // Getteri și Setteri...
    public Long getId() { return id; }
    public String getSessionId() { return sessionId; }
    public Integer getActivityPoints() { return activityPoints; }
    public void setActivityPoints(Integer activityPoints) { this.activityPoints = activityPoints; }
    public List<UserHistory> getHistory() { return history; }
}