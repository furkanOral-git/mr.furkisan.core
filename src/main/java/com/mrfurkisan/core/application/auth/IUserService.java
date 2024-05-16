package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.IApplicationService;
import com.mrfurkisan.core.application.forms.RegisterForm;
import com.mrfurkisan.core.security.authentication.User;

public interface IUserService extends IApplicationService {

    public Boolean CreateUser(RegisterForm user, String roleId);

    public Boolean DeleteUser(int userId);

    public Boolean ChangePassword(int userId, String newPassword);

    public Boolean ChangeEmail(int userId, String newEmail);

    public Boolean ChangeUsername(int userId, String newUserName);

    public User GetUserByEmail(String email);

    public User GetUserByUsername(String userName);

}
