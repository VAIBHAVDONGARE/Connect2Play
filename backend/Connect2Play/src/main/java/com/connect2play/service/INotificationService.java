package com.connect2play.service;

import java.util.List;

import com.connect2play.dto.NotificationResponseDTO;
import com.connect2play.entities.Booking;
import com.connect2play.entities.Notification;

public interface INotificationService {
	Notification createNotification(NotificationResponseDTO notificationDTO);

	List<NotificationResponseDTO> getUserNotifications(Long userId);

	List<NotificationResponseDTO> getUnreadNotifications(Long userId);

	void markAsRead(Long notificationId);

	void markAllAsRead(Long userId);

	void deleteNotification(Long notificationId);

	void deleteAllReadNotifications(Long userId);

	public void sendBookingConfirmation(Booking booking);
	
    public void sendRefundNotification(Booking booking);
    
    public void sendBookingStatusUpdate(Booking booking);



}
