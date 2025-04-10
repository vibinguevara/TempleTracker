package org.information.temple.service;

import org.information.temple.model.User;

public interface GoogleAuthService {
    User verifyGoogleTokenAndSaveUser(String token) throws Exception;
}
