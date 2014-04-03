package com.net.spantree.gmail.geb

import geb.Module
import groovy.json.JsonOutput
import org.openqa.selenium.firefox.FirefoxDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GmailComposeModule extends Module {
    Logger log = LoggerFactory.getLogger(getClass())

    static content = {
        composeButton { $("div[gh='cm']") }
        composeDialog { $("table.aoP.aoC").first() }
        toField { composeDialog.find("textarea")[0] }
        subjectField { composeDialog.find("input", name: 'subjectbox') }
        bodyField { composeDialog.find("div[aria-label='Message Body']") }
        sendButton { composeDialog.find("div[aria-label='Send ‪(⌘Enter)‬']")}
        viewSentMessageLink { $('#link_vsm') }
    }

    def writeToBodyField(String message) {
        message = message.trim().replaceAll(/(?s)\s+/, ' ')

        /**
         * This is a huge hack as WebDriver doesn't allow native events on MacOS yet, but on the bright side it gives
         * us an excuse to show helper methods.
         *
         * See: https://code.google.com/p/selenium/issues/detail?id=2442
         */
        def id = bodyField.attr('id')
        bodyField.click()
        def script
        if(driver.getClass() == FirefoxDriver) {
            script = "document.getElementById('${id}').childNodes[0].contentDocument.body.innerHTML = \"${message}\";"
        } else {
            script = "document.getElementById('${id}').innerHTML = \"${message}\";"
        }
        log.info "Executing script: ${script}"
        driver.executeScript(script)
    }
}
