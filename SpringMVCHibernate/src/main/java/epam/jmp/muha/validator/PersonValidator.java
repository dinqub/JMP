package epam.jmp.muha.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import epam.jmp.muha.entity.Person;


@Component
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Person person = (Person) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (person.getName().length() < 2 || person.getName().length() > 20) {
            errors.rejectValue("name", "Size.person.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Required");
        if (person.getSurname().length() < 2 || person.getSurname().length() > 20) {
            errors.rejectValue("surname", "Size.person.surname");
        }
    }
}
