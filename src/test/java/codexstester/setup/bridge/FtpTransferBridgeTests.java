package codexstester.setup.bridge;

import codexstester.engine.bridge.CodexsTesterCoreBridgeTests;
import com.huntercodexs.FtpTransferDemoApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = FtpTransferDemoApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class FtpTransferBridgeTests extends CodexsTesterCoreBridgeTests {

    protected FtpTransferBridgeTests() {
        super("ftp-transfer/");
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}