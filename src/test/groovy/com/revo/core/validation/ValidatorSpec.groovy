package com.revo.core.validation

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ValidatorSpec extends Specification {

    @Shared
    def message = "Message"

    @Subject
    def validator = new Validator<TestValidationRule, Boolean>(Set.of(new TestValidationRule()))


    @Unroll
    def 'should validate rule'() {
        when:
        ValidationResult result = validator.validate(parameter)

        then:
        result == expectedResult

        where:
        parameter || expectedResult
        true      || ValidationResult.OK
        false     || ValidationResult.fail(message)
    }

    @Unroll
    def 'should return ok if no rules defined'() {
        setup:
        def noRulesValidator = new Validator<TestValidationRule, Boolean>(Set.of())

        when:
        ValidationResult result = noRulesValidator.validate(parameter)

        then:
        result == expectedResult

        where:
        parameter || expectedResult
        true      || ValidationResult.OK
        false     || ValidationResult.OK
    }


    class TestValidationRule implements ValidationRule<Boolean> {
        @Override
        ValidationResult validate(Boolean parameter) {
            return parameter ? ValidationResult.OK : ValidationResult.fail(message)
        }
    }
}
