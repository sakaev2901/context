package com.example.demo.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WordRepositoryImpl implements WordRepository {

    @Autowired
    private SQLiteConnection sqLiteConnection;

    @Override
    public boolean isExists(String word) {
        PreparedStatement statement = null;
        try {
            statement = sqLiteConnection.getConnection().prepareStatement("select * from words where word = ?");
            statement.setString(1, word);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return false;
    }

    @Override
    public void save(String word) {
        try {
            PreparedStatement statement = sqLiteConnection.getConnection().prepareStatement("insert into words values (?)");
            statement.setString(1, word);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String findLastWord() {
        PreparedStatement statement = null;
        String lastWord = null;
        try {
            statement = sqLiteConnection.getConnection().prepareStatement("SELECT * FROM words ORDER BY ROWID DESC LIMIT 1;");
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                lastWord =  set.getString("word");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastWord;
    }

    @Override
    public boolean isEmpty() {
        PreparedStatement statement = null;
        try {
            statement = sqLiteConnection.getConnection().prepareStatement("select  * from words");
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return true;
    }

    @Override
    public void dropTable() {
        PreparedStatement statement = null;
        try {
            statement = sqLiteConnection.getConnection().prepareStatement("DELETE FROM words");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
