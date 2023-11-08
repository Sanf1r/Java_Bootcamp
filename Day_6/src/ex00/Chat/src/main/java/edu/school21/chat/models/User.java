package edu.school21.chat.models;

import java.util.List;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRoomsList;
    private List<Chatroom> chatroomSocializesList;

    public User(Long id, String login, String password, List<Chatroom> createdRoomsList,
            List<Chatroom> chatroomSocializesList) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRoomsList = createdRoomsList;
        this.chatroomSocializesList = chatroomSocializesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRoomsList() {
        return createdRoomsList;
    }

    public void setCreatedRoomsList(List<Chatroom> createdRoomsList) {
        this.createdRoomsList = createdRoomsList;
    }

    public List<Chatroom> getChatroomSocializesList() {
        return chatroomSocializesList;
    }

    public void setChatroomSocializesList(List<Chatroom> chatroomSocializesList) {
        this.chatroomSocializesList = chatroomSocializesList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", createdRoomsList="
                + createdRoomsList + ", chatroomSocializesList=" + chatroomSocializesList + "]";
    }

}
