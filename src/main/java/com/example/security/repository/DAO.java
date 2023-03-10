package com.example.security.repository;

import java.util.List;

public abstract class DAO<T> {
    public abstract void readCsvFile(String fileName);
    public abstract List<T> getAllRecords();
    public abstract T getRecordById(int id);
    public abstract T getRecordByName(String name);
    public abstract void addRecord(T record);
    public abstract void updateRecord(T record);
    public abstract void deleteRecord(int id);
    public abstract List<T> search(String keyword);
}
