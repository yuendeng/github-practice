package com.example.demo;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public ProxyController(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@RequestMapping(value = "/**", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.DELETE, RequestMethod.PATCH })
	public ResponseEntity<?> proxyAll(HttpServletRequest request, @RequestBody(required = false) byte[] body // 接收所有原始
																												// body
	) throws IOException {

		// 拿到原始 URL
		String originalUri = request.getRequestURI(); // /proxy/api/xxx
		String proxyPath = originalUri.replaceFirst("/proxy", ""); // /api/xxx
//		String targetUrl = "http://10.109.212.175:8080/" + proxyPath;
		String targetUrl = "http://www.crcind.com/" + proxyPath;

		if (request.getQueryString() != null) {
			targetUrl += "?" + request.getQueryString();
		}

		// 複製原本的 headers
		HttpHeaders headers = new HttpHeaders();
		Collections.list(request.getHeaderNames())
				.forEach(headerName -> headers.add(headerName, request.getHeader(headerName)));

		// 建立 HttpEntity（body + headers）
		HttpEntity<byte[]> httpEntity = new HttpEntity<>(body, headers);

		// 對應 HTTP 方法轉換
		HttpMethod method = HttpMethod.resolve(request.getMethod());

		// 發送轉發請求
		ResponseEntity<byte[]> response = restTemplate.exchange(targetUrl, method, httpEntity, byte[].class);

		// 回傳原始 response（狀態碼、header、body）
		return ResponseEntity.status(response.getStatusCode()).headers(response.getHeaders()).body(response.getBody());
	}
}
