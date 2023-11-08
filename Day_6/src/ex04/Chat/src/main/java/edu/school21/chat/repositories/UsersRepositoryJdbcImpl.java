package edu.school21.chat.repositories;

import edu.school21.chat.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private CustomDataSource ds;

    public UsersRepositoryJdbcImpl(CustomDataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> result = new ArrayList<User>();
        int move = page * size;
        String query = "with cte_1 AS (\n" +
                "select * from chat.users\n" +
                "order by 1\n" +
                "limit " + size + "offset " + move + "\n" +
                "),cte_3 AS (\n" +
                "select cte_1.id as id_1,\n" +
                "cte_1.login as login_1,\n" +
                "cte_1.password as password_1,\n" +
                "chat.chatroom.id as id_2,\n" +
                "chat.chatroom.name as name_2,\n" +
                "chat.chatroom.owner as owner_2\n" +
                "from cte_1\n" +
                "left join chat.chatroom on cte_1.id = chat.chatroom.owner\n" +
                "), cte_2 as (\n" +
                " select * from chat.socialize\n" +
                "join chat.chatroom on chat.socialize.chatroom = chat.chatroom.id\n" +
                "join chat.users on chat.users.id = chat.chatroom.owner\n" +
                ")\n" +
                "select * from cte_3\n" +
                "join cte_2 on cte_2.user_id = cte_3.id_1\n" +
                "order by 1\n";
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            while (rs.next()) {
                final Long id, chatId, ownerId;
                id = rs.getLong(1);
                chatId = rs.getLong(4);
                ownerId = rs.getLong(10);
                User insert;
                if (!result.stream().anyMatch(item -> id.equals(item.getId()))) {
                    insert = new User(rs.getLong(1), rs.getString(2), rs.getString(3),
                            new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
                    result.add(insert);
                }
                insert = result.stream().filter(item -> id.equals(item.getId())).findAny().get();
                if (chatId != 0) {
                    if (!insert.getCreatedRoomsList().stream()
                            .anyMatch(item -> chatId.equals(item.getId()))) {
                        insert.getCreatedRoomsList().add(new Chatroom(rs.getLong(4), rs.getString(5),
                                new User(insert.getId(), insert.getLogin(), insert.getPassword(), null,
                                        null),
                                null));
                    }
                }
                if (!insert.getChatroomSocializesList().stream()
                        .anyMatch(item -> ownerId.equals(item.getId()))) {
                    insert.getChatroomSocializesList().add(new Chatroom(rs.getLong(10),
                            rs.getString(11),
                            new User(rs.getLong(13), rs.getString(14), rs.getString(15), null, null),
                            null));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return result;
    }
}
