package com.example.homewithmongo;

import com.example.homewithmongo.dao.AlarmDaoService;
import com.example.homewithmongo.model.Alarm;
import com.example.homewithmongo.model.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class HomeWithMongoApplication {

    @Autowired
    private AlarmDaoService alarmDaoService;

    public static void main(String[] args) {
        SpringApplication.run(HomeWithMongoApplication.class, args);
    }


    @PostConstruct
    void test() {
        Alarm alarm = new Alarm();
        alarm.setOn(true);
        alarm.setDays(List.of());
        alarm.setTime("09:26");
        alarmDaoService.addAlarm(alarm);

        alarmDaoService.editedSchedule(List.of(Days.SUNDAY), List.of(Days.MONDAY));

        alarmDaoService.editedAlarmTime("09:25", "10:35");

        alarmDaoService.turnsOffTurnsOn(true, false);
    }
}
