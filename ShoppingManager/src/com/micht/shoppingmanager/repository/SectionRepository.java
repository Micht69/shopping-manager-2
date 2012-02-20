/**
 * 
 */
package com.micht.shoppingmanager.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.micht.shoppingmanager.bean.SectionBean;
import com.micht.shoppingmanager.helper.SectionOpenHelper;

/**
 * @author Micht
 * 
 */
public class SectionRepository extends Repository<SectionBean> {

	public SectionRepository(Context context) {
		sqLiteOpenHelper = new SectionOpenHelper(context, null);
	}

	/**
	 * Get list of all sections
	 */
	@Override
	public List<SectionBean> getAll() {
		// Récupération de la liste des courses
		Cursor cursor = myDb
		.query(SectionOpenHelper.SECTION_TABLE_NAME,
				SectionOpenHelper.getAllColumns(), null, null, null,
				null, null);

		return convertCursorToListObject(cursor);
	}

	/**
	 * Get one section
	 */
	@Override
	public SectionBean getById(int id) {
		Cursor cursor = myDb.query(SectionOpenHelper.SECTION_TABLE_NAME,
				SectionOpenHelper.getAllColumns(), SectionOpenHelper.COLUMN_ID
				+ "=?", new String[] { String.valueOf(id) }, null,
				null, null);

		return convertCursorToObject(cursor);
	}

	/**
	 * Save a new Section to DB
	 */
	@Override
	public void save(SectionBean entite) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(SectionOpenHelper.COLUMN_NAME, entite.name);
		contentValues.put(SectionOpenHelper.COLUMN_POSITION, entite.position);

		myDb.insert(SectionOpenHelper.SECTION_TABLE_NAME, null, contentValues);
	}

	/**
	 * Update a Section
	 */
	@Override
	public void update(SectionBean entite) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(SectionOpenHelper.COLUMN_NAME, entite.name);
		contentValues.put(SectionOpenHelper.COLUMN_POSITION, entite.position);

		myDb.update(SectionOpenHelper.SECTION_TABLE_NAME, contentValues,
				SectionOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(entite.id) });
	}

	/**
	 * Delete a Section
	 */
	@Override
	public void delete(int id) {
		myDb.delete(SectionOpenHelper.SECTION_TABLE_NAME,
				SectionOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	@Override
	protected List<SectionBean> convertCursorToListObject(Cursor c) {
		List<SectionBean> liste = new ArrayList<SectionBean>();

		// Si la liste est vide
		if (c.getCount() == 0) {
			return liste;
		}

		// position sur le premeir item
		c.moveToFirst();

		// Pour chaque item
		do {

			SectionBean course = convertCursorToObject(c);

			liste.add(course);
		} while (c.moveToNext());

		// Fermeture du curseur
		c.close();

		return liste;
	}

	@Override
	protected SectionBean convertCursorToObject(Cursor c) {

		SectionBean course = new SectionBean(
				c.getString(SectionOpenHelper.NUM_COLUMN_NAME),
				c.getInt(SectionOpenHelper.NUM_COLUMN_POSITION));
		course.id = c.getInt(SectionOpenHelper.NUM_COLUMN_ID);

		return course;
	}

	@Override
	protected SectionBean convertCursorToOneObject(Cursor c) {
		c.moveToFirst();

		SectionBean course = convertCursorToObject(c);

		c.close();
		return course;
	}

}
