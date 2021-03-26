package annotations.test;

import annotations.UseCase;

import java.util.List;

public class UseCaseDemo {


    @UseCase(id = 47,description = "hello annotation for java se")
    public boolean validatePassword(String password){
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 1)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id= 12)
    public boolean checkForNewPassword(
        List<String> prevPassword,String password){
        return !prevPassword.contains(password);
    }


}
