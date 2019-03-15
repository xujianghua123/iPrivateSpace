package com.jianghua.xu.test;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : xujianghua
 * @description : TODO
 * @date : 2019/3/8 17:43
 */
public class ReadExcelTest {

    public static void main(String[] args) throws Exception{
        FileInputStream fout1 = new FileInputStream(new File("C:/Users/Administrator/Desktop/网联——C类和Z2类总部机构金融机构编码.xls"));
        FileInputStream fout2 = new FileInputStream(new File("C:/Users/Administrator/Desktop/t_epcc_corginf.xlsx"));
        List<String[]> list = ReadExcelTest.read(fout1);

        for (String[] str:list) {
            for(String s:str){
                System.out.print("\t"+s);
            }
            System.out.println();
        }
    }


    public static List<String[]> read(InputStream is)  {
        List<String[]> data = new ArrayList<String[]>();
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(is);
            int nos = wb.getNumberOfSheets();
            for (int i = 0; i < nos; i++) {
                Sheet sheet = wb.getSheetAt(i);
                int rn = 0;
                for (Row row : sheet) {
                    rn++;
                    String[] c = getCellValue2(row);
                    if(c==null) {
                        continue;
                    }
                    if( c==null || c.length==0 ) {
                        continue;
                    }
                    boolean ok = false;
                    int len = c.length;
                    for( int x=0; x<len; x++) {
                        if( c[x]!=null && c[x].trim().length()>0 )
                        {
                            ok=true;
                        }
                    }
                    //过滤所有字段为空的记录
                    if( ok )
                        data.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{

        }
        return data;
    }

    public static String[] getCellValue2(Row row) {
        // 创建字符创缓冲区
        int lastCellnum = row.getLastCellNum();
        if(lastCellnum<=0) {
            return null;
        }
        String[] sb = new String[lastCellnum];
        for(int i=0;i<lastCellnum;i++){
            Cell cell = row.getCell(i);
            if(cell==null)
            {
                sb[i]="";
                continue;
            }

            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    sb[i] = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date c = cell.getDateCellValue();
                        SimpleDateFormat sFormat = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        sb[i] = sFormat.format(c);
                    } else {

                        String val = cell.getNumericCellValue() + "";
                        if( val.endsWith(".0"))
                        {
                            //去掉整数的.0,
                            Double d = cell.getNumericCellValue();
                            DecimalFormat df = new DecimalFormat("#");
                            val = df.format(d);
                        }

                        if( val.indexOf("E")  > 0 )
                        {
                            Double d = cell.getNumericCellValue();


                            val = format(d);

                        }
                        sb[i] = val;

                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    sb[i] = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    try {
                        sb[i] = ""+cell.getNumericCellValue();
                        break;
                    }catch (Exception e) {
                        // TODO: handle exception
                        sb[i] = ""+ String.valueOf(cell.getRichStringCellValue());
                        //break;
                    }
                    //sb[i] = ""+cell.getNumericCellValue();
                    break;
                default:
                    //System.err.println("rn:"+i);
                    //错误情况当空格处理
                    //sb[i] = cell.getRichStringCellValue().getString();
                    sb[i] = "";
            }
            if(sb[i]!=null){
                sb[i]=sb[i].trim();
            }
        }
        return sb;
    }

    public static String  format(Double d)
    {
        String val="";
        DecimalFormat df = new DecimalFormat("#.000000");
        val = df.format(d);
        int dotpos = val.indexOf('.');
        int dotlength = val.substring(dotpos+1).length();
        int lastchar='0';
        for(int x=val.length()-1; x>dotpos;x--)
        {
            if( lastchar=='0' && val.charAt(x)=='0')
            {
                dotlength--;
            }
            lastchar=val.charAt(x);
        }
        String format="#";
        if( dotlength>0 )
        {
            format="#.";
            for( int y=0; y<dotlength; y++)
            {
                format += "0";
            }
        }
        DecimalFormat pdf = new DecimalFormat(format);
        val = pdf.format(d);
        return val;
    }
}
