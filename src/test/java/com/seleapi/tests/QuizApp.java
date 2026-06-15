package com.seleapi.tests;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Question {
    String type;
    String text;
    String[] options;
    String answer = "";
    boolean skipped = false;
    int timeLimit;
    boolean answered = false;

    Question(String type, String text, int timeLimit) {
        this.type = type;
        this.text = text;
        this.timeLimit = timeLimit;
    }

    Question(String type, String text, String[] options, int timeLimit) {
        this.type = type;
        this.text = text;
        this.options = options;
        this.timeLimit = timeLimit;
    }
}

public class QuizApp {

    JFrame frame;

    JTextArea questionArea;
    JTextArea answerArea;
    JTextField answerField;

    JLabel timerLabel;

    JButton nextButton, skipButton, uploadButton, exportButton;

    JPanel mainPanel;

    javax.swing.Timer timer;
    int timeRemaining;

    List<Question> questions = new ArrayList<>();
    int currentIndex = 0;

    ButtonGroup mcqGroup;
    JRadioButton[] mcqOptions;

    public QuizApp() {

        frame = new JFrame("Interview Practice App");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        timerLabel = new JLabel("Upload questions to start", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(timerLabel, BorderLayout.NORTH);

        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        uploadButton = new JButton("Upload Questions");
        nextButton = new JButton("Next");
        skipButton = new JButton("Skip");
        exportButton = new JButton("Export Results");

        bottom.add(uploadButton);
        bottom.add(skipButton);
        bottom.add(nextButton);
        bottom.add(exportButton);

        frame.add(bottom, BorderLayout.SOUTH);

        uploadButton.addActionListener(e -> loadFile());
        nextButton.addActionListener(e -> moveNext());
        skipButton.addActionListener(e -> skip());
        exportButton.addActionListener(e -> exportResults());

        frame.setVisible(true);
    }

    // ---------------- FILE UPLOAD ----------------
    private void loadFile() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(frame);

        if (res == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            parseFile(file);

            if (!questions.isEmpty()) {
                currentIndex = 0;
                showQuestion();
            }
        }
    }

    // ---------------- FILE PARSE ----------------
    private void parseFile(File file) {

        questions.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim();
                if (line.isEmpty()) continue;

                String type = detectType(line);
                int time = getTime(type);

                if (type.equals("MCQ")) {

                    String[] parts = line.split("\\|");
                    if (parts.length < 2) continue;

                    String q = parts[0];
                    String[] options = Arrays.copyOfRange(parts, 1, parts.length);

                    questions.add(new Question(type, q, options, time));

                } else {
                    questions.add(new Question(type, line, time));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- TYPE DETECTION ----------------
    private String detectType(String q) {

        q = q.toLowerCase();

        if (q.contains("|")) return "MCQ";
        if (q.contains("write") || q.contains("program") || q.contains("code"))
            return "PROGRAM";
        if (q.contains("correct") || q.contains("typo"))
            return "TYPO";

        return "TYPO";
    }

    // ---------------- TIMER RULES ----------------
    private int getTime(String type) {

        switch (type) {
            case "PROGRAM": return 300;
            case "MCQ": return 45;
            default: return 120;
        }
    }

    // ---------------- SHOW QUESTION ----------------
    private void showQuestion() {

        if (currentIndex >= questions.size()) {
            showResult();
            return;
        }

        mainPanel.removeAll();

        Question q = questions.get(currentIndex);

        timeRemaining = q.timeLimit;
        startTimer();

        timerLabel.setText("Type: " + q.type);

        JPanel content = new JPanel(new BorderLayout());

        questionArea = new JTextArea(q.text);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);

        content.add(new JScrollPane(questionArea), BorderLayout.NORTH);

        if (q.type.equals("PROGRAM")) {

            answerArea = new JTextArea(q.answer);
            content.add(new JScrollPane(answerArea), BorderLayout.CENTER);

        } else if (q.type.equals("TYPO")) {

            answerField = new JTextField(q.answer);
            content.add(answerField, BorderLayout.CENTER);

        } else if (q.type.equals("MCQ")) {

            JPanel mcqPanel = new JPanel(new GridLayout(q.options.length, 1));

            mcqGroup = new ButtonGroup();
            mcqOptions = new JRadioButton[q.options.length];

            for (int i = 0; i < q.options.length; i++) {

                mcqOptions[i] = new JRadioButton(q.options[i]);

                if (q.options[i].equals(q.answer)) {
                    mcqOptions[i].setSelected(true);
                }

                mcqGroup.add(mcqOptions[i]);
                mcqPanel.add(mcqOptions[i]);
            }

            content.add(mcqPanel, BorderLayout.CENTER);
        }

        mainPanel.add(content);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // ---------------- TIMER ----------------
    private void startTimer() {

        if (timer != null) timer.stop();

        timer = new javax.swing.Timer(1000, e -> {

            int m = timeRemaining / 60;
            int s = timeRemaining % 60;

            timerLabel.setText(String.format("Time Left: %02d:%02d", m, s));

            timeRemaining--;

            if (timeRemaining < 0) {
                moveNext();
            }
        });

        timer.start();
    }

    // ---------------- SAVE ANSWER ----------------
    private void saveAnswer() {

        if (currentIndex >= questions.size()) return;

        Question q = questions.get(currentIndex);

        if (q.type.equals("PROGRAM")) {

            if (answerArea != null)
                q.answer = answerArea.getText();

        } else if (q.type.equals("TYPO")) {

            if (answerField != null)
                q.answer = answerField.getText();

        } else if (q.type.equals("MCQ")) {

            if (mcqOptions != null) {
                for (JRadioButton rb : mcqOptions) {
                    if (rb.isSelected()) {
                        q.answer = rb.getText();
                    }
                }
            }
        }

        q.answered = !q.answer.isEmpty();
    }

    // ---------------- NAVIGATION ----------------
    private void moveNext() {
        saveAnswer();
        currentIndex++;
        showQuestion();
    }

    private void skip() {
        if (currentIndex < questions.size()) {
            questions.get(currentIndex).skipped = true;
        }
        moveNext();
    }

    // ---------------- RESULT ----------------
    private void showResult() {

        if (timer != null) timer.stop();

        mainPanel.removeAll();

        JTextArea result = new JTextArea();

        int answered = 0;

        for (Question q : questions) {
            if (q.answered) answered++;
        }

        result.setText(
                "INTERVIEW COMPLETED\n\n" +
                "Total Questions: " + questions.size() + "\n" +
                "Answered: " + answered + "\n" +
                "Skipped: " + (questions.size() - answered)
        );

        mainPanel.add(result);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // ---------------- EXPORT RESULTS ----------------
    private void exportResults() {

        try (PrintWriter pw = new PrintWriter(new File("QuizResults.txt"))) {

            for (int i = 0; i < questions.size(); i++) {

                Question q = questions.get(i);

                pw.println("Q" + (i + 1) + ": " + q.text);
                pw.println("Type: " + q.type);
                pw.println("Answer: " + q.answer);
                pw.println("Skipped: " + q.skipped);
                pw.println("-----------------------------------");
            }

            JOptionPane.showMessageDialog(frame, "Results exported!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp());
    }
}