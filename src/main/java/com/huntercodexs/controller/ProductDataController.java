package com.huntercodexs.controller;

import com.huntercodexs.data.ProductData;
import com.huntercodexs.ftp.ApacheCommonsFtpHandler;
import com.huntercodexs.ftp.ProftpdHandler;
import com.huntercodexs.ftp.SftpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static com.huntercodexs.data.ProductData.stringCsvLineFromArray;

@ControllerAdvice
@RestController
@RequestMapping("${api.prefix}")
@CrossOrigin(origins = "*")
@Slf4j
public class ProductDataController {

    @Autowired
    SftpHandler sftpHandler;

    @Autowired
    ProftpdHandler proftpdHandler;

    @Autowired
    ApacheCommonsFtpHandler apacheCommonsFtpHandler;

    @PostMapping(path = "/dispatcher/sftp")
    @ResponseBody
    public ResponseEntity<?> sftp() throws IOException {

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("SFTP REQUESTED");

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

        return ResponseEntity.ok().body("SFTP OK");

    }

    @PostMapping(path = "/dispatcher/proftpd")
    @ResponseBody
    public ResponseEntity<?> proftpd() throws IOException {

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("PROFTPD REQUESTED");

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

        return ResponseEntity.ok().body("PROFTPD OK");

    }

    @PostMapping(path = "/dispatcher/apacheftp")
    @ResponseBody
    public ResponseEntity<?> apacheftp() throws IOException {

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("APACHEFTP REQUESTED");

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

        return ResponseEntity.ok().body("APACHEFTP OK");

    }

}
