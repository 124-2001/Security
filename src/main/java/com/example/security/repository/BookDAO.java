package com.example.security.repository;

import com.example.security.model.Book;

import java.util.List;

public class BookDAO extends  DAO<Book>{
    @Override
    public void readCsvFile(String fileName) {

    }

    @Override
    public List<Book> getAllRecords() {
        return null;
    }

    @Override
    public Book getRecordById(int id) {
        return null;
    }

    @Override
    public Book getRecordByName(String name) {
        return null;
    }

    @Override
    public void addRecord(Book record) {

    }

    @Override
    public void updateRecord(Book record) {

    }

    @Override
    public void deleteRecord(int id) {

    }

    @Override
    public List<Book> search(String keyword) {
        return null;
    }
}
