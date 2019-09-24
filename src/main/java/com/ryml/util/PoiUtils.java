package com.ryml.util;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/9/24 16:16
 */
public class PoiUtils {

    /**
     * Excel加密
     * @param fileUrl
     */
    public static void encrypt(String fileUrl,String password){
        try {
            POIFSFileSystem fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);

            Encryptor enc = info.getEncryptor();
            enc.confirmPassword(password);

            OPCPackage opc = OPCPackage.open(new File(fileUrl), PackageAccess.READ_WRITE);
            //OPCPackage opc = OPCPackage. .create(FileOutputStream);
            OutputStream os = enc.getDataStream(fs);

            opc.save(os);
            opc.close();

            FileOutputStream fos = new FileOutputStream(fileUrl);
            fs.writeFilesystem(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
