package com.epam.ld.module2.testing.template;

/**
 * The type Template.
 */
public class Template {

    public final String templateMessage = "#{subject}\n" +
            "\n" +
            "Hello, #{recipientName}! #{messageBody}\n" +
            "\n" +
            "Best regards,\n" +
            " #{name}, #{jobTitle}, #{company}.\n" +
            " \n" +
            " Phone number: #{phoneNumber}\n" +
            " E-mail: #{email}";
}