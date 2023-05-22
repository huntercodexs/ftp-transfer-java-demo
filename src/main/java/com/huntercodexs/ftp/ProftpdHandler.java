package com.huntercodexs.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ProftpdHandler {

    @Value("${proftpd.server.address}")
    String proftpdHost;

    @Value("${proftpd.server.port}")
    int proftpdServerPort;

    @Value("${proftpd.username}")
    String proftpdUsername;

    @Value("${proftpd.password}")
    String proftpdPassword;

    @Value("${proftpd.folder-path}")
    String proftpdFolderPath;

    @Value("${proftpd.remote-verification}")
    boolean proftpdRemoteVerification;

    private FTPClient ftpConnect() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setRemoteVerificationEnabled(proftpdRemoteVerification);
        ftpClient.setDefaultPort(proftpdServerPort);
        ftpClient.connect( proftpdHost);
        ftpClient.login( proftpdUsername, proftpdPassword);
        ftpClient.changeWorkingDirectory(proftpdFolderPath);

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
