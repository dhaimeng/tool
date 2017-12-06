package excel;

import java.io.File;
import java.io.IOException;
import org.dom4j.DocumentException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExcelTest {
	public static void main(String file_path[]) throws IOException, DocumentException, BiffException, RowsExceededException, WriteException {
		
		writeExcel("C:\\Users\\dhm\\Desktop\\coffee.xlsx");
		readExcel();
		System.out.println("done"); 
	}
	public static void readExcel() throws BiffException, IOException {
		Workbook wb=Workbook.getWorkbook(new File("C:\\Users\\dhm\\Desktop\\coffee.xlsx"));
		Sheet sheet = wb.getSheet(0); //get sheet(0)
		//遍历
		for(int i=0; i<sheet.getRows(); i++)
		{
			for (int j = 0; j < sheet.getColumns(); j++) 
			{
				System.out.println(sheet.getCell(j,i).getContents());
			}
		}
		System.out.println("行："+sheet.getRows()+"   列："+sheet.getColumns());
		
		
	}
    public static void writeExcel(String fileName) throws IOException, RowsExceededException, WriteException{    
            
        WritableWorkbook wwb = Workbook.createWorkbook(new File(fileName));
           
        //创建一个可写入的工作表    
        //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置    
        WritableSheet ws = wwb.createSheet("工作表名称:hahahaha", 0);    
            //下面开始添加单元格    
        for(int i=0;i<5;i++){    
                for(int j=0;j<10;j++){    
                    //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行    
                    Label labelC = new Label(j, i, "第 "+(i+1)+"行"+(j+1)+"列"); 
                        //将生成的单元格添加到工作表中    
                        ws.addCell(labelC);    
                }    
         }    
         //从内存中写入文件中    
         wwb.write();    
         //关闭资源，释放内存    
         wwb.close();    
    }
}
    