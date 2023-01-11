/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author naser
 */
// importing packages
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class StudentQuiz extends javax.swing.JFrame {

    public String questionId = "1";
    public String answer;
    public int min = 0;
    public int sec = 0;
    public int marks = 0;
    public String id;

    /**
     * Creates new form StudentQuiz
     */
    //here  is the answer check method
    public void answerCheck() {

        String studentAnswer = "";

        if (Option1.isSelected()) {
            studentAnswer = Option1.getText();
        } else if (Option2.isSelected()) {
            studentAnswer = Option2.getText();
        } else if (Option3.isSelected()) {
            studentAnswer = Option3.getText();
        } else {
            studentAnswer = Option4.getText();
        }

        //check code
        if (studentAnswer.equals(answer)) {
            marks += 1;
            String marks1 = String.valueOf(marks);
            CorrectAnswersLabel.setText(marks1);
        }

        //to change the question number
        int questionsId1 = Integer.parseInt(questionId);
        questionsId1 = questionsId1 +1;
        questionId = String.valueOf(questionsId1);

        // to clear the radios btns
        Option1.setSelected(false);
        Option2.setSelected(false);
        Option3.setSelected(false);
        Option4.setSelected(false);

        //to hide the nextt btn in last question
        if (questionId.equals("10")) {
            Nextbtn.setVisible(false);
        }

    }

    //questtion method in order touse the method in the next btn to show the correct answers
    public void question() {

        try {
            //open db connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_systemdb", "root", "Password1!");
            // my db name is quiz_systemdb as written above

            Statement stm = con.createStatement(); //it will ccreate a statement object for sending sql statemnet to the satabase
            //convert stdID from string to integer before sent it to db

            //start of displaying questions
            ResultSet result1 = stm.executeQuery("select * from exam where exam_id='" + questionId + "'");
            while (result1.next()) {
                QuestionNumberLabel.setText(result1.getString(1));
                QuestionLabel.setText(result1.getString(2));
                Option1.setText(result1.getString(3));
                Option2.setText(result1.getString(4));
                Option3.setText(result1.getString(5));
                Option4.setText(result1.getString(6));
                answer = result1.getString(7);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error ocure!! while trying to send data to database");
        }
    }

    //the submit method
    public void submit() {
        String id = StudentIDLabel.getText();
        answerCheck();

        try {
            //open db connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_systemdb", "root", "Password1!");
            // my db name is quiz_systemdb as written above

            Statement stm = con.createStatement(); //it will ccreate a statement object for sending sql statemnet to the satabase
            //convert stdID from string to integer before sent it to db

            stm.executeUpdate("update student set grade='" + marks + "'whwere student_id='" + id + "'");
            String marks1 = String.valueOf(marks);
            dispose();
            new StdResult(marks1).setVisible(true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error ocure!! while trying to send data to database in submit section");
        }
    }

    public StudentQuiz() {
        initComponents();
    }
  
    Timer time;

    public StudentQuiz(String id, String name) {
        initComponents();
        StudentIDLabel.setText(id);
        NameLebal.setText(name);
        try {
            //open db connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_systemdb", "root", "Password1!");
            // my db name is quiz_systemdb as written above

            Statement stm = con.createStatement(); //it will ccreate a statement object for sending sql statemnet to the satabase
            //convert stdID from string to integer before sent it to db
            
            
            
            //the following cod is for displaying id and name of the student
            ResultSet result = stm.executeQuery("select * from student where student_id='" + id + "'");
            while (result.next()) {
                NameLebal.setText(result.getString(2)); //we set number 2 due to thi number is the number of name in db
            }

            //start of displaying questions
            ResultSet result1 = stm.executeQuery("select * from exam where exam_id='" + questionId + "'");
            while (result1.next()) {
                QuestionNumberLabel.setText(result1.getString(1));
                QuestionLabel.setText(result1.getString(2));
                Option1.setText(result1.getString(3));
                Option2.setText(result1.getString(4));
                Option3.setText(result1.getString(5));
                Option4.setText(result1.getString(6));
                answer = result.getString(7);
                CourseIdmLabel.setText(result1.getString(8));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error ocure!! while trying to send data to database");
        }

        //timer code 
        setLocationRelativeTo(this);
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecondLabel.setText(String.valueOf(sec));
                MinutesLabel.setText(String.valueOf(min));

                if (sec == 60) {
                    sec = 0;
                    min++;
                    if (min == 10) {
                        time.stop();
                        answerCheck();  //htis is to check the correct answer
                        submit(); //this is to submit my answer        
                    }
                }
                sec++;
            }

        });
        time.start(); // the order of starting the time
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ExitPlace = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        MinutesLabel = new javax.swing.JLabel();
        CorrectAnswersLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        StudentIDLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        QuestionNumberLabel = new javax.swing.JLabel();
        SecondLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        NameLebal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CourseIdmLabel = new javax.swing.JLabel();
        QuestionLabel = new javax.swing.JLabel();
        Option1 = new javax.swing.JCheckBox();
        Option2 = new javax.swing.JCheckBox();
        Option3 = new javax.swing.JCheckBox();
        Option4 = new javax.swing.JCheckBox();
        Nextbtn = new javax.swing.JButton();
        Submitbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(220, 120));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(170, 170, 170));
        jPanel1.setPreferredSize(new java.awt.Dimension(1144, 644));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 35, 63));
        jPanel2.setPreferredSize(new java.awt.Dimension(35, 35));

        ExitPlace.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        ExitPlace.setForeground(new java.awt.Color(255, 255, 255));
        ExitPlace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitPlace.setText("x");
        ExitPlace.setPreferredSize(new java.awt.Dimension(35, 35));
        ExitPlace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitPlaceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ExitPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ExitPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel2.setText("Exam Time:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel3.setText("10 min");

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel4.setText("Correct Answer:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel5.setText("Time Taken:");

        MinutesLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        MinutesLabel.setForeground(new java.awt.Color(255, 0, 0));
        MinutesLabel.setText("00 ");

        CorrectAnswersLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        CorrectAnswersLabel.setText("00");

        jLabel9.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel9.setText("Total of Question:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel10.setText("10");

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student ID:");
        jLabel1.setToolTipText("");

        StudentIDLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        StudentIDLabel.setText("20212022010");

        jLabel11.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel11.setText("Question Number:");

        QuestionNumberLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        QuestionNumberLabel.setText("00");

        SecondLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        SecondLabel.setForeground(new java.awt.Color(255, 0, 0));
        SecondLabel.setText("00");

        jLabel6.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText(":");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(StudentIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CorrectAnswersLabel)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(QuestionNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(MinutesLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SecondLabel)
                                        .addGap(18, 18, 18)))))
                        .addGap(0, 80, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MinutesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SecondLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuestionNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CorrectAnswersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 640));

        jPanel4.setBackground(new java.awt.Color(230, 230, 230));

        jLabel12.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel12.setText("Student Name:");

        NameLebal.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        NameLebal.setText("Nasser Hussein");

        jLabel13.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel13.setText("Course ID:");

        CourseIdmLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        CourseIdmLabel.setText("00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(NameLebal, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CourseIdmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameLebal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CourseIdmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 860, 120));

        QuestionLabel.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        QuestionLabel.setText("Question?");
        jPanel1.add(QuestionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 630, 50));

        Option1.setText("jCheckBox1");
        Option1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option1ActionPerformed(evt);
            }
        });
        jPanel1.add(Option1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 330, 40));

        Option2.setText("jCheckBox2");
        Option2.setPreferredSize(new java.awt.Dimension(87, 30));
        Option2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option2ActionPerformed(evt);
            }
        });
        jPanel1.add(Option2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 330, 40));

        Option3.setText("jCheckBox3");
        Option3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option3ActionPerformed(evt);
            }
        });
        jPanel1.add(Option3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 330, 40));

        Option4.setText("jCheckBox4");
        Option4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option4ActionPerformed(evt);
            }
        });
        jPanel1.add(Option4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 330, 40));

        Nextbtn.setBackground(new java.awt.Color(153, 35, 63));
        Nextbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        Nextbtn.setForeground(new java.awt.Color(255, 255, 255));
        Nextbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgPackage/arrow-next-2825.png"))); // NOI18N
        Nextbtn.setText("Next");
        Nextbtn.setBorder(null);
        Nextbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextbtnActionPerformed(evt);
            }
        });
        jPanel1.add(Nextbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 560, 120, 40));

        Submitbtn.setBackground(new java.awt.Color(153, 35, 63));
        Submitbtn.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        Submitbtn.setForeground(new java.awt.Color(255, 255, 255));
        Submitbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgPackage/save-file-5339 (1).png"))); // NOI18N
        Submitbtn.setText("Submit");
        Submitbtn.setBorder(null);
        Submitbtn.setIconTextGap(15);
        Submitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitbtnActionPerformed(evt);
            }
        });
        jPanel1.add(Submitbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 560, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitPlaceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitPlaceMouseClicked
        // exit code 
        dispose();
    }//GEN-LAST:event_ExitPlaceMouseClicked

    private void SubmitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitbtnActionPerformed
        // submit btn code 
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to submit your answers?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0)
        {
        answerCheck();
        submit();
        }
    }//GEN-LAST:event_SubmitbtnActionPerformed

    private void Option1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option1ActionPerformed
        // Code to set just one option
        if (Option1.isSelected()) {
            Option2.setSelected(false);
            Option3.setSelected(false);
            Option4.setSelected(false);
        }

    }//GEN-LAST:event_Option1ActionPerformed

    private void Option2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option2ActionPerformed
        // Code to set just one option
        if (Option2.isSelected()) {
            Option1.setSelected(false);
            Option3.setSelected(false);
            Option4.setSelected(false);
        }
    }//GEN-LAST:event_Option2ActionPerformed

    private void Option3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option3ActionPerformed
        // Code to set just one option
        if (Option3.isSelected()) {
            Option1.setSelected(false);
            Option2.setSelected(false);
            Option4.setSelected(false);
        }
    }//GEN-LAST:event_Option3ActionPerformed

    private void Option4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option4ActionPerformed
        // Code to set just one option
        if (Option4.isSelected()) {
            Option1.setSelected(false);
            Option2.setSelected(false);
            Option3.setSelected(false);
        }
    }//GEN-LAST:event_Option4ActionPerformed

    private void NextbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextbtnActionPerformed
        //Next btn code
        answerCheck();
        question();
        
    }//GEN-LAST:event_NextbtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentQuiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CorrectAnswersLabel;
    private javax.swing.JLabel CourseIdmLabel;
    private javax.swing.JLabel ExitPlace;
    private javax.swing.JLabel MinutesLabel;
    private javax.swing.JLabel NameLebal;
    private javax.swing.JButton Nextbtn;
    private javax.swing.JCheckBox Option1;
    private javax.swing.JCheckBox Option2;
    private javax.swing.JCheckBox Option3;
    private javax.swing.JCheckBox Option4;
    private javax.swing.JLabel QuestionLabel;
    private javax.swing.JLabel QuestionNumberLabel;
    private javax.swing.JLabel SecondLabel;
    private javax.swing.JLabel StudentIDLabel;
    private javax.swing.JButton Submitbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
