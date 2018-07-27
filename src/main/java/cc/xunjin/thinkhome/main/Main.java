package cc.xunjin.thinkhome.main;

import cc.xunjin.thinkhome.client.ThinkHomeHouseClient;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/27.
 * @author WangBo.
 */
public class Main {

    public static void main(String[] args) {

        //创建一个对应的 客户端。
        ThinkHomeHouseClient houseClient = new ThinkHomeHouseClient();

        //调用客户端的 API。
        houseClient.getHomes();
    }
}
