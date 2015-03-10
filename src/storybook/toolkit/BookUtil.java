/*
Storybook: Open Source software for novelists and authors.
Copyright (C) 2008 - 2012 Martin Mustun

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package storybook.toolkit;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.hibernate.Session;

import storybook.SbConstants;
import storybook.SbConstants.BookKey;
import storybook.SbConstants.PreferenceKey;
import storybook.model.DbFile;
import storybook.model.BookModel;
import storybook.model.hbn.dao.InternalDAOImpl;
import storybook.model.hbn.entity.Internal;
import storybook.model.hbn.entity.Preference;
import storybook.toolkit.filefilter.H2FileFilter;
import storybook.ui.MainFrame;

/**
 * @author martin
 *
 */
public class BookUtil {

	public static boolean isUseLibreOffice(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.USE_LIBREOFFICE,
				SbConstants.DEFAULT_USE_LIBREOFFICE);
		return internal.getBooleanValue();
	}

	public static boolean isUseHtmlScenes(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.USE_HTML_SCENES,
				SbConstants.DEFAULT_USE_HTML_SCENES);
		return internal.getBooleanValue();
	}

	public static boolean isUseHtmlDescr(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.USE_HTML_DESCR, SbConstants.DEFAULT_USE_HTML_DESCR);
		return internal.getBooleanValue();
	}
	
	public static boolean isExportBookHtmlMulti(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.HTML_BOOK_MULTI, false);
		return internal.getBooleanValue();
	}

	public static boolean isExportChapterNumbers(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EXPORT_CHAPTER_NUMBERS,
				SbConstants.DEFAULT_EXPORT_CHAPTER_NUMBERS);
		return internal.getBooleanValue();
	}

	public static boolean isExportRomanNumerals(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EXPORT_ROMAN_NUMERALS,
				SbConstants.DEFAULT_EXPORT_ROMAN_NUMERALS);
		return internal.getBooleanValue();
	}

	public static boolean isExportChapterTitles(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EXPORT_CHAPTER_TITLES,
				SbConstants.DEFAULT_EXPORT_CHAPTER_TITLES);
		return internal.getBooleanValue();
	}

	public static boolean isExportChapterDatesLocations(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EXPORT_CHAPTER_DATES_LOCATIONS,
				SbConstants.DEFAULT_EXPORT_CHAPTER_DATES_LOCATIONS);
		return internal.getBooleanValue();
	}

	public static boolean isExportSceneTitle(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EXPORT_SCENE_TITLES,
				SbConstants.DEFAULT_EXPORT_SCENE_TITLES);
		return internal.getBooleanValue();
	}

	public static boolean isExportSceneSeparator(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EXPORT_SCENE_SEPARATOR,
				SbConstants.DEFAULT_EXPORT_SCENE_SEPARATOR);
		return internal.getBooleanValue();
	}

	public static boolean isExportPartTitles(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EXPORT_PART_TITLES,
				SbConstants.DEFAULT_EXPORT_PART_TITLES);
		return internal.getBooleanValue();
	}

	public static boolean isEditorFullToolbar(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EDITOR_FULL_TOOLBAR,
				SbConstants.DEFAULT_EDITOR_FULL_TOOLBAR);
		return internal.getBooleanValue();
	}

	public static boolean isEditorModless(MainFrame mainFrame) {
		Internal internal = get(mainFrame,
				BookKey.EDITOR_MODLESS,
				SbConstants.DEFAULT_EDITOR_MODLESS);
		return internal.getBooleanValue();
	}

	public static void store(MainFrame mainFrame, BookKey key, Object val) {
		store(mainFrame,key.toString(),val);
	}

	public static void store(MainFrame mainFrame, String strKey, Object val) {
		BookModel model = mainFrame.getBookModel();
		Session session = model.beginTransaction();
		InternalDAOImpl dao = new InternalDAOImpl(session);
		dao.saveOrUpdate(strKey, val);
		model.commit();
	}

	public static Internal get(MainFrame mainFrame, BookKey key, Object val) {
		return(get(mainFrame,key.toString(),val));
	}

	public static Internal get(MainFrame mainFrame, String strKey, Object val) {
		BookModel model = mainFrame.getBookModel();
		Session session = model.beginTransaction();
		InternalDAOImpl dao = new InternalDAOImpl(session);
		Internal internal = dao.findByKey(strKey);
		if (internal == null) {
			internal = new Internal(strKey, val);
			session.save(internal);
		}
		model.commit();
		return internal;
	}

	public static DbFile openDocumentDialog() {
		final JFileChooser fc = new JFileChooser();
		Preference pref = PrefUtil.get(PreferenceKey.LAST_OPEN_DIR, getHomeDir());
		fc.setCurrentDirectory(new File(pref.getStringValue()));
		H2FileFilter filter = new H2FileFilter();
		fc.addChoosableFileFilter(filter);
		fc.setFileFilter(filter);
		int ret = fc.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (!file.exists()) {
				JOptionPane.showMessageDialog(null,
						I18N.getMsg("msg.dlg.project.not.exits.text", file),
						I18N.getMsg("msg.dlg.project.not.exits.title"),
						JOptionPane.ERROR_MESSAGE);
				return null;
			}
			DbFile dbFile = new DbFile(file);
			return dbFile;
		}
		return null;
	}
	
	// JFileChooser for import Files
	public static File getImportCharsFileDialog(){
		final JFileChooser fc = new JFileChooser();
		Preference pref = PrefUtil.get(PreferenceKey.LAST_OPEN_DIR, getHomeDir());
		fc.setCurrentDirectory(new File(pref.getStringValue()));
		fc.setDialogTitle(I18N.getMsg("msg.file.import.characters"));
        fc.setApproveButtonText(I18N.getMsg("msg.common.import"));
		
		int ret = fc.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (!file.exists()) {
				JOptionPane.showMessageDialog(null,
						I18N.getMsg("msg.dlg.project.not.exits.text", file),
						I18N.getMsg("msg.dlg.project.not.exits.title"),
						JOptionPane.ERROR_MESSAGE);
				return null;
			}
			return file;
		}
		
		return null;
	}

	public static String getHomeDir() {
		return System.getProperty("user.home");
	}

}
