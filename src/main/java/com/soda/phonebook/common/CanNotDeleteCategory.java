package com.soda.phonebook.common;

public class CanNotDeleteCategory extends RuntimeException{
	
	private static final long serialVersionUID = -976843304790926688L;
	
	public CanNotDeleteCategory(){
		super();
	}
	public CanNotDeleteCategory(String string){
		super(string);
	}
}
