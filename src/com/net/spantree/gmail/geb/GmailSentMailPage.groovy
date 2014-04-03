package com.net.spantree.gmail.geb

import geb.Module
import geb.Page

class GmailSentMailPage extends Page {
    static url = 'https://mail.google.com/mail/u/0/#sent'

    static at = {
        title.startsWith("Sent Mail")
    }

    static content = {
        sentMailTable { $('table.F') }
        sentMail {
            sentMailTable.find("tr").collect {
                [
                    email: it.find("span[email]").attr('email'),
                    summary: it.find("td[role='link']").text()
                ]
            }
        }
    }
}
