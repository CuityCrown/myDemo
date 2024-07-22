package com.ryml.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


/**
 * @Description:
 * @author: 刘一博
 * @date: 2024/7/22 9:36
 */
public class SftpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpUtils.class);

    private String host;
    private int port;
    private String username;
    private String password;

    public SftpUtils(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * @param file
     * @param remoteFilePath
     * @throws IOException
     */
    public void uploadFile(MultipartFile file, String remoteFilePath) throws IOException {
        InputStream inputStream = null;
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            inputStream = file.getInputStream();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.put(inputStream, remoteFilePath);
        } catch (Exception e) {
            LOGGER.error("SftpUtils.uploadFile,上传文件到服务器失败,remoteFilePath={}", remoteFilePath, e);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * @param file
     * @param remoteFilePath
     * @throws IOException
     */
    public void uploadFile(File file, String remoteFilePath) throws IOException {
        InputStream inputStream = null;
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            inputStream = Files.newInputStream(file.toPath());
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.put(inputStream, remoteFilePath);
        } catch (Exception e) {
            LOGGER.error("SftpUtils.uploadFile,上传文件到服务器失败,remoteFilePath={}", remoteFilePath, e);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * @param remoteFilePath
     * @param localFilePath
     * @throws IOException
     */
    public void downloadFile(String remoteFilePath, String localFilePath) throws IOException {
        Session session = null;
        ChannelSftp channelSftp = null;
        FileOutputStream fileOutputStream = new FileOutputStream(localFilePath);
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.get(remoteFilePath, fileOutputStream);
        } catch (Exception e) {
            LOGGER.error("SftpUtils.downloadFile,从服务器下载文件失败,remoteFilePath={},localFilePath={}", remoteFilePath, localFilePath, e);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            fileOutputStream.close();
        }
    }


    public static void main(String[] args) throws IOException {
        SftpUtils sftpUtils = new SftpUtils("airdrop.citizenplane.com", 22, "ftp-user-944", "VireTReo2FfM");
        sftpUtils.uploadFile(new File("E:\\test.txt"), "/flight-ingest/test.txt");
        sftpUtils.downloadFile("/flight-ingest/test.txt", "D:\\tx\\test.txt");
    }

}
