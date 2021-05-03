package com.example.sftpsender.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
@Getter
@Setter
public class SftpConfiguration {

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.port:22}")
    private int port;

    @Value("${sftp.username}")
    private String username;

    @Value("${sftp.password:#{null}}")
    private String password;

    @Value("${sftp.keepalive.interval}")
    private int keepAliveInterval;

    @Value("${sftp.remote.in.directory}")
    private String remoteInDirectory;

    @Value("${sftp.remote.out.directory}")
    private String remoteOutDirectory;

    @Value("${sftp.local.in.directory}")
    private String localInDirectory;

    private DefaultSftpSessionFactory defaultSftpSessionFactory;

    public void setDefaultSftpSessionFactory(DefaultSftpSessionFactory defaultSftpSessionFactory) {
        this.defaultSftpSessionFactory = defaultSftpSessionFactory;
    }


}
