package com.revo.core


import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.validation.Path

class ValidationExceptionMapperSpec extends Specification {

    @Subject
    def validationExceptionMapper = new ValidationExceptionMapper()

    @Unroll
    def 'should create message from validation error'() {
        setup:
        def exception = new ConstraintViolationException(violations)

        when:
        def response = validationExceptionMapper.toResponse(exception)
        OperationResult result = response.getEntity()
        def matcher = result.message =~ expectedMessage

        then:
        matcher.find()
        result.status == expectedStatus

        where:
        violations                           || expectedMessage                                                  || expectedStatus
        createViolations('field1')           || /^[a-zA-Z]*[0-9] cannot be null$/                                || OperationResult.Status.FAILED
        createViolations('field1', 'field2') || /^[a-zA-Z]*[0-9] cannot be null, [a-zA-Z]*[0-9] cannot be null$/ || OperationResult.Status.FAILED
        Set.of()                             || ''                                                               || OperationResult.Status.FAILED

    }

    Set createViolations(String... fields) {
        def violations = [] as Set
        for (String field: fields) {
            violations.add(createViolation(createPath(field)))
        }
        violations
    }


    ConstraintViolation createViolation(Path path) {
        def violation = Stub(ConstraintViolation)
        violation.propertyPath >> path
        violation.message >> 'cannot be null'
        violation
    }

    Path createPath(String fieldName) {
        def path = Stub(Path)
        path.toString() >> fieldName
        path
    }

}
