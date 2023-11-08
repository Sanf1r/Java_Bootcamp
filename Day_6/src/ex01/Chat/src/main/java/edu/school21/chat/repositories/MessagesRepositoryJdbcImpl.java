package edu.school21.chat.repositories;

import java.sql.*;
import java.time.LocalDateTime;
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
                Long sqlAuthor = rs.getLong(2);
                Long sqlChatroom = rs.getLong(3);
                String sqlString = rs.getString(4);
                LocalDateTime sqlDateTime = rs.getTimestamp(5).toLocalDateTime();
                User sqlUser = findUserbyID(sqlAuthor, in);
                Chatroom sqlRoom = findChatroombyID(sqlChatroom, in);
                result = Optional.of(new Message(sqlId, sqlUser, sqlRoom, sqlString, sqlDateTime));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return result;
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
