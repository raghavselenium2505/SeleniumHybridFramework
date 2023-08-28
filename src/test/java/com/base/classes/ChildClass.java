package com.base.classes;

public class ChildClass extends MethodOverloadingConcept{

	public static void main(String[] args) {

		MethodOverloadingConcept parent=new MethodOverloadingConcept();
		parent.Method1();
		parent.Method1(12);
		parent.Method1("Hello",12);
		parent.Method1('A',"Hello",12);
		parent.Method1('A',"Hello",12,122);
		
		
	}

}
