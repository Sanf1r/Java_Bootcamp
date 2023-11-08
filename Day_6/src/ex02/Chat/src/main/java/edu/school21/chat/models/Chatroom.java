package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> chatroomMessagesList;

    public Chatroom(Long id, String name, User owner, List<Message> chatroomMessagesList) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.chatroomMessagesList = chatroomMessagesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getChatroomMessagesList() {
        return chatroomMessagesList;
    }

    public void setChatroomMessagesList(List<Message> chatroomMessagesList) {
        this.chatroomMessagesList = chatroomMessagesList;
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
        Chatroom other = (Chatroom) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{id=" + id + ",name=\"" + name + "\",creator=" + owner + ",messages="
                + chatroomMessagesList + "}";
    }

}
