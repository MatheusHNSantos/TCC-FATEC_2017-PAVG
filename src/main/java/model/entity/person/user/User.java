package model.entity.person.user;


import util.exception.UserException;

/**
 *
 * @author Matheus Henrique
 */

public class User {
    private String login;
    private String password;
    private int idEmployee;

    public User(String login, String password) throws UserException {
        if(login.isEmpty() && password.isEmpty()){
            throw new UserException("Digite um login e uma senha!") {};
        }else if (login.isEmpty()) {
            throw new UserException("Digite um login!") {};
        }else if (password.isEmpty()) {
            throw new UserException("Digite uma senha!") {};
        }else{
            this.login = login;
            this.password = password;
        }

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
            throw new UserException("Login não pode ser nulo") {};
        }

        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws UserException {

        if (password.isEmpty()) {
            throw new UserException("Password não pode ser nulo") {};
        }

        this.password = password;
    }
}
