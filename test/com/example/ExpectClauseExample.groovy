package com.example

import com.extensions.Report
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Report
class ExpectClauseExample extends Specification {
    Logger log = LoggerFactory.getLogger(getClass())

    def messages = ["foo", "bar", "buzz", "cjug"]

    def "Math.max should return the higher value"() {
        expect:
        Math.max(1, 2) == 2
        Math.max(2, 4) == 4
    }

    def "Math.min should return lower value"() {
        expect:
        Math.min(1, 2) == 1
    }

    @Unroll
    def "Math.max(#a, #b) == #c"() {
        when:
        def value = Math.max(a, b)

        then:
        value == c
        value.getClass() == Integer

        where:
        a   | b     || c
        1   | 2     || 2
        2   | 1     || 2
    }

    def "Should mock out Math.max"() {
        given: "We have a publisher"
        def publisher = new Publisher()

        and: "we spy on the publisher's subscribers"
        publisher.subscribers = Spy(ArrayList)

        and: "we mock the subscriber"
        def subscriber = Mock(Subscriber)
        subscriber.receive(_) >> { String message ->
            log.info "Received message: ${message}"
            "ok"
        }

        when: "we send a message to the subscriber"
        def resp = subscriber.receive("foo")

        then:
        resp == "ok"

        when: "we add a publisher"
        publisher.add(subscriber)

        then: "the subscriber is in the list of publishers"
        subscriber in publisher.subscribers

        and: "the publisher added the subscriber via the 'add' method"
        1 * publisher.subscribers.add(subscriber)

        when: "we fire a message to the publisher"
        publisher.fire("Hello CJUG!")

        then: "that message was received by the subscriber"
        1 * subscriber.receive("Hello CJUG!")
    }
}
