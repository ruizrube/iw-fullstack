package es.uca.iw.fullstackwebapp.user.services;


import es.uca.iw.fullstackwebapp.user.domain.User;

public interface UserEmailService {

    boolean sendRegistrationEmail(User user);

}