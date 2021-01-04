package icm.lab05.RoomDB.ViewModel;



import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import icm.lab05.RoomDB.entity.User;
import icm.lab05.RoomDB.repository.UserRepository;


public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;
    private final LiveData<List<User>> mAllUsers;

    public UserViewModel (Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() { return mAllUsers; }

    public void insert(User user) { mRepository.insert(user); }
}