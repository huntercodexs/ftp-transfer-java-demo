# FTP TRANSFER DEMO
A simple java spring project to file transfer in FTP

# Overview

- License: MIT
- Coding: Open Source Code
- Language: Java 8 (1.8) (jre1.8.0_212)

# Included

- SFTP
- PROFTPD
- APACHE COMMONS FTP

# Attached

- Postman

[FTP-TRANSFER-JAVA-DEMO.postman_collection.json](midias/postman/FTP-TRANSFER-JAVA-DEMO.postman_collection.json)

# Usage

- pom.xml

<code>

		<!--SFTP-->
		<dependency>
			<groupId>org.springframework.integration</groupId>
			<artifactId>spring-integration-sftp</artifactId>
		</dependency>

		<!--FTP-->
		<dependency>
			<groupId>org.springframework.integration</groupId>
			<artifactId>spring-integration-ftp</artifactId>
		</dependency>

</code>

- SFTP

<code>

    @Autowired
    SftpHandler sftpHandler;

    public void sftpHandlerUploadTest() throws IOException {
        ...
        sftpHandler.uploadFile(inputStream, filename);
    }

</code>

- PROFTPD

<code>

    @Autowired
    ProftpdHandler proftpdHandler;

    public void proftpdHandlerUploadTest() throws IOException {
        ...
        proftpdHandler.uploadFile(inputStream, filename);
    }

</code>

- APACHEFTP

<code>

    @Autowired
    ApacheCommonsFtpHandler apacheCommonsFtpHandler;

    public void apacheftpHandlerUploadTest() throws IOException {
        ...
        apacheCommonsFtpHandler.uploadFile(inputStream, filename);
    }

</code>