package site.dogether.notification.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.dogether.common.audit.entity.BaseTimeEntity;
import site.dogether.member.infrastructure.entity.MemberJpaEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notification_token")
@Entity
public class NotificationTokenJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberJpaEntity member;

    @Column(name = "token_value", length = 500, nullable = false, unique = true)
    private String tokenValue;

    public NotificationTokenJpaEntity(final MemberJpaEntity member, final String tokenValue) {
        this(null, member, tokenValue);
    }

    public NotificationTokenJpaEntity(
        final Long id,
        final MemberJpaEntity member,
        final String tokenValue
    ) {
        this.id = id;
        this.member = member;
        this.tokenValue = tokenValue;
    }
}
