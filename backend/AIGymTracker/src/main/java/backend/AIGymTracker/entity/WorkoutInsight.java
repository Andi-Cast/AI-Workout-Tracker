package backend.AIGymTracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "workout_insights")
public class WorkoutInsight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "workout_log_id", unique = true)
    private WorkoutLog workoutLog;

    @Column(name = "generated_feedback", columnDefinition = "TEXT")
    private String generatedFeedback;

    @Column(name = "context_data", columnDefinition = "TEXT")
    private String contextData;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}