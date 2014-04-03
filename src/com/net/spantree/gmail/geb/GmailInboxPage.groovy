package com.net.spantree.gmail.geb

import geb.Page

class GmailInboxPage extends Page {
    static url = 'https://mail.google.com/mail/u/0/?pli=1#sent'

    static at = {
        title.startsWith('Inbox')
    }

    static content = {
        compose { module GmailComposeModule }
    }
}
