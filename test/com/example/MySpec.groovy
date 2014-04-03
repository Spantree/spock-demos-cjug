package com.example

import spock.lang.Specification
import spock.lang.Unroll

class MySpec extends Specification {
    @Unroll("Math.max(#first, #second) == #maximum")
    def "should figure out the maximum number"() {
        when: "we compare two positive numbers"
        def result = Math.max(first, second)

        then: "the larger positive number is returned"
        result == maximum

        where:
        first    | second    | maximum
        1        | 2         | 2
        2        | -3        | 2
    }
}