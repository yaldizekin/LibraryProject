package com.ekinyaldiz.project.library.service;

import com.ekinyaldiz.project.library.repo.user.Customer;
import com.ekinyaldiz.project.library.repo.user.SystemUser;

public interface MailService {
    void sendUserUpdatedMail(SystemUser user);
    void sendUserCreatedMail(SystemUser user);

}
