package site.dogether.dailytodocertification.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.dogether.common.audit.entity.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "daily_todo_certification_media_url")
@Entity
public class DailyTodoCertificationMediaUrlJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "daily_todo_certification_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DailyTodoCertificationJpaEntity dailyTodoCertification;

    @Column(name = "value", length = 500, nullable = false)
    private String value;

    public DailyTodoCertificationMediaUrlJpaEntity(final DailyTodoCertificationJpaEntity dailyTodoCertification, final String value) {
        this(null, dailyTodoCertification, value);
    }

    public DailyTodoCertificationMediaUrlJpaEntity(
        final Long id,
        final DailyTodoCertificationJpaEntity dailyTodoCertification,
        final String value
    ) {
        this.id = id;
        this.dailyTodoCertification = dailyTodoCertification;
        this.value = value;
    }
}
