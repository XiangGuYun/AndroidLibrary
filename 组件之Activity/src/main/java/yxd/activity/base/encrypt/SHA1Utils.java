package yxd.activity.base.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by asus on 2017/12/21.
 */
/*
SHA1算法（单向加密）

sha1也不可逆，比md5长度更长，所以更安全，但是加密的效率比md5要慢一些，如文件的秒传功能，
以及相同的v4包冲突都是根据文件的sha1值进行比对的
 */
public class SHA1Utils {

    /**
     * 使用sha-1方式进行加密
     * @return
     */
    public static String digest(String content){
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest msgDitest = MessageDigest.getInstance("SHA-1");
            msgDitest.update(content.getBytes());
            byte[] digests = msgDitest.digest();
            //将每个字节转为16进制
            for (int i=0;i<digests.length;i++){
                builder.append(Integer.toHexString(digests[i] & 0xff +8));//+8为加盐操作
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  builder.toString();
    }

}
