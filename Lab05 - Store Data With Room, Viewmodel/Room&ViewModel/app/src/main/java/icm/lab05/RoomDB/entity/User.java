package icm.lab05.RoomDB.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @NonNull
    @ColumnInfo(name = "username")
    private String username;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    public User(@NonNull String email, @NonNull String password, String username) {
        this.email = email;
        this.password = password;
        if (username != null) {
            this.username = username;
        }else
            this.username = email.split("@")[0];
    }
    public String getEmail(){
        return this.email;
    }
    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean equals(User user){
        if (this.username.equals(user.username) && this.email.equals(user.email) &&
        this.password.equals(user.password)){
            return true;
        }
        return false;
    }
}
