package com.jly.purejiandan;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by jly on 2016/6/15.
 */
public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        //生成数据库文件的目标包名//target package for dao files
        //第一个参数是数据库版本号，第二个参数是包的根目录的包
        Schema schema = new Schema(1, "db");
        addTaskDetail(schema);
        new DaoGenerator().generateAll(schema,"../PureJianDan/app/src/main/java/com/jly/purejiandan/");
    }
    private static void addTaskDetail(Schema schema) {
        //指定实体类
        Entity entity = schema.addEntity("ReadFresh");
        entity.addIntProperty("freshId");
    }

}
