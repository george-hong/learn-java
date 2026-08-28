/**
 * page 282
 * 6.4 服务加载器
 * META-INF/services 目录是 Java SPI（Service Provider Interface，服务提供者接口） 机制的核心组成部分
 * 简单来说，它帮助 Java 程序实现可插拔的架构，让系统能够在运行时动态地发现和加载服务实现，而无需在代码中硬编码具体的实现类
 * 这个目录需要放在源代码根目录
 *  1.java.util.ServiceLoader<S>
 *      static <S>ServiceLoader load(Class<S> service) 创建一个服务加载器来加载实现了给定服务接口的类
 *      Iterator<S> iterator() 生成一个以“懒”方式加载服务类的迭代器，只有迭代器推进时才会加载类
 *      Stream<ServiceLoader.Provider<S>> stream() 返回提供者描述符的一个流，从而可以采用兰方式加载所需类的提供者
 *      Optional<S> findFirst() 查找第一个可用的服务提供者（如果有）
 *  2.java.util.ServiceLoader.Provider<S>
 *      Class<? extends S> type() 获得者提供者的类型
 *      S get() 获得这个提供者的实例
 */
package page282ServcerLoader;

import java.util.Optional;
import java.util.ServiceLoader;

public class Demo {
    public static ServiceLoader<Cipher> cipherLoader = ServiceLoader.load(Cipher.class);

    public static Cipher getCipher(int minStrength) {
        for (Cipher cipher : cipherLoader) {
            if (cipher.strength() >= minStrength) return cipher;
        }
        return null;
    }

    public static void main(String[] args) {
        Optional<Cipher> cipher = cipherLoader.findFirst();
        System.out.println(cipher);
        for (Cipher ins : cipherLoader) {
            System.out.println("ins:" + ins);
        }
    }
}

interface Cipher {
    byte[] encrypt(byte[] source, byte[] key);
    byte[] decrypt(byte[] source, byte[] key);
    int strength();
}