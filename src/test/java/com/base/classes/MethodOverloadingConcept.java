package com.base.classes;

public class MethodOverloadingConcept  {
	
	public void Method1()
	{
		System.out.println("Default");
		
	}
	
	public void Method1(int i)
	{

	
		System.out.println(i);
	}
	public void Method1(String A,int i)
	{

System.out.println(A+" "+i);
	}
	public void Method1(char ch,String B,int i)
	{
System.out.println(ch+B+i);
		
	}
	public void Method1(char ch,String B,int i,int j)
	{
	
		System.out.println(i+j);

	
	
	}

}
