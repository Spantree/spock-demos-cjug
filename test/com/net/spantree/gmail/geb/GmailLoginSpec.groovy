package com.net.spantree.gmail.geb

import geb.spock.GebReportingSpec
import geb.spock.GebSpec
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GmailLoginSpec extends GebReportingSpec {
    Logger log = LoggerFactory.getLogger(getClass())

    def email = "spockdemo@gmail.com"
    def password = "gebspockdemo"

    def sendTo = "cedric.hurst@gmail.com"
    def subject = "Geb and Spock are Awesome!"
    def body = """
    Geb and Spock are really awesome. Truly. I don't know why anyone is writing raw JUnit anymore, or working with a
    bare Selenium WebDriver. This is so much nicer!
    """

    def "should be able to login"() {
        when: "I visit the login page"
        to GmailLoginPage

        then: "The page title should reflect the login page"
        waitFor { at GmailLoginPage }
        report "blank login page"

        when: "I enter my email and password and click sign in"
        emailField = email
        passwordField = password

        and:
        report "login screen"

        signInButton.click()

        then: "I should be taken to the inbox page"
        waitFor { at GmailInboxPage }

        and:
        report "inbox screen"

        when: "I click the compose button"
        compose.composeButton.click()

        then: "A compose dialog should come up"
        waitFor { compose.composeDialog }

        when: "I author a message and click send"
        compose.toField = sendTo
        compose.subjectField = subject
        compose.bodyField.click()
        compose.writeToBodyField(body)

        and:
        report "compose screen"

        compose.sendButton.click()

        then: "An alert to view the sent message should come up"
        waitFor { compose.viewSentMessageLink }

        when: "I click the sent mail link"
        to GmailSentMailPage

        then: "I should be taken to the sent mail page"
        waitFor { at GmailSentMailPage }

        and:
        report "sent mail screen"

        and: "I should find the message I just sent"
        sentMail.find { mail ->
            def fullSummary = "${subject} - ${body.trim()}"
            mail.email == sendTo && fullSummary.startsWith(mail.summary)
        }
    }
}
