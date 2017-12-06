package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 按行读取txt内容
 * @author dhm
 *
 */
public class readtxt {
	private static final double ALPHA = 0.85;  
    private static final double DISTANCE =  0.1; 
    private static final double Intite = 1; //每个数据源的初始值 
	public static void main(String args[]) throws IOException{
		String filePath="./data\\wenzhang.txt"; 
		String wPath="./data\\"+"wenzhang"+"ed.txt"; 
		ArrayList url=new ArrayList(); 
		ArrayList suburl=new ArrayList();    //子新闻网页url,第二列
		try{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			int index=0;
			String pre=br.readLine();
			String webcon[][]=new String[100][2];
			//将图信息txt存入矩阵webcon
			while(pre!=null){
				String[] aftersplit=pre.split(" ");
				webcon[index][0]=aftersplit[0];
				webcon[index][1]=aftersplit[1];
				if(url.indexOf(webcon[index][0])==-1)  //不存在
					url.add(webcon[index][0]);
				if(url.indexOf(webcon[index][1])==-1)
					url.add(webcon[index][1]);
				pre=br.readLine();
				index++;
			}
			int n=url.size();   //节点个数
			int edge=index;		//边的条数
			double[][] arrayS=new double[n][n];
			double[][] arrayG=new double[n][n];
			ArrayList pageRank=new ArrayList();
			for(int i=0;i<n;i++){  //列
				String node1=url.get(i).toString();      //读取每一个节点
				double allNum=0;
				for(int k=0;k<edge;k++){        //变量k遍历每条边
					if(webcon[k][0].equals(node1)){
						allNum++;                //对每个节点计算它的出度allnum
					}
				}
//				System.out.println(url.get(i)+"的outdegree为"+allNum);
				for(int j=0;j<n;j++){
					String Index2=url.get(j).toString();
					for(int k=0;k<edge;k++){
						if(webcon[k][0].equals(node1)&&webcon[k][1].equals(Index2)){
							arrayS[j][i]=1/allNum;
						}
					}
				}
			}
			/*矩阵G初始化*/
			for(int i=0;i<n;i++){
	        	 for(int j=0;j<n;j++){
	        		 arrayG[i][j]=(1-ALPHA)/n+ALPHA*arrayS[i][j];
	        	 }
	        }
			/*pageRank初始化*/
			for(int i=0;i<n;i++){
	        	 pageRank.add(i, Intite);
	        }
			int iterator =0; 
			ArrayList prePR=new ArrayList();
			do{
	        	 prePR=pageRank;
	        	 pageRank=doPageRank(arrayG,prePR);       	         	 
	        	 iterator++;
			}while(discompare(prePR,pageRank)==0);
			FileWriter fw = new FileWriter(wPath, false);
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0;i<url.size();i++){
				bw.write(url.get(i).toString()+": "+pageRank.get(i).toString());
				bw.newLine();
				bw.flush(); //将数据更新至文件
			}
			bw.close();
			fw.close();		
		}catch (Exception e){
			System.out.println("File not find!");
		}
	}
	public static ArrayList doPageRank(double[][] arrayG,ArrayList pre){
		int n=arrayG.length;  
		ArrayList pageRank=new ArrayList();		
		for(int i=0;i<n;i++){
			 double p=0;
        	 for(int j=0;j<n;j++){
        		 p+=arrayG[i][j]*Double.parseDouble(pre.get(j).toString());        		 
        	 }
        	 pageRank.add(i,p);
         }
		return pageRank;
	}
	public static int discompare(ArrayList pre,ArrayList pageRank){
		int flag=1;
		for(int i=0;i<pre.size();i++){
			 double p1=Double.parseDouble(pre.get(i).toString());
			 double p2=Double.parseDouble(pageRank.get(i).toString());	
			 double dis=Math.abs(p1-p2);
			 if(dis>DISTANCE){  
            	 flag = 0;
            	 break;
             } 
		}
		return flag;
	}	
	
}
