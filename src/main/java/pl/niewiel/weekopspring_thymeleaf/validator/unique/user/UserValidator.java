package pl.niewiel.weekopspring_thymeleaf.validator.unique.user;

import org.springframework.beans.factory.annotation.Autowired;
import pl.niewiel.weekopspring_thymeleaf.model.User;
import pl.niewiel.weekopspring_thymeleaf.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<UniqueUser, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isUserNameUnique(s);
    }

    private boolean isUserNameUnique(String userName) {
        try {
            User user = userService.getByUsername(userName);
            return user == null;
        }catch (NullPointerException e){
            return true;
        }

    }

    boolean isEmailValid(String email) {
        User user;
        user = userService.getUserByEmail(email);
        return (user != null);
    }
}
