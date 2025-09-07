package backend.AIGymTracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "body_stats")
public class BodyStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "body_fat_percentage")
    private BigDecimal bodyFatPercentage;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDate createdAt;

}