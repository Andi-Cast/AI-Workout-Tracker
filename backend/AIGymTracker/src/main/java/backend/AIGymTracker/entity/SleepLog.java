package backend.AIGymTracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sleep_logs")
public class SleepLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hours_slept")
    private BigDecimal hoursSlept;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDate createdAt;

}