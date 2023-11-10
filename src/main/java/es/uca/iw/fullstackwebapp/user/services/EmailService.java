package es.uca.iw.fullstackwebapp.user.services;


import es.uca.iw.fullstackwebapp.user.domain.User;

public interface EmailService {

    boolean sendRegistrationEmail(User user);

}