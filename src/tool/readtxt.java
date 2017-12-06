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
 * ���ж�ȡtxt����
 * @author dhm
 *
 */
public class readtxt {
	private static final double ALPHA = 0.85;  
    private static final double DISTANCE =  0.1; 
    private static final double Intite = 1; //ÿ������Դ�ĳ�ʼֵ 
	public static void main(String args[]) throws IOException{
		String filePath="./data\\wenzhang.txt"; 
		String wPath="./data\\"+"wenzhang"+"ed.txt"; 
		ArrayList url=new ArrayList(); 
		ArrayList suburl=new ArrayList();    //��������ҳurl,�ڶ���
		try{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			int index=0;
			String pre=br.readLine();
			String webcon[][]=new String[100][2];
			//��ͼ��Ϣtxt�������webcon
			while(pre!=null){
				String[] aftersplit=pre.split(" ");
				webcon[index][0]=aftersplit[0];
				webcon[index][1]=aftersplit[1];
				if(url.indexOf(webcon[index][0])==-1)  //������
					url.add(webcon[index][0]);
				if(url.indexOf(webcon[index][1])==-1)
					url.add(webcon[index][1]);
				pre=br.readLine();
				index++;
			}
			int n=url.size();   //�ڵ����
			int edge=index;		//�ߵ�����
			double[][] arrayS=new double[n][n];
			double[][] arrayG=new double[n][n];
			ArrayList pageRank=new ArrayList();
			for(int i=0;i<n;i++){  //��
				String node1=url.get(i).toString();      //��ȡÿһ���ڵ�
				double allNum=0;
				for(int k=0;k<edge;k++){        //����k����ÿ����
					if(webcon[k][0].equals(node1)){
						allNum++;                //��ÿ���ڵ�������ĳ���allnum
					}
				}
//				System.out.println(url.get(i)+"��outdegreeΪ"+allNum);
				for(int j=0;j<n;j++){
					String Index2=url.get(j).toString();
					for(int k=0;k<edge;k++){
						if(webcon[k][0].equals(node1)&&webcon[k][1].equals(Index2)){
							arrayS[j][i]=1/allNum;
						}
					}
				}
			}
			/*����G��ʼ��*/
			for(int i=0;i<n;i++){
	        	 for(int j=0;j<n;j++){
	        		 arrayG[i][j]=(1-ALPHA)/n+ALPHA*arrayS[i][j];
	        	 }
	        }
			/*pageRank��ʼ��*/
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
				bw.flush(); //�����ݸ������ļ�
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
