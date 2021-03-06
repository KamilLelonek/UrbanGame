package com.blstream.urbangame.database.test;

import android.test.AndroidTestCase;

import com.blstream.urbangame.database.Database;
import com.blstream.urbangame.database.DatabaseInterface;
import com.blstream.urbangame.database.entity.PlayerTaskSpecific;

public class DatabaseTaskSpecificInsertTest extends AndroidTestCase {
	
	private DatabaseInterface database;
	private PlayerTaskSpecific playerTaskSpecific;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		if (database == null) {
			database = new Database(mContext);
		}
		playerTaskSpecific = new PlayerTaskSpecific("em@em.em", 1L, 10, false, false, false, "no",
			PlayerTaskSpecific.ACTIVE, null);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mContext.deleteDatabase(database.getDatabaseName());
		database = null;
	}
	
	/* ------------------------ NULL VALUES ------------------------ */
	//not allowed
	public void testNullEmail() {
		playerTaskSpecific.setPlayerEmail(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertFalse(isOK);
	}
	
	//not allowed
	public void testNullTaskID() {
		playerTaskSpecific.setTaskID(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertFalse(isOK);
	}
	
	//allowed
	public void testNullPoints() {
		playerTaskSpecific.setPoints(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertTrue(isOK);
	}
	
	//not allowed
	public void testNullIsFinished() {
		playerTaskSpecific.setIsFinishedByUser(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertFalse(isOK);
	}
	
	//allowed
	public void testNullAreChanges() {
		playerTaskSpecific.setAreChanges(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertTrue(isOK);
	}
	
	//allowed
	public void testNullWasHidden() {
		playerTaskSpecific.setWasHidden(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertTrue(isOK);
	}
	
	//allowed
	public void testNullChanges() {
		playerTaskSpecific.setChanges(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertTrue(isOK);
	}
	
	//allowed
	public void testNullStatus() {
		playerTaskSpecific.setStatus(null);
		boolean isOK = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertTrue(isOK);
	}
	
	/* ------------------------ NULL VALUES END ------------------------ */
	
	/* ------------------------ WRONG VALUES END ------------------------ */
	public void testKeyDuplication() {
		playerTaskSpecific.setPlayerEmail("em@em.em");
		playerTaskSpecific.setTaskID(2L);
		boolean ok = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertTrue(ok);
		playerTaskSpecific.setPlayerEmail("em@em.em");
		playerTaskSpecific.setTaskID(2L);
		ok = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertFalse(ok);
	}
	
	/* ------------------------ WRONG VALUES END ------------------------ */
	
	/* ------------------------ NORMAL VALUES ------------------------ */
	public void testInsertNormalValues() {
		boolean ok = database.insertPlayerTaskSpecific(playerTaskSpecific);
		assertTrue(ok);
	}
	/* ------------------------ NORMAL VALUES END ------------------------ */
}