package backend.AIGymTracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "workout_feedback")
public class WorkoutFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "workout_log_id")
    private WorkoutLog workoutLog;

    @Column(name = "energy_level")
    private Integer energyLevel;

    @Column(name = "mood")
    private Integer mood;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "notes")
    private String notes;

}