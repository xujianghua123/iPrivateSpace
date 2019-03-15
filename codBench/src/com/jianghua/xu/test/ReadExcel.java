package com.jianghua.xu.test;

import com.jianghua.xu.util.ExcelWriteUtil;
import com.jianghua.xu.util.TxtWriteUtil;
import com.jianghua.xu.util.ExcelReadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xujianghua
 * @description : 读取Excel
 * @date : 2019/3/8 17:02
 */
public class ReadExcel {

    public static void main(String[] args) {
        ReadExcel readExl = new ReadExcel();
        ExcelWriteUtil writeUtil = new ExcelWriteUtil();
        String type[] = {"C1","C3","C4","C5","Z2"};
        String titleRow[] = {"通道编码","机构号","机构名称"};
        String fileDir = "C:\\Users\\Administrator\\Desktop\\网联——C类和Z2类总部通道机构编码汇总.xls";
        for(String choose: type){
            List<String[]> list = readExl.getList(choose);
            List<String[]> writeList = readExl.getAllList(list);
            writeUtil.writeExcel(fileDir,choose,titleRow,writeList);
            System.out.println(choose+"输出成功！");
        }
        /*String path1 = "t_epcc_corginf_"+type;
        String path2 = "t_cmm_orgn_"+type;
        readExl.getSql(list,path1,path2);
        System.out.println("总数量："+list.size());*/

    }

     /**
      * @description : 获取总的列表信息
      * @author : xujianghua
      * @date : 2019/3/11 17:26
      *
      */
    public List<String[]> getList(String choose){
        ExcelReadUtil readExl = new ExcelReadUtil();
        List<String[]> list1 = readExl.readExcel("C:/Users/Administrator/Desktop/网联——C类和Z2类总部机构金融机构编码.xls",0);
        List<String[]> list2 = readExl.readExcel("C:/Users/Administrator/Desktop/网联——C类和Z2类总部机构金融机构编码.xls",1);
        List<String[]> list3 = readExl.readExcel("C:/Users/Administrator/Desktop/t_epcc_corginf.xlsx",0);
        System.out.println("c类总部机构数量："+list1.size());
        System.out.println("z类总部机构数量："+list2.size());
        System.out.println("t_epcc_corginf数量："+list3.size());
        List<String[]> list4 = new ArrayList<String[]>();
        String[] str3 = new String[list3.size()];
        int m = 0;
        for (String[] str:list3) {
            str3[m] = str[2];
            m++;
        }
        for (String[] str:list1) {
            boolean flag = true;
            for(int i=0;i<list3.size();i++){
                if((str3[i].trim()).equals(str[0].trim())) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                String[] str1 = new String[2];
                str1[0] = str[0];
                str1[1] = str[1];
                list4.add(str1);
            }

        }
        for (String[] str:list2) {
            boolean flag = true;
            for(int i=0;i<list3.size();i++){
                if((str[0].trim()).equals(str3[i].trim())) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                String[] str2 = new String[2];
                str2[0] = str[2];
                str2[1] = str[1];
                list4.add(str2);
            }
        }
        return this.listSplit(list4,choose);
    }

     /**
      * @description : 列表拆分
      * @author : xujianghua
      * @date : 2019/3/11 17:27
      */
    public List<String[]> listSplit(List<String[]> list,String choose){
        List<String[]> list_C1 = new ArrayList<String[]>(); // 银行
        List<String[]> list_C3 = new ArrayList<String[]>(); // 信用社
        List<String[]> list_C4 = new ArrayList<String[]>(); // 互助社
        List<String[]> list_C5 = new ArrayList<String[]>(); // 财务公司
        List<String[]> list_Z2 = new ArrayList<String[]>(); // 非银行金融机构
        for (String[] str:list) {
            if(str[0].substring(0,2).equals("C1")){
                list_C1.add(str);
            }else if(str[0].substring(0,2).equals("C3")){
                list_C3.add(str);
            }else if(str[0].substring(0,2).equals("C4")){
                list_C4.add(str);
            }else if(str[0].substring(0,2).equals("C5")){
                list_C5.add(str);
            }else if(str[0].substring(0,2).equals("Z2")){
                list_Z2.add(str);
            }
        }
        switch (choose){
            case "C1":
                return list_C1;
            case "C3":
                return list_C3;
            case "C4":
                return list_C4;
            case "C5":
                return list_C5;
            case "Z2":
                return list_Z2;
            default:
                return null;
        }

    }

     /**
      * @description : 按照指定规则生成sql,并导出txt到本地
      * @author : xujianghua
      * @date : 2019/3/11 17:24
      */
    public void getSql(List<String[]> list,String excelName1,String excelName2) {
        TxtWriteUtil export = new TxtWriteUtil();
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        System.out.println("总数量："+list.size());
        int n = 1 ;
        for (String[] str:list) {
            String sql = "Insert into ipay.t_epcc_corginf (CAP_CORG,CNL_TYP,PYERISSRID,RSVFDISSRID,ACC_NO,ACC_NM,LBNK_NO,LBNK_NM,TM_SMP) values ('";
            sql += this.serialReg(str[0],n)+"','B2E','"+str[0]+"','Z2008631000011','215500691','安付宝商务有限公司',' ','"+str[1]+"',to_char(sysdate,'yyyymmddhh24miss'));";
            String sql1 = "Insert into ipay.t_cmm_orgn (CORP_ORG,ENB_FLG,CORG_NM,CORG_TYP,CTT_PSN_CNM,CTT_PSN_TEL,CUS_MGR,CORP_SNM,CORP_URL,TM_SMP,RSV_FLD1,BNK_TYP,LNK_FLG,LBNK_CD,NOD_ID) values ('";
            sql1 += this.serialReg(str[0],n)+"','1','"+str[1]+"','0',' ',' ',' ','"+str[1]+"',' ',to_char(sysdate,'yyyymmddhh24miss'),' ',' ',' ',' ',' ');";
            n++;
            list1.add(sql);
            list2.add(sql1);
        }
        export.writeFileContext(list1,excelName1);
        export.writeFileContext(list2,excelName2);
    }
 /**
  * @description : 生成通道编码
  * @author : xujianghua
  * @date : 2019/3/11 17:23
  * @params :
  * @return : String
  */
    public String serialReg(String str,int num){
        String serNum = str.substring(0,2);
        if(num<10){
            serNum += "000"+num;
        }else if(num>=10 && num <100){
            serNum += "00"+num;
        }else if(num>=100 && num <1000){
            serNum += "0"+num;
        }else{
            serNum += num;
        }
        return serNum;
    }

    public List<String[]> getAllList(List<String[]> list){
        List<String[]> listAll = new ArrayList<String[]>();
        int n = 1;
        for (String[] str:list) {
            String[] strList = new String[3];
            strList[0] = this.serialReg(str[0],n);
            strList[1] = str[0];
            strList[2] = str[1];
            listAll.add(strList);
            n++;
        }
        return listAll;
    }

}
