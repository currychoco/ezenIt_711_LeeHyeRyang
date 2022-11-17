package com.board.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.board.controller.ActionFactory;
import com.board.common.dao.BoardDAO;
import com.board.common.dto.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardVO bVo = new BoardVO();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String savePath = ActionFactory.savePath;
		// 업로드 파일 크기 5mb 제한
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String enType = "UTF-8";

		MultipartRequest multi = new MultipartRequest(request, savePath, uploadFileSizeLimit, enType, new DefaultFileRenamePolicy());
		System.out.println("멀티 네임" + multi.getParameter("name"));
		// System.out.println("리퀘스트 네임"+request.getParameter("name"));

		bVo.setName(multi.getParameter("name"));
		bVo.setEmail(multi.getParameter("email"));
		bVo.setTitle(multi.getParameter("title"));
		bVo.setContent(multi.getParameter("content"));
		bVo.setPictureUrl(multi.getFilesystemName("pictureUrl"));

		System.out.println(bVo.toString());

		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertBoard(bVo);

		new BoardListAction().execute(request, response);

	}
}
