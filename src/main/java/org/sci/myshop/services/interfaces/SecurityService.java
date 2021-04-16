package org.sci.myshop.services.interfaces;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}