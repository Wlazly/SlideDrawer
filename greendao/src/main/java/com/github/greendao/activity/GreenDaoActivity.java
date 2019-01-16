package com.github.greendao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.greendao.R;
import com.github.greendao.R2;
import com.github.greendao.bean.user.LoginBean;
import com.github.greendao.module.UserDbModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {


    @BindView(R2.id.delete)
    Button delete;
    @BindView(R2.id.insetUser)
    Button insetUser;

    private static final String TAG = "GreenDaoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.insetUser,R2.id.delete, R2.id.query,R2.id.update,R2.id.deleteAll})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.insetUser) {
            insertUser();
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
        LoginBean loginBean = new LoginBean();
        loginBean.setPlatform("twitter");
        loginBean.setUid("123323223");
        UserDbModel.setLoginBean(loginBean);
    }

    private void deleteUser() {

    }

    private void queryUser() {
        UserDbModel.getUid();
    }

    private void updateUser(){

    }

    private void deleteAllTable() {
//        DbHelper.getInstance().deleteAllTable();
    }
}
