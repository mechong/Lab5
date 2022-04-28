package com.example.lab5;


import static org.junit.Assert.assertNotEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TodoDatabaseTest {
    private TodoListItemDao dao;
    private TodoDatabase db;

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.todoListItemDao();
    }

    @After
    public void closeDb() throws IOException{
        db.close();
    }

    @Test
    public void testInsert(){
        TodoListItem item1 = new TodoListItem("Pizza time", false, 0);
        TodoListItem item2 = new TodoListItem("Photos of Spider-man", false, 1);

        long id1 = dao.insert(item1);
        long id2 = dao.insert(item2);

        assertNotEquals(id1, id2);
    }
}
