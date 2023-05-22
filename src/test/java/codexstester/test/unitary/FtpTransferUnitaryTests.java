package codexstester.test.unitary;

import codexstester.setup.bridge.FtpTransferBridgeTests;
import codexstester.setup.datasource.FtpTransferDataSourceTests;
import com.huntercodexs.data.ProductData;
import com.huntercodexs.ftp.ApacheCommonsFtpHandler;
import com.huntercodexs.ftp.ProftpdHandler;
import com.huntercodexs.ftp.SftpHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static com.huntercodexs.data.ProductData.stringCsvLineFromArray;

public class FtpTransferUnitaryTests extends FtpTransferBridgeTests {

    @Autowired
    SftpHandler sftpHandler;

    @Autowired
    ProftpdHandler proftpdHandler;

    @Autowired
    ApacheCommonsFtpHandler apacheCommonsFtpHandler;

    @Test
    public void sftpHandlerUploadTest() throws IOException {

        /*Database Simulate*/
        ProductData[] productData = new ProductData[]{
                new ProductData(1, "Material Plastic 1", "Plastic Modal 1", 300),
                new ProductData(2, "Material Plastic 2", "Plastic Modal 2", 400),
                new ProductData(3, "Material Plastic 3", "Plastic Modal 3", 500)
        };

        /*File Generate Simulate*/
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(ProductData.HEADER_CSV.getBytes(StandardCharsets.UTF_8));

        for (ProductData productDatum : productData) {
            byteArrayOutputStream.write(stringCsvLineFromArray(productDatum.toStringArray()).getBytes(StandardCharsets.UTF_8));
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        /*Sftp Upload Simulate*/
        sftpHandler.uploadFile(inputStream, "sftp_products_"+ LocalDateTime.now()+".csv");

    }

    @Test
    public void proftpdHandlerUploadTest() throws IOException {

        /*Database Simulate*/
        ProductData[] productData = new ProductData[]{
                new ProductData(1, "Material Plastic 1", "Plastic Modal 1", 300),
                new ProductData(2, "Material Plastic 2", "Plastic Modal 2", 400),
                new ProductData(3, "Material Plastic 3", "Plastic Modal 3", 500)
        };

        /*File Generate Simulate*/
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(ProductData.HEADER_CSV.getBytes(StandardCharsets.UTF_8));

        for (ProductData productDatum : productData) {
            byteArrayOutputStream.write(stringCsvLineFromArray(productDatum.toStringArray()).getBytes(StandardCharsets.UTF_8));
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        /*Ftp Upload Simulate*/
        proftpdHandler.uploadFile(inputStream, "proftpd_products_"+ LocalDateTime.now()+".csv");

    }

    @Test
    public void apacheftpHandlerUploadTest() throws IOException {

        /*Database Simulate*/
        ProductData[] productData = new ProductData[]{
                new ProductData(1, "Material Plastic 1", "Plastic Modal 1", 300),
                new ProductData(2, "Material Plastic 2", "Plastic Modal 2", 400),
                new ProductData(3, "Material Plastic 3", "Plastic Modal 3", 500)
        };

        /*File Generate Simulate*/
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(ProductData.HEADER_CSV.getBytes(StandardCharsets.UTF_8));

        for (ProductData productDatum : productData) {
            byteArrayOutputStream.write(stringCsvLineFromArray(productDatum.toStringArray()).getBytes(StandardCharsets.UTF_8));
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        /*Ftp Upload Simulate*/
        apacheCommonsFtpHandler.uploadFile(inputStream, "apache_products_"+ LocalDateTime.now()+".csv");

    }

}
