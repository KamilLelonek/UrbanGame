package com.blstream.urbangame.session;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.blstream.urbangame.GamesListActivity;
import com.blstream.urbangame.database.Database;
import com.blstream.urbangame.database.DatabaseInterface;
import com.blstream.urbangame.database.entity.Player;
import com.blstream.urbangame.web.WebHighLevel;
import com.blstream.urbangame.web.WebHighLevelInterface;
import com.blstream.urbangame.web.server.ServerResponseHandler;
import com.blstream.urbangame.web.server.WebServerNotificationListener;

//formatter:off
/**
 * This class manages user session.
 * 
 * It provides the following functionalities: 
 * 		- adding users to DB
 * 
 * Due to this class we can easily connect with server to check and compare
 * local and online data.
 */
//formatter:on
public abstract class SessionManager {
	public static final String TAG = "LoginRegisterAction";
	
	protected DatabaseInterface database;
	protected Context context;
	protected ServerResponseHandler handler;
	protected WebHighLevelInterface web;
	
	public SessionManager(Context context) {
		this(context, null);
	}
	
	protected SessionManager(Context context, WebServerNotificationListener listener) {
		this.context = context;
		this.database = new Database(context);
		this.handler = new ServerResponseHandler(listener);
		this.web = new WebHighLevel(handler, context);
	}
	
	public boolean doesPlayerExist(String email) {
		return getPlayerFromDB(email) != null;
	}
	
	protected Player getPlayerFromDB(String email) {
		return database.getPlayer(email);
	}
	
	protected void startMainActivity() {
		Intent intent = new Intent(context, GamesListActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
	protected void addUserToDB(String email, String password, String displayName, Drawable avatar) {
		Player player = new Player(email, password, displayName, avatar);
		database.insertUser(player);
	}
	
	protected void addUserToDB(Player player) {
		database.insertUser(player);
	}
	
	protected void updatePlayerInDB(Player player) {
		database.updatePlayer(player);
	}
}