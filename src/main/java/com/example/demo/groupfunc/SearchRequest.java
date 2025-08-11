package com.example.demo.groupfunc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
	private long groupid;
	private long funcid;
	private String authtype;
}
