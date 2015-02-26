/*
 * SbApp: Open Source software for novelists and authors.
 * Original idea 2008 - 2012 Martin Mustun
 * Copyrigth (C) Favdb
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package storybook.ui.dialog.edit;

import java.awt.CardLayout;
import org.hibernate.Session;
import storybook.model.BookModel;
import storybook.model.hbn.dao.ChapterDAOImpl;
import storybook.model.hbn.entity.Chapter;
import storybook.toolkit.I18N;
import static storybook.toolkit.TextUtil.isNumber;
import storybook.toolkit.swing.htmleditor.HtmlEditor;
import storybook.ui.MainFrame;
import static storybook.ui.dialog.edit.CommonBox.loadCbParts;
import static storybook.ui.dialog.edit.CommonBox.findPart;
import static storybook.ui.dialog.edit.DlgErrorMessage.mandatoryField;
import static storybook.ui.dialog.edit.DlgErrorMessage.wrongFormat;

/**
 *
 * @author favdb
 */
public class EditChapter extends javax.swing.JPanel {

	Editor parent;
	MainFrame mainFrame;
	Chapter chapter;
	private final CardLayout cardDescription = new CardLayout(0, 0);
	private final CardLayout cardNotes = new CardLayout(0, 0);
	private final HtmlEditor description = new HtmlEditor();
	private final HtmlEditor notes = new HtmlEditor();

	/**
	 * Creates new form EditChapter
	 */
	public EditChapter() {
		initComponents();
	}

	public EditChapter(Editor m, Chapter c) {
		initComponents();
		parent = m;
		mainFrame = parent.mainFrame;
		chapter = c;
		initUI();
	}

	private void initUI() {
		paneDescription.setLayout(cardDescription);
		paneDescription.add(description, "description");
		cardDescription.show(paneDescription, "description");
		paneNotes.setLayout(cardNotes);
		paneNotes.add(notes);
		cardNotes.show(paneNotes, "notes");
		if (chapter != null) {
			txtId.setText(Long.toString(chapter.getId()));
			txNumber.setText(chapter.getChapternoStr());
			txTitle.setText(chapter.getTitle());
			description.setText(chapter.getDescription());
			notes.setText(chapter.getNotes());
		} else {
			chapter = createNewChapter();
			txtId.setText(Long.toString(chapter.getId()));
			txNumber.setText("");
			txTitle.setText("");
			description.setText("");
			notes.setText("");
		}
		loadCbParts(parent.mainFrame, cbPart, chapter);
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        paneCommon = new javax.swing.JPanel();
        lbId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbPart = new javax.swing.JLabel();
        cbPart = new javax.swing.JComboBox();
        lbNumber = new javax.swing.JLabel();
        txNumber = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        txTitle = new javax.swing.JTextField();
        paneDescription = new javax.swing.JPanel();
        paneNotes = new javax.swing.JPanel();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("storybook/msg/messages"); // NOI18N
        lbId.setText(bundle.getString("msg.common.id")); // NOI18N

        txtId.setEditable(false);

        lbPart.setText(bundle.getString("msg.common.part")); // NOI18N

        cbPart.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbNumber.setText(bundle.getString("msg.common.number")); // NOI18N

        txNumber.setText(" ");

        lbTitle.setText(bundle.getString("msg.common.title")); // NOI18N

        paneDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("msg.common.description"))); // NOI18N

        javax.swing.GroupLayout paneDescriptionLayout = new javax.swing.GroupLayout(paneDescription);
        paneDescription.setLayout(paneDescriptionLayout);
        paneDescriptionLayout.setHorizontalGroup(
            paneDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paneDescriptionLayout.setVerticalGroup(
            paneDescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout paneCommonLayout = new javax.swing.GroupLayout(paneCommon);
        paneCommon.setLayout(paneCommonLayout);
        paneCommonLayout.setHorizontalGroup(
            paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCommonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNumber)
                    .addComponent(lbTitle)
                    .addComponent(lbId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneCommonLayout.createSequentialGroup()
                        .addGroup(paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPart, 0, 323, Short.MAX_VALUE))
                    .addComponent(txTitle))
                .addGap(12, 12, 12))
            .addComponent(paneDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        paneCommonLayout.setVerticalGroup(
            paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCommonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPart)
                    .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumber)
                    .addComponent(txNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneCommonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitle)
                    .addComponent(txTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab(bundle.getString("msg.common"), paneCommon); // NOI18N

        javax.swing.GroupLayout paneNotesLayout = new javax.swing.GroupLayout(paneNotes);
        paneNotes.setLayout(paneNotesLayout);
        paneNotesLayout.setHorizontalGroup(
            paneNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );
        paneNotesLayout.setVerticalGroup(
            paneNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(bundle.getString("msg.common.notes"), paneNotes); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbPart;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbNumber;
    private javax.swing.JLabel lbPart;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel paneCommon;
    private javax.swing.JPanel paneDescription;
    private javax.swing.JPanel paneNotes;
    private javax.swing.JTextField txNumber;
    private javax.swing.JTextField txTitle;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

	public void set(Chapter c) {
		chapter = c;
		initUI();
	}

	public boolean isOK() {
		boolean rc = true;
		return (rc);
	}

	private Chapter createNewChapter() {
		BookModel model = mainFrame.getBookModel();
		Session session = model.beginTransaction();
		ChapterDAOImpl dao = new ChapterDAOImpl(session);
		Integer nextNumber = dao.getNextChapterNumber();
		model.commit();

		Chapter rchapter = new Chapter();
		rchapter.setChapterno(nextNumber);
		rchapter.setDescription("");
		rchapter.setNotes("");
		rchapter.setTitle(I18N.getMsg("msg.common.chapter") + " " + nextNumber);
		return (rchapter);
	}

	public boolean isModified() {
		if (!txNumber.getText().equals(chapter.getChapternoStr()))
			return (true);
		if (!txTitle.getText().equals(chapter.getTitle()))
			return (true);
		if (!description.getText().equals(chapter.getDescription()))
			return (true);
		if (!notes.getText().equals(chapter.getNotes()))
			return (true);
		String str = cbPart.getSelectedItem().toString();
		if (str.isEmpty() && chapter.hasPart())
			return (true);
		if (chapter.hasPart())
			if (!str.equals(chapter.getPart().getNumberName()))
				return (true);
		return (false);
	}

	public String saveData() {
		String rt = ctrlData();
		if ("".equals(rt)) {
			chapter.setChapterno(Integer.getInteger(txNumber.getText()));
			chapter.setTitle(txTitle.getText());
			chapter.setDescription(description.getText());
			chapter.setNotes(notes.getText());
			chapter.setPart(findPart(parent.mainFrame, cbPart.getSelectedItem().toString()));
		}
		return (rt);
	}

	private String ctrlData() {
		if (!isNumber(txNumber.getText()))
			return (wrongFormat(I18N.getMsg("msg.common.number")));
		if ("".equals(txTitle.getText()))
			return (mandatoryField(I18N.getMsg("msg.common.title")));
		return ("");
	}
}