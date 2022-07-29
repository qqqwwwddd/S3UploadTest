package com.example.aws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

	@GetMapping("")
	public String showHomePage() {
		return "upload";
	}

	@PostMapping("/upload")
	public String uploadFile(String description, @RequestParam("file") MultipartFile multipart, Model model) {
		String fileName = multipart.getOriginalFilename();

		System.out.println("Description: " + description);
		System.out.println("filename: " + fileName);

		String message = "";

		// file to Amazon S3
		try {
			S3Util.uploadFile(fileName, multipart.getInputStream());
			message = "파일이 성공적으로 업로드 되었습니다!";
		} catch (Exception e) {
			message = "파일 업로드 실패!: " + e.getMessage();
		}
		model.addAttribute("message", message);
		return "message";
	}
}
