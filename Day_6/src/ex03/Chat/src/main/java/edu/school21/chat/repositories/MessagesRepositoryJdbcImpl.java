package edu.school21.chat.repositories;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private CustomDataSource ds;

    public MessagesRepositoryJdbcImpl(CustomDataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> result = Optional.empty();
        String query = "SELECT * FROM chat.messageobj WHERE id = " + id;
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            if (rs.next()) {
                Long sqlId = rs.getLong(1);
                int sqlAuthor = rs.getInt(2);
                int sqlChatroom = rs.getInt(3);
                String sqlString = rs.getString(4);
                LocalDateTime sqlDateTime = (rs.getTimestamp(5) == null) ? null : rs.getTimestamp(5).toLocalDateTime();
                User sqlUser = findUserbyID((long) sqlAuthor, in);
                Chatroom sqlRoom = findChatroombyID((long) sqlChatroom, in);
                result = Optional.of(new Message(sqlId, sqlUser, sqlRoom, sqlString, sqlDateTime));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return result;
    }

    @Override
    public void save(Message message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String query = "INSERT INTO chat.messageobj VALUES (DEFAULT, " +
                message.getAuthor().getId() + ", " + message.getRoom().getId() +
                ", '" + message.getText() + "', '" + message.getDateTime().format(formatter) + "') RETURNING ID";
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            if (rs.next()) {
                message.setId(rs.getLong(1));
            }
        } catch (SQLException ex) {
            throw new NotSavedSubEntityException(
                    "Author or room have no ID existing in the database assigned, or these IDs are null");
        }
    }

    @Override
    public void update(Message message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String textString = (message.getText() == null) ? "null" : "'" + message.getText() + "'";
        String dateString = (message.getDateTime() == null) ? "null"
                : "'" + message.getDateTime().format(formatter) + "'";
        String query = "UPDATE chat.messageobj SET string = " +
                textString + ", localdatetime = " + dateString +
                " WHERE id = " + message.getId();
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            sm.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private User findUserbyID(Long id, Connection in) throws SQLException {
        String query = "SELECT * FROM chat.users WHERE id = " + id;
        Long sqlId = 0L;
        String sqlLogin = "";
        String sqlPassword = "";
        try {
            Statement sm = in.createStatement();
            ResultSet rs = sm.executeQuery(query);
            if (rs.next()) {
                sqlId = rs.getLong(1);
                sqlLogin = rs.getString(2);
                sqlPassword = rs.getString(3);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return new User(sqlId, sqlLogin, sqlPassword, null, null);
    }

    private Chatroom findChatroombyID(Long id, Connection in) throws SQLException {
        String query = "SELECT * FROM chat.chatroom WHERE id = " + id;
        Long sqlId = 0L;
        String sqlName = "";
        try {
            Statement sm = in.createStatement();
            ResultSet rs = sm.executeQuery(query);
            if (rs.next()) {
                sqlId = rs.getLong(1);
                sqlName = rs.getString(2);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return new Chatroom(sqlId, sqlName, null, null);
    }
}
