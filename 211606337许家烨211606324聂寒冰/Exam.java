package test1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Exam {
	 public static void main(String[] args) throws ScriptException, IOException
     {	
	        creatArith();//��������ʽ
	}
	//���������
	public static int getNumber(){  
	        int number = 0;  
	        Random num = new Random();  
	        number = num.nextInt(100+1);  
	        return number;  
	} 
	//��������������
	public static char getOperator(){  
     char operator = 0;  
     Random ran = new Random();  
     int i = ran.nextInt(4);  
     switch(i){  
         case 0:  
             operator = '+';  
             break;  
         case 1:  
             operator = '-';  
             break;  
         case 2:  
             operator = '*';  
             break;  
         case 3:  
             operator = '/';  
             break;  
     }  
     return operator;  
  } 
	//�����������ʽ
	static ScriptEngine js = new ScriptEngineManager().getEngineByName("JavaScript");
	public static void creatArith() throws ScriptException, IOException{
		boolean flag=true;
		int num=0;
		do {
			System.out.print("������Ҫ��������������ĸ�����");
			Scanner arith_num = new Scanner(System.in);
			num=arith_num.nextInt();
			if(num>=1) {
				flag = true;
			}
			else {
				flag=false;
				System.out.println("��������");
			}
		}while(flag==false);
		ArrayList<String> arithList = new ArrayList<String>();//����ʽ
		for(int i=0;i<num;i++)
		{
			//����3~5��������
			int num_number = 0;
			Random n = new Random();
			num_number =n.nextInt(3)+3;
			
			String arr = "";
			for(int j=0;j<num_number;j++){
				int number = getNumber();
				String operator = String.valueOf(getOperator());
				arr += number + operator;
			}
			arr =arr.substring(0,arr.length()-1);
			//��arrת��Ϊ����
			char[] arr_str = arr.toCharArray();
			String arrString = "";
			ArrayList<String> arrslist1 = new ArrayList<String>();
			ArrayList<String> arrslist2 = new ArrayList<String>();
			ArrayList<String> arrslist = new ArrayList<String>();
			//�洢�����
			for(int j=0;j<arr_str.length;j++) {
				if(arr_str[j]=='+'||arr_str[j]=='-'||arr_str[j]=='*'||arr_str[j]=='/') {	
					arrString=String.valueOf(arr_str[j]);
					arr_str[j]=',';
					arrslist1.add(arrString);
				}
			}
			//�洢������
			String s = new String(arr_str);
			String[] ss = s.split(",");
			for(int k=0;k<ss.length;k++) {
				arrslist2.add(ss[k]);
			}
			//��������ʽ
			String[] arrs1 = arrslist1.toArray(new String[arrslist1.size()]);//�����
			String[] arrs2 = arrslist2.toArray(new String[arrslist2.size()]);//������
			
			for(int m=0;m<arrs1.length;m++){
				arrslist.add(arrs2[m]);//������
				arrslist.add(arrs1[m]);//�����
			}
			arrslist.add(arrs2[arrs2.length-1]);
			String[] arrs = arrslist.toArray(new String[arrslist2.size()]);//����ʽ
			arr="";//��ʼ��Ϊ��
			check_arith(arrs);
			for(int t=0;t<arrs.length;t++) {
				arr+=arrs[t];
			}
			check_arith_question(arrs,arr);
			arr="";
			for(int t=0;t<arrs.length;t++) {
				arr+=arrs[t];
			}
			arithList.add(arr);
			
		}
		//�������ʽ
		for(String arr: arithList){
			try {System.out.println(arr);
				System.out.println(arr+"="+js.eval(arr));
			} catch (ScriptException e) {
				e.printStackTrace();
			} 	
			
		}
		filewriter(arithList);
	}
	private static void filewriter(ArrayList<String> arithList) {
		// TODO Auto-generated method stub
		
	}
	public static void  check_arith(String arr[]){
		for(int i=0;i<arr.length;i++){
			//�ж��Ƿ��������	
			if(arr[i].contains("-")){
				if(Integer.valueOf(arr[i-1])<Integer.valueOf(arr[i+1])){
					arr[i]="+";
				}
			}
			//�ж��Ƿ����С��
			if(arr[i].contains("/")){
				int a = Integer.valueOf(arr[i-1]);
				int b = Integer.valueOf(arr[i+1]);
				int x = a%b;
				if(x!=0){
					arr[i]="+";
				}
			}
		}
	}
	//�жϽ���Ƿ����С��
	public static void  check_arith_question(String arr[],String arrString) throws ScriptException{
			for(int i=0;i<arr.length;i++){
				String str = js.eval(arrString).toString();
				if(Integer.valueOf(str)<0) {
					if(arr[i].contains("-")){
						arr[i]="+";
					}
				}				
			}
	}
	public static void filewriter1(ArrayList<String> arithList) throws IOException {
		//封装存储位置
		BufferedWriter BufferedWriterbw = new BufferedWriter(new FileWriter("result.txt"));
		  BufferedWriterbw.newLine();
		  for(String s : arithList){
		        //写数据
			  try {
				BufferedWriterbw.write(s+"="+js.eval(s));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
			  BufferedWriterbw.newLine();
			  BufferedWriterbw.flush();
		  }
	}
    
}
















