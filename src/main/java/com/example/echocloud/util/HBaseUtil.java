package com.example.echocloud.util;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;


public class HBaseUtil {

    /**
     * 创建表
     * @param tableName 表名
     * @param crd 列族的数组
     * @return 是否创建成功
     */
    public static boolean createTable(String tableName,String[] crd){

        try(HBaseAdmin admin = (HBaseAdmin)HBaseConn.getHBaseConn().getAdmin()){
            System.out.println(5555555);
               if(admin.tableExists(tableName)){
                   System.out.println(123123);
                   return false;
               }
            System.out.println(456456);
            HTableDescriptor tableDescriptor=new HTableDescriptor(TableName.valueOf(tableName));
               Arrays.stream(crd).forEach(cf->{
                   HColumnDescriptor columnDescriptor=new HColumnDescriptor(cf);
                   columnDescriptor.setMaxVersions(1);
                   tableDescriptor.addFamily(columnDescriptor);
               });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * 删除表
     * @param tableName 表名
     * @return 是否成功
     *
     */

    public static boolean deleteTable(String tableName){
        try (HBaseAdmin admin = (HBaseAdmin)HBaseConn.getHBaseConn().getAdmin()){

            //先屏蔽该表
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
/**
 * 插入一条数据
 *
 * @param tableName 表名
 * @param rowKey 唯一标识
 * @param cfNmame 列族名
 * @param qualifier 列标识
 * @param data 数据
 * @return 是否插入成功
 */
    public static boolean putRow(String tableName,String rowKey,String cfNmame,String qualifier,MultipartFile data) throws IOException {
        byte[] buffer = data.getBytes();
      try(Table table=HBaseConn.getTable((tableName))){
          Put put = new Put(Bytes.toBytes(rowKey));
          put.addColumn(Bytes.toBytes(cfNmame),Bytes.toBytes(qualifier),Bytes.toBytes(ByteBuffer.wrap(buffer)));
          table.put(put);
      } catch (IOException e) {
          e.printStackTrace();
      }


        return true;
    }

    /**
     * 批量插入
     * @param tableName
     * @param puts
     * @return
     */
    public static boolean putRows(String tableName,List<Put> puts){
        try (Table table = HBaseConn.getTable(tableName)){
            table.put(puts);

        }catch (IOException i){
            i.printStackTrace();
        }
        return true;
    }

    /**
     * 获取单条数据
     * @param tableName 表名
     * @param rowKey 唯一标识
     * @return 查询结果
     */
    public static Result getRow(String tableName, String rowKey){
        try (Table table = HBaseConn.getTable(tableName)){
            Get get=new Get(Bytes.toBytes(rowKey));
            return table.get(get);
        }catch (IOException i){
            i.printStackTrace();
        }
        return null;
    }

    /**
     * 根据过滤器获取单条数据
     * @param tableName
     * @param rowKey
     * @param filterList
     * @return
     */
    public static Result getRow(String tableName, String rowKey, FilterList filterList){
        try (Table table = HBaseConn.getTable(tableName)){
            Get get=new Get(Bytes.toBytes(rowKey));
            get.setFilter(filterList);
            return table.get(get);
        }catch (IOException i){
            i.printStackTrace();
        }
        return null;
    }

    /**
     * 通过Scan检索数据
     * @param tableName
     * @return
     */
    public static ResultScanner getScanner(String tableName){
        try (Table table = HBaseConn.getTable(tableName)){
           Scan scan =new Scan();

           //缓存的条数
           scan.setCaching(1000);
            return table.getScanner(scan);
        }catch (IOException i){
            i.printStackTrace();
        }
        return null;
    }

    /**
     * 根据区间批量检索数据
     * @param tableName
     * @param startRowKey 起始rowKey
     * @param endRowKey 终止rowKey
     * @return ResultScanner实例
     */
    public static ResultScanner getScanner(String tableName, String startRowKey, String endRowKey){
        try (Table table = HBaseConn.getTable(tableName)){
            Scan scan =new Scan();
            scan.setStartRow(Bytes.toBytes(startRowKey));
            scan.setStopRow(Bytes.toBytes(endRowKey));
            //缓存的条数
            scan.setCaching(1000);

            return table.getScanner(scan);
        }catch (IOException i){
            i.printStackTrace();
        }
        return null;
    }

    public static ResultScanner getScanner(String tableName, String startRowKey, String endRowKey, FilterList filterList){
        try (Table table = HBaseConn.getTable(tableName)){
            Scan scan =new Scan();
            scan.setStartRow(Bytes.toBytes(startRowKey));
            scan.setStopRow(Bytes.toBytes(endRowKey));
            scan.setFilter(filterList);
            //缓存的条数
            scan.setCaching(1000);

            return table.getScanner(scan);
        }catch (IOException i){
            i.printStackTrace();
        }
        return null;
    }

    /**
     * 删除一行数据
     *
     * @param tableName
     * @param rowKey 唯一标识
     * @return
     *
     * Hbase没有update操作
     */
    public static boolean deleteRow(String tableName,String rowKey){
        try (Table table = HBaseConn.getTable(tableName)){
            Delete delete =new Delete(Bytes.toBytes(rowKey));
            table.delete(delete);


        }catch (IOException i){
            i.printStackTrace();
        }
        return true;
    }

    public  static boolean deleteCloumnFamily(String tableName,String cfName){
        try (HBaseAdmin admin = (HBaseAdmin)HBaseConn.getHBaseConn().getAdmin()){

            admin.deleteColumn(tableName,cfName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean deleteQualifier(String tableName,String rowKey,String cfName,String qualifier){
        try (Table table = HBaseConn.getTable(tableName)){
            Delete delete =new Delete(Bytes.toBytes(rowKey));
            delete.addColumn(Bytes.toBytes(cfName),Bytes.toBytes(qualifier));

            table.delete(delete);
        }catch (IOException i){
            i.printStackTrace();
        }
        return true;
    }
}

