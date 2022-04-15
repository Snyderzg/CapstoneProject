package com.techelevator.dao;

import com.techelevator.model.Notification;

import java.util.List;

public interface NotificationDao {

    List<Notification> findAllByUsername(String userName);

    void updateNotification(Notification notification);

    void addNotification(Notification notification, String userName);

   void deleteNotification(Long notificationId);

}
