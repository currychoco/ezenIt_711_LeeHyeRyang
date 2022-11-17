package com.board.board.controller;

import com.board.board.controller.action.Action;
import com.board.board.controller.action.BoardDeleteAction;
import com.board.board.controller.action.BoardListAction;
import com.board.board.controller.action.BoardReplyAction;
import com.board.board.controller.action.BoardReplyFormAction;
import com.board.board.controller.action.BoardUpdateAction;
import com.board.board.controller.action.BoardUpdateFormAction;
import com.board.board.controller.action.BoardViewAction;
import com.board.board.controller.action.BoardWriteAction;
import com.board.board.controller.action.BoardWriteFormAction;
import com.board.board.controller.action.CommentWriteAction;
import com.board.board.controller.action.LikeUpdateAction;

public class ActionFactory {
	
	public static String savePath = "C:\\Users\\currychoco\\git\\ezenIT\\board-server-weather\\WebContent\\upload";

	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
		super();
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory:" + command);

		if (command.equals("board_list")) {
			action = new BoardListAction();
		} else if (command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		} else if (command.equals("board_write")) {
			action = new BoardWriteAction();
		} else if (command.equals("board_view")) {
			action = new BoardViewAction();
		} else if (command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		} else if (command.equals("board_update")) {
			action = new BoardUpdateAction();
		} else if (command.equals("board_delete")) {
			action = new BoardDeleteAction();
		} else if (command.equals("board_reply_form")) {
			action = new BoardReplyFormAction();
		} else if (command.equals("board_reply")) {
			action = new BoardReplyAction();
		} else if (command.equals("board_comment")) {
			action = new CommentWriteAction();
		} else if (command.equals("like_it")) {
			action = new LikeUpdateAction();
		} 
		return action;
	}

}
