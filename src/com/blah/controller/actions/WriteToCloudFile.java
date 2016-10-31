package com.blah.controller.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by VickyD on 10/29/16.
 */
public class WriteToCloudFile extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    final Project project = e.getProject();
    if (project == null) {
      return;
    }
    Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
    if (editor == null) {
      return;
    }
    final Document document = editor.getDocument();
    if (document == null) {
      return;
    }
    final String currentContents = document.getText();
    BufferedWriter writer = null;
    try {
      Path myCloudSettingsFilePath = Paths.get("/Users/VickyD/Desktop/mySettings2.txt");
      writer = Files.newBufferedWriter(myCloudSettingsFilePath);
      writer.write(currentContents);

    } catch (IOException e2) {
      e2.printStackTrace();
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e2) {
        e2.printStackTrace();
      }
    }
  }
}
