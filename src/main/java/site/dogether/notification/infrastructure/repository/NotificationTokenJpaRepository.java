package site.dogether.notification.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.dogether.member.infrastructure.entity.MemberJpaEntity;
import site.dogether.notification.infrastructure.entity.NotificationTokenJpaEntity;

import java.util.List;

public interface NotificationTokenJpaRepository extends JpaRepository<NotificationTokenJpaEntity, Long> {

    List<NotificationTokenJpaEntity> findAllByMember(MemberJpaEntity member);

    void deleteAllByValue(String value);
}
