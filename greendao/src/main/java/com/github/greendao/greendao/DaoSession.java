package com.github.greendao.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.github.greendao.Entity.Student;
import com.github.greendao.Entity.User;
import com.github.greendao.Entity.Teacher;

import com.github.greendao.greendao.StudentDao;
import com.github.greendao.greendao.UserDao;
import com.github.greendao.greendao.TeacherDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig teacherDaoConfig;

    private final StudentDao studentDao;
    private final UserDao userDao;
    private final TeacherDao teacherDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(User.class, userDao);
        registerDao(Teacher.class, teacherDao);
    }
    
    public void clear() {
        studentDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        teacherDaoConfig.clearIdentityScope();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

}
