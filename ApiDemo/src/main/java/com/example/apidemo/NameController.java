package com.example.apidemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class NameController {

    // /inputname
    @GetMapping("/inputname")
    public Name greeting(@RequestParam(value = "name", defaultValue = "") String name) {
        // local variables.
        String firstName = "";
        String lastName = "";
        // lastname section
        Pattern lastNamePattern = Pattern.compile("((?=\\w*,)\\w*)|(\\w*$)");
        Matcher lastNameMatch = lastNamePattern.matcher(name);
        if (lastNameMatch.find()){
            if (lastNameMatch.group(0) != null) {
                lastName = lastNameMatch.group(0);
            } else {
                lastName = lastNameMatch.group(1);
            }
        }
        // firstname section
        Pattern firstNamePattern = Pattern.compile("((?<=,\\s).*)|((?=\\w*\\.)\\w*\\.?)|(^(?=\\w* )\\w*)");
        Matcher firstNameMatch = firstNamePattern.matcher(name);
        if (firstNameMatch.find()) {
            if (lastNameMatch.group(0) != null & firstNameMatch.group(1) != null) {
                firstName = firstNameMatch.group(1);
            } else if (firstNameMatch.group(1) == null) {
                firstName = firstNameMatch.group(0);
            } else {
                firstName = firstNameMatch.group(2);
            }
        }
        name = "First name = " + firstName + " | Last name = " + lastName;
        return new Name(String.format(name));
    }
}
