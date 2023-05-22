package com.huntercodexs.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ApacheCommonsFtpHandler {

    @Value("${apacheftp.server.address}")
    String apacheftpHost;

    @Value("${apacheftp.server.port}")
    int apacheftpServerPort;

    @Value("${apacheftp.username}")
    String apacheftpUsername;

    @Value("${apacheftp.password}")
    String apacheftpPassword;

    @Value("${apacheftp.folder-path}")
    String apacheftpFolderPath;

    @Value("${apacheftp.remote-verification}")
    boolean apacheftpRemoteVerification;

    private FTPClient ftpConnect() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setRemoteVerificationEnabled(apacheftpRemoteVerification);
        ftpClient.connect( apacheftpHost, apacheftpServerPort);
        ftpClient.login( apacheftpUsername, apacheftpPassword);
        ftpClient.changeWorkingDirectory(apacheftpFolderPath);

        return ftpClient;
    }

    private void ftpDisconnect(FTPClient ftp) throws IOException {
        ftp.logout();
        ftp.disconnect();
    }

    private void ftpStore(InputStream inputStream, String fileName) throws IOException {
        try {
            FTPClient ftp = ftpConnect();
            ftp.storeFile(fileName, inputStream);
            System.out.println("Send file successfully !");
            ftpDisconnect(ftp);
        } catch (RuntimeException re) {
            System.out.println("Error to send file !");
            throw new RuntimeException("Ftp Error " + re.getMessage());
        }
    }

    public void uploadFile(InputStream inputStream, String fileName) throws IOException {
        try {
            ftpStore(inputStream, fileName);
        } catch (RuntimeException re) {
            throw new RuntimeException("Ftp Error " + re.getMessage());
        }
    }
}
