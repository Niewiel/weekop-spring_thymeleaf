package pl.niewiel.weekopspring_thymeleaf.validator.uniqueUser;

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

        return false;
    }

    boolean isUserNameUnique(String userName){
        User user;
        user=userService.getByUsername(userName);
        if (user!=null)
            return false;
    }
}
