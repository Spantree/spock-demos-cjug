import org.openqa.selenium.Dimension
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

// default is to use firefox
driver = {
//    def d = new PhantomJSDriver(new DesiredCapabilities())
//    d.manage().window().setSize(new Dimension(1028, 768))
//    d
    new FirefoxDriver()
}

//set the report output directory
reportsDir = "reports/"