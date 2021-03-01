package com.example.homewithmongo;

import com.example.homewithmongo.dao.AlarmDaoService;
import com.example.homewithmongo.model.Alarm;
import com.example.homewithmongo.model.Days;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class AlarmController {

    private final AlarmDaoService alarmDaoService;

    @Autowired
    public AlarmController(AlarmDaoService alarmDaoService) {
        this.alarmDaoService = alarmDaoService;
    }

    @GetMapping(value = "/alarms")
    public String getAll() {
        return findToString(alarmDaoService.findAll());
    }

    @PostMapping("/create")
    public void createAlarm(@RequestBody Alarm alarm) {
        alarmDaoService.addAlarm(alarm);
    }

    @PostMapping("/alarms/edit")
    public void editedTimeOfAlarm(@RequestBody String oldTime, @RequestBody String newTime) {
        alarmDaoService.editedAlarmTime(oldTime, newTime);
    }

    @PostMapping("/schedule/edit")
    public void editedSchedule(@RequestBody List<Days> oldList, @RequestBody List<Days> newList) {
        alarmDaoService.editedSchedule(oldList, newList);
    }

    @PostMapping("/alarms/turnsOff")
    public void turnsOffAlarm(@RequestBody Boolean oldValue, @RequestBody Boolean newValue) {
        alarmDaoService.turnsOffTurnsOn(oldValue, newValue);
    }

    private String findToString(FindIterable<Document> documents) {
        return StreamSupport.stream(documents.spliterator(),false)
                .map(Document::toJson)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}