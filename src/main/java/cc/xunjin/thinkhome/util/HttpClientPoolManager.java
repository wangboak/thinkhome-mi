package cc.xunjin.thinkhome.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * http 链接池管理器。
 */
public class HttpClientPoolManager {

    private static PoolingHttpClientConnectionManager clientConnectionManager;

    public static HttpClientBuilder clientBuilder = null;

    static {
        clientConnectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        clientConnectionManager.setMaxTotal(Configure.MAX_HTTP_CONNECTIONS);
        //每个服务最大连接数。因为这里只连接一个服务器，所以最大连接数和perRoute数一致。
        clientConnectionManager.setDefaultMaxPerRoute(Configure.MAX_HTTP_CONNECTIONS);

        //@see https://stackoverflow.com/questions/25889925/apache-poolinghttpclientconnectionmanager-throwing-illegal-state-exception
        clientBuilder = HttpClients.custom()//
                .setConnectionManager(clientConnectionManager)//
                .setConnectionManagerShared(true);//
    }

    /**
     * 获取Http Client。
     * @return
     */
    public static CloseableHttpClient getHttpClient() {
        return clientBuilder.build();
    }
}
