package com.tvarkarastis.entity;

import com.tvarkarastis.dao.UserManagerDao;

/**
 * Created by audri on 2017-05-09.
 */
public class Message {
    private int id;
//    private int sender, recipient;
    private User sender, recipient;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

//    public String getRecipientUsername() {
//        return recipient.getUsername();
//    }
//
//    public void setRecipientUsername(String userName) {
//        int userId = UserManagerDao.getUserId(userName);
//        recipient = UserManagerDao.getUser(userId);
//    }

//    public int getSender() {
//        return sender;
//    }
//
//    public void setSender(int sender) {
//        this.sender = sender;
//    }
//
//    public int getRecipient() {
//        return recipient;
//    }
//
//    public void setRecipient(int recipient) {
//        this.recipient = recipient;
//    }
//
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
