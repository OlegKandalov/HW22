package com.example.homewithmongo.dao;
import com.example.homewithmongo.model.Alarm;
import com.example.homewithmongo.model.Days;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlarmDaoService {

    public void addAlarm(Alarm alarm) {
        collection().insertOne(alarmToDocument(alarm));
    }

    public void editedAlarmTime(String oldTime, String newTime) {
        BasicDBObject query = new BasicDBObject();
        query.put("alarm time", oldTime);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("alarm time", newTime);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        database().getCollection("alarm").updateOne(query, updateObject);
    }

    public void editedSchedule(List<Days> daysOldList, List<Days> daysNewList) {
        BasicDBObject query = new BasicDBObject();
        query.put("schedule", daysOldList.toString());

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("schedule", daysNewList.toString());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        database().getCollection("alarm").updateOne(query, updateObject);
    }

    public FindIterable<Document> findAll() {
        return collection().find();
    }

    public void turnsOffTurnsOn(Boolean oldValue, Boolean newValue) {
        BasicDBObject query = new BasicDBObject();
        query.put("is On", oldValue);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("is On", newValue);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        database().getCollection("alarm").updateOne(query, updateObject);
    }

    private Document alarmToDocument(Alarm alarm) {
        Document document = new Document();
        document.put("alarm time", alarm.getTime());
        document.put("schedule", alarm.getDays().toString());
        document.put("is On", alarm.isOn());
        return document;
    }

    private MongoDatabase database() {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        return mongoDatabase;
    }

    private MongoCollection<Document> collection() {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> mongoCollection = database.getCollection("alarm");
        return mongoCollection;
    }
}