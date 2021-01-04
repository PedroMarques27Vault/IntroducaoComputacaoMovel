package icm.lab05.RoomDB.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import icm.lab05.RoomDB.dao.UserDao;
import icm.lab05.RoomDB.entity.User;
import icm.lab05.RoomDB.roomDB.UserRoomDatabase;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    public UserRepository(Application application){
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return mAllUsers;
    }

    public void insert(User user){
        UserRoomDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }

}

