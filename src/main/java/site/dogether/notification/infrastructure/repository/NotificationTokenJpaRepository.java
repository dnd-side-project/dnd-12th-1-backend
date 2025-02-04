package site.dogether.notification.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.dogether.notification.infrastructure.entity.NotificationTokenJpaEntity;

import java.util.List;

public interface NotificationTokenJpaRepository extends JpaRepository<NotificationTokenJpaEntity, Long> {

    List<NotificationTokenJpaEntity> findAllByMember_Id(Long memberId);

    void deleteAllByValue(String value);
}
