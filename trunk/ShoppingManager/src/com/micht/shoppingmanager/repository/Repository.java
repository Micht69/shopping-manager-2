/**
 * 
 */
package com.micht.shoppingmanager.repository;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Micht
 *
 */
public abstract class Repository<T> {
	protected SQLiteDatabase myDb;
	protected SQLiteOpenHelper sqLiteOpenHelper;

	public Repository() {
	}

	public void Open() {
		myDb = sqLiteOpenHelper.getWritableDatabase();
	}

	public void Close() {
		myDb.close();
	}


	public abstract List<T> getAll();
	public abstract T getById(int id);

	public abstract void save(T entite);
	public abstract void update(T entite);
	public abstract void delete(int id);

	protected abstract List<T> convertCursorToListObject(Cursor c);
	protected abstract T convertCursorToObject(Cursor c);
	protected abstract T convertCursorToOneObject(Cursor c);
}
