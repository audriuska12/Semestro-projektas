package com.tvarkarastis.controller;

import com.tvarkarastis.dao.EventManagerDao;
import com.tvarkarastis.dao.UserManagerDao;
import com.tvarkarastis.entity.Event;
import com.tvarkarastis.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Edvinas on 2017-05-17.
 */
@Controller
public class EventController {

    @GetMapping("/addNewEvent")
    public String addNewEvent(ModelMap modelMap, HttpSession session) {

        Event newEvent = new Event();
        int hostId = ((Integer)session.getAttribute("userId")).intValue();
        newEvent.setHost(hostId);
//---
        Timestamp start = Timestamp.valueOf(LocalDateTime.now());
        Timestamp end = Timestamp.valueOf(LocalDateTime.now());
        newEvent.setStartDateTime(start);
        newEvent.setEndDateTime(end);
//---
        modelMap.put("newEvent", newEvent);
        return "newEventForm";
    }

    @PostMapping("/addNewEvent")
    public String addNewEvent (@ModelAttribute Event newEvent, ModelMap modelMap) {
        int insertEventStatus = EventManagerDao.insertEvent(newEvent);
        modelMap.put("insertEventStatus", insertEventStatus);
        if (insertEventStatus < 0) { //Atsirado klaida
            modelMap.put("newEvent", newEvent);
            return "newEventForm";
        } else { //Viskas gerai
            return "redirect:/hostedBy";
        }
    }

    @GetMapping("/removeEvent/id={eventID}")
    public String removeEvent (@PathVariable int eventID, ModelMap modelMap) {

        boolean deleteSuccess = EventManagerDao.removeEvent(eventID);
        modelMap.put("eventDeleteSuccess", deleteSuccess);
        return "redirect:/hostedBy";
    }

    @GetMapping("/hostedBy")
    public String hostedBy (HttpSession session, ModelMap modelMap) {
        String username = (String)session.getAttribute("username");
        List<Event> eventsHostedByThisUser = EventManagerDao.getEventsOfUser(username);
        modelMap.put("hostedByEvents", eventsHostedByThisUser);
        return "hostedBy";
    }

    @GetMapping("/invitations")
    public String invitations (HttpSession session, ModelMap modelMap) {
        String username = (String)session.getAttribute("username");
        List<Event> invitations = EventManagerDao.getInvitedEvents(username);
        modelMap.put("invitations", invitations);
        return "invitations";
    }

    @GetMapping("/attend/id={eventID}")
    public String attend (@PathVariable int eventID, HttpSession session, ModelMap modelMap) {
        int userID = UserManagerDao.getUserId((String)session.getAttribute("username"));
        int inviteAttendSuccess = UserManagerDao.attend(eventID, userID);
        modelMap.put("inviteAttendSuccess", inviteAttendSuccess);
        return "redirect:/invitations";
    }

    @GetMapping("/attends")
    public String attends(HttpSession session, ModelMap modelMap) {
        String username = (String)session.getAttribute("username");
        List<Event> attends = EventManagerDao.getEventsUserAttends(username);
        modelMap.put("attends", attends);
        return "attends";
    }

    @GetMapping("/unattend/id={eventID}")
    public String unattend (@PathVariable int eventID, HttpSession session, ModelMap modelMap) {
        int userID = UserManagerDao.getUserId((String)session.getAttribute("username"));
        boolean unattendSuccess = UserManagerDao.unattend(eventID, userID);
        modelMap.put("unattendSuccess", unattendSuccess);
        return "redirect:/attends";
    }


//    @GetMapping("/invite/id={eventID}")
//    public String invite(@PathVariable int eventID, ModelMap modelMap, HttpSession session) {
//
//
//        User userToInvite = new User();
//        Event thisEvent = EventManagerDao.getEvent(eventID);
//        modelMap.put("event", thisEvent);
//        modelMap.put("userToInvite", userToInvite);
//
//        return "inviteForm";
//    }
//
//    @PostMapping("/invite")
//    public String invite(@ModelAttribute Event event, User user) {
//        UserManagerDao.invite(user.getUsername(), event.getId());
//        return "redirect:/hostedBy";
//    }
}
