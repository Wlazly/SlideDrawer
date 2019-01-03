package com.github.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.greendao.Entity.Student;
import com.github.greendao.Entity.User;
import com.github.greendao.callback.InsertCallBack;
import com.github.greendao.module.UserDbModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {


    @BindView(R2.id.delete)
    Button delete;
//    @BindView(R2.id.query)
//    Button query;
    @BindView(R2.id.insetUser)
    Button insetUser;
    @BindView(R2.id.insertStudent)
    Button insertStudent;

    private static final String TAG = "GreenDaoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.insetUser,R2.id.insertStudent,R2.id.delete, R2.id.query,R2.id.update,R2.id.deleteAll})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.insetUser) {
            insertUser();
        }else if (id == R.id.insertStudent) {
            insertStudent();
        }else if (id == R.id.delete) {
            deleteUser();
        } else if (id == R.id.query) {
            queryUser();
        }else if (id == R.id.update) {
            updateUser();
        }else if (id == R.id.deleteAll){
            deleteAllTable();
        }
    }

    private void insertUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setAddress("shenzhen");
            user.setAge(18);
            user.setEducation("benke");
            user.setHates("none");
            user.setLikes("none");
            user.setSchool("shenzhendaxue");
            user.setSex("nan");
            Random random = new Random();
            user.setName("wlazly" +  random.nextInt());
            list.add(user);
        }
        final List<User> innerList = list;
        final long startTime = System.currentTimeMillis();
        UserDbModel.getInstance().insertAsnyc(innerList, new InsertCallBack() {
            @Override
            public void insertSuccess() {
                Log.i(TAG, "insertSuccess: ");
            }
        });
        long endTime = System.currentTimeMillis();
        Log.i(TAG, "insertUser: " + (endTime - startTime));
    }

    private void insertStudent(){
        for (int i = 0; i < 10; i++) {
            Student user = new Student("hello" + i, 19);
            DbHelper.getInstance().getDaoSession().getStudentDao().insert(user);
        }
    }

    private void deleteUser() {
        List<User> users = UserDbModel.getInstance().queryAll(User.class);
        long startTime = System.currentTimeMillis();
        UserDbModel.getInstance().delete(users);
        long endTime = System.currentTimeMillis();
        Log.i(TAG, "deleteUser: " + + (endTime - startTime) );
    }

    private void queryUser() {
        long startTime = System.currentTimeMillis();
        List<User> users = UserDbModel.getInstance().queryAll(User.class);
        long endTime = System.currentTimeMillis();
        Log.i(TAG, "queryUser: " + (endTime - startTime) + "---" + users.size());

        final long startTime1 = System.currentTimeMillis();
        UserDbModel.getInstance().queryAllAsync(User.class, uses -> {
            Log.i(TAG, "queryUser: " + (System.currentTimeMillis() - startTime1));
        });

//        List<User> list = DbHelper.getInstance().getDaoSession().getUserDao()
//                .queryBuilder()
//                .where(UserDao.Properties.Name.eq("wlazly"))
//                .list();
    }

    private void updateUser(){
        List<User> users = UserDbModel.getInstance().queryAll(User.class);
        for (User user : users) {
            user.setAge(20);
        }
        long startTime = System.currentTimeMillis();
        UserDbModel.getInstance().update(users);
        long endTime = System.currentTimeMillis();
        Log.i(TAG, "queryUser: " + (endTime - startTime) + "---" + users.size());
    }

    private void deleteAllTable() {
        DbHelper.getInstance().deleteAllTable();
    }
}
