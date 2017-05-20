package com.tvarkarastis.controller;

import com.tvarkarastis.dao.MessageManagerDao;
import com.tvarkarastis.dao.UserManagerDao;
import com.tvarkarastis.entity.Message;
import com.tvarkarastis.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Edvinas on 2017-05-20.
 */
@Controller
public class MessageController {

    @GetMapping("/messages")
    public String messages(ModelMap modelMap, HttpSession session) {
        int userID = UserManagerDao.getUserId((String)session.getAttribute("username"));
        List<Message> receivedMessages = MessageManagerDao.getMessagesReceived(userID);
        modelMap.put("receivedMessages", receivedMessages);
        return "messages";
    }

    @GetMapping("/createMessage")
    public String createMessage(ModelMap modelMap, HttpSession session) {
        Message newMessage = new Message();
        int senderID = UserManagerDao.getUserId((String)session.getAttribute("username"));
//        newMessage.setSender(senderID);

//----------------------
        User sender = UserManagerDao.getUser(senderID);
        newMessage.setSender(sender);
        newMessage.setRecipient(new User());

        newMessage.setId(12314);
//----------------------


        modelMap.put("newMessage",newMessage);
        return "newMessageForm";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(Message newMessage, ModelMap modelMap) {
//        int messageSendStatus = MessageManagerDao.send(newMessage.getSender(),
//                newMessage.getText(), newMessage.getRecipient());
//----------------------
        int userID = UserManagerDao.getUserId(newMessage.getRecipient().getUsername());
        User recipient = UserManagerDao.getUser(userID);
        newMessage.setRecipient(recipient);

        int messageSendStatus = MessageManagerDao.send(newMessage.getSender().getId(),
                newMessage.getText(), newMessage.getRecipient().getId());
//----------------------
        modelMap.put("messageSendStatus", messageSendStatus);
        return "redirect:/messages";
    }

    @GetMapping("/reply/id={recipientID}")
    public String sendMessage(@PathVariable int recipientID, ModelMap modelMap, HttpSession session) {
        Message newMessage = new Message();
        int senderID = UserManagerDao.getUserId((String)session.getAttribute("username"));
//        newMessage.setSender(senderID);
//        newMessage.setRecipient(recipientID);

//----------------------
        User sender = UserManagerDao.getUser(senderID);
        User recipient = UserManagerDao.getUser(recipientID);
        newMessage.setSender(sender);
        newMessage.setRecipient(recipient);
//----------------------


        modelMap.put("newMessage",newMessage);
        return "newMessageForm";
    }

    @GetMapping("/removeMessage/id={messageID}")
    public String deleteMessage(@PathVariable int messageID, ModelMap modelMap) {
        boolean messageDeleteSuccess = MessageManagerDao.deleteMessage(messageID);
        modelMap.put("messageDeleteSuccess", messageDeleteSuccess);
        return "redirect:/messages";
    }
}
