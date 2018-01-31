package yxd.encrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
/*
密码类的数据，为了让用户放心注册，密码类的数据的加密一般都是经过双重加密的，第一重加密使用不可逆的MD5算法加密，第二重加密是可逆的加密，
常见的可逆加密有对称加密和非对称加密。上述不可逆的意思就是一旦加密就不能反向得到密码原文，一般用来加密用户密码，
app的服务器端一般存储的也都是密文密码，不然用户就太危险了，app的运营商也承担不起这么大的责任。虽然现在MD5加密生成的密文也可以破解了，
但是我们不需要担心，因为我们可以采用多重加密方式来应对。在数据传输的过程中，首先把密码类数据经过MD5加密算法加密，
然后再在外面使用可逆的加密方式加密一次，这样在数据传输的过程中，即便数据被截获了，但是想要完全破解，还是很难的（本来就不存在完全不能解密的加密）。

第二类数据：非密码类的数据，虽然这些数据也很重要，但是一般其他人截获了这些数据对他们意义不太大，这些数据我们一般采用可逆的加密方式加密，
因为我们在服务器端还是需要这些数据的明文的，常用的可逆加密方法有：对称加密和非对称加密。
何为对称加密？在对称加密算法中，数据发信方将明文和加密密钥一起经过特殊的加密算法处理后，使其变成复杂的加密密文发送出去，
收信方收到密文后，若想解读出原文，则需要使用加密时用的密钥以及相同
加密算法的逆算法对密文进行解密，才能使其回复成可读明文。在对称加密算法中，使用的密钥只有一个，收发双方都使用这个密钥，这就需要解密方事先
知道加密密钥。
非对称加密算法是一种密钥的保密方法。非对称加密算法需要两个密钥：公开密钥（publickey）和私有密钥（privatekey）。
公开密钥与私有密钥是一对，如果用公开密钥对数据进行加密，只有用对应的私有密钥才能解密；如果用私有密钥对数据进行加密，那么只有用对应的
公开密钥才能解密。因为加密和解密使用的是两个不同的密钥，所以这种算法叫作非对称加密算法。 非对称加密算法实现机密信息交换的基本过程是：
甲方生成一对密钥并将其中的一把作为公用密钥向其它方公开；得到该公用密钥的乙方使用该密钥对机密信息进行加密后再发送给甲方；甲方再用自己
保存的另一把专用密钥对加密后的信息进行解密。

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rsa();

    }

//    private void rsa() {
//        /*
//        第一步：准备100条对象数据
//         */
//        List<Person> personList=new ArrayList<>();
//        int testMaxCount=100;//测试的最大数据条数
//        //添加测试数据
//        for(int i=0;i<testMaxCount;i++){
//            Person person =new Person();
//            person.setAge(i);
//            person.setName("person"+String.valueOf(i));
//            personList.add(person);
//        }
//        //GSON生成json数据
//        Gson gson = new Gson();
//        String jsonData=  gson.toJson(personList);
//
//        Log.e("Test","加密前json数据 ---->"+jsonData);
//        Log.e("Test","加密前json数据长度 ---->"+jsonData.length());
//
//        /*
//        第二步生成秘钥对
//         */
//        KeyPair keyPair=RSAUtils.generateRSAKeyPair(RSAUtils.DEFAULT_KEY_SIZE);
//        // 公钥
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        // 私钥
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//        /*
//        接下来分别使用公钥加密 私钥解密   私钥加密 公钥解密
//         */
//        //公钥加密
//        long start=System.currentTimeMillis();
//        byte[] encryptBytes= new byte[0];
//        try {
//            encryptBytes = RSAUtils.encryptByPublicKeyForSpilt(jsonData.getBytes(),publicKey.getEncoded());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        long end=System.currentTimeMillis();
//        Log.e("MainActivity","公钥加密耗时 cost time---->"+(end-start));
//        String encryStr=BASE64Encoder.encode(encryptBytes);
//        Log.e("MainActivity","加密后json数据 --1-->"+encryStr);
//        Log.e("MainActivity","加密后json数据长度 --1-->"+encryStr.length());
//        //私钥解密
//        start=System.currentTimeMillis();
//        byte[] decryptBytes=  RSAUtils.decryptByPrivateKeyForSpilt(BASE64Decoder.decodeToBytes(encryStr),privateKey.getEncoded());
//        String decryStr=new String(decryptBytes);
//        end=System.currentTimeMillis();
//        Log.e("MainActivity","私钥解密耗时 cost time---->"+(end-start));
//        Log.e("MainActivity","解密后json数据 --1-->"+decryStr);
//
//        //私钥加密
//        start=System.currentTimeMillis();
//        encryptBytes=    RSAUtils.encryptByPrivateKeyForSpilt(jsonData.getBytes(),privateKey.getEncoded());
//        end=System.currentTimeMillis();
//        Log.e("MainActivity","私钥加密密耗时 cost time---->"+(end-start));
//        encryStr=Base64Encoder.encode(encryptBytes);
//        Log.e("MainActivity","加密后json数据 --2-->"+encryStr);
//        Log.e("MainActivity","加密后json数据长度 --2-->"+encryStr.length());
//        //公钥解密
//        start=System.currentTimeMillis();
//        decryptBytes=  RSAUtils.decryptByPublicKeyForSpilt(Base64Decoder.decodeToBytes(encryStr),publicKey.getEncoded());
//        decryStr=new String(decryptBytes);
//        end=System.currentTimeMillis();
//        Log.e("MainActivity","公钥解密耗时 cost time---->"+(end-start));
//        Log.e("MainActivity","解密后json数据 --2-->"+decryStr);
//    }
}
