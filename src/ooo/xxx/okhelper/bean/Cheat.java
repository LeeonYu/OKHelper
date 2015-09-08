package ooo.xxx.okhelper.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Cheat extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BmobFile name;

	public BmobFile getName() {
		return name;
	}

	public void setName(BmobFile name) {
		this.name = name;
	}

}