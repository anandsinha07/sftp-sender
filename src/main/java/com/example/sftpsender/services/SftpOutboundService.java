package com.example.sftpsender.services;

import com.jcraft.jsch.ChannelSftp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;

@Slf4j
@Service
public class SftpOutboundService {

    final ClassPathXmlApplicationContext ac;

    public SftpOutboundService(){
        ac = new ClassPathXmlApplicationContext("/META-INF/spring/integration/SftpOutboundTransferSample-context.xml",
                        SftpOutboundService.class);
    }


    public void uploadToSftp(String filePath) throws Exception{

        final String sourcefilePath = filePath;
        // In case we need to change the name + "_foo"
//        final String destinationfilePath = sourcefilePath;
//        log.info(sourcefilePath);

        @SuppressWarnings("unchecked")
        SessionFactory<ChannelSftp.LsEntry> sessionFactory = ac.getBean(CachingSessionFactory.class);
        RemoteFileTemplate<ChannelSftp.LsEntry> template = new RemoteFileTemplate<ChannelSftp.LsEntry>(sessionFactory);

        try {
            final File file = new File(sourcefilePath);

            Assert.isTrue(file.exists(), String.format("File '%s' does not exist.", sourcefilePath));

            final Message<File> message = MessageBuilder.withPayload(file).build();
            final MessageChannel inputChannel = ac.getBean("inputChannel", MessageChannel.class);

            inputChannel.send(message);
            Thread.sleep(2000);

            log.info(String.format("\n\nSuccessfully transferred '%s' file to a " +
                    "remote location under /inbound directory", sourcefilePath + "\n"));

        }
        finally {
//            ac.close();
        }

        log.info("Payment Instruction sent to SFTP server");
    }


}
