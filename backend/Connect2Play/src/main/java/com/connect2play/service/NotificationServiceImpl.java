package com.connect2play.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connect2play.dto.NotificationResponseDTO;
import com.connect2play.entities.Booking;
import com.connect2play.entities.Notification;
import com.connect2play.entities.User;
import com.connect2play.exception.ResourceNotFoundException;
import com.connect2play.repository.INotificationRepository;
import com.connect2play.repository.IUserRepository;

@Service
@Transactional
public class NotificationServiceImpl implements INotificationService {
	@Autowired
	private INotificationRepository notificationRepository;
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public Notification createNotification(NotificationResponseDTO notificationDTO) {
		User user = userRepository.findById(notificationDTO.getSenderId()).orElseThrow(
				() -> new ResourceNotFoundException("User not found with ID: " + notificationDTO.getSenderId()));

		Notification notification = new Notification();
		notification.setUser(user);
		notification.setSenderId(notificationDTO.getSenderId());
		notification.setType(notificationDTO.getType());
		notification.setTitle(notificationDTO.getTitle());
		notification.setMessage(notificationDTO.getMessage());
		notification.setRelatedId(notificationDTO.getRelatedId());
		notification.setRelatedType(notificationDTO.getRelatedType());
		notification.setSentTime(LocalDateTime.now());
		notification.setIsRead(false);
		notification.setIsDeleted(false);

		return notificationRepository.save(notification);
	}

	@Override
	public List<NotificationResponseDTO> getUserNotifications(Long userId) {
		List<Notification> notifications = notificationRepository
				.findByUserIdAndIsDeletedFalseOrderBySentTimeDesc(userId);
		return notifications.stream().map(notification -> modelMapper.map(notification, NotificationResponseDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<NotificationResponseDTO> getUnreadNotifications(Long userId) {
	    List<Notification> unreadNotifications = notificationRepository.findByUserIdAndIsReadFalseAndIsDeletedFalseOrderBySentTimeDesc(userId);
	    
	    return unreadNotifications.stream()
	            .map(notification -> modelMapper.map(notification, NotificationResponseDTO.class))
	            .collect(Collectors.toList());
	}
	   @Override
	    @Transactional
	    public void markAsRead(Long notificationId) {
	        Notification notification = notificationRepository.findById(notificationId)
	                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + notificationId));

	        notification.setIsRead(true);
	        notification.setReadAt(LocalDateTime.now());
	        notificationRepository.save(notification);
	    }
	   @Override
	    @Transactional
	    public void markAllAsRead(Long userId) {
	        List<Notification> unreadNotifications = notificationRepository.findByUserIdAndIsReadFalseAndIsDeletedFalseOrderBySentTimeDesc(userId);
	        unreadNotifications.forEach(notification -> {
	            notification.setIsRead(true);
	            notification.setReadAt(LocalDateTime.now());
	        });
	        notificationRepository.saveAll(unreadNotifications);
	    }
	   
	   @Override
	    @Transactional
	    public void deleteNotification(Long notificationId) {
	        Notification notification = notificationRepository.findById(notificationId)
	                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + notificationId));

	        notification.setIsDeleted(true);
	        notificationRepository.save(notification);
	    }
	   
	   @Override
	    @Transactional
	    public void deleteAllReadNotifications(Long userId) {
	        List<Notification> readNotifications = notificationRepository.findByUserIdAndIsReadTrue(userId);
	        readNotifications.forEach(notification -> notification.setIsDeleted(true));
	        notificationRepository.saveAll(readNotifications);
	    }
	   
	   public void sendBookingConfirmation(Booking booking) {
	        System.out.println("📩 Sending confirmation to user: " + booking.getUser().getEmail());
	    }

	    public void sendRefundNotification(Booking booking) {
	        System.out.println("📩 Sending refund notification to user: " + booking.getUser().getEmail());
	    }

	    public void sendBookingStatusUpdate(Booking booking) {
	        System.out.println("📩 Sending status update for booking: " + booking.getId());
	    }
}
