package com.example.demo.groupfunc;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupFuncPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2322563068195864891L;
	private long groupid;
	private long funcid;
	
}
