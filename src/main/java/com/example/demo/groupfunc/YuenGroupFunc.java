package com.example.demo.groupfunc;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@IdClass(GroupFuncPK.class)
@Entity
@Table(name = "YUEN_GROUPFUNC")
@AllArgsConstructor
@NoArgsConstructor
public class YuenGroupFunc implements Serializable{
	
		private static final long serialVersionUID = 1L;
		@Id
		@Column(name="GROUP_ID")
		private long groupid;
		@Id
		@Column(name="FUNC_ID")
		private long funcid;
		@Column(name="AUTH_TYPE")
		private String authtype;
		@Column(name="cdate")
		private Timestamp cdate;
}
