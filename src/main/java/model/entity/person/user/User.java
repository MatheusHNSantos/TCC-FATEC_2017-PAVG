package model.entity.person.user;

import org.omg.CORBA.UserException;

public class User {
    private String login;
    private String password;
    private int idEmployee;

    public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws UserException {
        if (login.isEmpty()) {
            throw new UserException("O login nao pode ser nulo!") {};
        }

        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws UserException {

        if (password.isEmpty()) {
            throw new UserException("A senha nao pode ser nula!") {};
        }

        this.password = password;
    }
}
