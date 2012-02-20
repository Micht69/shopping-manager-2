/**
 * 
 */
package com.micht.shoppingmanager.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Micht
 * 
 */
public class SectionOpenHelper extends SQLiteOpenHelper {
	// Version de la base de données
	private static final int DATABASE_VERSION = 1;

	// Nom de la base
	private static final String SHOPPING_BASE_NAME = "shopping.db";

	// Nom de la table
	public static final String SECTION_TABLE_NAME = "Sections";

	// Description des colonnes
	public static final String COLUMN_ID = "ID";
	public static final int NUM_COLUMN_ID = 0;
	public static final String COLUMN_NAME = "NAME";
	public static final int NUM_COLUMN_NAME = 1;
	public static final String COLUMN_POSITION = "POSITION";
	public static final int NUM_COLUMN_POSITION = 2;

	// Requête SQL pour la création da la base
	private static final String REQUETE_CREATION_BDD = "CREATE TABLE "
		+ SECTION_TABLE_NAME + " ("
		+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ COLUMN_NAME + " TEXT NOT NULL, "
		+ COLUMN_POSITION + " INTEGER NOT NULL);";

	public SectionOpenHelper(Context context, CursorFactory factory) {
		super(context, SHOPPING_BASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > DATABASE_VERSION) {
			db.execSQL("DROP TABLE " + SECTION_TABLE_NAME + ";");
			onCreate(db);
		}
	}

	public static String[] getAllColumns() {
		return new String[] { SectionOpenHelper.COLUMN_ID,
				SectionOpenHelper.COLUMN_NAME,
				SectionOpenHelper.COLUMN_POSITION};
	}

}
