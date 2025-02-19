package com.connect2play.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.connect2play.entities.Notification;
import com.connect2play.entities.NotificationType;

public interface INotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByUserId(Long userId);

	List<Notification> findByType(NotificationType type);

	List<Notification> findByIsRead(boolean isRead);

//	 Get all notifications for a user (both read and unread)

	List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

//    Get all active notifications for a user (excluding deleted ones)

	List<Notification> findByUserIdAndIsDeletedFalseOrderBySentTimeDesc(Long userId);

//	Get notifications of a specific type for a user

	List<Notification> findByUserIdAndTypeAndIsDeletedFalseOrderBySentTimeDesc(Long userId, NotificationType type);

//	only unread notifications for a user

	List<Notification> findByUserIdAndIsReadFalseAndIsDeletedFalseOrderBySentTimeDesc(Long userId);

//	 Count unread notifications for a user

	@Query("SELECT COUNT(n) FROM Notification n WHERE n.user.id = :userId AND n.isRead = false")
	long countUnreadNotifications(@Param("userId") Long userId);

//	Mark all notifications as read for a user

	@Modifying
	@Transactional
	@Query("UPDATE Notification n SET n.isRead = true, n.readAt = CURRENT_TIMESTAMP WHERE n.user.id = :userId AND n.isRead = false")
	void markAllNotificationsAsRead(@Param("userId") Long userId);

//	 Delete all read notifications for a user (optional cleanup)

	@Modifying
	@Transactional
	@Query("DELETE FROM Notification n WHERE n.user.id = :userId AND n.isRead = true AND n.isDeleted = false")
	void deleteReadNotifications(@Param("userId") Long userId);
	
	List<Notification> findByUserIdAndIsReadTrue(Long userId);
}
