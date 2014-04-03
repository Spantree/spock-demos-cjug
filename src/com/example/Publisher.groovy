package com.example

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 * @author ksipe
 */
class Publisher {
    Logger log = LoggerFactory.getLogger(getClass())

    def subscribers = []

    def add(Subscriber subscriber) {
        log.info "Adding subscriber ${subscriber}"
        subscribers << subscriber
    }

    def fire(String s) {
        log.info "Firing for message '${s}'"
        subscribers.each {
            try {
                log.info "Pushing message to subscriber ${it}"
                it.receive(s)
            } catch (Exception e) {
                // do something magical
            }
        }
    }
}
