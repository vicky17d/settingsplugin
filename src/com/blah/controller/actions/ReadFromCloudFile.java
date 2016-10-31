package com.blah.controller.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by VickyD on 10/29/16.
 */
public class ReadFromCloudFile extends AnAction {
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
    VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(document);
    if (virtualFile == null) {
      return;
    }
    final String contents;
//    Path myCloudSettingsFilePath = Paths.get("/Users/VickyD/Desktop/mySettings2.txt");

    try {
      BufferedReader br = new BufferedReader(new FileReader("/Users/VickyD/Desktop/mySettings2.txt"));
      String currentLine;
      StringBuilder stringBuilder = new StringBuilder();
      while ((currentLine = br.readLine()) != null) {
        stringBuilder.append(currentLine);
        stringBuilder.append("\n");
      }
      contents = stringBuilder.toString();
    } catch (IOException e1) {
      return;
    }
    final Runnable readRunner = new Runnable() {
      @Override
      public void run() {
//        document.setText(contents);
        Path myCloudSettingsFilePath = Paths.get("/Users/VickyD/Desktop/mySettings1.txt");
        BufferedWriter writer = null;
        try {
          writer = Files.newBufferedWriter(myCloudSettingsFilePath);
          writer.write(contents);
        } catch (IOException e1) {
          e1.printStackTrace();
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
    };
    ApplicationManager.getApplication().invokeLater(new Runnable() {
      @Override
      public void run() {
        CommandProcessor.getInstance().executeCommand(project, new Runnable() {
          @Override
          public void run() {
            ApplicationManager.getApplication().runWriteAction(readRunner);
          }
        }, "DiskRead", null);
      }
    });
  }
}
