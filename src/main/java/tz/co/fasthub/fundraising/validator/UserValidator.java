package tz.co.fasthub.fundraising.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import tz.co.fasthub.fundraising.model.User;
import tz.co.fasthub.fundraising.service.UserService;


/**
 * Created by naaminicharles on 9/21/17.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        /*validate first name*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
            if (user.getName().length() < 6 || user.getName().length() > 32) {
                errors.rejectValue("name", "Size.userForm.name");
            }

        /*validate email*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
            if (user.getEmail().isEmpty()) {
                errors.rejectValue("email", "Size.userForm.email");
            }

            if (userService.findUserByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "Duplicate.userForm.email");
            }

        /*validate username*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","NotEmpty");
           if(userService.findUserByUsername(user.getUsername()) != null){
               errors.rejectValue("username", "Duplicate.userForm.username");
           }

        /*validate password and confirm password*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
            if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password");
            }

            if (!user.getCpassword().equals(user.getPassword())) {
                errors.rejectValue("cpassword", "Diff.userForm.passwordConfirm");
            }


/*
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "NotEmpty");
        if (user.getRole().isEmpty()) {
            errors.rejectValue("role", "Size.userForm.role");
        }
*/


    }
}
