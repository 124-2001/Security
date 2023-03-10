package com.example.security.repository;

import com.example.security.model.User;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User>{
    @Override
    public void readCsvFile(String fileName) {

    }

    @Override
    public List<User> getAllRecords() {
        List<User> users = new ArrayList<>();
        try {
            FileReader reader = new FileReader("users.csv");
            CsvToBeanBuilder<User> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            users = csvToBeanBuilder.withType(User.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getRecordById(int id) {
        return null;
    }

    @Override
    public User getRecordByName(String name) {
        List<User> users = getAllRecords();

        return users.stream()
                .filter(user -> user.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addRecord(User record) {

    }

    @Override
    public void updateRecord(User record) {

    }

    @Override
    public void deleteRecord(int id) {

    }

    @Override
    public List<User> search(String keyword) {
        return null;
    }
}
