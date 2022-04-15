package com.techelevator.dao;

import com.techelevator.model.Notification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcNotificationDao implements NotificationDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcNotificationDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Notification> findAllByUsername(String userName) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM notification\n" +
                "WHERE user_id =(Select user_id FROM users WHERE username = ? );";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        while (results.next()){
            Notification notification = mapRowToNotification(results);
            notifications.add(notification);
        }
        return notifications;
    }


    @Override
    public void updateNotification(Notification notification) {
        String sql = "UPDATE notification\n" +
                "SET message = ?, user_id = ?, read = ?\n" +
                "WHERE notification_id = ?;";
        jdbcTemplate.update(sql,notification.getMessage(),notification.getUser_id(),
                notification.isRead(),notification.getNotification_id());
        return;
    }

    @Override
    public void addNotification(Notification notification, String userName) {
        String sql = "INSERT INTO notification \n" +
                "(user_id, message, read)\n" +
                "VALUES ((Select user_id FROM users WHERE username = ? ), ?, FALSE);";
        jdbcTemplate.update(sql,userName,notification.getMessage());
        return;
    }

    @Override
    public void deleteNotification(Long notificationId) {
    return;
    }

    private Notification mapRowToNotification(SqlRowSet rs){
        Notification notification = new Notification();
        notification.setNotification_id(rs.getLong("notification_id"));
        notification.setUser_id(rs.getLong("user_id"));
        notification.setMessage(rs.getString("message"));
        notification.setRead(rs.getBoolean("read"));
        return notification;
    }


}
