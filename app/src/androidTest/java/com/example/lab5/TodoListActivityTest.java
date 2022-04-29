package com.example.lab5;


import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TodoListActivityTest {
    TodoDatabase testDb;
    TodoListItemDao todoListItemDao;

    private static void forceLayout(RecyclerView recyclerView){
        recyclerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        recyclerView.layout(0,0,1080,2280);
    }

    @Before
    public void resetDatabase(){
        Context context = ApplicationProvider.getApplicationContext();
        testDb = Room.inMemoryDatabaseBuilder(context, TodoDatabase.class)
                .allowMainThreadQueries()
                .build();
        TodoDatabase.injectTestDatabase(testDb);

        List<TodoListItem> todos = TodoListItem.loadJSON(context, "demo_todos.json");
        todoListItemDao = testDb.todoListItemDao();
        todoListItemDao.insertAll(todos);
    }
}
