package com.portal.actionfactory;

import bl.CommentManager;
import bl.NewsManager;
import bl.UserManager;
import bl.simple.SimpleCommentManager;
import bl.simple.SimpleNewsManager;
import bl.simple.SimpleUserManager;


public class BlManager {
	private static NewsManager newsManager;
	private static CommentManager commentManager;
	private static UserManager userManager;


	public static NewsManager getNewsManager() {
		return newsManager == null ? new SimpleNewsManager(): newsManager;
	}

	public static CommentManager getCommentManager() {
		return commentManager == null ? new SimpleCommentManager() : commentManager;
	}

	public static UserManager getUserManager() {
		return userManager == null ? new SimpleUserManager(): userManager;
	}
}
