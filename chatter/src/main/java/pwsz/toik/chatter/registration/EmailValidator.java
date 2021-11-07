package pwsz.toik.chatter.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        //TODO REGEX FOR EMAIL
        return true;
    }
}
