package com.base.classes;

import java.util.ArrayList;
import java.util.List;

public class ListExample1 {
	public static void main(String args[]){  
		 //Creating a List  
		 List<String> list=new ArrayList<String>();  
		 //Adding elements in the List  
		 list.add("1");  
		  list.add("2");  
		 list.add("3");  
		 list.add("4");  
		 list.add("adding number 1-4");
		 list.add(null);
list.add("1");
		 //Iterating the List element using for-each loop  
		 for(String fruit:list) 
			 System.out.println("before"+fruit);//1,2,3,4  
if(list.get(1).equals("2"))
{
	for(int i=1;i<=list.size()-3;i++)
	{
	list.remove(list.get(i));
	}

	 for(String fruit:list) 
		 System.out.println("After"+fruit);
}
			
		  
		}  
	
	

		}  
