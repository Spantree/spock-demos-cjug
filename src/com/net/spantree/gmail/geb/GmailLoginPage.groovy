package com.net.spantree.gmail.geb

import geb.Page

class GmailLoginPage extends Page {
    static url = 'https://www.google.com/accounts/ServiceLoginAuth?continue=http://gmail.google.com/gmail'

    static at = { title.startsWith('Sign in') }

    static content = {
        emailField { $('#Email') }
        passwordField { $('#Passwd')}
        signInButton { $('#signIn') }
    }
}
