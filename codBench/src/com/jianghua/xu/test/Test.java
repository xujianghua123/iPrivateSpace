package com.jianghua.xu.test;

import com.jianghua.xu.util.ExcelReadUtil;
import com.jianghua.xu.util.ExcelWriteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xujianghua
 * @description : TODO
 * @date : 2019/1/18 18:38
 */
public class Test {

    public static void main(String args[]){

        Test test = new Test();
        test.allList();
    }

    private void allList(){
        ExcelReadUtil readExl = new ExcelReadUtil();
        ExcelWriteUtil writeUtil = new ExcelWriteUtil();
        List<String[]> readList = readExl.readExcel("C:\\Users\\Administrator\\Desktop\\t_epcc_corginf.xlsx",0);
        List<String[]> readList1 = readExl.readExcel("C:\\Users\\Administrator\\Desktop\\网联——C类和Z2类总部机构金融机构编码.xls",0);
        List<String[]> returnList = new ArrayList<String[]>();
        for(int i=0;i<readList1.size();i++){
            String[] str = new String[3];
            for(String[] read:readList){
                if(readList1.get(i)[0].equals(read[2])){
                    str[0] = read[0];
                    str[1] = read[2];
                    str[2] = readList1.get(i)[1];
                    returnList.add(str);
                    break;
                }
            }
        }
        String titleRow[] = {"通道编码","机构号","机构名称"};
        String fileDir = "C:\\Users\\Administrator\\Desktop\\网联——C类和Z2类总部通道机构编码汇总.xls";
        String sheetName = "生产已录入";
        ExcelWriteUtil excelWriteUtil = new ExcelWriteUtil();
        excelWriteUtil.writeExcel(fileDir,sheetName,titleRow,returnList);
        System.out.println(returnList.size());
    }

}
